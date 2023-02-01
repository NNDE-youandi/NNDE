package com.ssafy.youandi.service;

import com.ssafy.youandi.entity.result.MultipleResult;
import com.ssafy.youandi.entity.result.Result;
import com.ssafy.youandi.entity.result.SingleResult;

import java.util.List;

public interface ResponseService {
    public <T> SingleResult<T> getSingleResult(T data);
    public <T> MultipleResult<T> getMultipleResult(List<T> data);
    public Result getSuccessResult();

    public void setSuccessResult(Result result);

    public Result getFailureResult(int code, String msg);
}
