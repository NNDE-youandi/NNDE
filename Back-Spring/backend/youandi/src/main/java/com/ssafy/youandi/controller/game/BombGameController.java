package com.ssafy.youandi.controller.game;

import com.ssafy.youandi.dto.response.BalanceGameResponseDto;
import com.ssafy.youandi.dto.response.BombGameResponseDto;
import com.ssafy.youandi.service.BalanceGameService;
import com.ssafy.youandi.service.BombGameService;
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
@RequestMapping("/bomb")
public class BombGameController {
    @Autowired
    private BombGameService bombGameService;

    @PostMapping("/test")
    public String test() {
        return "success";
    }

    @GetMapping("/question")
    public ResponseEntity<BombGameResponseDto> getRandomBombQuestion() {
        BombGameResponseDto bombGameResponseDto = bombGameService.getRandomBombQuestion();
        return new ResponseEntity<>(bombGameResponseDto, HttpStatus.OK);
    }
}
