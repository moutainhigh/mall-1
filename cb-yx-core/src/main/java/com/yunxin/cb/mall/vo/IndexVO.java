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
     * 第二层-运营分类
     */
    @ApiModelProperty(value="第二层-运营分类",name="brandList",example="运营分类")
    private List<CategoryVO> categoryFiveList;

    /**
     * 第三层-品牌精选
     */
    @ApiModelProperty(value="第三层-品牌精选",name="brandChoiceVOList",example="第三层-品牌精选")
    private List<BrandChoiceVO> brandChoiceList;

    /**
     * 第四层-精品推荐
     */
    @ApiModelProperty(value="第四层-精品推荐",name="recommendationVOList",example="第四层-精品推荐")
    private List<RecommendationVO> recommendationList;

    public List<AdvertisementVO> getHomeList() {
        return homeList;
    }

    public void setHomeList(List<AdvertisementVO> homeList) {
        this.homeList = homeList;
    }

    public List<CategoryVO> getCategoryFiveList() {
        return categoryFiveList;
    }

    public void setCategoryFiveList(List<CategoryVO> categoryFiveList) {
        this.categoryFiveList = categoryFiveList;
    }

    public List<BrandChoiceVO> getBrandChoiceList() {
        return brandChoiceList;
    }

    public void setBrandChoiceList(List<BrandChoiceVO> brandChoiceList) {
        this.brandChoiceList = brandChoiceList;
    }

    public List<RecommendationVO> getRecommendationList() {
        return recommendationList;
    }

    public void setRecommendationList(List<RecommendationVO> recommendationList) {
        this.recommendationList = recommendationList;
    }
}
