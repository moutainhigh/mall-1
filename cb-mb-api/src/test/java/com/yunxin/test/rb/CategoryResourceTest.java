package com.yunxin.test.rb;

import com.yunxin.cb.Application;
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
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class CategoryResourceTest {

    private static final Logger log = LoggerFactory.getLogger(HistoryRecordTest.class);
    @Autowired
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void carBrandTest() throws Exception {
        log.info("查询汽车品牌 V1 start");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/v1/mall/category/list/carBrand")
                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiI4ODg4ODgiLCJleHAiOjE1MzU0MjcyMTMsImp0aSI6IjEifQ.NskhiSw4EO_JlWBqEkQJmXTHiFwQLXHy8GUZsouSpfUAGl5VXH4MhHbXgPbqvurk2AUuDk0az0wHcZhNbhTQpg")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("查询汽车品牌 V1 end result : " + content);
    }

    @Test
    public void getCategoryByIdTest() throws Exception {
        log.info("根据汽车品牌分类查询车系 V1 start");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/v1/mall/category/getCategoryById/1")
                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiI4ODg4ODgiLCJleHAiOjE1MzU0MjcyMTMsImp0aSI6IjEifQ.NskhiSw4EO_JlWBqEkQJmXTHiFwQLXHy8GUZsouSpfUAGl5VXH4MhHbXgPbqvurk2AUuDk0az0wHcZhNbhTQpg")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("根据汽车品牌分类查询车系 V1 end result : " + content);
    }

    @Test
    public void getCategoryByBrandIdTest() throws Exception {
        log.info("根据汽车品牌ID查询车系 V1 start");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/v1/mall/category/getCategoryByBrandId/1")
                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiI4ODg4ODgiLCJleHAiOjE1MzU0MjcyMTMsImp0aSI6IjEifQ.NskhiSw4EO_JlWBqEkQJmXTHiFwQLXHy8GUZsouSpfUAGl5VXH4MhHbXgPbqvurk2AUuDk0az0wHcZhNbhTQpg")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("根据汽车品牌ID查询车系 V1 end result : " + content);
    }
}
