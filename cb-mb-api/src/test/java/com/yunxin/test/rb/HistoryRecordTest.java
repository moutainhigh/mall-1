package com.yunxin.test.rb;

import com.alibaba.fastjson.JSONObject;
import com.yunxin.cb.Application;
import com.yunxin.cb.meta.Result;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
* @Description:    HistoryRecordTest
* @Author:         likang
* @CreateDate:     2018/8/22 10:40
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class HistoryRecordTest extends MockHttpUtils{

    @Test
    public void getCustomerHistoryRecordTest() throws Exception {
        log.info("获取用户我的浏览 start");
        String url="/v1/mall/history/getCustomerHistoryRecord";
        String content="";
        String contentType=MediaType.APPLICATION_FORM_URLENCODED_VALUE;
        String acceptStatus=Result.SUCCESS.name();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("pageNo","1");
        map.add("pageSize","10");
        commonMvcPerFormPost(url,content,contentType,acceptStatus,map);
        log.info("获取用户我的浏览 end ");
    }

    @Test
    public void delHistoryRecords() throws Exception {
        log.info("商品移出我的浏览 start");
        String url="/v1/mall/history/delHistoryRecords";
        String content="";
        String contentType=MediaType.APPLICATION_FORM_URLENCODED_VALUE;
        List<Integer> historyRecordIds = new ArrayList<>();
        historyRecordIds.add(1);
        content = JSONObject.toJSONString(historyRecordIds);
        commonMvcPerFormPost(url,content,MediaType.APPLICATION_JSON_VALUE,"SUCCESS",null);
        log.info("商品移出我的浏览 end ");
    }

}
