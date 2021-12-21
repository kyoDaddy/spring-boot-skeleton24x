package com.kyo.basic.sample.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.kyo.basic.base.constant.CommonConstants;
import com.kyo.basic.base.controller.request.CommonRequestDto;
import com.kyo.basic.base.utils.AES256Utils;
import com.kyo.basic.sample.repository.dto.AuthInfoDto;
import com.kyo.basic.sample.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.kyo.basic.sample.controller.utils.ApiDocumentUtils.getDocumentRequest;
import static com.kyo.basic.sample.controller.utils.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class}) // junit5 필수
@WebMvcTest(MainController.class)
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost")
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Autowired
    protected ObjectMapper objectMapper;

    @BeforeEach
    void setup(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    @DisplayName("메인 정보 조회 테스트")
    void getInitData() throws Exception {
        // given
        String uniqueKey = "0000000000000001";
        String encKey = "e834acb95da6bf9f";
        String mappingId = "6ebc632e-98d1-46a4-8f2d-5b5b7e940899";

        AuthInfoDto authInfoDto = AuthInfoDto.builder()
                .mappingId(mappingId)
                .encKey(encKey)
                .build();

        given(authService.getAuthInfo(uniqueKey, mappingId))
                .willReturn(authInfoDto);

        // when
        CommonRequestDto dto = CommonRequestDto.builder()
                .mappingId(mappingId).build();
        AES256Utils utils = new AES256Utils(encKey);
        String data = objectMapper.writeValueAsString(dto);

        ResultActions resultActions =
                mockMvc.perform(post("/main/init-data")
                        .content(data)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .header(CommonConstants.REQ_HEADER_UNIQUE_KEY, uniqueKey))
                ;

        // then
        resultActions
                .andExpect(status().isOk())
                .andDo(print())
                /* rest-docs
                .andDo(
                        document(
                                "main/init-data",
                                getDocumentRequest(),
                                getDocumentResponse(),
                                responseFields(
                                        beneathPath("data").withSubsectionId("data"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("init data").optional()
                                )
                        )
                )*/
                // open-api
                .andDo(
                        document(
                                "main/init-data",
                                getDocumentRequest(),
                                getDocumentResponse(),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .description("메인 진입시 첫 호출하는 API로 메인 초기 데이터를 전달합니다.")
                                                .summary("main init data -> request json body -> encKey 값으로 평문 전체 암호화 후 전달")
                                                .requestFields(
                                                        fieldWithPath("mappingId").type(JsonFieldType.STRING).description("auth/check 시 전닯받은 mappingId 설정")
                                                )
                                                .responseFields(
                                                        fieldWithPath("code").type(JsonFieldType.NUMBER).description("code"),
                                                        fieldWithPath("message").type(JsonFieldType.STRING).description("message"),
                                                        fieldWithPath("data.message").type(JsonFieldType.STRING).description("init data").optional(),
                                                        fieldWithPath("debug").type(JsonFieldType.STRING).description("debug").optional()
                                                )
                                                .build()
                                )
                        )
                );

    }

}