package com.springboot.cloud.redis.service;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springboot.cloud.redis.fallback.RedisListServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "redis-server", fallback = RedisListServiceFallback.class)
public interface RedisListServiceFeign {

    /**
     * 获取list缓存的内容
     *csp
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return
     */
    @RequestMapping(value = "/redisList/lGet", method = RequestMethod.GET)
    Result lGet(@RequestParam("key") String key,
                @RequestParam("start") Long start,
                @RequestParam("end") Long end);

    /**
     * 获取list缓存的内容
     *
     * @param key 键
     * @return
     */
    @RequestMapping(value = "/redisList/lGetAll", method = RequestMethod.GET)
    Result lGetAll(@RequestParam("key") String key);

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return
     */
    @RequestMapping(value = "/redisList/lGetListSize", method = RequestMethod.GET)
    Result lGetListSize(@RequestParam("key") String key);

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    @RequestMapping(value = "/redisList/lGetIndex", method = RequestMethod.GET)
    Result lGetIndex(@RequestParam("key") String key,
                     @RequestParam("index") Long index);

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    @RequestMapping(value = "/redisList/lSet", method = RequestMethod.POST)
    Result lSet(@RequestParam("key") String key,
                @RequestBody String value);

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    @RequestMapping(value = "/redisList/lSetAndTime", method = RequestMethod.POST)
    Result lSetAndTime(@RequestParam("key") String key,
                       @RequestBody String value,
                       @RequestParam("time") Long time);

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    @RequestMapping(value = "/redisList/lSetList", method = RequestMethod.POST)
    Result lSetList(@RequestParam("key") String key,
                    @RequestBody String value);

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    @RequestMapping(value = "/redisList/lSetListAndTime", method = RequestMethod.POST)
    Result lSetListAndTime(@RequestParam("key") String key,
                           @RequestBody String value,
                           @RequestParam("time") Long time);

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    @RequestMapping(value = "/redisList/lUpdateIndex", method = RequestMethod.POST)
    Result lUpdateIndex(@RequestParam("key") String key,
                        @RequestBody String value,
                        @RequestParam("index") Long index);

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    @RequestMapping(value = "/redisList/lRemove", method = RequestMethod.POST)
    Result lRemove(@RequestParam("key") String key,
                   @RequestParam("time") Long count,
                   @RequestBody String value);

    /**
     * 移除前缀为prefix的键
     *
     * @param prefix 前缀
     * @return 移除的个数
     */

    @RequestMapping(value = "/redisList/delByPrefix", method = RequestMethod.POST)
    Result delByPrefix(@RequestParam("prefix") String prefix);

    /**
     * 指定key里面的member自增、减score
     */
    @RequestMapping(value = "/redisList/incrementScore", method = RequestMethod.POST)
    Result incrementScore(@RequestParam("key") String key,
                          @RequestParam("score") Double score,
                          @RequestParam("member") String member);

    /**
     * 存储set
     */
    @RequestMapping(value = "/redisList/addToSet", method = RequestMethod.POST)
    Result addToSet(@RequestParam("key") String key,
                    @RequestBody String value);

    /**
     * 获取keys
     */
    @RequestMapping(value = "/redisList/keys", method = RequestMethod.GET)
    Result keys(@RequestParam("key") String key);

    /**
     * 获取指定长度数据
     */
    @RequestMapping(value = "/redisList/zSetReverseRange", method = RequestMethod.GET)
    Result zSetReverseRange(@RequestParam("key") String key,
                            @RequestParam("start") Long start,
                            @RequestParam("end") Long end);

    /**
     * 获取指定元素分数
     *
     * @param key
     * @param member
     * @return
     */
    @RequestMapping(value = "/redisList/zSetScore", method = RequestMethod.GET)
    Result zSetScore(@RequestParam("key") String key,
                     @RequestParam("member") String member);
}
