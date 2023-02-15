package com.ssafy.youandi.service;

import com.ssafy.youandi.dto.request.AnswerRequestDto;
import com.ssafy.youandi.dto.request.MatchAnwserRequestDto;
import com.ssafy.youandi.dto.response.AnswerResponseDto;
import com.ssafy.youandi.entity.user.answer.Answer;
import com.ssafy.youandi.entity.user.survey.Survey;
import com.ssafy.youandi.repository.AnswerRepository;
import com.ssafy.youandi.repository.SurveyRepository;
import com.ssafy.youandi.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.ssafy.youandi.entity.user.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService{
    private final AnswerRepository answerRepository;
    private final SurveyRepository surveyRepository;
    private final UserRepository userRepository;

    // 설문에 맞는 답변 저장하기 - 유저 아이디에 맞게
    @ApiOperation(value = "답변 DB 저장", notes = "설문에 맞는 답변 저장하기 + ture : 성공, false : 실패")
    @Transactional
    @Override
    public boolean saveAnswer(AnswerRequestDto answerRequestDto){
        Answer answer = Answer.builder()
                .answer(answerRequestDto.getAnswer())
                .survey(surveyRepository.findBySurveyId(answerRequestDto.getSurveyId()))
                .user(userRepository.findByUserId(answerRequestDto.getUserId()))
                .build();

        answerRepository.save(answer);
        return true;
    }

    // 설문 번호에 맞는 답변 가져오기
    @ApiOperation(value = "랜덤으로 설문에 맞는 답변 가져오기 ")
    @Transactional
    // surveyid , nickname
    public AnswerResponseDto getmatchAnswerNSurvey(MatchAnwserRequestDto matchAnwserRequestDto){
        // 소켓 통신을 통해 랜덤으로 닉네임이름을 받아온 걸로 DB에서 회원정보롤 가져온다.
        User findNicknameUser = userRepository.findByNickname(matchAnwserRequestDto.getNickname());
        log.info("findNicknameUser.getNickname()={}",findNicknameUser.getNickname());
        log.info("findNicknameUser.getUserId()={}",findNicknameUser.getUserId());

        long findUserId = findNicknameUser.getUserId();
        long surveyId = matchAnwserRequestDto.getSurveyId();

        // user_id에 맞게 Answer에서 survey_id, answer_id, answer 가져온다.
        Answer answer = answerRepository.findByUser_UserIdAndSurvey_SurveyId(findUserId, surveyId);

        // 가져온 답변을 랜덤으로 가져온다
//        int idx =(int) (Math.random() * answerList.size());
//        Page<Answer> answerPage = answerRepository.findAll(PageRequest.of(idx,1));
//
//        Answer answer = null;
//        if(answerPage.hasContent()){
//            answer = answerPage.getContent().get(0);
//        }
        // 가져온 답변에 맞는 survey_id에 맞는 survey 가져온다.
        Survey survey = surveyRepository.findBySurveyId(surveyId);

        return new AnswerResponseDto(answer.getAnswer(),matchAnwserRequestDto.getNickname(), survey.getSurvey());
    }
}
