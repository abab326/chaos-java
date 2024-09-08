package org.liushuxue.chaos.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    // 作用: 向 Redis 中存储一个键值对
    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // 作用: 从 Redis 中获取指定键的值
    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 作用: 向 Redis 中存储一个键值对，并设置其过期时间
    // timeout 指定时间量，timeUnit 指定时间单位
    public void setValueWithExpiry(String key, Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    // 作用: 从 Redis 中删除指定键及其对应的值
    public void deleteValue(String key) {
        redisTemplate.delete(key);
    }
}
