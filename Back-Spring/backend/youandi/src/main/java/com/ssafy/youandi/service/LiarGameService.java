package com.ssafy.youandi.service;

import com.ssafy.youandi.dto.response.LiarGameResponseDto;

import java.util.List;


public interface LiarGameService {

    // 라이어 게임 모든 종류 가져오기
    public List<String> getDistinctLgType();
    // 라이어 게임 종류에 따른 단어 랜덤으로 가져오기
    public LiarGameResponseDto getRandomLiarGameWord(String lgType);

}
