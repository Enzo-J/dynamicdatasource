package com.springboot.cloud.redis.fallback;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.exception.SystemErrorType;
import com.springboot.cloud.redis.service.RedisBaseServiceFeign;
import com.springboot.cloud.redis.service.RedisListServiceFeign;
import org.springframework.stereotype.Component;

@Component
public class RedisListServiceFallback implements RedisListServiceFeign {
    public Result lGet(String key, Long start, Long end) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result lGetAll(String key) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result lGetListSize(String key) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result lGetIndex(String key, Long index) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result lSet(String key, String value) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result lSetAndTime(String key, String value, Long time) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result lSetList(String key, String value) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result lSetListAndTime(String key, String value, Long time) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result lUpdateIndex(String key, String value, Long index) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result lRemove(String key, Long count, String value) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result delByPrefix(String prefix) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result incrementScore(String key, Double score, String member) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result addToSet(String key, String value) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result keys(String key) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result zSetReverseRange(String key, Long start, Long end) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result zSetScore(String key, String member) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }
}
