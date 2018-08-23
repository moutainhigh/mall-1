package com.yunxin.test.rb;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunxin.cb.Application;
import com.yunxin.cb.jwt.JwtUtil;
import com.yunxin.cb.mall.entity.meta.PaymentType;
import com.yunxin.cb.mall.vo.OrderConfirmProductVO;
import com.yunxin.cb.mall.vo.OrderConfirmVO;
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

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class OrderResourceTest {
    private static final Logger log = LoggerFactory.getLogger(OrderResourceTest.class);
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
    public void getTempOrderTest() throws Exception {
        log.info("获取预下单数据 V1 start");
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/v1/mall/order/tempOrder")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header(JwtUtil.HEADER_STRING,token)
                .param("productId","489")
                .param("buyNum","1")
                .param("paymentType","UNDER_LINE"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("获取预下单数据 V1 end result : " + content);
    }

    @Test
    public void addOrderTest() throws Exception {
        log.info("添加订单 V1 start");
        //mock进行模拟
        OrderConfirmVO orderConfirmVO = new OrderConfirmVO();
        orderConfirmVO.setPaymentType(PaymentType.UNDER_LINE);
        orderConfirmVO.setConsigneeName("张三");
        orderConfirmVO.setConsigneeMobile("13815695656");
        orderConfirmVO.setConsigneeAddress("深圳市");
        orderConfirmVO.setSellerId("1");
        List<OrderConfirmProductVO> orderConfirmProductList = new ArrayList<OrderConfirmProductVO>();
        OrderConfirmProductVO orderConfirmProductVO = new OrderConfirmProductVO();
        orderConfirmProductVO.setProductId(489);
        orderConfirmProductVO.setProductNum(1);
        orderConfirmProductList.add(orderConfirmProductVO);
        orderConfirmVO.setOrderConfirmProductList(orderConfirmProductList);

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/v1/mall/order")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header(JwtUtil.HEADER_STRING,token)
                .content(objectMapper.writeValueAsString(orderConfirmVO)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("添加订单 V1 end result : " + content);
    }

    @Test
    public void getOrderListTest() throws Exception {
        log.info("查询订单列表 V1 start");
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/v1/mall/order/pageList")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header(JwtUtil.HEADER_STRING,token)
                .param("pageNo","1")
                .param("pageSize","10"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("查询订单列表 V1 end result : " + content);
    }

    @Test
    public void getOrderTest() throws Exception {
        log.info("根据订单id查询订单详情 V1 start");
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/v1/mall/order/372")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header(JwtUtil.HEADER_STRING,token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("根据订单id查询订单详情 V1 end result : " + content);
    }

    @Test
    public void cancelOrderTest() throws Exception {
        log.info("根据订单id取消订单 V1 start");
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put("/v1/mall/order/cancelOrder")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header(JwtUtil.HEADER_STRING,token)
                .param("orderId","372")
                .param("cancelReason","取消测试"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("根据订单id取消订单 V1 end result : " + content);
    }

    @Test
    public void confirmOrderTest() throws Exception {
        log.info("根据订单id确认收货 V1 start");
        //mock进行模拟
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put("/v1/mall/order/confirmOrder/372")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header(JwtUtil.HEADER_STRING,token))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("根据订单id确认收货 V1 end result : " + content);
    }

}
