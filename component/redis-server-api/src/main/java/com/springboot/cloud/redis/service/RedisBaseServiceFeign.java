package com.springboot.cloud.redis.service;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.redis.fallback.RedisBaseServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "redis-server", fallback = RedisBaseServiceFallback.class)
public interface RedisBaseServiceFeign {
    /**
     * 指定缓存失效时间
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    @RequestMapping(value = "/redisBase/expire", method = RequestMethod.POST)
    Result expire(@RequestParam("key") String key,
                  @RequestParam("time") Long time);
    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    @RequestMapping(value = "/redisBase/getExpire", method = RequestMethod.GET)
    Result getExpire(@RequestParam("key") String key);
    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    @RequestMapping(value = "/redisBase/hasKey", method = RequestMethod.GET)
    Result hasKey(@RequestParam("key") String key);
    /**
     * 删除缓存
     *
     * @param key
     */
    @RequestMapping(value = "/redisBase/delete", method = RequestMethod.POST)
    Result delete(@RequestParam("key") String key);
    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    @RequestMapping(value = "/redisBase/get", method = RequestMethod.GET)
    Result get(@RequestParam("key") String key);
    /**
     * 普通缓存放入
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    @RequestMapping(value = "/redisBase/set", method = RequestMethod.POST)
    Result set(@RequestParam("key") String key,
               @RequestBody String value);
    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    @RequestMapping(value = "/redisBase/setAndTime", method = RequestMethod.POST)
    Result setAndTime(@RequestParam("key") String key,
                      @RequestBody String value,
                      @RequestParam("time") Long time
    );
    /**
     * 递增/递减
     *
     * @param key   键
     * @param delta 正为递增，负为递减
     * @return
     */
    @RequestMapping(value = "/redisBase/incr", method = RequestMethod.POST)
    Result incr(@RequestParam("key") String key,
                @RequestParam("delta") Long delta);

}
