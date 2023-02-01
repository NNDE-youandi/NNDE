package com.ssafy.youandi.service;

import com.ssafy.youandi.advice.exception.CommunicationException;
import com.ssafy.youandi.dto.kakao.AccessToken;
import com.ssafy.youandi.dto.kakao.ProfileDto;
import org.springframework.http.ResponseEntity;



public interface ProviderService {
    public AccessToken getAccessToken(String code, String provider) throws CommunicationException ;
    public ProfileDto getProfile(String accessToken, String provider) throws CommunicationException;
    public ProfileDto extractProfile(ResponseEntity<String> response, String provider) throws CommunicationException;

}
