package com.ssafy.youandi.controller;

import com.ssafy.youandi.service.Impl.UserServiceImpl;
import com.ssafy.youandi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.CommunicationException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/social/login")
@CrossOrigin("*")
public class SocialController {
    @Value("${spring.social.kakao.client_id}")
    private String kakaoClientId;
    @Value("${spring.social.kakao.redirect_uri}")
    private String kakaoRedirect;
    private final Environment env;

    @GetMapping()
    public String socialKakaoLogin() {
        StringBuilder loginUrl1 = new StringBuilder()
                .append(env.getProperty("spring.social.kakao.url.login"))
                .append("?client_id=").append(kakaoClientId)
                .append("&response_type=code")
                .append("&redirect_uri=").append(kakaoRedirect);
        return "redirect:"+loginUrl1.toString();
    }

    @ResponseBody
    @GetMapping("/kakao")
    public void kakaoCallback(@RequestParam String code) {
        System.out.println("kakaoCallback : "+ code);
    }

}
