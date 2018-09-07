package com.yunxin.cb.vo.responsevo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="品牌",description="品牌VO对象 CarBrandVO")
public class CarBrandVO implements java.io.Serializable{
    private static final long serialVersionUID = 3473099803823395986L;
    @ApiModelProperty(value="品牌ID",name="brandId",example="1")
    private Integer id;
    /**
     * 品牌中文名称
     */
    @ApiModelProperty(value="品牌中文名称",name="brandName",example="宝马")
    private String brandName;

    /**
     * 品牌英文名称
     */
    @ApiModelProperty(value="品牌英文名称",name="brandEnName",example="BMW")
    private String brandEnName;

    /**
     * 首字母
     */
    @ApiModelProperty(value="首字母",name="initial",example="A")
    private String initial;
    /**
     * 图片路径  150*58 png
     */
    @ApiModelProperty(value="品牌图片",name="picPath",example="xxxxx.png")
    private String picPath;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getBrandEnName() {
        return brandEnName;
    }

    public void setBrandEnName(String brandEnName) {
        this.brandEnName = brandEnName;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }
}
