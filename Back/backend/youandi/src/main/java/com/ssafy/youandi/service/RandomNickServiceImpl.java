package com.ssafy.youandi.service;


import com.ssafy.youandi.dto.response.RandomNickResponseDto;
import com.ssafy.youandi.entity.randomnick.RandomNick;
import com.ssafy.youandi.repository.RandomNickRepository;
import com.ssafy.youandi.repository.UserRepository;
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
public class RandomNickServiceImpl implements RandomNickService {

    private final RandomNickRepository randomNickRepository;
    private final UserRepository userRepository;

    // 닉네임 랜덤으로 가져오기
    @Transactional
    public RandomNickResponseDto getRandomNickname() {
        Long count = randomNickRepository.countBy();
        int idx = (int) (Math.random() * count);
        Page<RandomNick> randomNickPage = randomNickRepository.findAll(PageRequest.of(idx, 1));
        RandomNick randomNick = null;
        if (randomNickPage.hasContent()) {
            randomNick = randomNickPage.getContent().get(0);
        }

        // 랜덤 닉네임 중복 체크, 중복이 있으면 getRandomNickname() 다시 실행
        if (userRepository.existsByNickname(randomNick.getRandomNickname())) getRandomNickname();

        return RandomNickResponseDto.builder()
                .randomNickname(randomNick.getRandomNickname())
                .build();
    }
}
