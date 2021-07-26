package com.roshi.controller;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * test for rest controller / endpoints
 *
 * ref: https://www.youtube.com/watch?v=8S8o46avgAw
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloResourceTest {

    private MockMvc mockMvc;

    @InjectMocks
    private HelloResource helloResource;


    @Before
    public void setUp() {
        // creates in-memory spring mvc builder i.e. server kind of setup
        mockMvc = MockMvcBuilders.standaloneSetup(helloResource)
                .build();
    }

    @Test
    public void testHelloWorld() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello rishi :)"));
    }

    @Test
    public void testHelloWorldJson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello/json").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("rishi")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("what's up")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(2)));
    }

}