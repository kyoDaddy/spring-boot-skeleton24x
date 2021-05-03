package com.kyo.basic.process.controllers.restful;

import com.google.gson.JsonObject;
import com.kyo.basic.process.controllers.AbstractControllerTest;
import com.kyo.basic.process.controllers.restful.HelloApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    public void getUser() throws Exception {

        JsonObject jObj = new JsonObject();
        jObj.addProperty("username", "kyo");
        jObj.addProperty("password", "123");
        jObj.addProperty("gender", "F");

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jObj.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("hi"))
                .andDo(print());
        ;

    }



}
