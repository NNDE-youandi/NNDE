package com.ssafy.youandi.controller;

import com.ssafy.youandi.dto.response.BalanceGameResponseDto;
import com.ssafy.youandi.service.BalanceGameService;
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
@RequestMapping("/balancegame")
public class BalanceGameController {
    @Autowired
    private BalanceGameService balanceGameService;

    @PostMapping("/test")
    public String test() {
        return "success";
    }

    @GetMapping("/random")
    public ResponseEntity<BalanceGameResponseDto> getRandomBalanceGame() {
        BalanceGameResponseDto balanceGameResponseDto = balanceGameService.randomBalanceGame();
        return new ResponseEntity<>(balanceGameResponseDto, HttpStatus.OK);
    }
}
