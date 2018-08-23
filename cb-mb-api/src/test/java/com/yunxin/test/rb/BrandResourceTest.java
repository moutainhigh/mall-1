package com.yunxin.test.rb;

import com.alibaba.fastjson.JSONObject;
import com.yunxin.cb.Application;
import com.yunxin.cb.mall.entity.meta.AddressType;
import com.yunxin.cb.mall.vo.DeliveryAddressVO;
import com.yunxin.cb.rest.mall.BrandResource;
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

import java.util.HashMap;
import java.util.Map;

public class BrandResourceTest extends MockHttpUtils{
    private static final Logger log = LoggerFactory.getLogger(BrandResourceTest.class);
    @Autowired
    private BrandResource brandResource;
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(brandResource).build();
    }
    @Test
    public void getBrand() throws Exception {
        log.info("查询所有品牌 V1 start");
        String url = "/v1/mall/brand/list";
        Map<String,Object> map = new HashMap<>();
        commonMvcPerFormGet(url,"",MediaType.APPLICATION_JSON_VALUE,200,map);

    }
    @Test
    public void getHotBrand() throws Exception {
        log.info("查询所有品牌 V1 start");
        String url = "/v1/mall/brand/list";
        Map<String,Object> map = new HashMap<>();
        commonMvcPerFormPost(url,"",MediaType.APPLICATION_JSON_VALUE,200,map);

    }
}
