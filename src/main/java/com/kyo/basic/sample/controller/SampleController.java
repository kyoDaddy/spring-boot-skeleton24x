package com.kyo.basic.sample.controller;

import com.kyo.basic.base.constant.UrlConstants;
import com.kyo.basic.base.controller.request.CommonRequestDto;
import com.kyo.basic.base.controller.response.CustomResponseEntity;
import com.kyo.basic.base.utils.PrintUtils;
import com.kyo.basic.sample.service.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
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
    public CustomResponseEntity headers(HttpServletRequest request,
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
        return CustomResponseEntity.success("ok");
    }


    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public CustomResponseEntity modelAttributeV1(@ModelAttribute CommonRequestDto commonRequestDto) {
        log.info("mappingId={}", commonRequestDto.getMappingId());
        return CustomResponseEntity.success("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream,
                StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    /**
     * httpEntity 외에 requestEntity/responseEntity 사용할수 있음
     * @param httpEntity
     * @return
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) {
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);
        return new HttpEntity<>("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public CustomResponseEntity requestBodyStringV4(@RequestBody String messageBody) {
        log.info("messageBody={}", messageBody);
        return CustomResponseEntity.success("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public CustomResponseEntity requestBodyJsonV3(@RequestBody CommonRequestDto data) {
        log.info("mappingId={}", data.getMappingId());
        return CustomResponseEntity.success("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public CustomResponseEntity requestBodyJsonV4(HttpEntity<CommonRequestDto> httpEntity) {
        CommonRequestDto data = httpEntity.getBody();
        log.info("mappingId={}", data.getMappingId());
        return CustomResponseEntity.success("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public CustomResponseEntity requestBodyJsonV5(@RequestBody CommonRequestDto data) {
        log.info("mappingId={}", data.getMappingId());
        return CustomResponseEntity.success("ok");
    }





}
