package com.ssafy.youandi.dto.response;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class JoinResponseDto {

    @NotBlank
    @Size(min = 3, max = 50)
    private String email;

}