package com.kyo.basic.base.constant;

import java.util.Arrays;
import java.util.List;

public class CommonConstants {


    public static final String REQ_HEADER_UNIQUE_KEY = "unique-key";
    public static final String REQ_HEADER_PASS_ENCRYPTION = "pass-encryption";


    /** API REQUEST 헤더 필수값 */
    public final static List<String> REQ_HEADER_VALUE = Arrays.asList(
            CommonConstants.REQ_HEADER_UNIQUE_KEY
    );

}
