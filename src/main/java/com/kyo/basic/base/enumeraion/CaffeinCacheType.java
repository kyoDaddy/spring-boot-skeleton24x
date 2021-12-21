package com.kyo.basic.base.enumeraion;

import lombok.Getter;

@Getter
public enum CaffeinCacheType {

    SAMPLES("samples", 60, 10000);

    CaffeinCacheType(String cacheName, int expiredAfterWrite, int maximumSize) {
        this.cacheName = cacheName;
        this.expiredAfterWrite = expiredAfterWrite;
        this.maximumSize = maximumSize;
    }

    private String cacheName;
    private int expiredAfterWrite;
    private int maximumSize;

}
