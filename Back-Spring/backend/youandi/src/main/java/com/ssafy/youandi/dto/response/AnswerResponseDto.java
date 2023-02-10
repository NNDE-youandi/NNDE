package com.ssafy.youandi.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class AnswerResponseDto {
    private String answer;
//    private long user_id;
//    private long survey_id;

    private String nickname;
    private String survey;
}
