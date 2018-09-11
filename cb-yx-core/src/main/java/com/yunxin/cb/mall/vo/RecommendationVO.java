package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Auther: hfy
 * @Date: 2018/9/10 14:42
 * @Description: 首页推荐商品VO
 */
@ApiModel(value="首页推荐商品VO",description="首页推荐商品VO RecommendationVO")
public class RecommendationVO implements Serializable {

    private static final long serialVersionUID = 3862859626346943936L;

    public RecommendationVO(Integer commodityId, String commodityName, String defaultPicPath, Double sellPrice) {
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.defaultPicPath = defaultPicPath;
        this.sellPrice = sellPrice;
    }

    public RecommendationVO() {
    }

    /** 商品ID */
    @ApiModelProperty(value="商品ID",name="commodityId",example="1")
    private Integer commodityId;

    /** 商品名 */
    @ApiModelProperty(value="商品名",name="commodityName",example="BMWX1")
    private String commodityName;


    /** 默认图片路径 */
    @ApiModelProperty(value="默认图片路径",name="defaultPicPath",example="bmw.jpg")
    private String defaultPicPath;

    /** 销售价 */
    @ApiModelProperty(value="销售价",name="sellPrice",example="239888.00")
    private Double sellPrice;

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

    public String getDefaultPicPath() {
        return defaultPicPath;
    }

    public void setDefaultPicPath(String defaultPicPath) {
        this.defaultPicPath = defaultPicPath;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }
}
