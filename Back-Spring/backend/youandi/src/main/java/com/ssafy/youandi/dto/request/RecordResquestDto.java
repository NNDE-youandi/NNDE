package com.ssafy.youandi.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RecordResquestDto {
    @NotEmpty(message = "닉네임을 알 수 없습니다.")
    String nickname;
}
