package com.ssafy.youandi.controller;

import com.ssafy.youandi.dto.kakao.AuthCode;
import com.ssafy.youandi.dto.response.LoginResponseDto;
import com.ssafy.youandi.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})
@RequestMapping("/social/login")
public class SocialController {
    @Value("${spring.social.kakao.client_id}")
    private String kakaoClientId;
    @Value("${spring.social.kakao.redirect_uri}")
    private String kakaoRedirect;
    private final Environment env;

    @Autowired
    private UserService userService;

    @GetMapping()
    public String socialKakaoLogin() {
        StringBuilder loginUrl1 = new StringBuilder()
                .append(env.getProperty("spring.social.kakao.url.login"))
                .append("?client_id=").append(kakaoClientId)
                .append("&response_type=code")
                .append("&redirect_uri=").append(kakaoRedirect);
        return loginUrl1.toString();
    }

//    @ResponseBody
//    @GetMapping("/kakao")
//    public void kakaoCallback(@RequestParam String code) {
//        System.out.println("kakaoCallback : " + code);
//    }

    @ApiOperation(value = "소셜 로그인", notes = "소셜을 통해 로그인을 진행한다.")
    @GetMapping("/kakao/callback/{code}")
    public ResponseEntity<LoginResponseDto> loginByProvider(@PathVariable("code") String authCode) {
        String provider ="kakao";
        LoginResponseDto responseDto = userService.loginUserByProvider(authCode, provider);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


//    @ApiOperation(value = "소셜 로그인", notes = "소셜을 통해 로그인을 진행한다.")
//    @GetMapping("/kakao/callback")
//    public RedirectView loginByProvider(@RequestParam("code") String authCode, HttpServletResponse httpServletResponse) {
//        String provider ="kakao";
//        LoginResponseDto responseDto = userService.loginUserByProvider(authCode, provider);
////        httpServletResponse.sendRedirect("/");
////        return new ResponseEntity<>("redirect:/", HttpStatus.OK);
//
//        return new RedirectView("http://localhost:8081");
//    }

}
