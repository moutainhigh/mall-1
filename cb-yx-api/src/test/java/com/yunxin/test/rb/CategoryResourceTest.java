package com.yunxin.test.rb;

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

/**
* @Description:    CategoryResourceTest
* @Author:         likang
* @CreateDate:     2018/8/22 10:40
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class CategoryResourceTest extends MockHttpUtils{

    @Test
    public void carBrandTest() throws Exception {
        log.info("查询汽车品牌 start");
        String url="/v1/mall/category/list/carBrand";
        String content="";
        String contentType=MediaType.APPLICATION_FORM_URLENCODED_VALUE;
        String acceptStatus=Result.SUCCESS.name();
        commonMvcPerFormGet(url,content,contentType,acceptStatus,null);
        log.info("查询汽车品牌 end ");
    }

    @Test
    public void getCategoryByIdTest() throws Exception {
        log.info("根据汽车品牌分类查询车系 start");
        String url="/v1/mall/category/getCategoryById/1";
        String content="";
        String contentType=MediaType.APPLICATION_FORM_URLENCODED_VALUE;
        String acceptStatus=Result.SUCCESS.name();
        commonMvcPerFormGet(url,content,contentType,acceptStatus,null);
        log.info("根据汽车品牌分类查询车系 end ");
    }

    @Test
    public void getCategoryByBrandIdTest() throws Exception {
        log.info("根据汽车品牌ID查询车系 start");
        String url="/v1/mall/category/getCategoryByBrandId/279";
        String content="";
        String contentType=MediaType.APPLICATION_FORM_URLENCODED_VALUE;
        String acceptStatus=Result.SUCCESS.name();
        commonMvcPerFormGet(url,content,contentType,acceptStatus,null);
        log.info("根据汽车品牌ID查询车系 end ");
    }
}
