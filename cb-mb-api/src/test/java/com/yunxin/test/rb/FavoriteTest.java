package com.yunxin.test.rb;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunxin.cb.Application;
import com.yunxin.cb.mall.vo.FavoriteVo;
import com.yunxin.cb.meta.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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
public class FavoriteTest extends MockHttpUtils {

    @Test
    public void getCustomerFavoriteTest() throws Exception {
        log.info("获取用户收藏夹 start");
        String url="/v1/mall/favorite/getCustomerFavorite";
        String content="";
        String contentType=MediaType.APPLICATION_FORM_URLENCODED_VALUE;
        String acceptStatus=Result.SUCCESS.name();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("pageNo","1");
        map.add("pageSize","10");
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
