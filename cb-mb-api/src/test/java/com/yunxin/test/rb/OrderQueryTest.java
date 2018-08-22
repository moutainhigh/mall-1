package com.yunxin.test.rb;

import com.yunxin.cb.Application;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class OrderQueryTest {
    private static final Logger log = LoggerFactory.getLogger(OrderQueryTest.class);
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() throws Exception {
        //单个类  拦截器无效
//        mvc = MockMvcBuilders.standaloneSetup(historyRecordResource).build();

        //MockMvcBuilders使用构建MockMvc对象   （项目拦截器有效）
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


//    @Test
//    public void getOrderListSuccesTest() throws Exception {
//        log.info("查询订单列表 V1 start");
//        //mock进行模拟
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/v1/mall/order/pageList")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxODA4NjQ2MDAwMyIsImV4cCI6MTUzNTQ1MDY4NCwianRpIjoiNTMzIn0.FK3d6ofCa1kgr8wB1uyO50Xs7LqYmfLfLnvXnzSdA8TkMcLYl6CFaIPbG8q98W8fnYsiHm0zyr5m8fN9DSIfAg")
//                .param("pageNo","1").param("pageSize","10"))
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
//
//        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
//        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
//
//        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
//        log.info("查询订单列表 V1 end result : " + content);
//    }

    @Test
    public void getOrderListErrerTest() throws Exception {
        log.info("查询订单列表 V1 start");
        //mock进行模拟
        MultiValueMap<String,String> params = new LinkedMultiValueMap<String,String>();
        params.add("pageNo","1");
        params.add("pageSize","10");
        MvcResult mvcResult = req("/v1/mall/history/getCustomerHistoryRecord", params);
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("查询订单列表 V1 end result : " + content);
    }

    public MvcResult req (String url, MultiValueMap<String,String> params) throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxODA4NjQ2MDAwMyIsImV4cCI6MTUzNTQ1MDY4NCwianRpIjoiNTMzIn0.FK3d6ofCa1kgr8wB1uyO50Xs7LqYmfLfLnvXnzSdA8TkMcLYl6CFaIPbG8q98W8fnYsiHm0zyr5m8fN9DSIfAg")
                .params(params))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        return mvcResult;
    }
}
