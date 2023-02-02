package com.ssafy.youandi.service.Impl;

import com.ssafy.youandi.service.RedisService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Service
@Transactional
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate redisTemplate;
    @ApiOperation(value = "data 가져오기", notes = "해당 key의 value 값 가져온다.")
    public String getData(String key){
        return (String) redisTemplate.opsForValue().get(key);
    }
    @ApiOperation(value = "data 삭제", notes = "해당 key 삭제")
    public void deleteData(String key){
        redisTemplate.delete(key);
    }
    @ApiOperation(value = "key-value 생성,저장", notes = "만료기간이 있는 key-value 생성하고 저장한다.")
    public void setDataWithExpiration(String key, String value, Long time){
        if(this.getData(key) != null){
            this.deleteData(key);
        }
        Duration expireDuration = Duration.ofSeconds(time);
        redisTemplate.opsForValue().set(key, value, expireDuration);
    }
}
