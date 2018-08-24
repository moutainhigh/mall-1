package com.yunxin.cb.test;

import com.alibaba.fastjson.JSONObject;
import com.yunxin.cb.Application;
import com.yunxin.cb.mall.vo.CustomerVo;
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
public class RegisterTest {
    private static final Logger log = LoggerFactory.getLogger(RegisterTest.class);
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    private String code;
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
//    @Test
//    public void sendMobileValidCode() throws Exception{
//        log.info("发送验证码 start");
//        String mobile="13823221027";
//        //mock进行模拟
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/noAuth/sendMobileValidCode/REGISTER/"+mobile)
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
//        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
//
//        Assert.assertEquals(200, status);                //断言，判断返回代码是否正确
//        log.info("发送验证码 end result : " + content);
//        code=content;
//    }

    @Test
    public void registerTest() throws Exception {
        log.info("注册用户 start");

        CustomerVo customerVo=new CustomerVo();
        customerVo.setMobile("13823221027");
        customerVo.setPwd("123456");
        customerVo.setInvitationCode("CCCCCn");
        customerVo.setCode(code);

        String requestJson = JSONObject.toJSONString(customerVo);
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/noAuth/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果

        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("注册用户 end result : " + content);
    }
}
