package com.ssafy.youandi.service;

import com.ssafy.youandi.dto.request.*;
import com.ssafy.youandi.dto.response.JoinResponseDto;
import com.ssafy.youandi.dto.response.LoginResponseDto;
import com.ssafy.youandi.dto.response.TokenResponseDto;
import com.ssafy.youandi.dto.response.UpdateResponseDto;
import org.springframework.http.ResponseEntity;


public interface UserService {
    public LoginResponseDto login(LoginRequestDto loginRequestDto) throws Exception;
    public JoinResponseDto join(JoinRequestDto joinRequestDto) throws Exception;
    public TokenResponseDto reIssue(ReIssueRequestDto reIssueRequestDto) throws Exception;

    public LoginResponseDto loginUserByProvider(String code,String provider) throws Exception;
    public UpdateResponseDto update(UpdateRequestDto updateRequestDto) throws Exception;
    public void logout(LogoutRequestDto logoutRequestDto) throws Exception;
}
