package com.ssafy.youandi.service.Impl;


import com.ssafy.youandi.config.jwt.JwtTokenProvider;
import com.ssafy.youandi.dto.kakao.AccessToken;
import com.ssafy.youandi.dto.kakao.ProfileDto;
import com.ssafy.youandi.dto.request.*;
import com.ssafy.youandi.dto.response.*;
import com.ssafy.youandi.entity.Role;
import com.ssafy.youandi.entity.redis.RedisKey;
import com.ssafy.youandi.entity.user.User;
import com.ssafy.youandi.exception.InvalidRefreshTokenException;
import com.ssafy.youandi.exception.UserNotFoundException;
import com.ssafy.youandi.repository.UserRepository;
import com.ssafy.youandi.service.ProviderService;
import com.ssafy.youandi.service.RedisService;
import com.ssafy.youandi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.naming.CommunicationException;
import java.util.Optional;

import static com.ssafy.youandi.entity.redis.RedisKey.REGISTER;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisService redisService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProviderService providerService;


    // 이메일 증복 확인 - requestDto 수정
    public String checkEmail(UserInfoRequestDto requestDto) throws Exception{
        if(userRepository.findByEmail(requestDto.getEmail()).isPresent()){
            log.info("이미 존재하는 이메일입니다.");
            return "이미 존재하는 이메일입니다.";
        }
        return null;
    }
    // 닉네임 증복 확인
    public String checkNickName(UserInfoRequestDto requestDto) throws Exception{
        if(userRepository.findByNickname(requestDto.getNickname()).isPresent()){
            log.info("이미 존재하는 닉네임입니다.");
            return "이미 존재하는 닉네임입니다.";
        }
        return null;
    }


    // 회원가입 -> 닉네임, 이메일 중복 메소드 합쳐서 최적화하기
    @Transactional
    @Override
    public JoinResponseDto join(UserInfoRequestDto userInfoRequestDto) throws Exception {
        if(userRepository.findByEmail(userInfoRequestDto.getEmail()).isPresent()){
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        if(userRepository.findByNickname(userInfoRequestDto.getNickname()).isPresent()){
            throw new Exception("이미 존재하는 닉네임입니다.");
        }
        if(!userInfoRequestDto.getPassword().equals(userInfoRequestDto.getCheckedpassword())){
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }


        User user = User.builder()
                .email(userInfoRequestDto.getEmail())
                .password(passwordEncoder.encode(userInfoRequestDto.getPassword()))
                .nickname(userInfoRequestDto.getNickname())
                .provider(null)
                .role(Role.ROLE_USER).
                build();

        userRepository.save(user);
        return JoinResponseDto.builder()
                .email(user.getEmail())
                .build();
    }

    // 로컬 로그인 구현
    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto) throws Exception {
        User user = userRepository
                .findByEmail(loginRequestDto.getEmail()).orElseThrow(UserNotFoundException::new);

        if(!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())){
            throw new Exception("잘못된 비밀번호입니다.");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        String refreshToken = jwtTokenProvider.createRefreshToken();

        redisService.setDataWithExpiration(RedisKey.REGISTER.getKey() +user.getEmail(), refreshToken, JwtTokenProvider.REFRESH_TOKEN_VALID_TIME);

        return new LoginResponseDto(user.getNickname(), user.getEmail(),jwtTokenProvider.createAccessToken(authentication),refreshToken);
    }

    // 소셜 로그인
    @Transactional
    public LoginResponseDto loginUserByProvider(String code,String provider) throws CommunicationException {
        AccessToken accessToken = providerService.getAccessToken(code,provider);
        ProfileDto profileDto = providerService.getProfile(accessToken.getAccess_token(),provider);
        Optional<User> findUser = userRepository.findByEmailAndProvider(profileDto.getEmail(),provider);

        if(findUser.isPresent()){
            User user = findUser.get();
            String refreshToken = jwtTokenProvider.createRefreshToken();
            redisService.setDataWithExpiration(RedisKey.REGISTER.getKey() +user.getEmail(), refreshToken, JwtTokenProvider.REFRESH_TOKEN_VALID_TIME);

            return new LoginResponseDto(profileDto.getName(),user.getEmail(),jwtTokenProvider.createToken(user.getEmail()), refreshToken);
        }else{
            User saveUser = saveUser(profileDto,provider);
            String refreshToken = jwtTokenProvider.createRefreshToken();
            redisService.setDataWithExpiration(RedisKey.REGISTER.getKey() +saveUser.getEmail(), refreshToken, JwtTokenProvider.REFRESH_TOKEN_VALID_TIME);

            return new LoginResponseDto(saveUser.getNickname(),saveUser.getEmail(),jwtTokenProvider.createToken(saveUser.getEmail())
                    ,refreshToken);
        }
    }



    // 유저 저장 - 비밀번호 처리 어떻게 할지
    private User saveUser(ProfileDto profileDto, String provider) {
        User user = User.builder()
                .nickname(profileDto.getName())
                .email(profileDto.getEmail())
                .password(null)
                .role(Role.ROLE_USER)
                .provider(provider)
                .build();
        User saveUser = userRepository.save(user);
        return saveUser;
    }

    // refreshtoken 재발행
    @Transactional
        public TokenResponseDto reIssue(ReIssueRequestDto reIssueRequestDto) throws Exception{
        String findRefreshToken = redisService.getData(REGISTER.getKey() + reIssueRequestDto.getEmail());
        if(findRefreshToken == null || !findRefreshToken.equals(reIssueRequestDto.getRefreshToken())){
            log.info("refreshToken이 일치하지 않습니다.");
            throw new InvalidRefreshTokenException();
        }

        User user = userRepository.findByEmail(reIssueRequestDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String accessToken = jwtTokenProvider.createAccessToken(authentication);
        String refreshToken = jwtTokenProvider.createRefreshToken();

        redisService.setDataWithExpiration(RedisKey.REGISTER.getKey() +user.getEmail(), refreshToken, JwtTokenProvider.REFRESH_TOKEN_VALID_TIME);

        return new TokenResponseDto(accessToken,refreshToken);
    }

    // 회원 정보 수정
    @Transactional
    @Override
    public UpdateResponseDto update(UserInfoRequestDto userInfoRequestDto) throws Exception {
        if (!userInfoRequestDto.getPassword().equals(userInfoRequestDto.getCheckedpassword())) log.info("비밀번호가 일치하지 않습니다.");
        Optional<User> updateUser = userRepository.findByEmail(userInfoRequestDto.getEmail());

        updateUser.ifPresent(selectUser->{
            selectUser.setNickname(userInfoRequestDto.getNickname());
            selectUser.setPassword(passwordEncoder.encode(userInfoRequestDto.getPassword()));

            userRepository.save(selectUser);
        });
        User user = updateUser.get();

        return UpdateResponseDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .build();
    }

    // 로컬 로그아웃
    @Override
    public void logout(LogoutRequestDto logoutRequestDto) throws Exception{
        if (!jwtTokenProvider.validateToken(logoutRequestDto.getAccessToken())) {
            log.info("잘못된 요청입니다.");
        }
        Authentication authentication = jwtTokenProvider.getAuthentication(logoutRequestDto.getAccessToken());

        redisService.deleteData(RedisKey.REGISTER.getKey() +authentication.getName());
        log.info("로그아웃 되었습니다.");
    }

    // 회원 탈퇴
    @Transactional
    public void delete(String email) throws Exception{
        Optional<User> user = userRepository.findByEmail(email);
        log.info("delete : findByEmail={}",user.toString());
        if(!user.isPresent()){
            log.info("회원 탈퇴에 실패하셨습니다.");
            return;
        }

        userRepository.delete((User) user.get());
        log.info("회원이 탈퇴되었습니다.");

    }


}
