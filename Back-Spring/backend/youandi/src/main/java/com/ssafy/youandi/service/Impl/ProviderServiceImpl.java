package com.ssafy.youandi.service.Impl;

import com.google.gson.Gson;
import com.ssafy.youandi.dto.kakao.AccessToken;
import com.ssafy.youandi.config.social.OAuthRequestFactory;
import com.ssafy.youandi.dto.kakao.KakaoProfileDto;
import com.ssafy.youandi.dto.request.OAuthRequestDto;
import com.ssafy.youandi.dto.kakao.ProfileDto;
import com.ssafy.youandi.service.ProviderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.naming.CommunicationException;
@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {

    private final RestTemplate restTemplate;
    private final Gson gson;
    private final OAuthRequestFactory oAuthRequestFactory;

    // 유저의 정보 가져오기
    public ProfileDto getProfile(String accessToken, String provider) throws CommunicationException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        String profileUrl = oAuthRequestFactory.getProfileUrl(provider);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity(profileUrl, request, String.class);

        try {
            if (response.getStatusCode() == HttpStatus.OK) {
                return extractProfile(response, provider);
            }
        } catch (Exception e) {
            throw new CommunicationException();
        }
        throw new CommunicationException();
    }

    // provider별 유저 정보 발췌
    public ProfileDto extractProfile(ResponseEntity<String> response, String provider) throws Exception{
        if (provider.equals("kakao")) {
            KakaoProfileDto kakaoProfile = gson.fromJson(response.getBody(), KakaoProfileDto.class);
            return new ProfileDto(kakaoProfile.getProperties().getNickname(), kakaoProfile.getKakao_account().getEmail());
        } else{
            return null;
        }
    }

    // oAuth 토큰 가져오기
    public AccessToken getAccessToken(String code, String provider) throws CommunicationException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        OAuthRequestDto oAuthRequest = oAuthRequestFactory.getRequest(code, provider);
        HttpEntity<LinkedMultiValueMap<String, String>> request = new HttpEntity<>(oAuthRequest.getMap(), httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity(oAuthRequest.getUrl(), request, String.class);
        try {
            if (response.getStatusCode() == HttpStatus.OK) {
                return gson.fromJson(response.getBody(), AccessToken.class);
            }
        } catch (Exception e) {
            throw new CommunicationException();
        }
        throw new CommunicationException();
    }
}
