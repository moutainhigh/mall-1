package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.entity.meta.ReimbursementState;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class AlreadyReimbursement implements Serializable {
    private static final long serialVersionUID = -3932022749211988786L;

    @ApiModelProperty(value="报账信息ID",name="reimbursementId",example="1")
    private int reimbursementId;
    @ApiModelProperty(value="报账信息状态",name="orderState",example="1")
    private ReimbursementState orderState;
    @ApiModelProperty(value="创建时间",name="createTime",example="2018-08-08 15:20:20")
    private Date createTime;
    @ApiModelProperty(value="操作时间",name="operationTime",example="2018-08-08 15:20:20")
    private Date operationTime;
    @ApiModelProperty(value="报账单号",name="reimbursementNo",example="52525525")
    private String reimbursementNo;
    @ApiModelProperty(value="卖家地址",name="sellerAddress",example="深圳市xx区xx号")
    private String sellerAddress;
    @ApiModelProperty(value="商品默认图片",name="defaultPicPath",example="xxx.jpg")
    private String defaultPicPath;
    @ApiModelProperty(value="商品名称",name="productName",example="颜色：雪松灰&排量：1.5L&变速箱：自动")
    private String productName;
    @ApiModelProperty(value="商品售价",name="salePrice",example="55")
    private float salePrice;
    @ApiModelProperty(value="商品数量",name="productNum",example="1")
    private int productNum;

    public int getReimbursementId() {
        return reimbursementId;
    }

    public void setReimbursementId(int reimbursementId) {
        this.reimbursementId = reimbursementId;
    }

    public ReimbursementState getOrderState() {
        return orderState;
    }

    public void setOrderState(ReimbursementState orderState) {
        this.orderState = orderState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getReimbursementNo() {
        return reimbursementNo;
    }

    public void setReimbursementNo(String reimbursementNo) {
        this.reimbursementNo = reimbursementNo;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
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

    public float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }
}
