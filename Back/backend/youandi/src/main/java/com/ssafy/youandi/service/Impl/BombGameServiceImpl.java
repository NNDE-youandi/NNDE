package com.ssafy.youandi.service.Impl;


import com.ssafy.youandi.dto.response.BombGameResponseDto;
import com.ssafy.youandi.entity.game.bombgame.BombGame;
import com.ssafy.youandi.repository.game.BombGameRepository;
import com.ssafy.youandi.service.BombGameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BombGameServiceImpl implements BombGameService {

    private final BombGameRepository bombGameRepository;

    // 폭탄 게임 질문 랜덤으로 가져오기
    @Transactional
    @Override
    public BombGameResponseDto getRandomBombQuestion() {
        Long count = bombGameRepository.countBy();
        int idx = (int) (Math.random() * count);
        Page<BombGame> bombGamePage = bombGameRepository.findAll(PageRequest.of(idx, 1));
        BombGame bombGame = null;
        if (bombGamePage.hasContent()) {
            bombGame = bombGamePage.getContent().get(0);
        }

        return BombGameResponseDto.builder()
                .bombQuestion(bombGame.getBombQuestion())
                .build();
    }

}
