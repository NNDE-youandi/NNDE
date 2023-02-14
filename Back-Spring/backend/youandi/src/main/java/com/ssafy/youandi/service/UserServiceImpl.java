package com.ssafy.youandi.service;


import com.ssafy.youandi.config.advice.exception.*;
import com.ssafy.youandi.config.jwt.JwtTokenProvider;
import com.ssafy.youandi.dto.kakao.AccessToken;
import com.ssafy.youandi.dto.kakao.ProfileDto;
import com.ssafy.youandi.dto.request.*;
import com.ssafy.youandi.dto.response.*;
import com.ssafy.youandi.entity.Role;
import com.ssafy.youandi.entity.mypageinfo.Record;
import com.ssafy.youandi.entity.redis.RedisKey;
import com.ssafy.youandi.entity.user.User;
import com.ssafy.youandi.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

import static com.ssafy.youandi.entity.redis.RedisKey.REGISTER;

// TODO : builder() or 생성자 둘 중 하나로 통일하기

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

    private final RecordService recordService;


    @ApiOperation(value = "이메일 증복 확인", notes = "true : 증복 , false : 중복x")
    @Transactional(readOnly = true)
    public boolean checkEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            return true;
//            throw new UserEmailAlreadyExistsException("이미 회원가입된 이메일입니다.");
        }
        return false;
    }

    @ApiOperation(value = "닉네임 증복 확인", notes = "true : 증복 , false : 중복x")
    @Transactional(readOnly = true)
    public boolean checkNickName(String Nickname) {
        if (userRepository.existsByNickname(Nickname)) {
            return true;
//            throw new UserNicknameAlreadyExistsException("이미 존재한 닉네임입니다.");
        }
        return false;
    }


    @ApiOperation(value = "회원가입", notes = "ture : 성공, false : 실패")
    @Transactional
    @Override
    public boolean join(UserInfoRequestDto userInfoRequestDto) {

        if (checkEmail(userInfoRequestDto.getEmail())) {
            throw new UserEmailAlreadyExistsException("이미 회원가입된 이메일입니다.");
        }
        if (checkNickName(userInfoRequestDto.getNickname())) {
            throw new UserNicknameAlreadyExistsException("이미 존재한 닉네임입니다.");
        }

        if (!userInfoRequestDto.getPassword().equals(userInfoRequestDto.getCheckedpassword())) {
            throw new DisagreePasswordException("비밀번호가 일치하지 않습니다.");
        }


        User user = User.builder().email(userInfoRequestDto.getEmail()).password(passwordEncoder.encode(userInfoRequestDto.getPassword())).nickname(userInfoRequestDto.getNickname()).provider(null).role(Role.ROLE_USER).build();

        userRepository.save(user);

        return true;
    }

    @ApiOperation(value = "로컬 로그인")
    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(UserNotFoundException::new);

        boolean message = false;
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            return LoginResponseDto.builder().message(message).build();
//            throw new LoginFailureException("잘못된 비밀번호입니다.");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        String refreshToken = jwtTokenProvider.createRefreshToken();

        redisService.setDataWithExpiration(RedisKey.REGISTER.getKey() + user.getEmail(), refreshToken, JwtTokenProvider.REFRESH_TOKEN_VALID_TIME);

        message = true;
        return new LoginResponseDto(user.getNickname(), user.getEmail(), jwtTokenProvider.createAccessToken(authentication), refreshToken,user.getUserId(),message);
    }

    @ApiOperation(value = "소셜 로그인")
    @Transactional
    public LoginResponseDto loginUserByProvider(String code, String provider) {
        AccessToken accessToken = providerService.getAccessToken(code, provider);
        ProfileDto profileDto = providerService.getProfile(accessToken.getAccess_token(), provider);
        Optional<User> findUser = userRepository.findByEmailAndProvider(profileDto.getEmail(), provider);

        boolean message = false;

        if (findUser.isPresent()) {
            User user = findUser.get();
            String refreshToken = jwtTokenProvider.createRefreshToken();
            redisService.setDataWithExpiration(RedisKey.REGISTER.getKey() + user.getEmail(), refreshToken, JwtTokenProvider.REFRESH_TOKEN_VALID_TIME);

            message = true;

            return new LoginResponseDto(profileDto.getName(), user.getEmail(), jwtTokenProvider.createToken(user.getEmail()), refreshToken,user.getUserId(),message);
        } else {
            User saveUser = saveUser(profileDto, provider);
            String refreshToken = jwtTokenProvider.createRefreshToken();
            redisService.setDataWithExpiration(RedisKey.REGISTER.getKey() + saveUser.getEmail(), refreshToken, JwtTokenProvider.REFRESH_TOKEN_VALID_TIME);

            message = true;

            return new LoginResponseDto(saveUser.getNickname(), saveUser.getEmail(), jwtTokenProvider.createToken(saveUser.getEmail()), refreshToken, saveUser.getUserId(),message);
        }
    }

    @ApiOperation(value = "소셜 로그인 유저 저장")
    // TODO : 비밀번호 처리 어떻게 할지 profileDto.getPassword not null인지 확인
    private User saveUser(ProfileDto profileDto, String provider) {
        User user = User.builder().nickname(profileDto.getName()).email(profileDto.getEmail()).password(null).role(Role.ROLE_USER).provider(provider).build();
        User saveUser = userRepository.save(user);
        return saveUser;
    }

    @ApiOperation(value = "refreshtoken 재발행")
    @Transactional
    public TokenResponseDto reIssue(ReIssueRequestDto reIssueRequestDto) {
        String findRefreshToken = redisService.getData(REGISTER.getKey() + reIssueRequestDto.getEmail());
        if (findRefreshToken == null || !findRefreshToken.equals(reIssueRequestDto.getRefreshToken())) {
            throw new InvalidRefreshTokenException("refreshToken이 일치하지 않습니다.");
        }

        User user = userRepository.findByEmail(reIssueRequestDto.getEmail()).orElseThrow(() -> new UserNotFoundException());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String accessToken = jwtTokenProvider.createAccessToken(authentication);
        String refreshToken = jwtTokenProvider.createRefreshToken();

        redisService.setDataWithExpiration(RedisKey.REGISTER.getKey() + user.getEmail(), refreshToken, JwtTokenProvider.REFRESH_TOKEN_VALID_TIME);

        return new TokenResponseDto(accessToken, refreshToken);
    }

    @ApiOperation(value="마이페이지 - 게임 이력 조회")
    @Override
    public List<Record> recordinfo(String email){
        log.info("email ={}",email);
        User info = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);;
        log.info("info={}",info.toString());
        String nickname = info.getNickname();

        List<Record> recordList = recordService.selectRecordByNickname(nickname);
        return recordList;
    }

    @ApiOperation(value="마이페이지 - 회원 정보 조회")
    @Override
    public UpdateResponseDto userinfo(String email){
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return new UpdateResponseDto(user.getEmail(),user.getPassword(), user.getNickname());
    }

    @ApiOperation(value = "회원 정보 수정")
    @Transactional
    @Override
    public UpdateResponseDto update(UserInfoRequestDto userInfoRequestDto) {
        if (!userInfoRequestDto.getPassword().equals(userInfoRequestDto.getCheckedpassword())) {
            throw new DisagreePasswordException("비밀번호가 일치하지 않습니다.");
        }
        if (checkNickName(userInfoRequestDto.getNickname())) {
            throw new UserNicknameAlreadyExistsException("이미 존재한 닉네임입니다.");
        }
        Optional<User> updateUser = userRepository.findByEmail(userInfoRequestDto.getEmail());

        updateUser.ifPresent(selectUser -> {
            selectUser.setNickname(userInfoRequestDto.getNickname());
            selectUser.setPassword(passwordEncoder.encode(userInfoRequestDto.getPassword()));

            userRepository.save(selectUser);
        });
        User user = updateUser.get();

        return UpdateResponseDto.builder().email(user.getEmail()).password(user.getPassword()).nickname(user.getNickname()).build();
    }

    @ApiOperation(value = "로그아웃", notes = "true : 성공, false: 실패")
    @Override
    public boolean logout(LogoutRequestDto logoutRequestDto) {
        if (!jwtTokenProvider.validateToken(logoutRequestDto.getAccessToken())) {
//            throw new InvalidRefreshTokenException("잘못된 요청입니다.");
            return false;
        }
        log.info("assess = {}", logoutRequestDto.getAccessToken());
        Authentication authentication = jwtTokenProvider.getAuthentication(logoutRequestDto.getAccessToken());
        redisService.deleteData(RedisKey.REGISTER.getKey() + authentication.getName());

        return true;
    }

    @ApiOperation(value = "회원 탈퇴", notes = "true : 성공, false: 실패")
    @Transactional
    public boolean delete(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new));
        if (!user.isPresent()) {
            throw new UserNotFoundException("회원 탈퇴에 실패하셨습니다.");
        }

        userRepository.delete((User) user.get());
        return true;
    }


}
