package com.ssafy.youandi.controller;

import com.ssafy.youandi.advice.exception.AuthenticationEntryPointException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/exception")
public class ExceptionController {
    @ApiOperation(value = "인증 실패", notes = "인증 실패에 따른 예외가 발생했습니다.")
    @GetMapping(value = "/entry")
    public void EntryPointException() {
        throw new AuthenticationEntryPointException();
    }

    @ApiOperation(value = "인가 거부", notes = "인가에 따른 예외가 발생했습니다.")
    @GetMapping(value = "/denied")
    public void AccessDeniedException() {
        throw new AccessDeniedException("");
    }
}
