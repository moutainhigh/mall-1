package com.yunxin.cb.mall.entity;

import java.util.Date;

public class OrderItem {
    /** 订单明细id */
    private Integer itemId;

    /** 买家留言 */
    private String buyerMessage;

    /** 创建时间 */
    private Date createTime;

    /** 是否做了评价 */
    private Boolean evaluate;

    /** 总价格 */
    private Float orderItemPrice;

    /** 货品图片 */
    private String productImg;

    /** 货品购买数量 */
    private Integer productNum;

    /** 销售价格 */
    private Float salePrice;

    /** 活动id */
    private Integer activityId;

    /** 订单id */
    private Integer orderId;

    /** 货品id */
    private Integer productId;

    /** 成本价 */
    private Float costPrice;

    /** 市场价 */
    private Float marketPrice;

    /** 货品编码 */
    private String productNo;

    /** 货品名称：由 商品名称+上规格值 组成  用&分隔 */
    private String productName;

    /** 体积 */
    private Float volume;

    /** 重量 */
    private Float weight;

    /** 货品 */
    private Product product;

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
        this.buyerMessage = buyerMessage == null ? null : buyerMessage.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        this.productImg = productImg == null ? null : productImg.trim();
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

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Float getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Float costPrice) {
        this.costPrice = costPrice;
    }

    public Float getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Float marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }
}