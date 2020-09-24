package com.springboot.cloud.redis.controller;

import com.alibaba.fastjson.JSON;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.redis.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

import static com.springboot.cloud.common.core.constant.Constants.PARAS_MSG;

/**
 * @author MaJu
 * @date 2020/9/21
 */
@Api(tags = "redis hash操作")
@RestController
@RequestMapping("/redisHash")
@Validated
public class RedisHashController {
    @Resource
    private RedisUtils redisUtils;

    @ApiOperation("获取变量中的指定map键是否有值")
    @GetMapping("/hget")
    public Result hget(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                       @NotBlank(message = PARAS_MSG) @RequestParam String item
    ) {
        return Result.success(redisUtils.hget(key, item));
    }

    @ApiOperation("获取hashKey对应的所有键值")
    @GetMapping("/hmget")
    public Result hmget(@NotBlank(message = PARAS_MSG) @RequestParam String key) {
        return Result.success(redisUtils.hmget(key));
    }

    @ApiOperation("以map集合的形式添加键值对")
    @PostMapping("/hmset")
    public Result hmset(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                        @NotNull(message = PARAS_MSG) @RequestBody String value) {
        return Result.success(redisUtils.hmset(key, JSON.parseObject(value, Map.class)));
    }

    @ApiOperation("以map集合的形式添加键值对并设置时间")
    @PostMapping("/hmsetAndTime")
    public Result hmsetAndTime(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                               @NotNull(message = PARAS_MSG) @RequestBody String value,
                               @NotNull(message = PARAS_MSG) @RequestParam Long time) {
        return Result.success(redisUtils.hmset(key, JSON.parseObject(value, Map.class), time));
    }

    @ApiOperation("向一张hash表中放入数据,如果不存在将创建")
    @PostMapping("/hset")
    public Result hset(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                       @NotBlank(message = PARAS_MSG) @RequestParam String item,
                       @NotNull(message = PARAS_MSG) @RequestBody String value) {
        return Result.success(redisUtils.hset(key, item, value));
    }

    @ApiOperation("向一张hash表中放入数据,如果不存在将创建，并设置时间")
    @PostMapping("/hsetAndTime")
    public Result hsetAndTime(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                              @NotBlank(message = PARAS_MSG) @RequestParam String item,
                              @NotNull(message = PARAS_MSG) @RequestBody String value,
                              @NotNull(message = PARAS_MSG) @RequestParam Long time) {
        return Result.success(redisUtils.hset(key, item, value, time));
    }

    @ApiOperation("删除hash表中的值")
    @PostMapping("/hdel")
    public Result hdel(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                       @NotBlank(message = PARAS_MSG) @RequestParam String item) {
        try {
            return Result.success(redisUtils.hdel(key, JSON.parseArray(item, List.class)));
        } catch (Exception e) {
            return Result.success(redisUtils.hdel(key, item));
        }
    }

    @ApiOperation("判断hash表中是否有该项的值")
    @GetMapping("/hHasKey")
    public Result hHasKey(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                          @NotBlank(message = PARAS_MSG) @RequestParam String item) {
        return Result.success(redisUtils.hHasKey(key, item));
    }

    @ApiOperation("hash递增/递减 如果不存在,就会创建一个 并把新增/递减后的值返回")
    @PostMapping("/hincr")
    public Result hincr(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                        @NotBlank(message = PARAS_MSG) @RequestParam String item,
                        @NotNull(message = PARAS_MSG) @RequestParam Double incr) {
        return Result.success(redisUtils.hincr(key, item, incr));
    }

    @ApiOperation("根据key获取Set中的所有值")
    @GetMapping("/sGet")
    public Result sGet(@NotBlank(message = PARAS_MSG) @RequestParam String key) {
        return Result.success(redisUtils.sGet(key));
    }

    @ApiOperation("根据value从一个set中查询,是否存在")
    @PostMapping("/sHasKey")
    public Result sHasKey(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                          @NotBlank(message = PARAS_MSG) @RequestBody String value) {
        return Result.success(redisUtils.sHasKey(key, value));
    }

    @ApiOperation("将数据放入set缓存")
    @PostMapping("/sSet")
    public Result sSet(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                       @NotNull(message = PARAS_MSG) @RequestBody String value) {
        try {
            return Result.success(redisUtils.sSet(key, JSON.parseArray(value, List.class)));
        } catch (Exception e) {
            return Result.success(redisUtils.sSet(key, value));
        }
    }

    @ApiOperation("将set数据放入缓存并设置时间")
    @PostMapping("/sSetAndTime")
    public Result sSetAndTime(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                              @NotNull(message = PARAS_MSG) @RequestParam Long time,
                              @NotNull(message = PARAS_MSG) @RequestBody String value) {
        try {
            return Result.success(redisUtils.sSetAndTime(key, time, JSON.parseArray(value, List.class)));
        } catch (Exception e) {
            return Result.success(redisUtils.sSetAndTime(key, time, value));
        }
    }

    @ApiOperation("获取set缓存的长度")
    @GetMapping("/sGetSetSize")
    public Result sGetSetSize(@NotBlank(message = PARAS_MSG) @RequestParam String key) {
        return Result.success(redisUtils.sGetSetSize(key));
    }

    @ApiOperation("移除值为value的")
    @PostMapping("/setRemove")
    public Result setRemove(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                            @NotNull(message = PARAS_MSG) @RequestBody String value) {
        try {
            return Result.success(redisUtils.setRemove(key, JSON.parseArray(value, List.class)));
        } catch (Exception e) {
            return Result.success(redisUtils.setRemove(key, value));
        }
    }

}
