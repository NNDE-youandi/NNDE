package com.ssafy.youandi.controller.game;

import com.ssafy.youandi.dto.response.OxQuizResponseDto;
import com.ssafy.youandi.service.game.OxQuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"*"})
@RequestMapping("/ox")
public class OxQuizController {
    @Autowired
    private OxQuizService oxQuizService;

    @PostMapping("/test")
    public String test() {
        return "success";
    }

    @GetMapping("/quiz")
    public ResponseEntity<OxQuizResponseDto> getRandomOxQuiz() {
        OxQuizResponseDto oxQuizResponseDto = oxQuizService.getRandomOxQuiz();
        return new ResponseEntity<>(oxQuizResponseDto, HttpStatus.OK);
    }
}
