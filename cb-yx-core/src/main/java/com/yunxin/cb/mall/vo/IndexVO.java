package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
@ApiModel(value="首页",description="首页VO对象 IndexVO")
public class IndexVO implements java.io.Serializable{
    private static final long serialVersionUID = 3369062796042721490L;
    /**
     * 第一层-头部图片
     */
    @ApiModelProperty(value="第一层-头部图片",name="homeList",example="第一层-头部图片")
    private List<AdvertisementVO> homeList;
    /**
     * 第四层-中部图片
     */
    @ApiModelProperty(value="第四层-中部图片",name="milldeList",example="第四层-中部图片")
    private List<AdvertisementVO> milldeList;
    /**
     * 第二层-品牌
     */
    @ApiModelProperty(value="第二层-品牌",name="brandList",example="品牌")
    private List<BrandVO> brandList;
    /**
     * 第三层-分类
     */
    @ApiModelProperty(value="第三层-分类",name="categoryThreeList",example="第三层-分类")
    private List<CategoryVO> categoryThreeList;
    /**
     * 第五层-分类
     */
    @ApiModelProperty(value="第五层-分类",name="categoryFiveList",example="第五层-分类")
    private List<CategoryVO> categoryFiveList;

    public List<AdvertisementVO> getHomeList() {
        return homeList;
    }

    public void setHomeList(List<AdvertisementVO> homeList) {
        this.homeList = homeList;
    }

    public List<AdvertisementVO> getMilldeList() {
        return milldeList;
    }

    public void setMilldeList(List<AdvertisementVO> milldeList) {
        this.milldeList = milldeList;
    }

    public List<BrandVO> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<BrandVO> brandList) {
        this.brandList = brandList;
    }

    public List<CategoryVO> getCategoryThreeList() {
        return categoryThreeList;
    }

    public void setCategoryThreeList(List<CategoryVO> categoryThreeList) {
        this.categoryThreeList = categoryThreeList;
    }

    public List<CategoryVO> getCategoryFiveList() {
        return categoryFiveList;
    }

    public void setCategoryFiveList(List<CategoryVO> categoryFiveList) {
        this.categoryFiveList = categoryFiveList;
    }
}
