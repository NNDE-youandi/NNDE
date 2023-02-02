package com.ssafy.youandi.service;

import com.ssafy.youandi.dto.response.BombGameResponseDto;


public interface BombGameService {
    // 폭탄 게임 랜덤으로 가져오기
    public BombGameResponseDto getRandomBombQuestion();

}
