package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Commodity;

/**
 * @title: 商品接口类
 * @auther: eleven
 * @date: 2018/7/18 17:40
 */
public interface CommodityService {

    /**
     * @title: 通过商品id查找商品
     * @param: [commodityId]
     * @return: com.yunxin.cb.mall.entity.Commodity
     * @auther: eleven
     * @date: 2018/7/19 10:40
     */
    public Commodity selectByPrimaryKey(int commodityId);
}
