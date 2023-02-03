package com.ssafy.youandi.controller.game;

import com.ssafy.youandi.dto.response.BalanceGameResponseDto;
import com.ssafy.youandi.service.game.BalanceGameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = { "*" })
@RequestMapping("/balance")
public class BalanceGameController {
    @Autowired
    private BalanceGameService balanceGameService;

    @PostMapping("/test")
    public String test() {
        return "success";
    }

    @GetMapping("/question")
    public ResponseEntity<List<BalanceGameResponseDto>> getRandomBalanceQuestionList() {
        List<BalanceGameResponseDto> balanceGameResponseDtoList = balanceGameService.getRandomBalanceQuestionList();
        return new ResponseEntity<>(balanceGameResponseDtoList, HttpStatus.OK);
    }
}
