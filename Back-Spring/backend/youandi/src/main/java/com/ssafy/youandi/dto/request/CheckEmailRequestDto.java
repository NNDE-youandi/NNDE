package com.ssafy.youandi.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
@Getter
@Setter
@Builder
@Valid
@ToString
public class CheckEmailRequestDto {
    @ApiModelProperty(required = true)
    @NotBlank(message = "이메일을 입력해주세요")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    private String email;
}

