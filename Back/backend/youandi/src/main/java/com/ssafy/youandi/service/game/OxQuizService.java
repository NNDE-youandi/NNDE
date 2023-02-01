package com.ssafy.youandi.service.game;

import com.ssafy.youandi.dto.response.OxQuizResponseDto;


public interface OxQuizService {
    // OX 퀴즈 랜덤으로 가져오기
    public OxQuizResponseDto getRandomOxQuiz();

}
