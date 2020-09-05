package com.springboot.candyservice;

import com.jayway.jsonpath.JsonPath;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class BaseIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public final void setupMockMvc(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    public MockMvc getMockMvc() {return mockMvc;}

    public String getValueFromMvcResult(MvcResult mvcResult, String jsonPath)
            throws UnsupportedEncodingException {
        String json =
                mvcResult.getResponse().getContentAsString();
        return JsonPath.parse(json).read(jsonPath).toString();
    }
}
