package com.ssafy.youandi.entity.result;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MultipleResult<T> extends Result {
    private List<T> data;
}
