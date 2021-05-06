package com.kyo.basic.process.controllers.restful;


import com.google.gson.JsonObject;
import com.kyo.basic.process.controllers.AbstractControllerTest;
import com.kyo.basic.process.vo.validator.Gender;
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

public class RedisApiTest extends AbstractControllerTest {

    @Autowired
    private RedisApi redisApi;

    @Override
    protected Object controller() {
        return redisApi;
    }


    @Test
    public void get() throws Exception {

        String email = "rlarbghrbgh@gmail.com";

        mockMvc.perform(MockMvcRequestBuilders.get("/redis/get/" + email)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void save() throws Exception {

        JsonObject jObj = new JsonObject();
        jObj.addProperty("email", "rlarbghrbgh@gmail.com");
        jObj.addProperty("username", "kyo");
        jObj.addProperty("password", "123");
        jObj.addProperty("gender", "F");

        mockMvc.perform(MockMvcRequestBuilders.post("/redis/save")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jObj.toString()))
                .andExpect(status().isOk())
                .andDo(print());

    }



}
