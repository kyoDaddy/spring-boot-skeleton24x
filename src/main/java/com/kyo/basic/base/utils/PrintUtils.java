package com.kyo.basic.base.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * 자주 사용하는 로깅 정보 유틸
 */
@Slf4j
public class PrintUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void printRequest(HttpServletRequest request) {

        log.info("[Request 정보]");
        log.info("method= " + request.getMethod()); //GET
        log.info("protocal= " + request.getProtocol()); //HTTP/1.1
        log.info("scheme= " + request.getScheme()); //http
        log.info("requestURL= " + request.getRequestURL());
        log.info("requestURI= " + request.getRequestURI());
        log.info("queryString= " + request.getQueryString());
        log.info("is secure= " + request.isSecure()); //https 사용 유무

        log.info("[Remote 정보]");
        log.info("remote host=" + request.getRemoteHost());
        log.info("remote addr=" + request.getRemoteAddr());
        log.info("remote port=" + request.getRemotePort());

        log.info("[Local 정보]");
        log.info("local name=" + request.getLocalName());
        log.info("local addr=" + request.getLocalAddr());
        log.info("local port=" + request.getLocalPort());

        log.info("[header 정보]");
        request.getHeaderNames().asIterator()
                .forEachRemaining(headerNames -> log.info(headerNames + "::" + request.getHeader(headerNames)));

        log.info("[Cookie 정보]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                log.info(cookie.getName() + ": " + cookie.getValue());
            }
        }

        log.info("[Content 편의 조회]");
        log.info("contentType= " + request.getContentType());
        log.info("contentLength= " + request.getContentLength());
        log.info("characterEncoding= " + request.getCharacterEncoding());

        log.info("[Parameter 조회]");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> log.info(paramName + "=" + request.getParameter(paramName)));

    }


    public static void printRequestBody(HttpServletRequest request) {

        try {
            ServletInputStream inputStream = request.getInputStream();
            String bodyString = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            log.info("bodyString = " + bodyString);

            if (request.getContentType().equals(MediaType.APPLICATION_JSON)) {
                JsonNode jsonNode = objectMapper.readValue(bodyString, JsonNode.class);
                log.info(jsonNode.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
