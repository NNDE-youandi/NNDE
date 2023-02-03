package com.ssafy.youandi.controller;

import com.ssafy.youandi.dto.response.SurveyResponseDto;
import com.ssafy.youandi.service.SurveyService;
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
@CrossOrigin(origins = {"*"})
@RequestMapping("/survey")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @PostMapping("/test")
    public String test() {
        return "success";
    }

    @GetMapping("/random")
    public ResponseEntity<List<SurveyResponseDto>> getRandomSurvey() {
        List<SurveyResponseDto> surveyList = surveyService.getRandomSurvey();
        return new ResponseEntity<>(surveyList, HttpStatus.OK);
    }
}
