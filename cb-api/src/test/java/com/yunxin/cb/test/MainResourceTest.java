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

/**
 * wangteng  用户测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class MainResourceTest {

    private static final Logger log = LoggerFactory.getLogger(MainResourceTest.class);
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    /**
     * 注册发送短信验证码
     * @throws Exception
     */
    @Test
    public void sendMobileValidCode() throws Exception{
        log.info("注册发送短信验证码 start");
        String mobile="13823221027";
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/v1/noAuth/sendMobileValidCode/REGISTER/"+mobile)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                //断言，判断返回代码是否正确
        log.info("注册发送短信验证码 end result : " + content);
    }

    /**
     * 注册用户
     * @throws Exception
     */
    @Test
    public void registerTest() throws Exception {
        log.info("注册用户 start");

        CustomerVo customerVo=new CustomerVo();
        customerVo.setMobile("13823221027");
        customerVo.setPwd("123456");
        customerVo.setInvitationCode("223YHJ");
        customerVo.setCode("191122");

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

    /**
     * 用户名密码登录
     * @throws Exception
     */
    @Test
    public void loginByPwdTest() throws Exception {
        log.info("用户名密码登录 start");
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/v1/noAuth/loginByPwd")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).param("accountName","13823221020").param("password","123456"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果

        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("用户名密码登录 end result : " + content);

    }


    /**
     * 登陆发送短信验证码
     * @throws Exception
     */
    @Test
    public void sendMobileValidCodes() throws Exception{
        log.info("登陆发送短信验证码 start");
        String mobile="13823221027";
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/noAuth/sendMobileValidCode/LOGIN/"+mobile)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                //断言，判断返回代码是否正确
        log.info("登陆发送短信验证码 end result : " + content);
    }


    /**
     * 手机号验证码登录
     * @throws Exception
     */
    @Test
    public void loginByCodeTest()  throws Exception{
        log.info("手机号验证码登录 start");
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/noAuth/loginByCode")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).param("mobile","13823221027").param("code","439976"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果

        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("手机号验证码登录 end result : " + content);
    }

    /**
     * 通过邀请码查询推荐人手机号和邀请人名称
     * @throws Exception
     */
    @Test
    public void recommendCustomerTest() throws Exception{
        log.info("通过邀请码查询推荐人手机号和邀请人名称 start");
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/noAuth/recommendCustomer/223YGE")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果

        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("通过邀请码查询推荐人手机号和邀请人名称 end result : " + content);
    }

    /**
     * 重置密码发送短信验证码
     * @throws Exception
     */
    @Test
    public void sendMobileValidCodeByUpdate() throws Exception{
        log.info("重置密码发送短信验证码 start");
        String mobile="13823221027";
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/noAuth/sendMobileValidCode/FORGET_PASSWORD/"+mobile)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                //断言，判断返回代码是否正确
        log.info("重置密码发送短信验证码 end result : " + content);
    }

    /**
     * 重置密码
     * @throws Exception
     */
    @Test
    public void resetPwd() throws Exception{
        log.info("重置密码 start");
        String mobile="13823221027";
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/noAuth/resetPwd/"+mobile)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).param("code","572957").param("resetNewPwd","654321").param("mobile",mobile))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果

        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("重置密码 end result : " + content);
    }

}
