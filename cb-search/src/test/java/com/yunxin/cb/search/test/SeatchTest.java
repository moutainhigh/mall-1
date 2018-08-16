package com.yunxin.cb.search.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunxin.cb.search.document.Commodity;
import com.yunxin.cb.search.vo.Brand;
import com.yunxin.cb.search.vo.PriceSection;
import com.yunxin.cb.search.vo.Seller;
import com.yunxin.cb.search.vo.meta.SellerType;

import java.util.Date;

public class SeatchTest {

    public void addCommodity() throws JsonProcessingException {
        String url = "http://localhost:8168/search/mall/search/commodity";
        Commodity commodity = new Commodity();
        commodity.setId("5");
        commodity.setCommodityId(5);
        commodity.setCommodityName("保时捷911");
        commodity.setCommodityCode("201807242011");
        Brand brand = new Brand();
        brand.setBrandId(235);
        brand.setBrandEnName("Porsche");
        brand.setBrandName("保时捷");
        brand.setBrandNo("5366122");
        brand.setBrandTitle("保时捷");
        brand.setPicPath("//1459827630399.jpg");
        commodity.setBrand(brand);
        commodity.setCity("350100");
        commodity.setCommodityPYName("diancijiaredianyalifanbao");
        commodity.setCommodityTitle("保时捷911");
        commodity.setDescription("保时捷911");
        commodity.setCreateTime(new Date());
        commodity.setMarketPrice(99.99);
        commodity.setDefaultPicPath("//1459827630399.jpg");
        commodity.setPopular(true);
        PriceSection priceSection = new PriceSection();
        priceSection.setEndPrice(199);
        priceSection.setStartPrice(0);
        commodity.setPriceSection(priceSection);
        commodity.setProvince("350000");
        commodity.setRecommend(true);
        commodity.setSaleNum(30);
        commodity.setSellPrice(129);
        commodity.setShortName("55555");
        Seller seller = new Seller();
        seller.setSellerId(2);
        seller.setSellerCode("6854351");
        seller.setSellerName("测试");
        seller.setSellerType(SellerType.SELF_OPERATION);
        commodity.setSeller(seller);
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
