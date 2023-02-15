package com.ssafy.youandi.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

@Getter
@Setter
@Valid
@ToString
public class MatchAnwserRequestDto {
    private String nickname;
    private long surveyId;
}
