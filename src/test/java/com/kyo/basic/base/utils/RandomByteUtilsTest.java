package com.kyo.basic.base.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class RandomByteUtilsTest {

    @Test
    void makeKeyTest() {
        String encKey = RandomByteUtils.makeKey();
        assertNotNull(encKey);
        assertSame(encKey.length(), 16);
    }

}