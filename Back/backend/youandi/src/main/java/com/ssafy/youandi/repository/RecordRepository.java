package com.ssafy.youandi.repository;

import com.ssafy.youandi.entity.mypageinfo.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Integer> {
    // 닉네임 따라서 레코드 리스트 출력하기
//    List<Record> findByRecordIdInAndGamer1AndGamer2AndGamer3AndGamer4AndGamer5AndGamer6(String recordId);
    List<Record> findAllByGamer1OrGamer2OrGamer3OrGamer4OrGamer5OrGamerOrGamer6(String gamer1
            ,String gamer2,String gamer3,String gamer4,String gamer5,String gamer6);
}

