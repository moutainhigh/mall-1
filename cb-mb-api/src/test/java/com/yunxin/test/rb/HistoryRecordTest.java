package com.yunxin.test.rb;

import com.yunxin.cb.Application;
import com.yunxin.cb.rest.mall.HistoryRecordResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class HistoryRecordTest {
    private static final Logger log = LoggerFactory.getLogger(HistoryRecordTest.class);
    @Autowired
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private HistoryRecordResource historyRecordResource;
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(historyRecordResource).build();
    }

    @Test
    public void getExpectbillTest() throws Exception {
        log.info("获取用户我的浏览 V1 start");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/v1/mall/history/getCustomerHistoryRecord")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED).param("pageNo","1").param("pageSize","10"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("获取用户我的浏览 V1 end result : " + content);
    }
}