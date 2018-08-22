package com.yunxin.test.rb;

import com.yunxin.cb.Application;
import com.yunxin.cb.jwt.JwtUtil;
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
public class PayResourceTest {
    private static final Logger log = LoggerFactory.getLogger(PayResourceTest.class);
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    private String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxODA4NjQ2MDAwMyIsImV4cCI6MTUzNTQ1MDY4NCwianRpIjoiNTMzIn0.FK3d6ofCa1kgr8wB1uyO50Xs7LqYmfLfLnvXnzSdA8TkMcLYl6CFaIPbG8q98W8fnYsiHm0zyr5m8fN9DSIfAg";

    @Before
    public void setUp() throws Exception {
        //MockMvcBuilders使用构建MockMvc对象   （项目拦截器有效）
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void appPay() throws Exception {
        log.info("app支付 V1 start");
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/v1/mall/appPay")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header(JwtUtil.HEADER_STRING,token)
                .param("orderId","489")
                .param("channcelType","ALIPAY"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("app支付 V1 end result : " + content);
    }

}
