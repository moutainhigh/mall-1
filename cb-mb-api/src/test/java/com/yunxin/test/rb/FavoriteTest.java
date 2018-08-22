package com.yunxin.test.rb;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.yunxin.cb.Application;
import com.yunxin.cb.rest.mall.CommodityResource;
import com.yunxin.cb.rest.mall.FavoriteResource;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @Title:收藏夹测试类
 * @Auther: eleven
 * @Date: 2018/8/21 16:50
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class FavoriteTest {

    private static final Logger log = LoggerFactory.getLogger(HistoryRecordTest.class);
    @Autowired
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private FavoriteResource favoriteResource;
    @Before
    public void setUp() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(favoriteResource).build();
        //MockMvcBuilders使用构建MockMvc对象   （项目拦截器有效）
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getCustomerFavoriteTest() throws Exception {
        log.info("获取用户收藏夹 V1 start");
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.post("/v1/mall/favorite/getCustomerFavorite")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiI4ODg4ODgiLCJleHAiOjE1MzU0MjcyMTMsImp0aSI6IjEifQ.NskhiSw4EO_JlWBqEkQJmXTHiFwQLXHy8GUZsouSpfUAGl5VXH4MhHbXgPbqvurk2AUuDk0az0wHcZhNbhTQpg")
                .param("pageNo","1").param("pageSize","10"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("获取用户收藏夹 V1 end result : " + content);
    }

    @Test
    public void addFavoriteTest() throws Exception {
        log.info("商品添加收藏夹 V1 start");
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("productId",546);
        jsonObject.put("salePrice",288888);
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.post("/v1/mall/favorite/addFavorite")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiI4ODg4ODgiLCJleHAiOjE1MzU0MjcyMTMsImp0aSI6IjEifQ.NskhiSw4EO_JlWBqEkQJmXTHiFwQLXHy8GUZsouSpfUAGl5VXH4MhHbXgPbqvurk2AUuDk0az0wHcZhNbhTQpg")
                .content(jsonObject.toJSONString()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("商品添加收藏夹 V1 end result : " + content);
    }

    @Test
    public void delFavoritesTest() throws Exception {
        log.info("商品移出收藏夹(批量) V1 start");
        List<Integer> favoriteIds=new ArrayList<>();
        favoriteIds.add(415);
        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.post("/v1/mall/favorite/delFavorites")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiI4ODg4ODgiLCJleHAiOjE1MzU0MjcyMTMsImp0aSI6IjEifQ.NskhiSw4EO_JlWBqEkQJmXTHiFwQLXHy8GUZsouSpfUAGl5VXH4MhHbXgPbqvurk2AUuDk0az0wHcZhNbhTQpg")
                .content(JSONObject.toJSONString(favoriteIds)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("商品移出收藏夹(批量) V1 end result : " + content);
    }
}
