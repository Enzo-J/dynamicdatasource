package com.springboot.cloud.redis.controller;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.redis.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.springboot.cloud.common.core.constant.Constants.PARAS_MSG;


/**
 * @author MaJu
 * @date 2020/9/21
 */
@Api(tags = "redis基础操作")
@RestController
@RequestMapping("/redisBase")
@Validated
public class RedisBaseController {
    @Resource
    private RedisUtils redisUtils;

    @ApiOperation("指定缓存失效时间")
    @PostMapping("/expire")
    public Result expire(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                         @NotNull(message = PARAS_MSG) @RequestParam Long time) {
        return Result.success(redisUtils.expire(key, time));
    }

    @ApiOperation("根据key 获取过期时间")
    @GetMapping("/getExpire")
    public Result getExpire(@NotBlank(message = PARAS_MSG) @RequestParam String key) {
        return Result.success(redisUtils.getExpire(key));
    }

    @ApiOperation("判断key是否存在")
    @GetMapping("/hasKey")
    public Result hasKey(@NotBlank(message = PARAS_MSG) @RequestParam String key) {
        return Result.success(redisUtils.hasKey(key));
    }

    @ApiOperation("删除缓存")
    @PostMapping("/delete")
    public Result delete(@NotBlank(message = PARAS_MSG) @RequestParam String key) {
        return Result.success(redisUtils.delete(key));
    }

    @ApiOperation("普通缓存获取")
    @GetMapping(value = "/get")
    public Result get(@NotBlank(message = PARAS_MSG) @RequestParam String key) {
        return Result.success(redisUtils.get(key));
    }

    @ApiOperation("普通缓存放入")
    @PostMapping("/set")
    public Result set(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                      @NotNull(message = PARAS_MSG) @RequestBody String value) {
        return Result.success(redisUtils.set(key, value));
    }

    @ApiOperation("普通缓存放入并设置时间")
    @PostMapping("/setAndTime")
    public Result setAndTime(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                             @NotNull(message = PARAS_MSG) @RequestBody String value,
                             @NotNull(message = PARAS_MSG) @RequestParam Long time
    ) {
        return Result.success(redisUtils.set(key, value, time));
    }

    @ApiOperation("递增/递减")
    @PostMapping("/incr")
    public Result incr(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                       @NotNull(message = PARAS_MSG) @RequestParam Long delta) {
        return Result.success(redisUtils.incr(key, delta));
    }
}
