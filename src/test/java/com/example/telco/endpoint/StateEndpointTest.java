package com.example.telco.endpoint;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class StateEndpointTest {

    @Autowired
    WebApplicationContext context;

    @Autowired
    MockMvc mvc;

    @Test
    public void testPostAndShowSessionValue() throws Exception {
        //init
        String sample = "hello world";
        MockHttpSession session = new MockHttpSession();
        //push a value to the server
        //have it persist in the session
        mvc.perform(post("/state").content(sample).accept(MediaType.APPLICATION_JSON).session(session)).andExpect(status().isCreated());
        //test for it
        assertNotNull(session.getAttribute("sessionWord"));
        //hit again and get the value back
        mvc.perform(get("/state").accept(MediaType.TEXT_PLAIN).session(session)).andExpect(status().isOk()).andExpect(content().string(sample));
    }

    @Test
    public void testNothingHasBeenPosted() throws Exception {
        String testString = "nothing has been set yet";
        MockHttpSession session = new MockHttpSession();
        mvc.perform(get("/state").accept(MediaType.TEXT_PLAIN).session(session)).andExpect(status().isOk()).andExpect(content().string(testString));
    }
}
