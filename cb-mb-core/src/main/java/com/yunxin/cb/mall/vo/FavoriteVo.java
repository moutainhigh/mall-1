package com.yunxin.cb.mall.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @title: 收藏夹VO
 * @auther: eleven
 * @date: 2018/7/24 15:01
 */
@ApiModel(value="收藏夹",description="收藏夹VO对象 Favorite")
public class FavoriteVo implements java.io.Serializable{

    private static final long serialVersionUID = -2695946271501714063L;

    /** 收藏Id */
    @ApiModelProperty(value="收藏Id",name="favoriteId",example="1")
    private Integer favoriteId;

    /** 创建时间 */
    @ApiModelProperty(value="创建时间",name="createTime",example="2018-07-24 15:02:32")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 销售价 */
    @ApiModelProperty(value="销售价",name="salePrice",example="200.00")
    private Float salePrice;

    /** 商品id */
    @ApiModelProperty(value="商品id",name="commodityId",example="476")
    private Integer commodityId;

    @ApiModelProperty(value="商品",name="commodity",example="commodity")
    private CommodityVo commodityVo;

    /** 客户id */
    @ApiModelProperty(value="客户id",name="customerId",example="1")
    private Integer customerId;

    public Integer getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.favoriteId = favoriteId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Float salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public CommodityVo getCommodityVo() {
        return commodityVo;
    }

    public void setCommodityVo(CommodityVo commodityVo) {
        this.commodityVo = commodityVo;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "FavoriteVo{" +
                "favoriteId=" + favoriteId +
                ", createTime=" + createTime +
                ", salePrice=" + salePrice +
                ", commodityId=" + commodityId +
                ", commodity=" + commodityVo +
                ", customerId=" + customerId +
                '}';
    }
}
