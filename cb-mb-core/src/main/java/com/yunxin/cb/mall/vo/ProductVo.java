package com.yunxin.cb.mall.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @title: 货品VO
 * @auther: eleven
 * @date: 2018/7/24 17:25
 */
@ApiModel(value="货品VO",description="货品VO对象 Product")
public class ProductVo  implements java.io.Serializable{

    private static final long serialVersionUID = -2014776413748556239L;

    /** 货品id */
    @ApiModelProperty(value="货品id",name="productId",example="1")
    private Integer productId;

    /** 创建时间 */
    @ApiModelProperty(value="创建时间",name="createTime",example="2018-07-24 17:30:40")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 默认图片路径 */
    @ApiModelProperty(value="默认图片路径",name="defaultPicPath",example="bmw001.jpg")
    private String defaultPicPath;

    /** 市场价 */
    @ApiModelProperty(value="市场价",name="marketPrice",example="199888")
    private Float marketPrice;

    /** 货品名称：由 商品名称+上规格值 组成  用&分隔 */
    @ApiModelProperty(value="货品名称：由 商品名称+上规格值 组成  用&分隔",name="productName",example="变速箱：自动&排量：3.0L&颜色：雪山白")
    private String productName;

    /** 货品编码 */
    @ApiModelProperty(value="货品编码",name="productNo",example="BMW001")
    private String productNo;

    /** 销售价 */
    @ApiModelProperty(value="销售价",name="salePrice",example="239999")
    private Float salePrice;

    /** 库存数量 */
    @ApiModelProperty(value="库存数量",name="storeNum",example="10000")
    private Integer storeNum;

    /** 预占库存数量 */
    @ApiModelProperty(value="预占库存数量",name="reservedStoreNum",example="10")
    private Integer reservedStoreNum;

    /** 体积 */
    @ApiModelProperty(value="体积",name="volume",example="30")
    private Float volume;

    /** 重量 */
    @ApiModelProperty(value="重量",name="weight",example="50")
    private Float weight;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
        this.defaultPicPath = defaultPicPath;
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

    @Override
    public String toString() {
        return "ProductVo{" +
                "productId=" + productId +
                ", createTime=" + createTime +
                ", defaultPicPath='" + defaultPicPath + '\'' +
                ", marketPrice=" + marketPrice +
                ", productName='" + productName + '\'' +
                ", productNo='" + productNo + '\'' +
                ", salePrice=" + salePrice +
                ", storeNum=" + storeNum +
                ", reservedStoreNum=" + reservedStoreNum +
                ", volume=" + volume +
                ", weight=" + weight +
                '}';
    }
}
