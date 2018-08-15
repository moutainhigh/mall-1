package com.yunxin.cb.mall.vo;

import java.util.List;

public class HomeFloorVO implements java.io.Serializable {
    /**
     * 品牌
     */
    private List<BrandVO> brand;
    /**
     * 分类
     */
    private List<CategoryVO> category;

    /**
     * 商品
     * @return
     */
    private List<IndexCommodityVO> indexCommodityList;

    public List<BrandVO> getBrand() {
        return brand;
    }

    public void setBrand(List<BrandVO> brand) {
        this.brand = brand;
    }

    public List<CategoryVO> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryVO> category) {
        this.category = category;
    }

    public List<IndexCommodityVO> getIndexCommodityList() {
        return indexCommodityList;
    }

    public void setIndexCommodityList(List<IndexCommodityVO> indexCommodityList) {
        this.indexCommodityList = indexCommodityList;
    }
}
