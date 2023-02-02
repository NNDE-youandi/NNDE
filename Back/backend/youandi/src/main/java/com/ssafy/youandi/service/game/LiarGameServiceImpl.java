package com.ssafy.youandi.service.game;


import com.ssafy.youandi.dto.response.LiarGameResponseDto;
import com.ssafy.youandi.entity.game.liargame.LiarGame;
import com.ssafy.youandi.repository.game.LiarGameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LiarGameServiceImpl implements LiarGameService {

    private final LiarGameRepository liarGameRepository;

    // 라이어 게임 종류 리스트 가져오기
    @Transactional
    @Override
    public List<String> getDistinctLgType() {
        return liarGameRepository.findDistinctLgType();
    }

    // 라이어 게임 종류에 따른 단어 랜덤으로 가져오기
    @Override
    public LiarGameResponseDto getRandomLiarGameWord(String lgType) {
        Long count = liarGameRepository.countByLgType(lgType);
        int idx = (int) (Math.random() * count);
        Page<LiarGame> liarGamePage = liarGameRepository.findAllByLgType(PageRequest.of(idx, 1), lgType);
        LiarGame liarGame = null;
        if (liarGamePage.hasContent()) {
            liarGame = liarGamePage.getContent().get(0);
        }

        return LiarGameResponseDto.builder()
                .lgWord(liarGame.getLgWord())
                .build();
    }

}
