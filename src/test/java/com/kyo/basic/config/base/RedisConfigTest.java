package com.kyo.basic.config.base;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * redis에서 사용되는 String, List, Sorted Set, Hash 데이터 타입별 테스트
 *
 */
@SpringBootTest
public class RedisConfigTest {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public void testString() {
        final String key = "testString";
        final ValueOperations<String, String> ops = redisTemplate.opsForValue();

        ops.set(key, "1");
        final String result1 = ops.get(key);
        System.out.println("result1 : " + result1);

        ops.increment(key);
        final String result2 = ops.get(key);
        System.out.println("result2 : " + result2);

        // redis-cli : GET testString
    }

    @Test
    public void testList() {
        final String key = "testList";
        final ListOperations<String, String> ops = redisTemplate.opsForList();

        ops.rightPush(key, "H");
        ops.rightPush(key, "E");
        ops.rightPush(key, "L");
        ops.rightPush(key, "L");
        ops.rightPush(key, "O");

        ops.rightPushAll(key, " ", "W", "O", "R", "L", "D");

        final String character1 = ops.index(key, 1);
        System.out.println("character1 : " + character1);

        final Long size = ops.size(key);
        System.out.println("size : " + size);

        final List<String> resultRange = ops.range(key, 0,10);

        assert resultRange != null;
        System.out.println("resultRange : " + Arrays.toString(resultRange.toArray()));

        // redis-cli
        // INDEX key index
        // LRANGE key start stop
    }

    @Test
    public void testSet() {
        final String key = "testSet";
        SetOperations<String, String> ops = redisTemplate.opsForSet();

        ops.add(key, "H");
        ops.add(key, "E");
        ops.add(key, "L");
        ops.add(key, "L");
        ops.add(key, "O");

        Set<String> test = ops.members(key);

        assert test != null;
        System.out.println("member : " + Arrays.toString(test.toArray()));

        final Long size = ops.size(key);
        System.out.println("size : " + size);

        Cursor<String> cursor = ops.scan(key, ScanOptions.scanOptions().match("*").count(3).build());

        while (cursor.hasNext()) {
            System.out.println("cursor : " + cursor.next());
        }

        // redis-cli
        // SMEMBERS key
    }

    @Test
    public void testSortedSet() {
        final String key = "testSortedSet";
        ZSetOperations<String, String> ops = redisTemplate.opsForZSet();

        ops.add(key, "H", 1);
        ops.add(key, "E", 5);
        ops.add(key, "L", 10);
        ops.add(key, "L", 15);
        ops.add(key, "O", 20);

        Set<String> range = ops.range(key, 0, 5);
        System.out.println("range : " + Arrays.toString(range.toArray()));

        final Long size = ops.size(key);
        System.out.println("size : " + size);

        Set<String> scoreRange = ops.rangeByScore(key, 0, 16);
        assert scoreRange != null;
        System.out.println("scoreRange : " + Arrays.toString(scoreRange.toArray()));

        // redis-cli
        // ZRANGE key start stop [WITHSCORES]
        // ZRANGEBYSCORE key min max [WITHSCORES]
    }

    @Test
    public void testHash() {
        final String key = "testHash";
        HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();

        ops.put(key, "Hello", "testHash");
        ops.put(key, "Hello2", "testHash2");
        ops.put(key, "Hello2", "testHash3");

        Object hello = ops.get(key, "Hello");
        System.out.println("hello : " + hello);

        Map<Object, Object> entries = ops.entries(key);
        System.out.println("entries : " + entries.get(key));

        Long size = ops.size(key);
        System.out.println("size : " + size);

        // redis-cli
        // HGET key field

    }

    // redis-cli 키 전체 삭제 명령어
    // flushAll

}
