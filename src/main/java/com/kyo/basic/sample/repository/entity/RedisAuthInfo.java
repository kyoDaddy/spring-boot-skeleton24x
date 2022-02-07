package com.kyo.basic.sample.repository.entity;

import com.kyo.basic.base.constant.RedisKeyConstants;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO(Data Transfer Object) : 계층(Layer)간 데이터 교환을 위해 사용하는 객체
 */
@RedisHash(value = RedisKeyConstants.REDIS_AUTH_INFO_KEY, timeToLive = RedisKeyConstants.REDIS_AUTH_INFO_EXPIRED_TIME)
@Getter @Setter
public class RedisAuthInfo {

    @Id
    @Column(nullable = false, name = "mappingId")
    private String id;
    @Indexed
    private String uniqueKey;
    private String billingId;
    private String encKey;
    private LocalDateTime encKeyUpdatedAt;
    private LocalDateTime createdAt;

    @PrePersist
    public void createdAt() {
        this.createdAt = LocalDateTime.now();
    }

    @Builder
    public RedisAuthInfo(String id, String uniqueKey, String billingId, String encKey, LocalDateTime encKeyUpdatedAt, LocalDateTime createdAt) {
        this.id = id;
        this.uniqueKey = uniqueKey;
        this.billingId = billingId;
        this.encKey = encKey;
        this.encKeyUpdatedAt = encKeyUpdatedAt;
        this.createdAt = createdAt;
    }

}
