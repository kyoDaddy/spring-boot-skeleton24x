package com.kyo.basic.sample.repository;

import com.kyo.basic.sample.repository.entity.AuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthInfoRepository extends JpaRepository<AuthInfo, Long> {

    Optional<AuthInfo> getAuthInfoByUniqueKey(String uniqueKey);

}
