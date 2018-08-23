package com.yunxin.test.rb;

import com.yunxin.cb.rest.mall.IndexResource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class IndexResourceTest extends MockHttpUtils{

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
        commonMvcPerFormGet(url,"",MediaType.APPLICATION_JSON_VALUE,"SUCCESS",null);
    }
}
