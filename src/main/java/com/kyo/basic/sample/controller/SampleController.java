package com.kyo.basic.sample.controller;

import com.kyo.basic.base.constant.UrlConstants;
import com.kyo.basic.base.controller.response.CustomResponseEntity;
import com.kyo.basic.base.utils.PrintUtils;
import com.kyo.basic.sample.service.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

@Slf4j
@RestController
@RequestMapping(UrlConstants.SAMPLE_API_PRE)
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;

    @GetMapping("/hello")
    public CustomResponseEntity<String> getHello(HttpServletRequest request) {
        PrintUtils.printRequest(request);
        return CustomResponseEntity.success("hi Bro~ :)");
    }


    /**
     * caffein 로컬 캐시 사용 테스틀를 위한 SAMPLE API
     */
    @GetMapping("/samples")
    public CustomResponseEntity<List<String>> getSamples() {
        log.info("info");
        log.error("error");
        log.debug("debug");
        return CustomResponseEntity.success(sampleService.getSamples());
    }


    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap, //하나의 키에 여러값을 받을 수 있다.
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false) String cookie
                          ){

        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);
        return "ok";
    }

}
