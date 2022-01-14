package com.kyo.basic.sample.controller;

import com.kyo.basic.base.constant.UrlConstants;
import com.kyo.basic.base.controller.response.CustomResponseEntity;
import com.kyo.basic.sample.service.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(UrlConstants.SAMPLE_API_PRE)
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;

    @GetMapping("/hello")
    public CustomResponseEntity<String> getHello() {
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


}
