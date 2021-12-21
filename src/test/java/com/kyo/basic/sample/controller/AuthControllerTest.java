package com.kyo.basic.sample.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.kyo.basic.base.constant.CommonConstants;
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
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.kyo.basic.sample.controller.utils.ApiDocumentUtils.getDocumentRequest;
import static com.kyo.basic.sample.controller.utils.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith({RestDocumentationExtension.class, SpringExtension.class}) // junit5 필수
@WebMvcTest(AuthController.class)
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost")
class AuthControllerTest {

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
    @DisplayName("사용자 유효성 확인 테스트")
    void check() throws Exception {

        String uniqueKey = "0000000000000001";

        // given
        AuthInfoDto authInfoDto = AuthInfoDto.builder()
                .mappingId(UUID.randomUUID().toString())
                .build();

        given(authService.check(any()))
        .willReturn(authInfoDto);

        // when
        ResultActions resultActions =
                mockMvc.perform(post("/auth/check")
                        //.contentType(MediaType.APPLICATION_JSON)
                        //.accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .header(CommonConstants.REQ_HEADER_UNIQUE_KEY, uniqueKey));

        // then
        resultActions
                .andExpect(status().isOk())
                .andDo(print())
                /*.andDo(
                        // rest-docs
                        document(
                                "auth/check",
                                getDocumentRequest(),
                                getDocumentResponse(),
                                responseFields(
                                        beneathPath("data").withSubsectionId("data"),
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("사용자아이디").optional(),
                                        fieldWithPath("mappingId").type(JsonFieldType.STRING).description("사용자아이디").optional()
                                )
                        )
                )*/
                .andDo(
                        // open-api
                        document(
                                "auth/check",
                                getDocumentRequest(),
                                getDocumentResponse(),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .description("사용자의 유효성을 확인합니다.")
                                                .summary("사용자 인증")
                                                .responseFields(
                                                        fieldWithPath("code").description("code"),
                                                        fieldWithPath("message").description("message"),
                                                        fieldWithPath("data.encKey").description("encryption key").optional(),
                                                        fieldWithPath("data.mappingId").description("npsn mapping-id").optional(),
                                                        fieldWithPath("debug").description("debug message").optional()
                                                )
                                                .build()
                                )
                        )
                );
    }


}