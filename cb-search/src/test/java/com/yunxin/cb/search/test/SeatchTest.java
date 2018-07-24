package com.yunxin.cb.search.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunxin.cb.search.vo.Commodity;

public class SeatchTest {

    public void addCommodity() throws JsonProcessingException {
        String url = "http://localhost:8168/search/mall/search/addCommodity";
        Commodity commodity = new Commodity();
        commodity.setCommodityName("南极人电热毯单人单控学生宿舍家用安全自动断电电褥子");
        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(commodity);
        System.out.println(url);
        System.out.println(body);
        String response = HttpUtils.doJsonPost(url, body);
        System.out.println(response);
    }

    public static void main(String[] args) throws JsonProcessingException {
        SeatchTest api = new SeatchTest();
        api.addCommodity();
    }
}
