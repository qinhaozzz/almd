package com.lim.springboot.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author qinhao
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloControllerTest {

    private MockMvc mock;
    private MultiValueMap<String, String> params;

    @Before
    public void init() {
        mock = MockMvcBuilders.standaloneSetup(new HelloController()).build();
        params = new LinkedMultiValueMap<>();
        params.add("username", "qinhaozzz");
    }

    @Test
    public void test() throws Exception {
        MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.get("/hello").params(params))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(mvcResult);
    }
}