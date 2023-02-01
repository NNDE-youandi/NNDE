package com.ssafy.youandi.controller.game;

import com.ssafy.youandi.dto.response.BalanceGameResponseDto;
import com.ssafy.youandi.service.BalanceGameService;
import com.ssafy.youandi.service.Impl.BalanceGameServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<BalanceGameResponseDto> getRandomBalanceQuestion() {
        BalanceGameResponseDto balanceGameResponseDto = balanceGameService.getRandomBalanceQuestion();
        return new ResponseEntity<>(balanceGameResponseDto, HttpStatus.OK);
    }
}
