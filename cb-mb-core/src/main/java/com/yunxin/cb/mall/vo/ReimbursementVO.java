package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
@ApiModel(value="可报账对象",description="可报账对象 ReimbursementVO")
public class ReimbursementVO implements Serializable {
    private static final long serialVersionUID = -6049036070415070775L;
    @ApiModelProperty(value="可报账ID",name="reimbursementQueryId",example="1")
    private int reimbursementQueryId;
    @ApiModelProperty(value="货品数量",name="productNum",example="1")
    private int productNum;
    @ApiModelProperty(value="商家地址",name="sellerAddress",example="深圳市福田区xxx号")
    private String sellerAddress;
    @ApiModelProperty(value="商品默认图片",name="defaultPicPath",example="xxx.jpg")
    private String defaultPicPath;
    @ApiModelProperty(value="货品名称",name="productName",example="颜色：雪山白&排量：2.0L&变速箱：自动")
    private String productName;
    @ApiModelProperty(value="合计",name="accountSalePrice",example="111")
    private float accountSalePrice;
    @ApiModelProperty(value="到账金额",name="accountAmount",example="111")
    private BigDecimal accountAmount;
    @ApiModelProperty(value="税",name="tax",example="111")
    private BigDecimal tax;

    public int getReimbursementQueryId() {
        return reimbursementQueryId;
    }

    public void setReimbursementQueryId(int reimbursementQueryId) {
        this.reimbursementQueryId = reimbursementQueryId;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
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

    public float getAccountSalePrice() {
        return accountSalePrice;
    }

    public void setAccountSalePrice(float accountSalePrice) {
        this.accountSalePrice = accountSalePrice;
    }

    public BigDecimal getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

}
