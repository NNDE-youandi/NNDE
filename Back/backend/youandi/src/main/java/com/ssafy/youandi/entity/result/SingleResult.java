package com.ssafy.youandi.entity.result;

import lombok.*;

@Getter
@Setter
public class SingleResult<T> extends Result {
    private T date;
}
