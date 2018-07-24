package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @title: 商品VO
 * @auther: eleven
 * @date: 2018/7/24 16:00
 */
@ApiModel(value="商品VO",description="商品VO对象 Commodity")
public class CommodityVo  implements java.io.Serializable{

    private static final long serialVersionUID = 1872359766814469998L;

    /** 商品ID */
    @ApiModelProperty(value="商品ID",name="commodityId",example="1")
    private Integer commodityId;

    /** 产地市区 */
    @ApiModelProperty(value="产地市区",name="city",example="深圳")
    private String city;

    /** 商品编码 */
    @ApiModelProperty(value="商品编码",name="commodityCode",example="121233485")
    private String commodityCode;

    /** 商品名 */
    @ApiModelProperty(value="商品名",name="commodityName",example="BMWX1")
    private String commodityName;

    /** 商品标题 */
    @ApiModelProperty(value="商品标题",name="commodityTitle",example="BMWX1")
    private String commodityTitle;

    /** 商品介绍 */
    @ApiModelProperty(value="商品介绍",name="content",example="BMWX1 超舒适 你值得拥有")
    private String content;

    /** 默认图片路径 */
    @ApiModelProperty(value="默认图片路径",name="defaultPicPath",example="bmw.jpg")
    private String defaultPicPath;

    /** 描述 */
    @ApiModelProperty(value="描述",name="description",example="1")
    private String description;

    /** 产地省份 */
    @ApiModelProperty(value="产地省份",name="province",example="广东省")
    private String province;

    /** 销量 */
    @ApiModelProperty(value="销量",name="saleNum",example="1000")
    private Integer saleNum;

    /** 销售价 */
    @ApiModelProperty(value="销售价",name="sellPrice",example="239888")
    private Double sellPrice;

    /** 简称 */
    @ApiModelProperty(value="简称",name="shortName",example="BMW")
    private String shortName;

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDefaultPicPath() {
        return defaultPicPath;
    }

    public void setDefaultPicPath(String defaultPicPath) {
        this.defaultPicPath = defaultPicPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
