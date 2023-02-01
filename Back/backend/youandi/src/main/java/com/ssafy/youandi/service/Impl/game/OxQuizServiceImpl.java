package com.ssafy.youandi.service.Impl.game;


import com.ssafy.youandi.dto.response.OxQuizResponseDto;
import com.ssafy.youandi.entity.game.oxquiz.OxQuiz;
import com.ssafy.youandi.repository.game.OxQuizRepository;
import com.ssafy.youandi.service.game.OxQuizService;
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
public class OxQuizServiceImpl implements OxQuizService {

    private final OxQuizRepository oxQuizRepository;

    // OX 퀴즈 랜덤으로 가져오기
    @Transactional
    @Override
    public OxQuizResponseDto getRandomOxQuiz() {
        Long count = oxQuizRepository.countBy();
        int idx = (int) (Math.random() * count);
        Page<OxQuiz> oxQuizPage = oxQuizRepository.findAll(PageRequest.of(idx, 1));
        OxQuiz oxQuiz = null;
        if (oxQuizPage.hasContent()) {
            oxQuiz = oxQuizPage.getContent().get(0);
        }

        return OxQuizResponseDto.builder()
                .quiz(oxQuiz.getQuiz())
                .isTrue(oxQuiz.isTrue())
                .description(oxQuiz.getDescription())
                .build();
    }

}
