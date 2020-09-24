package com.springboot.cloud.redis.fallback;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.common.core.exception.SystemErrorType;
import com.springboot.cloud.redis.service.RedisBaseServiceFeign;
import org.springframework.stereotype.Component;

@Component
public class RedisBaseServiceFallback implements RedisBaseServiceFeign {
    public Result expire(String key, Long time) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result getExpire(String key) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result hasKey(String key) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result delete(String key) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result get(String key) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result set(String key, String value) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result setAndTime(String key, String value, Long time) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }

    public Result incr(String key, Long delta) {
        return Result.fail(SystemErrorType.SYSTEM_BUSY);
    }
}
