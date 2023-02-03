package com.ssafy.youandi.service.game;


import com.ssafy.youandi.dto.response.BalanceGameResponseDto;
import com.ssafy.youandi.dto.response.SurveyResponseDto;
import com.ssafy.youandi.entity.game.balancegame.BalanceGame;
import com.ssafy.youandi.entity.user.survey.Survey;
import com.ssafy.youandi.repository.game.BalanceGameRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BalanceGameServiceImpl implements BalanceGameService {

    private final BalanceGameRepository balanceGameRepository;

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

    // 밸런스 게임 리스트(질문 3쌍) 랜덤으로 가져오기
    @Transactional
    public List<BalanceGameResponseDto> getRandomBalanceQuestionList() {
        List<BalanceGame> balanceGameList = balanceGameRepository.findAll();
        List<BalanceGameResponseDto> balanceGameResponseDtoList = new ArrayList<>();
        for (int i = 0; i < balanceGameList.size(); i++) {
            BalanceGameResponseDto balanceGameResponseDto = BalanceGameResponseDto.builder().
                    bgQuestion1(balanceGameList.get(i).getBgQuestion1())
                    .bgQuestion2(balanceGameList.get(i).getBgQuestion2())
                    .build();
            balanceGameResponseDtoList.add(balanceGameResponseDto);
        }
        return balanceGameResponseDtoList;
    }
}
