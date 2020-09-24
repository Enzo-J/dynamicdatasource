package com.springboot.cloud.redis.controller;

import com.alibaba.fastjson.JSON;
import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.redis.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

import static com.springboot.cloud.common.core.constant.Constants.PARAS_MSG;

/**
 * @author MaJu
 * @date 2020/9/21
 */
@Api(tags = "redis List操作接口")
@RestController
@RequestMapping("/redis")
@Validated
public class RedisListController {
    @Resource
    private RedisUtils redisUtils;

    @ApiOperation("获取list缓存的内容")
    @GetMapping("/lGet")
    public Result lGet(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                       @NotNull(message = PARAS_MSG) @RequestParam Long start,
                       @NotNull(message = PARAS_MSG) @RequestParam Long end) {
        return Result.success(redisUtils.lGet(key, start, end));
    }

    @ApiOperation("获取list缓存的内容")
    @GetMapping("/lGetAll")
    public Result lGetAll(@NotBlank(message = PARAS_MSG) @RequestParam String key) {
        return Result.success(redisUtils.lGetAll(key));
    }

    @ApiOperation("获取list缓存的长度")
    @GetMapping("/lGetListSize")
    public Result lGetListSize(@NotBlank(message = PARAS_MSG) @RequestParam String key) {
        return Result.success(redisUtils.lGetListSize(key));
    }

    @ApiOperation("通过索引 获取list中的值")
    @GetMapping("/lGetIndex")
    public Result lGetIndex(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                            @NotNull(message = PARAS_MSG) @RequestParam Long index) {
        return Result.success(redisUtils.lGetIndex(key, index));
    }

    @ApiOperation("将list放入缓存")
    @PostMapping("/lSet")
    public Result lSet(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                       @NotNull(message = PARAS_MSG) @RequestBody String value) {
        return Result.success(redisUtils.lSet(key, value));
    }

    @ApiOperation("将list放入缓存并设置时间")
    @PostMapping("/lSetAndTime")
    public Result lSetAndTime(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                              @NotNull(message = PARAS_MSG) @RequestBody String value,
                              @NotNull(message = PARAS_MSG) @RequestParam Long time) {
        return Result.success(redisUtils.lSet(key, value, time));
    }

    @ApiOperation("将list放入缓存")
    @PostMapping("/lSetList")
    public Result lSetList(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                           @NotNull(message = PARAS_MSG) @RequestBody String value) {
        try {
            return Result.success(redisUtils.lSet(key, JSON.parseArray(value, List.class)));
        } catch (Exception e) {
            return Result.success(false);
        }
    }

    @ApiOperation("将list放入缓存并设置时间")
    @PostMapping("/lSetListAndTime")
    public Result lSetListAndTime(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                                  @NotNull(message = PARAS_MSG) @RequestBody String value,
                                  @NotNull(message = PARAS_MSG) @RequestParam Long time) {
        try {
            return Result.success(redisUtils.lSet(key, JSON.parseArray(value, List.class), time));
        } catch (Exception e) {
            return Result.success(false);
        }
    }

    @ApiOperation("根据索引修改list中的某条数据")
    @PostMapping("/lUpdateIndex")
    public Result lUpdateIndex(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                               @NotNull(message = PARAS_MSG) @RequestBody String value,
                               @NotNull(message = PARAS_MSG) @RequestParam Long index) {
        return Result.success(redisUtils.lUpdateIndex(key, index, value));
    }

    @ApiOperation("移除N个值为value")
    @PostMapping("/lRemove")
    public Result lRemove(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                          @NotNull(message = PARAS_MSG) @RequestParam Long count,
                          @NotNull(message = PARAS_MSG) @RequestBody String value) {
        return Result.success(redisUtils.lRemove(key, count, value));
    }

    @ApiOperation("移除前缀为prefix的键")
    @PostMapping("/delByPrefix")
    public Result delByPrefix(@NotBlank(message = PARAS_MSG) @RequestParam String prefix) {
        return Result.success(redisUtils.delByPrefix(prefix));
    }

    @ApiOperation("指定key里面的member自增、减score")
    @PostMapping("/incrementScore")
    public Result incrementScore(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                                 @NotNull(message = PARAS_MSG) @RequestParam Double score,
                                 @NotBlank(message = PARAS_MSG) @RequestParam String member) {
        return Result.success(redisUtils.incrementScore(key, score, member));
    }

    @ApiOperation("存储set")
    @PostMapping("/addToSet")
    public Result addToSet(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                           @NotNull(message = PARAS_MSG) @RequestBody String value) {
        return Result.success(redisUtils.addToSet(key, value));
    }

    @ApiOperation("获取keys")
    @GetMapping("/keys")
    public Result keys(@NotBlank(message = PARAS_MSG) @RequestParam String key) {
        return Result.success(redisUtils.keys(key));
    }

    @ApiOperation("获取指定长度数据")
    @GetMapping("/zSetReverseRange")
    public Result zSetReverseRange(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                                   @NotNull(message = PARAS_MSG) @RequestParam Long start,
                                   @NotNull(message = PARAS_MSG) @RequestParam Long end) {
        return Result.success(redisUtils.zSetReverseRange(key, start, end));
    }

    @ApiOperation("获取指定元素分数")
    @GetMapping("/zSetScore")
    public Result zSetScore(@NotBlank(message = PARAS_MSG) @RequestParam String key,
                            @NotBlank(message = PARAS_MSG) @RequestParam String member) {
        return Result.success(redisUtils.zSetScore(key, member));
    }
}
