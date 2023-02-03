package com.ssafy.youandi.repository;

import com.ssafy.youandi.entity.mypageinfo.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record, Integer> {

    List<Record> findAllByGamer1OrGamer2OrGamer3OrGamer4OrGamer5OrGamer6(String gamer1,String gamer2,String gamer3,String gamer4,String gamer5,String gamer6);
}


