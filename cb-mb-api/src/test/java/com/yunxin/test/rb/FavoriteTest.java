package com.yunxin.test.rb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.yunxin.cb.Application;
import com.yunxin.cb.jwt.JwtUtil;
import com.yunxin.cb.mall.vo.FavoriteVo;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.mall.CommodityResource;
import com.yunxin.cb.rest.mall.FavoriteResource;
import com.yunxin.cb.util.LogicUtils;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class FavoriteTest extends MockHttpUtils {

    private static final Logger log = LoggerFactory.getLogger(HistoryRecordTest.class);
    @Autowired
    private WebApplicationContext context;
    @Before
    public void setUp() throws Exception {
        //MockMvcBuilders使用构建MockMvc对象   （项目拦截器有效）
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getCustomerFavoriteTest() throws Exception {
        log.info("获取用户收藏夹 start");
        String url="/v1/mall/favorite/getCustomerFavorite";
        String content="";
        String contentType=MediaType.APPLICATION_FORM_URLENCODED_VALUE;
        String acceptStatus=Result.SUCCESS.name();
        Map<String,Object> map=new HashMap<>();
        map.put("pageNo",1);
        map.put("pageSize",10);
        commonMvcPerFormPost(url,content,contentType,acceptStatus,map);
        log.info("获取用户收藏夹 end ");
    }

    @Test
    public void addFavoriteTest() throws Exception {
        log.info("商品添加收藏夹 start");
        FavoriteVo favoriteVo=new FavoriteVo();
        favoriteVo.setProductId(546);
        favoriteVo.setSalePrice(2000.88f);
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        String url="/v1/mall/favorite/addFavorite";
        String content=objectMapper.writeValueAsString(favoriteVo);
        String contentType=MediaType.APPLICATION_JSON_VALUE;
        String acceptStatus=Result.SUCCESS.name();
        commonMvcPerFormPost(url,content,contentType,acceptStatus,null);
        log.info("商品添加收藏夹 end ");
    }

    @Test
    public void delFavoritesTest() throws Exception {
        log.info("商品删除收藏夹 start");
        List<Integer> favoriteIds=new ArrayList<>();
        favoriteIds.add(415);
        String url="/v1/mall/favorite/delFavorites";
        String content=JSONObject.toJSONString(favoriteIds);
        String contentType=MediaType.APPLICATION_JSON_VALUE;
        String acceptStatus=Result.SUCCESS.name();
        commonMvcPerFormPost(url,content,contentType,acceptStatus,null);
        log.info("商品删除收藏夹 end ");
    }


}
