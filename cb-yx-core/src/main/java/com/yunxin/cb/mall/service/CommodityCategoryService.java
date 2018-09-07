package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.CommodityCategory;

import java.util.List;

/**
 * @title: 商品分类关联表 服务接口类
 * @auther: eleven
 * @date: 2018/7/18 18:01
 */
public interface CommodityCategoryService{
    /**
     * 根据分类ID查询
     * @param categoryId
     * @return
     */
    List<CommodityCategory> selectByCategoryId(Integer categoryId);

}
