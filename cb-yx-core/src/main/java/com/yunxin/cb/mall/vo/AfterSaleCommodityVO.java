package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Auther: hfy
 * @Date: 2018/9/10 16:18
 * @Description: 售后商品VO
 */
@ApiModel(value="售后商品VO",description="售后商品VO AfterSaleCommodityVO")
public class AfterSaleCommodityVO implements Serializable {

    private static final long serialVersionUID = 6726341464218370757L;

    public AfterSaleCommodityVO() {
    }

    public AfterSaleCommodityVO(Integer commodityId, String commodityName, String defaultPicPath, Double commodityTotalPrice, Integer commodityNum) {
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.defaultPicPath = defaultPicPath;
        this.commodityTotalPrice = commodityTotalPrice;
        this.commodityNum = commodityNum;
    }

    /** 商品ID */
    @ApiModelProperty(value="商品ID",name="commodityId",example="1")
    private Integer commodityId;

    /** 商品名 */
    @ApiModelProperty(value="商品名",name="commodityName",example="BMWX1")
    private String commodityName;


    /** 默认图片路径 */
    @ApiModelProperty(value="默认图片路径",name="defaultPicPath",example="bmw.jpg")
    private String defaultPicPath;

    /** 商品总价 */
    @ApiModelProperty(value="销售总价",name="commodityTotalPrice",example="239888.00")
    private Double commodityTotalPrice;

    /** 商品数量 */
    @ApiModelProperty(value="商品数量",name="commodityNum",example="239888.00")
    private Integer commodityNum;

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getDefaultPicPath() {
        return defaultPicPath;
    }

    public void setDefaultPicPath(String defaultPicPath) {
        this.defaultPicPath = defaultPicPath;
    }

    public Double getCommodityTotalPrice() {
        return commodityTotalPrice;
    }

    public void setCommodityTotalPrice(Double commodityTotalPrice) {
        this.commodityTotalPrice = commodityTotalPrice;
    }

    public Integer getCommodityNum() {
        return commodityNum;
    }

    public void setCommodityNum(Integer commodityNum) {
        this.commodityNum = commodityNum;
    }
}
