package com.kyo.basic.process.controllers.mvc;

import com.kyo.basic.process.controllers.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SampleControllerTest extends AbstractControllerTest {
    @Autowired
    private SampleController sampleController;

    @Override
    protected Object controller() {
        return sampleController;
    }

    @Test
    public void sample() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/sample"))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(view().name("sample"))
                    .andExpect(forwardedUrl("classpath:/templates/sample.html"))
                    .andExpect(model().attribute("name", is("kyo"))
        );

    }

}
