package com.kyo.basic.sample.repository.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthInfoDto {

    private String mappingId;

    private String encKey;

    @Builder
    public AuthInfoDto(String mappingId, String encKey) {
        this.mappingId = mappingId;
        this.encKey = encKey;
    }

}
