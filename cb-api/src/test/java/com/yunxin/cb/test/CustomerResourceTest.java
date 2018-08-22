package com.yunxin.cb.test;

import com.alibaba.fastjson.JSONObject;
import com.yunxin.cb.Application;
import com.yunxin.cb.config.RestTokenFilter;
import com.yunxin.cb.mall.entity.Feedback;
import com.yunxin.cb.mall.vo.CustomerMatchsVo;
import com.yunxin.cb.mall.vo.CustomerUpdateVo;
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

    /**
     * 提交反馈
     * @throws Exception
     */
    @Test
    public void addFeedbackTest() throws Exception{
        log.info("提交反馈 start");
        Feedback feedback=new Feedback();
        feedback.setContent("看不破的永远是真相，道不清的永远是谎言");
        String requestJson = JSONObject.toJSONString(feedback);
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/customer/addFeedback")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzgyMzIyMTAyNyIsImV4cCI6MTUzNTQ0NjE2MiwianRpIjoiNTYzIn0.XVXzI_GKRzZ1x2_OW_P2sfhlKfPbTxYDglQO6EPxePQMi281RIZXVg353Yl38qtx2X9t-aq6rFVb4kB7h6PpQQ").content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                //断言，判断返回代码是否正确
        log.info("提交反馈 end result : " + content);
    }

    /**
     * 添加黑名单
     * @throws Exception
     */
    @Test
    public void addBlacklistTest()  throws Exception{
        log.info("添加黑名单 start");
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/customer/addBlacklist/310")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzgyMzIyMTAyNyIsImV4cCI6MTUzNTQ0NjE2MiwianRpIjoiNTYzIn0.XVXzI_GKRzZ1x2_OW_P2sfhlKfPbTxYDglQO6EPxePQMi281RIZXVg353Yl38qtx2X9t-aq6rFVb4kB7h6PpQQ"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                //断言，判断返回代码是否正确
        log.info("添加黑名单 end result : " + content);
    }

    /**
     * 移除黑名单
     * @throws Exception
     */
    @Test
    public void removeBlacklist()  throws Exception{
        log.info("移除黑名单 start");
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/customer/removeBlacklist/310")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzgyMzIyMTAyNyIsImV4cCI6MTUzNTQ0NjE2MiwianRpIjoiNTYzIn0.XVXzI_GKRzZ1x2_OW_P2sfhlKfPbTxYDglQO6EPxePQMi281RIZXVg353Yl38qtx2X9t-aq6rFVb4kB7h6PpQQ"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                //断言，判断返回代码是否正确
        log.info("移除黑名单 end result : " + content);
    }

    /**
     * 获取黑名单
     * @throws Exception
     */
    @Test
    public void getBlacklist()  throws Exception{
        log.info("获取黑名单 start");
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/customer/getBlacklist")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzgyMzIyMTAyNyIsImV4cCI6MTUzNTQ0NjE2MiwianRpIjoiNTYzIn0.XVXzI_GKRzZ1x2_OW_P2sfhlKfPbTxYDglQO6EPxePQMi281RIZXVg353Yl38qtx2X9t-aq6rFVb4kB7h6PpQQ"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                //断言，判断返回代码是否正确
        log.info("获取黑名单 end result : " + content);
    }
    /**
     * 用户点赞
     * @throws Exception
     */
    @Test
    public void praiseTest()  throws Exception{
        log.info("用户点赞 start");
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/customer/praise")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzgyMzIyMTAyNyIsImV4cCI6MTUzNTQ0NjE2MiwianRpIjoiNTYzIn0.XVXzI_GKRzZ1x2_OW_P2sfhlKfPbTxYDglQO6EPxePQMi281RIZXVg353Yl38qtx2X9t-aq6rFVb4kB7h6PpQQ"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                //断言，判断返回代码是否正确
        log.info("用户点赞 end result : " + content);
    }


    /**
     * 查询点赞用户
     * @throws Exception
     */
    @Test
    public void getPraiseCustomerTest()  throws Exception{
        log.info("查询点赞用户 start");
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/customer/getPraiseCustomer")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzgyMzIyMTAyNyIsImV4cCI6MTUzNTQ0NjE2MiwianRpIjoiNTYzIn0.XVXzI_GKRzZ1x2_OW_P2sfhlKfPbTxYDglQO6EPxePQMi281RIZXVg353Yl38qtx2X9t-aq6rFVb4kB7h6PpQQ"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                //断言，判断返回代码是否正确
        log.info("查询点赞用户 end result : " + content);
    }

    /**
     * 更新用户信息
     * @throws Exception
     */
    @Test
    public void updateCustomerTest()  throws Exception{

        log.info("更新用户信息 start");
        CustomerUpdateVo customerUpdateVo=new CustomerUpdateVo();
        customerUpdateVo.setRealName("张三丰");
        String jsonString= JSONObject.toJSONString(customerUpdateVo);
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/customer/updateCustomer")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzgyMzIyMTAyNyIsImV4cCI6MTUzNTQ0NjE2MiwianRpIjoiNTYzIn0.XVXzI_GKRzZ1x2_OW_P2sfhlKfPbTxYDglQO6EPxePQMi281RIZXVg353Yl38qtx2X9t-aq6rFVb4kB7h6PpQQ").content(jsonString))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                //断言，判断返回代码是否正确
        log.info("更新用户信息 end result : " + content);
    }

    /**
     * 匹配通讯录
     * @throws Exception
     */
    @Test
    public void matchAddressBookTest()  throws Exception{

        log.info("匹配通讯录 start");
        CustomerMatchsVo[] customerUpdateVo=new CustomerMatchsVo[2];
        CustomerMatchsVo customerMatchsVos=new CustomerMatchsVo();
        customerMatchsVos.setMobile("13823221029");
        customerMatchsVos.setRealName("大表哥");
        customerUpdateVo[0]=customerMatchsVos;

        CustomerMatchsVo customerMatchsVo=new CustomerMatchsVo();
        customerMatchsVo.setMobile("13163317878");
        customerMatchsVo.setRealName("一块大哥");
        customerUpdateVo[1]=customerMatchsVo;

        String  str= JSONObject.toJSONString(customerUpdateVo);
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/customer/matchAddressBook")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzgyMzIyMTAyNyIsImV4cCI6MTUzNTQ0NjE2MiwianRpIjoiNTYzIn0.XVXzI_GKRzZ1x2_OW_P2sfhlKfPbTxYDglQO6EPxePQMi281RIZXVg353Yl38qtx2X9t-aq6rFVb4kB7h6PpQQ").content(str))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                //断言，判断返回代码是否正确
        log.info("匹配通讯录 end result : " + content);
    }

    /**
     * 获取感恩统计
     * @throws Exception
     */
    @Test
    public void getGratitudeTest() throws Exception{
        log.info("获取感恩统计 start");
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/customer/getGratitude")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzgyMzIyMTAyNyIsImV4cCI6MTUzNTQ0NjE2MiwianRpIjoiNTYzIn0.XVXzI_GKRzZ1x2_OW_P2sfhlKfPbTxYDglQO6EPxePQMi281RIZXVg353Yl38qtx2X9t-aq6rFVb4kB7h6PpQQ"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                //断言，判断返回代码是否正确
        log.info("获取感恩统计 end result : " + content);
    }

    /**
     * 获取感恩列表
     * @throws Exception
     */
    @Test
    public void getGratitudeDataTest() throws Exception{
        log.info("获取感恩列表 start");
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/customer/getGratitudeData/GRATITUDEME")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzgyMzIyMTAyNyIsImV4cCI6MTUzNTQ0NjE2MiwianRpIjoiNTYzIn0.XVXzI_GKRzZ1x2_OW_P2sfhlKfPbTxYDglQO6EPxePQMi281RIZXVg353Yl38qtx2X9t-aq6rFVb4kB7h6PpQQ"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                //断言，判断返回代码是否正确
        log.info("获取感恩列表 end result : " + content);
    }

    /**
     * 我的个人统计
     * @throws Exception
     */
    @Test
    public void getInterpersonalTest() throws Exception{
        log.info("我的个人统计 start");
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/customer/getInterpersonal")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzgyMzIyMTAyNyIsImV4cCI6MTUzNTQ0NjE2MiwianRpIjoiNTYzIn0.XVXzI_GKRzZ1x2_OW_P2sfhlKfPbTxYDglQO6EPxePQMi281RIZXVg353Yl38qtx2X9t-aq6rFVb4kB7h6PpQQ"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                //断言，判断返回代码是否正确
        log.info("我的个人统计 end result : " + content);
    }
}
