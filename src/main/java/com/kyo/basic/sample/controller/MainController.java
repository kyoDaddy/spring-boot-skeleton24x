package com.kyo.basic.sample.controller;


import com.kyo.basic.base.constant.CommonConstants;
import com.kyo.basic.base.constant.UrlConstants;
import com.kyo.basic.base.controller.request.CommonRequestDto;
import com.kyo.basic.base.controller.response.CustomResponseEntity;
import com.kyo.basic.sample.repository.dto.AuthInfoDto;
import com.kyo.basic.sample.repository.dto.InitDataDto;
import com.kyo.basic.sample.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(UrlConstants.MAIN_API_PRE)
@RequiredArgsConstructor
public class MainController {

    private final AuthService authService;

    /**TODO
     * 메인 진입 정보 전달
     *      o filter에서 복호화된 requestBody 가 잘 매핑되는지 확인
     *      o requestBody 내 mappingId 로 조회한 npsn 과 header npsn 동일한지 유효성 1차 검증하기
     */
    @PostMapping("/init-data")
    public CustomResponseEntity<InitDataDto> initData(@RequestHeader(CommonConstants.REQ_HEADER_UNIQUE_KEY) String uniqueKey,
                                                      @RequestBody CommonRequestDto commonRequestDto) {
        AuthInfoDto authInfo = authService.getAuthInfo(uniqueKey, commonRequestDto.getMappingId());
        return CustomResponseEntity.success(new InitDataDto("init data " + authInfo.getMappingId()));
    }

}
