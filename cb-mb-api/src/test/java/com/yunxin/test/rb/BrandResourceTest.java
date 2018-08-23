package com.yunxin.test.rb;

import com.yunxin.cb.rest.mall.BrandResource;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
        commonMvcPerFormGet(url,"",MediaType.APPLICATION_JSON_VALUE,"SUCCESS",map);

    }
    @Test
    public void getHotBrand() throws Exception {
        log.info("查询所有品牌 V1 start");
        String url = "/v1/mall/brand/list";
        Map<String,Object> map = new HashMap<>();
        commonMvcPerFormPost(url,"",MediaType.APPLICATION_JSON_VALUE,"SUCCESS",map);

    }
}
