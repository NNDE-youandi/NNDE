package com.ssafy.youandi.dto.response;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class LoginResponseDto {

    @NotBlank
    private String nickname;
    @NotBlank
    @Size(min = 3, max = 50)
    private String email;

    @NotNull
    private String accessToken;
    @NotNull
    private String refreshToken;

    @NotNull
    private long userId;

    // 로그인 성공 실패 여부 (성공 : true, 실패 : false)
    private boolean message;
}