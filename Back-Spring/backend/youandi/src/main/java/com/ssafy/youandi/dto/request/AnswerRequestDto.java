package com.ssafy.youandi.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Valid
@ToString
public class AnswerRequestDto {
    @NotBlank(message = "답변을 입력해주세요.")
    private String answer;

    private long userId;

    private long surveyId;

}
