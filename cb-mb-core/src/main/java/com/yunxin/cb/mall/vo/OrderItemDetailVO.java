package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单商品详情VO
* @author gws
* @date 2018/7/24 19:59
* @param 
* @return 
*/
@ApiModel(value="商城订单商品详情",description="订单商品详情VO对象")
public class OrderItemDetailVO implements java.io.Serializable{

    /** 订单明细id */
    @ApiModelProperty(value="订单明细id",name="itemId",example="1")
    private Integer itemId;

    /** 买家留言 */
    @ApiModelProperty(value="买家留言",name="buyerMessage",example="1")
    private String buyerMessage;

    /** 是否做了评价 */
    @ApiModelProperty(value="是否做了评价",name="evaluate",example="false")
    private Boolean evaluate;

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

    /** 货品编码 */
    @ApiModelProperty(value="货品编码 ",name="productNo",example="11111")
    private String productNo;

    /** 商家名 */
    @ApiModelProperty(value="商家名",name="sellerName",example="4s店")
    private String sellerName;

    /** 商家地址 */
    @ApiModelProperty(value="商家地址",name="sellerAddress",example="深圳市")
    private String sellerAddress;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public Boolean getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Boolean evaluate) {
        this.evaluate = evaluate;
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

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getCommodityTitle() {
        return commodityTitle;
    }

    public void setCommodityTitle(String commodityTitle) {
        this.commodityTitle = commodityTitle;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    @Override
    public String toString() {
        return "OrderItemDetailVO{" +
                "itemId=" + itemId +
                ", buyerMessage='" + buyerMessage + '\'' +
                ", evaluate=" + evaluate +
                ", orderItemPrice=" + orderItemPrice +
                ", productImg='" + productImg + '\'' +
                ", productNum=" + productNum +
                ", salePrice=" + salePrice +
                ", productId=" + productId +
                ", commodityName='" + commodityName + '\'' +
                ", commodityTitle='" + commodityTitle + '\'' +
                ", commodityId=" + commodityId +
                ", productName='" + productName + '\'' +
                ", productNo='" + productNo + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", sellerAddress='" + sellerAddress + '\'' +
                '}';
    }
}
