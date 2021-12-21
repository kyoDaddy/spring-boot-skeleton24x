package com.kyo.basic.sample.repository.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO(Data Transfer Object) : 계층(Layer)간 데이터 교환을 위해 사용하는 객체
 */
@RedisHash("coin-user")
@Getter @Setter
public class CoinUserDto {

    @Id
    private String id;
    private String npsn;
    private UUID accessToken;
    private LocalDateTime expireDt;

    @Builder
    public CoinUserDto(String id, String npsn, UUID accessToken, LocalDateTime expireDt) {
        this.id = id;
        this.npsn = npsn;
        this.accessToken = accessToken;
        this.expireDt = expireDt;
    }

}
