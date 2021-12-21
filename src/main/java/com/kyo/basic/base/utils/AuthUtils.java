package com.kyo.basic.base.utils;

import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;

public class AuthUtils {

    /**
     * 클라이언트 IP 전달
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {

        String ip = null;
        String[] checkHeaderType = {"X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};

        try {

            for(String checkHeader : checkHeaderType) {
                if(ObjectUtils.isEmpty(ip)) {
                    ip = request.getHeader(checkHeader);
                }
                else {
                    break;
                }
            }
            if(ObjectUtils.isEmpty(ip)) ip = request.getRemoteAddr();

        }
        catch (Exception e) {
            e.printStackTrace();
            ip = null;
        }

        return ip;
    }

}
