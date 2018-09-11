package com.yunxin.cb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @title: 订单列表明细VO
 * @auther: gws
 * @date: 2018/9/10 17:25
 */
@ApiModel(value="订单列表货品明细VO",description="订单列表货品明细VO TempOrderVO")
public class OrderListItemVO {

    /** 订单明细id */
    @ApiModelProperty(value="订单明细id",name="itemId",example="1")
    private Integer itemId;

    /** 总价格 */
    @ApiModelProperty(value="货品总价格",name="orderItemPrice",example="1")
    private Float orderItemPrice;

    /** 货品图片 */
    @ApiModelProperty(value="货品图片",name="productImg",example="http://pic.png")
    private String productImg;

    /** 货品购买数量 */
    @ApiModelProperty(value="货品购买数量",name="productNum",example="1")
    private Integer productNum;

    /** 销售价格 */
    @ApiModelProperty(value="销售价格",name="salePrice",example="1")
    private Float salePrice;

    /** 货品id */
    @ApiModelProperty(value="货品id",name="productId",example="1")
    private Integer productId;

    /** 商品名称*/
    @ApiModelProperty(value="商品名称",name="commodityName",example="1")
    private String commodityName;

    /** 商品标题*/
    @ApiModelProperty(value="商品标题",name="commodityName",example="1")
    private String commodityTitle;

    /** 商品ID */
    @ApiModelProperty(value="商品ID",name="commodityId",example="1")
    private Integer commodityId;

    /** 货品名称：由 商品名称+上规格值 组成  用&分隔 */
    @ApiModelProperty(value="货品名称",name="productName",example="商品名称+上规格值 组成  用&分隔")
    private String productName;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Float getOrderItemPrice() {
        return orderItemPrice;
    }

    public void setOrderItemPrice(Float orderItemPrice) {
        this.orderItemPrice = orderItemPrice;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Float salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityTitle() {
        return commodityTitle;
    }

    public void setCommodityTitle(String commodityTitle) {
        this.commodityTitle = commodityTitle;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "OrderListItemVO{" +
                "itemId=" + itemId +
                ", orderItemPrice=" + orderItemPrice +
                ", productImg='" + productImg + '\'' +
                ", productNum=" + productNum +
                ", salePrice=" + salePrice +
                ", productId=" + productId +
                ", commodityName='" + commodityName + '\'' +
                ", commodityTitle='" + commodityTitle + '\'' +
                ", commodityId=" + commodityId +
                ", productName='" + productName + '\'' +
                '}';
    }
}
