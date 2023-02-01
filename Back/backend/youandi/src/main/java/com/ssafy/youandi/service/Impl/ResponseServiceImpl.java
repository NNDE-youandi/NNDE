package com.ssafy.youandi.service.Impl;

import com.ssafy.youandi.entity.result.MultipleResult;
import com.ssafy.youandi.entity.result.Result;
import com.ssafy.youandi.entity.result.SingleResult;
import com.ssafy.youandi.service.ResponseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(readOnly = true)
public class ResponseServiceImpl implements ResponseService {

    @Override
    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        setSuccessResult(result);
        result.setDate(data);

        return result;
    }

    @Override
    public <T> MultipleResult<T> getMultipleResult(List<T> data) {
        MultipleResult<T> result = new MultipleResult<>();
        setSuccessResult(result);
        result.setData(data);

        return result;
    }

    @Override
    public Result getSuccessResult() {
        Result result = new Result();
        setSuccessResult(result);

        return result;
    }

    @Override
    public void setSuccessResult(Result result) {
        result.setSuccess(true);
        result.setCode(0);
        result.setMessage("성공");
    }

    @Override
    public Result getFailureResult(int code, String msg) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(msg);

        return result;
    }
}
