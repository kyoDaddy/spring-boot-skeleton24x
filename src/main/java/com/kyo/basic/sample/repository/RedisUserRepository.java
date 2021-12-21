package com.kyo.basic.sample.repository;

import com.kyo.basic.sample.repository.dto.CoinUserDto;
import org.springframework.data.repository.CrudRepository;

public interface RedisUserRepository extends CrudRepository<CoinUserDto, String> {

}
