package com.ssafy.youandi.service.game;

import com.ssafy.youandi.dto.response.BalanceGameResponseDto;


public interface BalanceGameService {
    // 밸런스 게임 랜덤으로 가져오기
    public BalanceGameResponseDto getRandomBalanceQuestion();

}
