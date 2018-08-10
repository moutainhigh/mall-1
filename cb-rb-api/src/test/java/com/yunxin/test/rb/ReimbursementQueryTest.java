package com.yunxin.test.rb;

import com.yunxin.cb.rest.rb.ReimbursementQueryResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
@WebAppConfiguration

public class ReimbursementQueryTest {
    private static final Logger log= LoggerFactory.getLogger(ReimbursementQueryTest.class);

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception{
        mvc= MockMvcBuilders.standaloneSetup(new ReimbursementQueryResource()).build();
    }


    @Test
    public void getReimbursementTest()throws Exception{
        log.debug("查询可报账列表 V1 start");
        //mock进行模拟
        mvc.perform(MockMvcRequestBuilders.get("/V1/reimbursement/getReimbursement")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).param("pageNo", "1").param("pageSize","10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        log.debug("查询可报账列表 V1 end");
    }
}
