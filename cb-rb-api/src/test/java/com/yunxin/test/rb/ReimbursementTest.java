package com.yunxin.test.rb;

import com.yunxin.cb.rest.rb.ReimbursementQueryResource;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;


public class ReimbursementTest extends MockHttpUtils{

    private static final Logger log = LoggerFactory.getLogger(ReimbursementTest.class);

    @Before
    public void setUp(){
        mvc = MockMvcBuilders.standaloneSetup(new ReimbursementQueryResource()).build();
    }

    @Test
    public void getReimbursementTest() {
        log.info("查询可报账列表 V1 start");
        String url = "/v1/reimbursement/getReimbursement";
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("pageNo","1");
        paramMap.put("pageSize","10");
        try {
            commonMvcPerFormPost(url,paramMap,MediaType.APPLICATION_JSON_UTF8_VALUE,200);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void addReimbursementTest(){
        log.info("查询可报账列表 V1 start");
        String url = "/v1/reimbursement/addReimbursement";
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("orderId","1");
        paramMap.put("productId","2");
        paramMap.put("commodityId","3");
        try {
            commonMvcPerFormPost(url,paramMap,MediaType.APPLICATION_JSON_UTF8_VALUE,200);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
