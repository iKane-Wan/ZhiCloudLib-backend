package com.kane.utils;

import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 包名: om.kane.utils
 * 说明：Redis工具类
 * 创建时间： 2025-12-15
 */
@Component
@ConditionalOnBean(RedisTemplate.class)
public class RedisUtils {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 设置键值对
     * @param key 键
     * @param value 值
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置带过期时间的键值对
     * @param key 键
     * @param value 值
     * @param timeout 过期时间
     * @param timeUnit 时间单位
     */
        public void setx(String key, Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 根据键获取值
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除指定键
     * @param key 键
     */
    public void del(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 判断键是否存在
     * @param key 键
     * @return true-存在，false-不存在
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }


    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }
}
