package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;
import java.util.Set;

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

    /** 商品说明介绍 */
    @ApiModelProperty(value="商品说明介绍",name="explainContent",example="BMW")
    private String explainContent;

    /** 级别 */
    @ApiModelProperty(value="级别",name="showLevel",example="紧凑型车")
    private String showLevel;

    /** 规格及参数 */
    @ApiModelProperty(value="规格及参数",name="specs",example="厂商：华晨宝马")
    private Map specs;

    /** 支付方式 */
    @ApiModelProperty(value="支付方式",name="paymentType",example="0：全款购车")
    private Map paymentType;

    /** 商品图片 */
    @ApiModelProperty(value="商品图片",name="imageSet",example="bmw.jpg")
    private Set<String> imageSet;

    /** 货品 */
    @ApiModelProperty(value="货品",name="productVo",example="货品")
    private ProductVo productVo;

    /** 价格段 */
    @ApiModelProperty(value="价格段",name="priceSectionVo",example="1000-19999")
    private PriceSectionVo priceSectionVo;

    /** 商家 */
    @ApiModelProperty(value="商家",name="sellerVo",example="商家")
    private SellerVo sellerVo;

    /** 收藏夹 */
    @ApiModelProperty(value="收藏夹",name="favoriteVo",example="收藏夹")
    private FavoriteVo favoriteVo;

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

    public String getShowLevel() {
        return showLevel;
    }

    public void setShowLevel(String showLevel) {
        this.showLevel = showLevel;
    }

    public Map getSpecs() {
        return specs;
    }

    public void setSpecs(Map specs) {
        this.specs = specs;
    }

    public Map getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Map paymentType) {
        this.paymentType = paymentType;
    }

    public Set<String> getImageSet() {
        return imageSet;
    }

    public void setImageSet(Set<String> imageSet) {
        this.imageSet = imageSet;
    }

    public ProductVo getProductVo() {
        return productVo;
    }

    public void setProductVo(ProductVo productVo) {
        this.productVo = productVo;
    }

    public PriceSectionVo getPriceSectionVo() {
        return priceSectionVo;
    }

    public void setPriceSectionVo(PriceSectionVo priceSectionVo) {
        this.priceSectionVo = priceSectionVo;
    }

    public SellerVo getSellerVo() {
        return sellerVo;
    }

    public void setSellerVo(SellerVo sellerVo) {
        this.sellerVo = sellerVo;
    }

    public FavoriteVo getFavoriteVo() {
        return favoriteVo;
    }

    public void setFavoriteVo(FavoriteVo favoriteVo) {
        this.favoriteVo = favoriteVo;
    }

    public String getExplainContent() {
        return explainContent;
    }

    public void setExplainContent(String explainContent) {
        this.explainContent = explainContent;
    }

    @Override
    public String toString() {
        return "CommodityVo{" +
                "commodityId=" + commodityId +
                ", city='" + city + '\'' +
                ", commodityCode='" + commodityCode + '\'' +
                ", commodityName='" + commodityName + '\'' +
                ", commodityTitle='" + commodityTitle + '\'' +
                ", content='" + content + '\'' +
                ", defaultPicPath='" + defaultPicPath + '\'' +
                ", description='" + description + '\'' +
                ", province='" + province + '\'' +
                ", saleNum=" + saleNum +
                ", sellPrice=" + sellPrice +
                ", shortName='" + shortName + '\'' +
                ", explainContent='" + explainContent + '\'' +
                ", showLevel='" + showLevel + '\'' +
                ", specs=" + specs +
                ", paymentType=" + paymentType +
                ", imageSet=" + imageSet +
                ", productVo=" + productVo +
                ", priceSectionVo=" + priceSectionVo +
                ", sellerVo=" + sellerVo +
                ", favoriteVo=" + favoriteVo +
                '}';
    }
}
