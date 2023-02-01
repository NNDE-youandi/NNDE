package com.ssafy.youandi.advice;

import com.ssafy.youandi.advice.exception.*;
import com.ssafy.youandi.entity.result.Result;
import com.ssafy.youandi.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 수정중 - 17: 32 수빈  Exception 처리 수정하기
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {
    private final ResponseService responseService;

    @ExceptionHandler(UserEmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result userEmailAlreadyExistsException() {
        return responseService.getFailureResult(-101, "이미 존재하는 이메일입니다.");
    }
    @ExceptionHandler(UserNicknameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result UserNicknameAlreadyExistsException() {
        return responseService.getFailureResult(-101, "이미 존재한 닉네임입니다.");
    }
    @ExceptionHandler(LoginFailureException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result loginFailureException() {
        return responseService.getFailureResult(-102, "아이디 혹은 비밀번호가 틀립니다.");
    }

    @ExceptionHandler(InvalidRefreshTokenException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result invalidRefreshTokenException() {
        return responseService.getFailureResult(-104, "Refresh Token이 유효하지 않습니다.");
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result UserNotFoundException() {
        return responseService.getFailureResult(-105, "사용자를 찾을 수 없습니다.");
    }

}
