package com.ssafy.youandi.service;

import com.ssafy.youandi.dto.response.RecordResponseDto;
import com.ssafy.youandi.entity.mypageinfo.Record;
import com.ssafy.youandi.repository.RecordRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {
    private final RecordRepository recordRepository;
    @ApiOperation(value="record 정보 가져오기",notes = "해당 회원의 record 정보를 가져온다.")
    @Transactional
    @Override
    public List<Record> selectRecordByNickname(String nickname){
        List<Record> userRecordList = recordRepository.findAllByGamer1OrGamer2OrGamer3OrGamer4OrGamer5OrGamer6(nickname,nickname,nickname,nickname,nickname,nickname);
        log.info("nickname ={} , userRecordList={}",nickname,userRecordList);
        return userRecordList;
    }
}
