package com.ssafy.youandi.controller;

import com.ssafy.youandi.dto.response.KeywordResponseDto;
import com.ssafy.youandi.dto.response.RandomNickResponseDto;
import com.ssafy.youandi.service.KeywordService;
import com.ssafy.youandi.service.RandomNickService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = { "*" })
@RequestMapping("/introduce")
public class KeywordController {
    @Autowired
    private KeywordService keywordService;

    @GetMapping("/keyword")
    public ResponseEntity<KeywordResponseDto> getRandomNickname() {
        KeywordResponseDto keywordResponseDto = keywordService.getKeyword();
        return new ResponseEntity<>(keywordResponseDto, HttpStatus.OK);
    }
}
