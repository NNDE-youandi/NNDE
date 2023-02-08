package com.ssafy.youandi.service;

import com.ssafy.youandi.dto.request.AnswerRequestDto;

public interface AnswerService {
    public boolean saveAnswer(AnswerRequestDto answerRequestDto);
}
