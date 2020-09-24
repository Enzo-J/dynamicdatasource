package com.springboot.cloud.redis.service;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.redis.fallback.RedisHashServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "redis-server", fallback = RedisHashServiceFallback.class)
public interface RedisHashServiceFeign {
    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    @RequestMapping(value = "/redisHash/hget", method = RequestMethod.GET)
    Result hget(@RequestParam("key") String key,
                @RequestParam("item") String item
    );

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    @RequestMapping(value = "/redisHash/hmget", method = RequestMethod.GET)
    Result hmget(@RequestParam("key") String key);

    /**
     * HashSet
     *
     * @param key 键
     * @return true 成功 false 失败
     */
    @RequestMapping(value = "/redisHash/hmset", method = RequestMethod.POST)
    Result hmset(@RequestParam("key") String key,
                 @RequestBody String value);

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    @RequestMapping(value = "/redisHash/hmsetAndTime", method = RequestMethod.POST)
    Result hmsetAndTime(@RequestParam("key") String key,
                        @RequestBody String value,
                        @RequestParam("time") Long time);

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    @RequestMapping(value = "/redisHash/hset", method = RequestMethod.POST)
    Result hset(@RequestParam("key") String key,
                @RequestParam("item") String item,
                @RequestBody String value);

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    @RequestMapping(value = "/redisHash/hsetAndTime", method = RequestMethod.POST)
    Result hsetAndTime(@RequestParam("key") String key,
                       @RequestParam("item") String item,
                       @RequestBody String value,
                       @RequestParam("time") Long time);

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    @RequestMapping(value = "/redisHash/hdel", method = RequestMethod.POST)
    Result hdel(@RequestParam("key") String key,
                @RequestParam("item") String item);

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    @RequestMapping(value = "/redisHash/hHasKey", method = RequestMethod.GET)
    Result hHasKey(@RequestParam("key") String key,
                   @RequestParam("item") String item);

    /**
     * hash递增/递减 如果不存在,就会创建一个 并把新增/递减后的值返回
     *
     * @param key  键
     * @param item 项
     * @return
     */
    @RequestMapping(value = "/redisHash/hincr", method = RequestMethod.POST)
    Result hincr(@RequestParam("key") String key,
                 @RequestParam("item") String item,
                 @RequestParam("incr") Double incr);

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    @RequestMapping(value = "/redisHash/sGet", method = RequestMethod.GET)
    Result sGet(@RequestParam("key") String key);

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    @RequestMapping(value = "/redisHash/sHasKey", method = RequestMethod.POST)
    Result sHasKey(@RequestParam("key") String key,
                   @RequestBody String value);

    /**
     * 将数据放入set缓存
     *
     * @param key   键
     * @param value 值 可以是多个[]
     * @return 成功个数
     */
    @RequestMapping(value = "/redisHash/sSet", method = RequestMethod.POST)
    Result sSet(@RequestParam("key") String key,
                @RequestBody String value);

    /**
     * 将set数据放入缓存
     *
     * @param key   键
     * @param time  时间(秒)
     * @param value 值 可以是多个
     * @return 成功个数
     */
    @RequestMapping(value = "/redisHash/sSetAndTime", method = RequestMethod.POST)
    Result sSetAndTime(@RequestParam("key") String key,
                       @RequestParam("time") Long time,
                       @RequestBody String value);

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */
    @RequestMapping(value = "/redisHash/sGetSetSize", method = RequestMethod.GET)
    Result sGetSetSize(@RequestParam("key") String key);

    /**
     * 移除值为value的
     *
     * @param key   键
     * @param value 值 可以是多个
     * @return 移除的个数
     */
    @RequestMapping(value = "/redisHash/setRemove", method = RequestMethod.POST)
    Result setRemove(@RequestParam("key") String key,
                     @RequestBody String value);
}
