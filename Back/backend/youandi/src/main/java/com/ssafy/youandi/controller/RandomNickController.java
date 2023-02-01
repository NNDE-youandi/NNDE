package com.ssafy.youandi.controller;

import com.ssafy.youandi.dto.response.RandomNickResponseDto;
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
@RequestMapping("/randomnick")
public class RandomNickController {
    @Autowired
    private RandomNickService randomNickService;

    @PostMapping("/test")
    public String test() {
        return "success";
    }

    @GetMapping("/name")
    public ResponseEntity<RandomNickResponseDto> getRandomNickname() {
        RandomNickResponseDto randomNickResponseDto = randomNickService.getRandomNickname();
        return new ResponseEntity<>(randomNickResponseDto, HttpStatus.OK);
    }
}
