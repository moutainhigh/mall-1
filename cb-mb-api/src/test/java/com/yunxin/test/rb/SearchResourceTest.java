package com.yunxin.test.rb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.yunxin.cb.Application;
import com.yunxin.cb.rest.mall.SearchResource;
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

/**
 * 搜索测试类
 * add by chenpeng 2018年8月22日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class SearchResourceTest {

    private static final Logger log = LoggerFactory.getLogger(SearchResourceTest.class);
    @Autowired
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private SearchResource searchResource;
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(searchResource).build();
    }

    @Test
    public void keywordSearch() throws Exception {
        log.info("商品关键字搜索 V1 start");
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.post("/v1/mall/search/keywordSearch")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                        .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiI4ODg4ODgiLCJleHAiOjE1MzU0MjcyMTMsImp0aSI6IjEifQ.NskhiSw4EO_JlWBqEkQJmXTHiFwQLXHy8GUZsouSpfUAGl5VXH4MhHbXgPbqvurk2AUuDk0az0wHcZhNbhTQpg")
                        .param("keyword","车")
                        .param("page","1").param("size","10")
                        .param("lat","18.257776").param("lon","109.522771"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("商品关键字搜索 V1 end result : " + content);
    }

    @Test
    public void categorySearch() throws Exception {
        log.info("商品分类搜索 V1 start");
        JsonNode jsonNode = JsonNodeFactory.instance.objectNode()
                .put("cityCode", "440300")
                .put("page", 0)
                .put("size", 10)
                .put("lat", 18.257776)
                .put("lon", 109.522771);

        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.post("/v1/mall/search/categorySearch")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiI4ODg4ODgiLCJleHAiOjE1MzU0MjcyMTMsImp0aSI6IjEifQ.NskhiSw4EO_JlWBqEkQJmXTHiFwQLXHy8GUZsouSpfUAGl5VXH4MhHbXgPbqvurk2AUuDk0az0wHcZhNbhTQpg")
                        .content(new ObjectMapper().writeValueAsString(jsonNode)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("商品分类搜索 V1 end result : " + content);
    }


    @Test
    public void selectAll() throws Exception {
        log.info("查询所有规格属性等 V1 start");
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.get("/v1/mall/search/commodity"))
//                        .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiI4ODg4ODgiLCJleHAiOjE1MzU0MjcyMTMsImp0aSI6IjEifQ.NskhiSw4EO_JlWBqEkQJmXTHiFwQLXHy8GUZsouSpfUAGl5VXH4MhHbXgPbqvurk2AUuDk0az0wHcZhNbhTQpg")
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("查询所有规格属性等 V1 end result : " + content);
    }

    @Test
    public void hotSearch() throws Exception {
        log.info("热门搜索 V1 start");
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.get("/v1/mall/search/hotSearch"))
//                        .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiI4ODg4ODgiLCJleHAiOjE1MzU0MjcyMTMsImp0aSI6IjEifQ.NskhiSw4EO_JlWBqEkQJmXTHiFwQLXHy8GUZsouSpfUAGl5VXH4MhHbXgPbqvurk2AUuDk0az0wHcZhNbhTQpg")
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("热门搜索 V1 end result : " + content);
    }

    @Test
    public void specFilter() throws Exception {
        log.info("搜索条件-规格配置 V1 start");
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.get("/v1/mall/search/specFilter"))
//                        .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiI4ODg4ODgiLCJleHAiOjE1MzU0MjcyMTMsImp0aSI6IjEifQ.NskhiSw4EO_JlWBqEkQJmXTHiFwQLXHy8GUZsouSpfUAGl5VXH4MhHbXgPbqvurk2AUuDk0az0wHcZhNbhTQpg")
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("搜索条件-规格配置 V1 end result : " + content);
    }
}
