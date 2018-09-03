package com.yunxin.test.rb;

import com.alibaba.fastjson.JSONObject;
import com.yunxin.cb.mall.vo.AddReimbursementRequestVO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;


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

    @Test
    public void addReimbursement()throws Exception{
        log.info("报账 V1 start");
        String url = "/v1/reimbursement/addReimbursement";
        List<AddReimbursementRequestVO> list = new ArrayList<>();
        AddReimbursementRequestVO addReimbursementRequestVO = new AddReimbursementRequestVO();
        addReimbursementRequestVO.setReimbursementQueryId(3);
        list.add(addReimbursementRequestVO);
        String deadVO = JSONObject.toJSONString(list);
        commonMvcPerFormPost(url,deadVO,MediaType.APPLICATION_JSON_UTF8_VALUE,"SUCCESS",null);
    }

    @Test
    public void getAlreadyReimbursement()throws Exception{
        log.info("已报账列表查询 V1 start");
        String url = "/v1/reimbursement/getAlreadyReimbursement";
        MultiValueMap<String,String> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("pageNo","1");
        paramMap.add("pageSize","10");
        commonMvcPerFormPost(url,null,MediaType.APPLICATION_JSON_UTF8_VALUE,"SUCCESS",paramMap);
    }

    @Test
    public void getAlreadyReimbursementDetail()throws Exception{
        log.info("已报账详情 V1 start");
        String url = "/v1/reimbursement/getAlreadyReimbursementDetail/97";
        commonMvcPerFormGet(url,null,MediaType.APPLICATION_JSON_UTF8_VALUE,"SUCCESS",null);
    }

    @Test
    public void getCompleteReimbursement()throws Exception{
        log.info("报账已完成列表查询 V1 start");
        String url = "/v1/reimbursement/getCompleteReimbursement";
        MultiValueMap<String,String> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("pageNo","1");
        paramMap.add("pageSize","10");
        commonMvcPerFormPost(url,null,MediaType.APPLICATION_JSON_UTF8_VALUE,"SUCCESS",paramMap);
    }

    @Test
    public void getCompleteReimbursementDetail()throws Exception{
        log.info("报账已完成详情 V1 start");
        String url = "/v1/reimbursement/getCompleteReimbursementDetail/67";
        commonMvcPerFormGet(url,null,MediaType.APPLICATION_JSON_UTF8_VALUE,"SUCCESS",null);
    }

    @Test
    public void cancelReimbursement()throws Exception{
        log.info("取消报账  V1 start");
        String url = "/v1/reimbursement/cancelReimbursement/68";
        commonMvcPerFormGet(url,null,MediaType.APPLICATION_JSON_UTF8_VALUE,"SUCCESS",null);
    }
}
