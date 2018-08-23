package com.yunxin.test.rb;

import com.yunxin.cb.rest.mall.IndexResource;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

public class IndexResourceTest extends MockHttpUtils{

    private static final Logger log = LoggerFactory.getLogger(IndexResourceTest.class);
    @Autowired
    private IndexResource indexResource;
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(indexResource).build();
    }
    @Test
    public void index() throws Exception {
        log.info("获取首页 V1 start");
        String url = "/v1/mall/index/getIndex";
        Map<String,Object> map = new HashMap<>();
        commonMvcPerFormGet(url,"",MediaType.APPLICATION_JSON_VALUE,"SUCCESS",map);
    }
}
