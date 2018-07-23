package com.yunxin.cb.mall.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @title: 货品实体类
 * @auther: eleven
 * @date: 2018/7/18 17:40
 */
public class Product {
    /** 货品id */
    private Integer productId;

    /** 上架时间 */
    private Date addTime;

    /** 进货价 */
    private Float costPrice;

    /** 创建时间 */
    private Date createTime;

    /** 默认图片路径 */
    private String defaultPicPath;

    /** 市场价 */
    private Float marketPrice;

    /** 货品名称：由 商品名称+上规格值 组成  用&分隔 */
    private String productName;

    /** 货品编码 */
    private String productNo;

    /** 审核状态 0:待审核1:审核通过 2:审核未通过 3:删除 */
    private Integer productState;

    /** 上下架状态 0:待上架 1:上架 2:下架 */
    private Integer publishState;

    /** 审核备注 */
    private String remark;

    /** 销售价 */
    private Float salePrice;

    /** 库存数量 */
    private Integer storeNum;

    /** 预占库存数量 */
    private Integer reservedStoreNum;

    /** 体积 */
    private Float volume;

    /** 重量 */
    private Float weight;

    /** 商品id */
    private Integer commodityId;

    /** 仓库id */
    private Integer storeId;

    //货品所有属性
    public List<ProductAttribute> productAttributes = new ArrayList<ProductAttribute>();

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Float getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Float costPrice) {
        this.costPrice = costPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDefaultPicPath() {
        return defaultPicPath;
    }

    public void setDefaultPicPath(String defaultPicPath) {
        this.defaultPicPath = defaultPicPath == null ? null : defaultPicPath.trim();
    }

    public Float getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Float marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    public Integer getProductState() {
        return productState;
    }

    public void setProductState(Integer productState) {
        this.productState = productState;
    }

    public Integer getPublishState() {
        return publishState;
    }

    public void setPublishState(Integer publishState) {
        this.publishState = publishState;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Float salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(Integer storeNum) {
        this.storeNum = storeNum;
    }

    public Integer getReservedStoreNum() {
        return reservedStoreNum;
    }

    public void setReservedStoreNum(Integer reservedStoreNum) {
        this.reservedStoreNum = reservedStoreNum;
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

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public List<ProductAttribute> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(List<ProductAttribute> productAttributes) {
        this.productAttributes = productAttributes;
    }
}