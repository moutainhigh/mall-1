package com.yunxin.cb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(value="我的购物车货品信息",description="我的购物车货品信息VO对象")
public class MyShoppingCartProductVO {

    /**
     * 购物车id
     */
    @ApiModelProperty(value="购物车id",name="cartId",example="1", required = true)
    private Integer cartId;

    /**
     * 商品id
     */
    @ApiModelProperty(value="商品id",name="commodityId",example="1", required = true)
    private Integer commodityId;

    /**
     * 商品名称
     */
    @ApiModelProperty(value="商品名称",name="commodityName",example="商品名称", required = true)
    private String commodityName;

    /**
     * 货品id
     */
    @ApiModelProperty(value="货品id",name="productId",example="1", required = true)
    private Integer productId;

    /**
     * 图片
     */
    @ApiModelProperty(value="图片",name="defaultPicPath",example="http://test.resource.999shuijingqiu.com/COMMODITY/1534574506619", required = true)
    private String defaultPicPath;

    /**
     * 货品销售价
     */
    @ApiModelProperty(value="货品销售价",name="salePrice",example="110", required = true)
    private BigDecimal salePrice;

    /**
     * 货品数量
     */
    @ApiModelProperty(value="货品数量",name="productNum",example="2", required = true)
    private Integer productNum;

    /**
     * 货品状态
     */
    @ApiModelProperty(value="货品状态",name="productState",example="2", required = true)
    private Integer productState;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

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

    public Integer getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return "MyShoppingCartProductVO{" +
                "cartId=" + cartId +
                ", commodityId=" + commodityId +
                ", commodityName='" + commodityName + '\'' +
                ", productId=" + productId +
                ", defaultPicPath='" + defaultPicPath + '\'' +
                ", salePrice=" + salePrice +
                ", productNum=" + productNum +
                '}';
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getDefaultPicPath() {
        return defaultPicPath;
    }

    public void setDefaultPicPath(String defaultPicPath) {
        this.defaultPicPath = defaultPicPath;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }
}
