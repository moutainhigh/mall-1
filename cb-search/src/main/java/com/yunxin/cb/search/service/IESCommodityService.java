package com.yunxin.cb.search.service;

import com.yunxin.cb.search.vo.CommodityEs;

import java.util.List;

public interface IESCommodityService {
    public void addCommodity();

    public Iterable<CommodityEs> findAll();

    public List<CommodityEs> findByCommodityName(String commodityName);
}
