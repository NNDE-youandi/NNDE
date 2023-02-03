package com.ssafy.youandi.dto.response;

import lombok.*;

import javax.persistence.Column;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class OxQuizResponseDto {

    private String quiz;

    private boolean isTrue;

    private String description;
}
