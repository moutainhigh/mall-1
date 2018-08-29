package com.yunxin.test.rb;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


public class ReimbursementTest extends MockHttpUtils{

    private static final Logger log = LoggerFactory.getLogger(ReimbursementTest.class);


    @Test
    public void getReimbursementTest() throws Exception {
        log.info("查询可报账列表 V1 start");
        String url = "/v1/reimbursement/getReimbursement";
        MultiValueMap<String,String> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("pageNo","1");
        paramMap.add("pageSize","10");
        commonMvcPerFormPost(url,null,MediaType.APPLICATION_JSON_UTF8_VALUE,"SUCCESS",paramMap);
    }

}
