package com.kyo.basic.process.controllers.restful;

import com.google.gson.JsonObject;
import com.kyo.basic.process.controllers.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserApiTest extends AbstractControllerTest {

    @Autowired
    private UserApi userApi;

    @Override
    protected Object controller() {
        return userApi;
    }

    @Test
    public void getUser() throws Exception {

        JsonObject jObj = new JsonObject();
        jObj.addProperty("username", "kyo");
        jObj.addProperty("password", "123");
        jObj.addProperty("gender", "F");

        mockMvc.perform(MockMvcRequestBuilders.post("/user/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jObj.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("hi"))
                .andDo(print());

    }

}
