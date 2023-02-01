package com.ssafy.youandi.entity.redis;

import lombok.Getter;

@Getter
public enum RedisKey {
    REGISTER("Register_"),GUEST("Guest_");

    private String key;

    RedisKey(String key){
        this.key = key;
    }
}
