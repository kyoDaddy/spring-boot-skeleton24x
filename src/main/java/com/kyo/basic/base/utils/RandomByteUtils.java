package com.kyo.basic.base.utils;

import java.util.Random;

public class RandomByteUtils {

    public static String makeKey() {
        byte[] arr = new byte[8];
        new Random().nextBytes(arr);
        return convertBytesToHex(arr);
    }

    private static String convertBytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte temp : bytes) {
            result.append(String.format("%02x", temp));
        }
        return result.toString();
    }


}
