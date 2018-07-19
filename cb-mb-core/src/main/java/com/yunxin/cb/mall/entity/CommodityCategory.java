package com.yunxin.cb.mall.entity;

import org.springframework.stereotype.Repository;

/**
 * @title: 商品分类关联表实体类
 * @auther: eleven
 * @date: 2018/7/18 17:40
 */
public class CommodityCategory {
    /**  */
    private Integer cocaId;

    /** 推荐值，值越大，超靠前 */
    private Integer recommendValue;

    /** 商品分类 */
    private Integer categoryId;

    /** 商品 */
    private Integer commodityId;

    public Integer getCocaId() {
        return cocaId;
    }

    public void setCocaId(Integer cocaId) {
        this.cocaId = cocaId;
    }

    public Integer getRecommendValue() {
        return recommendValue;
    }

    public void setRecommendValue(Integer recommendValue) {
        this.recommendValue = recommendValue;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }
}