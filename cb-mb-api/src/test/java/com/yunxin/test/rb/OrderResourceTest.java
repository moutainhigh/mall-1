package com.yunxin.test.rb;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunxin.cb.Application;
import com.yunxin.cb.mall.entity.meta.PaymentType;
import com.yunxin.cb.mall.vo.OrderConfirmProductVO;
import com.yunxin.cb.mall.vo.OrderConfirmVO;
import com.yunxin.cb.meta.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@AutoConfigureMockMvc
@Transactional(transactionManager = "transactionManager")
@Rollback(value = true)
public class OrderResourceTest extends MockHttpUtils{

    @Test
    public void getTempOrderTest() throws Exception {
        log.info("获取预下单数据 V1 start");
        //mock进行模拟
        String url = "/v1/mall/order/tempOrder";
        String content = "";
        String contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE;
        String acceptStatus = Result.SUCCESS.name();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("productId","532");
        map.add("buyNum","1");
        map.add("paymentType","UNDER_LINE");
        commonMvcPerFormPost(url, content, contentType, acceptStatus, map);
        log.info("获取预下单数据 V1 end result : " + content);
    }

    @Test
    public void getTempOrderTest_productId() throws Exception {
        log.info("获取预下单数据 V1 start");
        //mock进行模拟
        String url = "/v1/mall/order/tempOrder";
        String content = "";
        String contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE;
        String acceptStatus = Result.FAILURE.name();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("productId","");
        map.add("buyNum","1");
        map.add("paymentType","UNDER_LINE");
        commonMvcPerFormPost(url, content, contentType, acceptStatus, map);
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
        orderConfirmProductVO.setProductId(532);
        orderConfirmProductVO.setProductNum(1);
        orderConfirmProductList.add(orderConfirmProductVO);
        orderConfirmVO.setOrderConfirmProductList(orderConfirmProductList);

        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        String url = "/v1/mall/order";
        String content = objectMapper.writeValueAsString(orderConfirmVO);
        String contentType = MediaType.APPLICATION_JSON_VALUE;
        String acceptStatus=Result.SUCCESS.name();
        commonMvcPerFormPost(url, content, contentType, acceptStatus,null);
        log.info("添加订单 V1 end result : " + content);
    }

    @Test
    public void getOrderListTest() throws Exception {
        log.info("查询订单列表 V1 start");
        //mock进行模拟
        String url = "/v1/mall/order/pageList";
        String content = "";
        String contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE;
        String acceptStatus = Result.SUCCESS.name();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("pageNo","1");
        map.add("pageSize","10");
        commonMvcPerFormPost(url, content, contentType, acceptStatus, map);
        log.info("查询订单列表 V1 end result : " + content);
    }

    @Test
    public void getOrderListTest_orderState() throws Exception {
        log.info("查询订单列表 V1 start");
        //mock进行模拟
        String url = "/v1/mall/order/pageList";
        String content = "";
        String contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE;
        String acceptStatus = Result.SUCCESS.name();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("pageNo","1");
        map.add("pageSize","10");
        map.add("orderState","PENDING_PAYMENT");
        commonMvcPerFormPost(url, content, contentType, acceptStatus, map);
        log.info("查询订单列表 V1 end result : " + content);
    }

    @Test
    public void getOrderTest() throws Exception {
        log.info("根据订单id查询订单详情 V1 start");
        //mock进行模拟
        String url = "/v1/mall/order/372";
        String content = "";
        String contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE;
        String acceptStatus = Result.SUCCESS.name();
        commonMvcPerFormGet(url, content, contentType, acceptStatus, null);
        log.info("根据订单id查询订单详情 V1 end result : " + content);
    }

    @Test
    public void cancelOrderTest() throws Exception {
        log.info("根据订单id取消订单 V1 start");
        //mock进行模拟
        String url = "/v1/mall/order/cancelOrder";
        String content = "";
        String contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE;
        String acceptStatus = Result.SUCCESS.name();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("orderId","403");
        map.add("cancelReason","取消测试");
        commonMvcPerFormPut(url, content, contentType, acceptStatus, map);
        log.info("根据订单id取消订单 V1 end result : " + content);
    }

    @Test
    public void confirmOrderTest() throws Exception {
        log.info("根据订单id确认收货 V1 start");
        //mock进行模拟
        String url = "/v1/mall/order/confirmOrder/404";
        String content = "";
        String contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE;
        String acceptStatus = Result.SUCCESS.name();
        commonMvcPerFormPut(url, content, contentType, acceptStatus, null);
    }

}
