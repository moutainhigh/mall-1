package com.yunxin.test.rb;

import com.yunxin.cb.Application;
import com.yunxin.cb.jwt.JwtUtil;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.mall.CommodityResource;
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

import java.util.HashMap;
import java.util.Map;

/**
 * @Title:商品测试类
 * @Auther: eleven
 * @Date: 2018/8/21 16:05
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class CommodityTest extends MockHttpUtils {

    private static final Logger log = LoggerFactory.getLogger(HistoryRecordTest.class);
    @Autowired
    private WebApplicationContext context;
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getCommdityDetailTest() throws Exception {
        log.info("通过货品ID查询商品详情 start");
        String url="/v1/mall/commodity/getCommdityDetail/"+546;
        String content="";
        String contentType=MediaType.APPLICATION_FORM_URLENCODED_VALUE;
        String acceptStatus=Result.SUCCESS.name();
        commonMvcPerFormGet(url,content,contentType,acceptStatus,null);
        log.info("通过货品ID查询商品详情 end ");
    }

    @Test
    public void getProductsByCommodityIdTest() throws Exception {
        log.info("通过商品ID查询所有货品属性 start");
        String url="/v1/mall/commodity/getProductsByCommodityId/"+457;
        String content="";
        String contentType=MediaType.APPLICATION_FORM_URLENCODED_VALUE;
        String acceptStatus=Result.SUCCESS.name();
        commonMvcPerFormGet(url,content,contentType,acceptStatus,null);
        log.info("通过商品ID查询所有货品属性 end ");
    }

    @Test
    public void hotCityTest() throws Exception {
        log.info("查询热门城市 start");
        String url="/v1/mall/commodity/hotCity/";
        String content="";
        String contentType=MediaType.APPLICATION_FORM_URLENCODED_VALUE;
        String acceptStatus=Result.SUCCESS.name();
        commonMvcPerFormGet(url,content,contentType,acceptStatus,null);
        log.info("查询热门城市 end ");
    }
}
