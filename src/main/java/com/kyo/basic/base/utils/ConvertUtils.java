package com.kyo.basic.base.utils;

public class ConvertUtils {

    public static String convert3CamelCase(String dashScore) {

        if(dashScore.indexOf('-') < 0 && Character.isLowerCase(dashScore.charAt(0))) return dashScore;

        StringBuilder result = new StringBuilder();

        boolean nextUpper = false;
        int len = dashScore.length();

        for(int i = 0; i < len; i++) {
            char currentChar = dashScore.charAt(i);
            if(currentChar == '-') {
                nextUpper = true;
            }
            else{

                if(nextUpper) {
                    result.append(Character.toUpperCase(currentChar));
                    nextUpper = false;
                }
                else {
                    result.append(Character.toLowerCase(currentChar));
                }

            }
        }

        return result.toString();

    }


}
