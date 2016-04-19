package com.springapp.mvc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import com.springapp.mvc.config.AppConfig;
import com.springapp.mvc.service.JdbcCustomerService;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= AppConfig.class)
public class AppTests {
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    protected JdbcCustomerService jdbcCustomerService;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void simple() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void insertAndLoadMsisdn(){
        jdbcCustomerService.insert("6975717951");
        List<String> msidsnLsit = jdbcCustomerService.findByMsisdn("6975717951");
        Assert.assertEquals(msidsnLsit.get(0), "6975717951");
    }

    @Test
    public void postCustomer() throws Exception {
        mockMvc.perform(post("/api/customer")
                .content("6975717952"))
                .andExpect(status().isOk())
                .andExpect(content().string("SUCCESS"));
    }
}