package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.entity.Product;

import java.util.List;
import java.util.Map;

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

    /**
     * @title: 通过货品id查找商品详情（商品属性、规格、价格段等）
     * @param: [commodityId]
     * @return: com.yunxin.cb.mall.entity.Commodity
     * @auther: eleven
     * @date: 2018/7/19 10:40
     */
    public Map getCommdityDetail(int productId,int customerId);

    /**
     * @title: 通过商品ID查询所有货品
     * @param: [commodityId]
     * @return: java.util.List<com.yunxin.cb.mall.entity.Product>
     * @auther: eleven
     * @date: 2018/7/20 18:00
     */
    public List<Product> getProductsByCommodityId(int commodityId);
}
