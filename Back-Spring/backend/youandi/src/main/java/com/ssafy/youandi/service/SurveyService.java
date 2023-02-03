package com.ssafy.youandi.service;

import com.ssafy.youandi.dto.response.SurveyResponseDto;

import java.util.List;


public interface SurveyService {
    // 설문 랜덤으로 가져오기
    public List<SurveyResponseDto> getRandomSurvey();

}
