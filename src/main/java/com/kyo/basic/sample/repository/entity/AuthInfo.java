package com.kyo.basic.sample.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "auth_info")
// 직렬화를 목적은 객체 네트워크 전송하거나 데이터베이스 저장하기 위해서, 마샬링,언마샬링해주기 위해 사용하는 것
public class AuthInfo implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "mapping_id")
    private String mappingId;

    @Column(nullable = false, name = "unique_key")
    private String uniqueKey;

    @Column(nullable = false, name = "enc_key")
    private String encKey;

    @Column(name = "enc_key_updated_at")
    private LocalDateTime encKeyUpdatedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void createdAt() {
        this.createdAt = LocalDateTime.now();
    }

    @Builder
    public AuthInfo(String mappingId, String uniqueKey, String encKey, LocalDateTime encKeyUpdatedAt) {
        this.mappingId = mappingId;
        this.uniqueKey = uniqueKey;
        this.encKey = encKey;
        this.encKeyUpdatedAt = encKeyUpdatedAt;
    }

}
