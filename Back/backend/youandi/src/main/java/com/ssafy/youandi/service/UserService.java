package com.ssafy.youandi.service;

import com.ssafy.youandi.dto.request.*;
import com.ssafy.youandi.dto.response.RecordResponseDto;
import com.ssafy.youandi.dto.response.LoginResponseDto;
import com.ssafy.youandi.dto.response.TokenResponseDto;
import com.ssafy.youandi.dto.response.UpdateResponseDto;
import com.ssafy.youandi.entity.mypageinfo.Record;

import java.util.List;


public interface UserService {
    public LoginResponseDto login(LoginRequestDto loginRequestDto);
    public boolean join(UserInfoRequestDto userInfoRequestDto);
    public TokenResponseDto reIssue(ReIssueRequestDto reIssueRequestDto);

    public LoginResponseDto loginUserByProvider(String code,String provider);
    public UpdateResponseDto update(UserInfoRequestDto userInfoRequestDto) throws Exception;
    public boolean logout(LogoutRequestDto logoutRequestDto);
    public boolean delete(String email);
    public boolean checkEmail(String email);
    public boolean checkNickName(String Nickname);
    public List<Record> recordinfo(String email);
    public UpdateResponseDto userinfo(String email);
}
