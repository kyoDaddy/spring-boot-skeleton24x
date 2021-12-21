package com.kyo.basic.sample.controller;

import com.epages.restdocs.apispec.ResourceSnippetParameters;

import com.kyo.basic.sample.service.AuthService;
import com.kyo.basic.sample.service.SampleService;
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
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class}) // junit5 필수
@WebMvcTest(SampleController.class)
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "locahost")
class SampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SampleService sampleService;

    @MockBean
    private AuthService authService;

    @BeforeEach
    void setup(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    @DisplayName("hello")
    void hello() throws Exception {

        // given

        // when
        ResultActions resultActions =
                mockMvc.perform(get("/sample/hello")
                        //.contentType(MediaType.APPLICATION_JSON)
                        //.accept(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8));

        // then
        resultActions
                .andExpect(status().isOk())
                .andDo(print())
                /* rest-docs
                .andDo(
                        document(
                                "sample/hello",
                                getDocumentRequest(),
                                getDocumentResponse(),
                                responseFields(
                                        fieldWithPath("code").type(JsonFieldType.NUMBER).description("code"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("message"),
                                        fieldWithPath("data").type(JsonFieldType.STRING).description("data"),
                                        fieldWithPath("debug").type(JsonFieldType.STRING).description("debug").optional()
                                )
                        )
                )*/
                .andDo(
                        // open-api
                        document(
                                "sample/hello",
                                getDocumentRequest(),
                                getDocumentResponse(),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .description("무인증 테스트를 위한 API 입니다.")
                                                .summary("hello")
                                                .responseFields(
                                                        fieldWithPath("code").type(JsonFieldType.NUMBER).description("code"),
                                                        fieldWithPath("message").type(JsonFieldType.STRING).description("message"),
                                                        fieldWithPath("data").type(JsonFieldType.STRING).description("data"),
                                                        fieldWithPath("debug").type(JsonFieldType.STRING).description("debug").optional()
                                                )
                                                .build()
                                )
                        )
                );

    }


}