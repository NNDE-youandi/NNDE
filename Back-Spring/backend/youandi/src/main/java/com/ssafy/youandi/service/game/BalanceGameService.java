package com.ssafy.youandi.service.game;

import com.ssafy.youandi.dto.response.BalanceGameResponseDto;

import java.util.List;


public interface BalanceGameService {
    public BalanceGameResponseDto getRandomBalanceQuestion();
    public List<BalanceGameResponseDto> getRandomBalanceQuestionList();

}
