package com.yunxin.test.rb;

import com.yunxin.cb.rest.mall.BrandResource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class BrandResourceTest extends MockHttpUtils{
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
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        commonMvcPerFormGet(url,"",MediaType.APPLICATION_JSON_VALUE,"SUCCESS",map);

    }
    @Test
    public void getHotBrand() throws Exception {
        log.info("查询所有品牌 V1 start");
        String url = "/v1/mall/brand/list";
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        commonMvcPerFormPost(url,"",MediaType.APPLICATION_JSON_VALUE,"SUCCESS",map);

    }
}
