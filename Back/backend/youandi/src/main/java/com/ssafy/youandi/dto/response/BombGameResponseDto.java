package com.ssafy.youandi.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class BombGameResponseDto {
    private String bombQuestion;
}
