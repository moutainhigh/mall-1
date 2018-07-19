package com.yunxin.cb.mall.entity;

import java.util.List;

public class Index implements java.io.Serializable{


    private static final long serialVersionUID = -5757724781631355830L;
    private List<Advertisement> adList;
    private List<Brand> brandList;
    private List<Category> categoryList;
    private List<Commodity> commodityList;

    public List<Advertisement> getAdList() {
        return adList;
    }

    public void setAdList(List<Advertisement> adList) {
        this.adList = adList;
    }

    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Commodity> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<Commodity> commodityList) {
        this.commodityList = commodityList;
    }
}
