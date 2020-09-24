package com.springboot.cloud.redis.fallback;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.exception.SystemErrorType;
import com.springboot.cloud.redis.service.RedisHashServiceFeign;
import org.springframework.stereotype.Component;

@Component
public class RedisHashServiceFallback implements RedisHashServiceFeign {
    public Result hget(String key, String item) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result hmget(String key) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result hmset(String key, String value) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result hmsetAndTime(String key, String value, Long time) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result hset(String key, String item, String value) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result hsetAndTime(String key, String item, String value, Long time) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result hdel(String key, String item) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result hHasKey(String key, String item) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result hincr(String key, String item, Double incr) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result sGet(String key) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result sHasKey(String key, String value) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result sSet(String key, String value) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result sSetAndTime(String key, Long time, String value) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result sGetSetSize(String key) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result setRemove(String key, String value) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }
}
