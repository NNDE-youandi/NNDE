package com.ssafy.youandi.service.Impl;


import com.ssafy.youandi.dto.response.BalanceGameResponseDto;
import com.ssafy.youandi.entity.game.balancegame.BalanceGame;
import com.ssafy.youandi.repository.game.BalanceGameRepository;
import com.ssafy.youandi.service.BalanceGameService;
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
public class BalanceGameServiceImpl implements BalanceGameService {

    private final BalanceGameRepository balanceGameRepository;

    // 밸런스 게임 질문 랜덤으로 가져오기
    @Transactional
    public BalanceGameResponseDto getRandomBalanceQuestion() {
        Long count = balanceGameRepository.countBy();
        int idx = (int) (Math.random() * count);
        Page<BalanceGame> balanceGamePage = balanceGameRepository.findAll(PageRequest.of(idx, 1));
        BalanceGame balanceGame = null;
        if (balanceGamePage.hasContent()) {
            balanceGame = balanceGamePage.getContent().get(0);
        }

        return BalanceGameResponseDto.builder()
                .bgQuestion1(balanceGame.getBgQuestion1())
                .bgQuestion2(balanceGame.getBgQuestion2())
                .build();
    }
}
