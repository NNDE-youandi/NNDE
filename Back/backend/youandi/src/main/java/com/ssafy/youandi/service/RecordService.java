package com.ssafy.youandi.service;

import com.ssafy.youandi.dto.request.RecordResquestDto;
import com.ssafy.youandi.dto.response.RecordResponseDto;

public interface RecordService {
    public RecordResponseDto selectuserByRecord(String nickname);
}
