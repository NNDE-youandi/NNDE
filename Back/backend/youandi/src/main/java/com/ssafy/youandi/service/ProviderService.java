package com.ssafy.youandi.service;

import com.ssafy.youandi.config.advice.exception.CommunicationException;
import com.ssafy.youandi.config.advice.exception.UserNotFoundException;
import com.ssafy.youandi.dto.kakao.AccessToken;
import com.ssafy.youandi.dto.kakao.ProfileDto;
import org.springframework.http.ResponseEntity;



public interface ProviderService {

    public ProfileDto getProfile(String accessToken, String provider) throws CommunicationException;
    public ProfileDto extractProfile(ResponseEntity<String> response, String provider) throws CommunicationException;
    public AccessToken getAccessToken(String code, String provider) throws CommunicationException ;
}
