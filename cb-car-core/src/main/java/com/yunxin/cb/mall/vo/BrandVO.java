package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="品牌",description="品牌VO对象 BrandVO")
public class BrandVO implements java.io.Serializable{
    private static final long serialVersionUID = 3473099803823395986L;
    @ApiModelProperty(value="品牌ID",name="brandId",example="1")
    private Integer brandId;
    /**
     * 品牌中文名称
     */
    @ApiModelProperty(value="品牌中文名称",name="brandName",example="宝马")
    private String brandName;
    /**
     * 图片路径  150*58 png
     */
    @ApiModelProperty(value="品牌图片",name="picPath",example="xxxxx.png")
    private String picPath;

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
}
