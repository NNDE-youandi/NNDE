package com.ssafy.youandi.controller;

import com.ssafy.youandi.config.jwt.JwtFilter;
import com.ssafy.youandi.dto.kakao.AuthCode;
import com.ssafy.youandi.dto.request.*;
import com.ssafy.youandi.dto.response.*;
import com.ssafy.youandi.entity.mypageinfo.Record;
import com.ssafy.youandi.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "회원가입", notes = "회원가입을 진행한다.")
    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> join(@Valid @RequestBody UserInfoRequestDto userInfoRequestDto) throws Exception {
        boolean result = userService.join(userInfoRequestDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "로컬 로그인", notes = "로컬을 통해 로그인을 진행한다.")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = userService.login(loginRequestDto);

        if(!loginResponseDto.isMessage()){
            return new ResponseEntity<>(loginResponseDto.isMessage(),HttpStatus.ACCEPTED);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + loginResponseDto.getAccessToken());

        return new ResponseEntity<>(loginResponseDto, headers, HttpStatus.OK);
    }

//    @ApiOperation(value = "소셜 로그인", notes = "소셜을 통해 로그인을 진행한다.")
//    @PostMapping("/login/{provider}")
//    public ResponseEntity<LoginResponseDto> loginByProvider(@RequestBody AuthCode authCode, @PathVariable String provider) {
//        LoginResponseDto responseDto = userService.loginUserByProvider(authCode.getCode(), provider);
//        return new ResponseEntity<>(responseDto, HttpStatus.OK);
//    }

    @ApiOperation(value = "토큰 재발급", notes = "Refresh Token을 통해 토큰을 재발급받는다.")
    @PostMapping("/reissue")
    public ResponseEntity<TokenResponseDto> reIssue(@RequestBody ReIssueRequestDto reIssueRequestDto) {
        TokenResponseDto responseDto = userService.reIssue(reIssueRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @ApiOperation(value = "회원 정보 수정", notes = "회원의 정보를 수정한다.")
    @PutMapping("/update")
    public ResponseEntity<UpdateResponseDto> update(@Valid @RequestBody UserInfoRequestDto userInfoRequestDto) throws Exception {
        UpdateResponseDto updateResponseDto = userService.update(userInfoRequestDto);
        return new ResponseEntity<>(updateResponseDto, HttpStatus.OK);
    }
    @ApiOperation(value = "회원 정보 조회 + 기록", notes = "회원의 정보를 조회한다.")
    @GetMapping("/info/{email}")
    public ResponseEntity<?> recordinfo(@PathVariable("email") String email) {
        List<Record> list = userService.recordinfo(email);
        UpdateResponseDto userinfo = userService.userinfo(email);
        MypageInfoResponseDto mypageInfoResponseDto=new MypageInfoResponseDto(list,userinfo);
        return new ResponseEntity<>(mypageInfoResponseDto,HttpStatus.OK);
    }
    // TODO : jwt 토큰을 이용한 회원 정보 조회 
//    @ApiOperation(value = "회원 정보 조회 + 기록", notes = "jwt ")
//    @GetMapping("/info")
//    public ResponseEntity<?> recordinfo() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();
//        log.info("email ={}",email);
//
//        List<Record> list = userService.recordinfo(email);
//        UpdateResponseDto userinfo = userService.userinfo(email);
//        MypageInfoResponseDto mypageInfoResponseDto=new MypageInfoResponseDto(list,userinfo);
//        return new ResponseEntity<>(mypageInfoResponseDto,HttpStatus.OK);
//    }
    @ApiOperation(value = "로그아웃", notes = "로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<Boolean> logout(@Valid @RequestBody LogoutRequestDto logoutRequestDto) {
        boolean result = userService.logout(logoutRequestDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "회원 탈퇴", notes = "회원을 탈퇴한다.")
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Boolean> delete(@PathVariable("email") String email) {
        boolean result = userService.delete(email);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "이메일 중복 확인", notes = "이메일 중복을 확인한다.")
    @PostMapping("/checkEmail")
    public ResponseEntity<Boolean> checkEmail(@RequestBody CheckEmailRequestDto checkEmailRequestDto) {
        boolean result = userService.checkEmail(checkEmailRequestDto.getEmail());
        log.info("result ={}",result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "닉네임 중복 확인", notes = "닉네임 중복을 확인한다.")
    @PostMapping("/checkNickname")
    public ResponseEntity<Boolean> checkNickName(@RequestBody CheckNicknameRequestDto requestDto) {
        boolean result = userService.checkNickName(requestDto.getNickname());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
