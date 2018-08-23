package com.yunxin.test.rb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.mall.SearchResource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * 搜索测试类
 * add by chenpeng 2018年8月22日
 */
public class SearchResourceTest extends MockHttpUtils{

    @Autowired
    private SearchResource searchResource;
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(searchResource).build();
    }

    @Test
    public void keywordSearch() throws Exception {
        log.info("商品关键字搜索 V1 start");

        String url = "/v1/mall/search/keywordSearch";
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("keyword", "车");
        map.add("page", "0");
        map.add("size", "10");
        map.add("lat", "18.257776");
        map.add("lon", "109.522771");
        String acceptStatus = Result.SUCCESS.name();
        commonMvcPerFormPost(url, "", MediaType.APPLICATION_FORM_URLENCODED_VALUE, acceptStatus, map);

        log.info("商品关键字搜索 V1 end ");
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

        String url = "/v1/mall/search/categorySearch";
        String acceptStatus = Result.SUCCESS.name();
        commonMvcPerFormPost(url, new ObjectMapper().writeValueAsString(jsonNode), MediaType.APPLICATION_JSON_UTF8_VALUE, acceptStatus, null);

        log.info("商品分类搜索 V1 end");
    }


    @Test
    public void selectAll() throws Exception {
        log.info("查询所有规格属性等 V1 start");

        String url = "/v1/mall/search/commodity";
        String acceptStatus = Result.SUCCESS.name();
        commonMvcPerFormGet(url, "", MediaType.APPLICATION_FORM_URLENCODED_VALUE, acceptStatus, null);

        log.info("查询所有规格属性等 V1 end");
    }

    @Test
    public void hotSearch() throws Exception {
        log.info("热门搜索 V1 start");

        String url = "/v1/mall/search/hotSearch";
        String acceptStatus = Result.SUCCESS.name();
        commonMvcPerFormGet(url, "", MediaType.APPLICATION_FORM_URLENCODED_VALUE, acceptStatus, null);

        log.info("热门搜索 V1 end ");
    }

    @Test
    public void specFilter() throws Exception {
        log.info("搜索条件-规格配置 V1 start");

        String url = "/v1/mall/search/specFilter";
        String acceptStatus = Result.SUCCESS.name();
        commonMvcPerFormGet(url, "", MediaType.APPLICATION_FORM_URLENCODED_VALUE, acceptStatus, null);

        log.info("搜索条件-规格配置 V1 end");
    }
}
