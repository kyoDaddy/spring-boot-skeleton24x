package com.kyo.basic.config.base;

import com.kyo.basic.config.properties.RedisProp;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 *
 * redis 연결 정의하는 클래스 작성
 *      -> RedisConnectionFactory를 통해 내장 혹은 외부의 Redis를 연결
 *      -> RedisTemplate을 통해 RedisConnection에서 넘겨준 byte 값을 객체 직렬화한다.
 *      -> 직접 서버 접근 : redis-cli -h 127.0.0.1 -p 6379 -a password
 *         접근 후 패턴에 따른 세션 조회 : keys spring:session*
 */
@RequiredArgsConstructor
@Configuration
@EnableRedisRepositories
public class RedisConfig {

    private final RedisProp redisProp;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisProp.getHost(), redisProp.getPort());
    }

    @Bean
    public RedisTemplate<?,?> redisTemplate() {
        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }


}
