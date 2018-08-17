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

    /**
     * baner
     * @return
     */
    private List<AdvertisementVO> advertisementList;
    /**
     * 排序
     */
    private int sortOrder;
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

    public List<AdvertisementVO> getAdvertisementList() {
        return advertisementList;
    }

    public void setAdvertisementList(List<AdvertisementVO> advertisementList) {
        this.advertisementList = advertisementList;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }
}
