package com.ssafy.youandi.service;

import com.ssafy.youandi.dto.response.AnswerResponseDto;
import com.ssafy.youandi.repository.AnswerRepository;
import com.ssafy.youandi.repository.SurveyRepository;
import com.ssafy.youandi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService{
    private final AnswerRepository answerRepository;
    private final SurveyRepository surveyRepository;
    private final UserRepository userRepository;

//    // 설문 번호에 맞는 답변 가져오기
//    public AnswerResponseDto getmatchAnswerNSurvey(){
//
//    }
}
