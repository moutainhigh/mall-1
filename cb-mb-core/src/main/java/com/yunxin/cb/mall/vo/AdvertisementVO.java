package com.yunxin.cb.mall.vo;

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

    /**
     * 广告图片路径
     */
    @ApiModelProperty(value="图片路径",name="picPath",example="xxxx.png")
    private String picPath;

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
}
