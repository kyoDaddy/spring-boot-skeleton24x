package com.kyo.basic.base.controller.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter

public class CommonRequestDto {

    private String mappingId;

    public CommonRequestDto() {

    }

    @Builder
    public CommonRequestDto(String mappingId) {
        this.mappingId = mappingId;
    }
}
