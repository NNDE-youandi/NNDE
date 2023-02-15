package com.ssafy.youandi.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class AnswerResponseDto {
    private String answer;
    private String nickname;
    private String survey;
}
