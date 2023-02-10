package com.ssafy.youandi.service;

import com.ssafy.youandi.dto.request.AnswerRequestDto;
import com.ssafy.youandi.dto.request.MatchAnwserRequestDto;
import com.ssafy.youandi.dto.response.AnswerResponseDto;

public interface AnswerService {
    public boolean saveAnswer(AnswerRequestDto answerRequestDto);
    public AnswerResponseDto getmatchAnswerNSurvey(MatchAnwserRequestDto matchAnwserRequestDto);
}
