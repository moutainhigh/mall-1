package com.yunxin.cb.test;

import com.yunxin.cb.Application;
import com.yunxin.cb.config.RestTokenFilter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
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
public class CustomerResourceTest {
    private static final Logger log = LoggerFactory.getLogger(MainResourceTest.class);
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).addFilter(new RestTokenFilter(), "/*").build();
    }
    /**
     * 查询客户基本信息
     * @throws Exception
     */
    @Test
    public void getCustomerInfoTest() throws Exception{
        log.info("查询客户基本信息 start");
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzgyMzIyMTAyNyIsImV4cCI6MTUzNTQ0NjE2MiwianRpIjoiNTYzIn0.XVXzI_GKRzZ1x2_OW_P2sfhlKfPbTxYDglQO6EPxePQMi281RIZXVg353Yl38qtx2X9t-aq6rFVb4kB7h6PpQQ");
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/customer/getCustomerInfo")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzgyMzIyMTAyNyIsImV4cCI6MTUzNTQ0NjE2MiwianRpIjoiNTYzIn0.XVXzI_GKRzZ1x2_OW_P2sfhlKfPbTxYDglQO6EPxePQMi281RIZXVg353Yl38qtx2X9t-aq6rFVb4kB7h6PpQQ"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                //断言，判断返回代码是否正确
        log.info("查询客户基本信息 end result : " + content);
    }


}
