package org.liushuxue.chaos.config;

import org.liushuxue.chaos.manager.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object,Object> redisTemplate = new RedisTemplate<>();
         redisTemplate.setConnectionFactory(redisConnectionFactory);
         FastJsonRedisSerializer<Object> fastjsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
         redisTemplate.setKeySerializer(new StringRedisSerializer());
         redisTemplate.setValueSerializer(fastjsonRedisSerializer);

         redisTemplate.setHashKeySerializer(new StringRedisSerializer());
         redisTemplate.setHashValueSerializer(fastjsonRedisSerializer);

         redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
