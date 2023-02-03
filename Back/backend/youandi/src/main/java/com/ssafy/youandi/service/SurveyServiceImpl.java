package com.ssafy.youandi.service;


import com.ssafy.youandi.dto.response.SurveyResponseDto;
import com.ssafy.youandi.entity.user.survey.Survey;
import com.ssafy.youandi.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;

    // 설문 리스트(설문 3개) 랜덤으로 가져오기
    @Transactional
    public List<SurveyResponseDto> getRandomSurvey() {
        List<Survey> surveyList = surveyRepository.findAll();
        List<SurveyResponseDto> surveyResponseDtoList = new ArrayList<>();
        for (int i = 0; i < surveyList.size(); i++) {
            SurveyResponseDto surveyResponseDto = SurveyResponseDto.builder().survey(surveyList.get(i).getSurvey()).build();
            surveyResponseDtoList.add(surveyResponseDto);
        }
        return surveyResponseDtoList;
    }
}
