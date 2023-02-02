package com.ssafy.youandi.service;

import com.ssafy.youandi.dto.request.*;
import com.ssafy.youandi.dto.response.JoinResponseDto;
import com.ssafy.youandi.dto.response.LoginResponseDto;
import com.ssafy.youandi.dto.response.TokenResponseDto;
import com.ssafy.youandi.dto.response.UpdateResponseDto;


public interface UserService {
    public LoginResponseDto login(LoginRequestDto loginRequestDto) throws Exception;
    public JoinResponseDto join(UserInfoRequestDto userInfoRequestDto) throws Exception;
    public TokenResponseDto reIssue(ReIssueRequestDto reIssueRequestDto) throws Exception;

    public LoginResponseDto loginUserByProvider(String code,String provider) throws Exception;
    public UpdateResponseDto update(UserInfoRequestDto userInfoRequestDto) throws Exception;
    public void logout(LogoutRequestDto logoutRequestDto) throws Exception;
    public void delete(String email) throws Exception;
    public String checkEmail(UserInfoRequestDto requestDto) throws Exception;
    public String checkNickName(UserInfoRequestDto requestDto) throws Exception;
}
