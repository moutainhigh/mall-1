package com.yunxin.cb.vo.responsevo;

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
    private List<CarAdvertisementsVO> homeList;
    /**
     * 第五层-中部图片
     */
    @ApiModelProperty(value="第四层-中部图片",name="milldeList",example="第四层-中部图片")
    private List<CarAdvertisementsVO> milldeList;
    /**
     * 第二层-品牌
     */
    @ApiModelProperty(value="第二层-品牌",name="brandList",example="品牌")
    private List<CarBrandVO> brandList;
    /**
     * 第三层-主打车系
     */
    @ApiModelProperty(value="第三层-主打车系",name="mainCarList",example="第三层-主打车系")
    private List<CarSystemVO> mainCarList;
    /**
     * 第四层-品类
     */
    @ApiModelProperty(value="第四层-品类",name="categoryFiveList",example="第四层-品类")
    private List<CarBaseDataVO> categoryList;
    /**
     * 第六层-热门车系
     */
    @ApiModelProperty(value="第六层-热门车系",name="hotCarList",example="第六层-热门车系")
    private List<CarSystemVO> hotCarList;

    public List<CarAdvertisementsVO> getHomeList() {
        return homeList;
    }

    public void setHomeList(List<CarAdvertisementsVO> homeList) {
        this.homeList = homeList;
    }

    public List<CarAdvertisementsVO> getMilldeList() {
        return milldeList;
    }

    public void setMilldeList(List<CarAdvertisementsVO> milldeList) {
        this.milldeList = milldeList;
    }

    public List<CarBrandVO> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<CarBrandVO> brandList) {
        this.brandList = brandList;
    }

    public List<CarSystemVO> getMainCarList() {
        return mainCarList;
    }

    public void setMainCarList(List<CarSystemVO> mainCarList) {
        this.mainCarList = mainCarList;
    }

    public List<CarBaseDataVO> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CarBaseDataVO> categoryList) {
        this.categoryList = categoryList;
    }

    public List<CarSystemVO> getHotCarList() {
        return hotCarList;
    }

    public void setHotCarList(List<CarSystemVO> hotCarList) {
        this.hotCarList = hotCarList;
    }
}
