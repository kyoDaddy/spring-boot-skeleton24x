package com.kyo.basic.sample.controller;


import com.kyo.basic.base.constant.CommonConstants;
import com.kyo.basic.base.constant.UrlConstants;
import com.kyo.basic.base.controller.response.CustomResponseEntity;
import com.kyo.basic.sample.repository.dto.AuthInfoDto;
import com.kyo.basic.sample.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(UrlConstants.AUTH_API_PRE)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 초기 사용자 정보 DB 등록
     */
    @PostMapping("/check")
    public CustomResponseEntity<AuthInfoDto> check(@RequestHeader(CommonConstants.REQ_HEADER_UNIQUE_KEY) String uniqueKey,
                                                   @RequestHeader Map<String, Object> requestHeader) {

        log.info("REQUEST HEADER : {}", requestHeader);
        return CustomResponseEntity.success(
                authService.check(uniqueKey)
        );
    }

}
