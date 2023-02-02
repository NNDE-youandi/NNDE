package com.ssafy.youandi.controller.game;

import com.ssafy.youandi.dto.response.BombGameResponseDto;
import com.ssafy.youandi.dto.response.LiarGameResponseDto;
import com.ssafy.youandi.service.BombGameService;
import com.ssafy.youandi.service.LiarGameService;
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
@RequestMapping("/liar")
public class LiarGameController {
    @Autowired
    private LiarGameService liarGameService;

    @PostMapping("/test")
    public String test() {
        return "success";
    }

    @GetMapping("/type")
    public ResponseEntity<List<String>> getLiarGameType() {
        List<String> dictinctType = liarGameService.getDistinctLgType();
        return new ResponseEntity<>(dictinctType, HttpStatus.OK);
    }

    @GetMapping("/word/{lgType}")
    public ResponseEntity<LiarGameResponseDto> getRandomWordByType(@PathVariable String lgType) {
        LiarGameResponseDto liarGameResponseDto = liarGameService.getRandomLiarGameWord(lgType);
        return new ResponseEntity<>(liarGameResponseDto, HttpStatus.OK);
    }
}
