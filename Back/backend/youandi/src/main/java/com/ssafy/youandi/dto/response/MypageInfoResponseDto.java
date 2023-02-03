package com.ssafy.youandi.dto.response;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MypageInfoResponseDto {
    private List<?> list;
    private UpdateResponseDto userinfo;
}
