package com.kyo.basic.process.controllers.restful;

import com.kyo.basic.process.controllers.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class HelloApiTest extends AbstractControllerTest {

    @Autowired
    private HelloApi helloApi;

    @Override
    protected Object controller() {
        return helloApi;
    }

    @Test
    public void hello() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("keyword", "제목");
        params.add("offset", "1");
        params.add("limit", "5");

        mockMvc.perform(
                get("/")
                        .params(params)
        );
    }





}
