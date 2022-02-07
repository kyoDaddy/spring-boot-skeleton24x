package com.kyo.basic.sample.repository;

import com.kyo.basic.sample.repository.entity.RedisAuthInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RedisAuthInfoRepository extends CrudRepository<RedisAuthInfo, Long> {

    Optional<RedisAuthInfo> findRedisAuthInfoByUniqueKey(String uniqueKey);

}
