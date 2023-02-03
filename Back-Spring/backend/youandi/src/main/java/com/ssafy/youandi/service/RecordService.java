package com.ssafy.youandi.service;

import com.ssafy.youandi.dto.request.RecordResquestDto;
import com.ssafy.youandi.dto.response.RecordResponseDto;
import com.ssafy.youandi.entity.mypageinfo.Record;

import java.util.List;

public interface RecordService {
    public List<Record> selectRecordByNickname(String nickname);
}
