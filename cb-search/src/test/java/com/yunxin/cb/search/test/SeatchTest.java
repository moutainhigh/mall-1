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
        String url = "http://localhost:8168/search/mall/search/addCommodity";
        Commodity commodity = new Commodity();
        commodity.setCommodityId(2);
        commodity.setCommodityName("电磁加热电压力饭煲");
        commodity.setCommodityCode("201807242026");
        Brand brand = new Brand();
        brand.setBrandId(169);
        brand.setBrandEnName("Weloop");
        brand.setBrandName("Weloop唯乐");
        brand.setBrandNo("53662");
        brand.setBrandTitle("Weloop唯乐");
        brand.setPicPath("//1459827630399.jpg");
        commodity.setBrand(brand);
        commodity.setCity("350100");
        commodity.setCommodityPYName("diancijiaredianyalifanbao");
        commodity.setCommodityTitle("电磁加热电压力饭煲");
        commodity.setDescription("保温 蒸煮 煲汤 煮粥 ");
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
