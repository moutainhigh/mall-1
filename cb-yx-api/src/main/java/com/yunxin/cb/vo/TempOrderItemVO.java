package com.yunxin.cb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @title: 临时订单货品VO
 * @auther: gws
 * @date: 2018/9/10 17:25
 */
@ApiModel(value="临时订单货品VO",description="临时订单货品VO对象 Product")
public class TempOrderItemVO{

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

    /** 商品标题 */
    @ApiModelProperty(value="商品标题",name="commodityTitle",example="宝马 宝马X1 2018款 sDrive18Li 尊享型")
    private String commodityTitle;

    /** 货品id */
    @ApiModelProperty(value="货品id",name="productId",example="1")
    private Integer productId;

    /** 默认图片路径 */
    @ApiModelProperty(value="默认图片路径",name="defaultPicPath",example="bmw001.jpg")
    private String defaultPicPath;

    /** 货品名称：由 商品名称+上规格值 组成  用&分隔 */
    @ApiModelProperty(value="货品名称：由 商品名称+上规格值 组成  用&分隔",name="productName",example="变速箱：自动&排量：3.0L&颜色：雪山白")
    private String productName;

    /** 货品编码 */
    @ApiModelProperty(value="货品编码",name="productNo",example="BMW001")
    private String productNo;

    /** 销售价 */
    @ApiModelProperty(value="销售价",name="salePrice",example="239999")
    private Float salePrice;

    /** 购买数量 */
    @ApiModelProperty(value="购买数量",name="buyNum",example="1")
    private Integer buyNum;

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

    public String getCommodityTitle() {
        return commodityTitle;
    }

    public void setCommodityTitle(String commodityTitle) {
        this.commodityTitle = commodityTitle;
    }

    public Integer getProductId() {
        return productId;
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

    public Float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Float salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    @Override
    public String toString() {
        return "TempOrderItemVO{" +
                "commodityId=" + commodityId +
                ", commodityName='" + commodityName + '\'' +
                ", commodityTitle='" + commodityTitle + '\'' +
                ", productId=" + productId +
                ", defaultPicPath='" + defaultPicPath + '\'' +
                ", productName='" + productName + '\'' +
                ", productNo='" + productNo + '\'' +
                ", salePrice=" + salePrice +
                ", buyNum=" + buyNum +
                '}';
    }
}
