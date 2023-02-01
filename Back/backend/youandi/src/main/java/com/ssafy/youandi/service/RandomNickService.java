package com.ssafy.youandi.service;

import com.ssafy.youandi.dto.response.RandomNickResponseDto;


public interface RandomNickService {
    // 밸런스 게임 랜덤으로 가져오기
    public RandomNickResponseDto getRandomNickname();

}
