package com.ssafy.youandi.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
@Getter
@Setter
@Builder
@Valid
@ToString
public class CheckNicknameRequestDto {
    @ApiModelProperty(required = true)
    @NotBlank(message = "닉네임을 입력해주세요")
    private String nickname;
}
