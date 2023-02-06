package com.ssafy.youandi.service;

import com.ssafy.youandi.dto.response.KeywordResponseDto;
import com.ssafy.youandi.entity.keyword.Keyword;
import com.ssafy.youandi.repository.KeywordRepository;
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
public class KeywordServiceImpl implements KeywordService{
    private final KeywordRepository keywordRepository;

    // 키워드 자기소개 키워드 랜덤 가져오기
    @Transactional
    @Override
    public KeywordResponseDto getKeyword(){
        Long count = keywordRepository.countBy();
        int idx =(int) (Math.random() * count);
        Page<Keyword> keywordPage = keywordRepository.findAll(PageRequest.of(idx,1));
        Keyword keyword = null;
        if(keywordPage.hasContent()){
            keyword = keywordPage.getContent().get(0);
        }
        return KeywordResponseDto.builder()
                .keyword(keyword.getKeyword())
                .build();
    }
}
