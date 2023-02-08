package com.ssafy.youandi.controller;

import com.ssafy.youandi.dto.request.AnswerRequestDto;
import com.ssafy.youandi.service.AnswerService;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @ApiOperation(value = "답변 DB 저장", notes = "설문에 맞는 답변 저장하기 + ture : 성공, false : 실패")
    @GetMapping("/save/{anwser}")
    public ResponseEntity<Boolean> saveAnswer(@PathVariable("anwser") AnswerRequestDto answerRequestDto){
        boolean result = answerService.saveAnswer(answerRequestDto);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
