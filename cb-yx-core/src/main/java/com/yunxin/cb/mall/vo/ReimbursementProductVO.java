package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ReimbursementProductVO implements Serializable {
    private static final long serialVersionUID = 8294440429229209652L;

    @ApiModelProperty(value="货品数量",name="productNum",example="1")
    private int productNum;
    @ApiModelProperty(value="商品默认图片",name="defaultPicPath",example="xxx.jpg")
    private String defaultPicPath;
    @ApiModelProperty(value="货品名称",name="productName",example="颜色：雪山白&排量：2.0L&变速箱：自动")
    private String productName;
    @ApiModelProperty(value="卖家地址",name="sellerAddress",example="深圳市xx区xx号")
    private String sellerAddress;
    @ApiModelProperty(value="商品单个售价",name="salePrice",example="22")
    private float salePrice;

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public String getDefaultPicPath() {
        return defaultPicPath;
    }

    public void setDefaultPicPath(String defaultPicPath) {
        this.defaultPicPath = defaultPicPath;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }
}
