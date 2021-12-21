package com.kyo.basic.sample.repository.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class InitDataDto {

    private String message;

    @Builder
    public InitDataDto(String message) {
        this.message = message;
    }

}
