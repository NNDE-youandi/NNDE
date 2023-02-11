package com.ssafy.youandi.controller;

import com.ssafy.youandi.dto.request.AnswerRequestDto;
import com.ssafy.youandi.dto.request.MatchAnwserRequestDto;
import com.ssafy.youandi.dto.response.AnswerResponseDto;
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
@RequestMapping("/surveyQuiz")
public class SurveyQuizController {

    @Autowired
    private AnswerService answerService;

    @ApiOperation(value = "설문 번호에 맞는 답변 가져오기")
    @PostMapping("/get")
    public ResponseEntity<AnswerResponseDto> getmatchAnswerNSurvey(@RequestBody MatchAnwserRequestDto matchAnwserRequestDto){
        AnswerResponseDto answerResponseDto = answerService.getmatchAnswerNSurvey(matchAnwserRequestDto);
        return new ResponseEntity<>( answerResponseDto,HttpStatus.OK);
    }
}
