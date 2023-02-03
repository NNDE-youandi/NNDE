package com.ssafy.youandi.dto.response;


import lombok.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateResponseDto {
    @NotNull
    @Size(min = 3, max = 50)
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String nickname;
}
