package com.yunxin.cb.mall.entity;

import java.util.List;

public class Index implements java.io.Serializable{


    private static final long serialVersionUID = -5757724781631355830L;
    private List<Advertisement> homeList;
    private List<Advertisement> milldeList;
    private FloorInfo brand;
    private FloorInfo categoryThree;
    private FloorInfo categoryFive;

    public List<Advertisement> getHomeList() {
        return homeList;
    }

    public void setHomeList(List<Advertisement> homeList) {
        this.homeList = homeList;
    }

    public List<Advertisement> getMilldeList() {
        return milldeList;
    }

    public void setMilldeList(List<Advertisement> milldeList) {
        this.milldeList = milldeList;
    }

    public FloorInfo getBrand() {
        return brand;
    }

    public void setBrand(FloorInfo brand) {
        this.brand = brand;
    }

    public FloorInfo getCategoryThree() {
        return categoryThree;
    }

    public void setCategoryThree(FloorInfo categoryThree) {
        this.categoryThree = categoryThree;
    }

    public FloorInfo getCategoryFive() {
        return categoryFive;
    }

    public void setCategoryFive(FloorInfo categoryFive) {
        this.categoryFive = categoryFive;
    }
}
