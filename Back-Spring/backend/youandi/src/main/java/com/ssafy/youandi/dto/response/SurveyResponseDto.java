package com.ssafy.youandi.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SurveyResponseDto {
    private long surveyId;
    private String survey;
}
