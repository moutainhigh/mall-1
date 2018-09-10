package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.meta.AdvertisementPlace;
import com.yunxin.cb.mall.entity.meta.AdvertisementType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="banner",description="bannerVO对象 AdvertisementVO")
public class AdvertisementVO implements java.io.Serializable{
    private static final long serialVersionUID = -4757351429057292609L;
    /**
     *
     */
    @ApiModelProperty(value="图片id",name="advertId",example="1")
    private Integer advertId;

    @ApiModelProperty(value="图片路径",name="picPath",example="xxxx.png")
    private String picPath;

    @ApiModelProperty(value="标题",name="advertTitle",example="让生活更美好")
    private String advertTitle;

    @ApiModelProperty(value="广告URL",name="advertUrl",example="www.baidu.com")
    private String advertUrl;

    @ApiModelProperty(value="视频路径",name="videoPath",example="www.baidu.com")
    private String videoPath;

    @ApiModelProperty(value="广告类型",name="advertisementType",example="枚举")
    private AdvertisementType advertisementType;

    @ApiModelProperty(value="广告类型",name="advertisementPlace",example="枚举")
    private AdvertisementPlace advertisementPlace;


    public Integer getAdvertId() {
        return advertId;
    }

    public void setAdvertId(Integer advertId) {
        this.advertId = advertId;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getAdvertTitle() {
        return advertTitle;
    }

    public void setAdvertTitle(String advertTitle) {
        this.advertTitle = advertTitle;
    }

    public String getAdvertUrl() {
        return advertUrl;
    }

    public void setAdvertUrl(String advertUrl) {
        this.advertUrl = advertUrl;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public AdvertisementType getAdvertisementType() {
        return advertisementType;
    }

    public void setAdvertisementType(AdvertisementType advertisementType) {
        this.advertisementType = advertisementType;
    }

    public AdvertisementPlace getAdvertisementPlace() {
        return advertisementPlace;
    }

    public void setAdvertisementPlace(AdvertisementPlace advertisementPlace) {
        this.advertisementPlace = advertisementPlace;
    }
}
