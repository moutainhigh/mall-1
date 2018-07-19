package com.yunxin.cb.mall.entity;

import java.util.ArrayList;
import java.util.List;

public class FloorInfo implements java.io.Serializable{
    private static final long serialVersionUID = 7438002442390481635L;
    //第二层品牌
    List<Brand> brandList = new ArrayList<>();
    //第三层分类
    List<Category> categoryThreeList = new ArrayList<>();
    //第5层分类
    List<Category> categoryFiveList = new ArrayList<>();

    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }

    public List<Category> getCategoryThreeList() {
        return categoryThreeList;
    }

    public void setCategoryThreeList(List<Category> categoryThreeList) {
        this.categoryThreeList = categoryThreeList;
    }

    public List<Category> getCategoryFiveList() {
        return categoryFiveList;
    }

    public void setCategoryFiveList(List<Category> categoryFiveList) {
        this.categoryFiveList = categoryFiveList;
    }
}
