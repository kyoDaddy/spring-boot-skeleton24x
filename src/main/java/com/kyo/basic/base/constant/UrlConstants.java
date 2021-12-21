package com.kyo.basic.base.constant;

import java.util.Arrays;
import java.util.List;

public class UrlConstants {

    public final static String DOCS = "/docs";

    public final static String SAMPLE_API_PRE = "/sample";
    public final static String AUTH_API_PRE = "/auth";
    public final static String MAIN_API_PRE = "/main";

    public final static List<String> EXCLUDE_PATH_PATTERNS = Arrays.asList(
            SAMPLE_API_PRE + "/**",
            "/docs/**",
            "/"
    );

    public final static String[] INCLUDE_PATH_PATTERNS_AUTH = {
            AUTH_API_PRE + "/*",
            MAIN_API_PRE + "/*"
    };

    public final static String[] INCLUDE_PATH_PATTERNS_DECRYPTION = {
            MAIN_API_PRE + "/*"
    };






}
