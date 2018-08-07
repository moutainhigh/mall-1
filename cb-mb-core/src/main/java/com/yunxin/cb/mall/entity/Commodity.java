package com.yunxin.cb.mall.entity;

import java.util.*;

/**
 * @title: 商品实体类
 * @auther: eleven
 * @date: 2018/7/18 17:29
 */
public class Commodity  implements java.io.Serializable{
    private static final long serialVersionUID = 1502774904693173239L;
    /** 商品ID */
    private Integer commodityId;

    /** 审核备注 */
    private String auditRemark;

    /** 是否可换购 false为非换购品 true为换购品 */
    private Boolean barter;

    /** 产地市区 */
    private String city;

    /** 商品编码 */
    private String commodityCode;

    /** 商品名 */
    private String commodityName;

    /** 商品拼音名 */
    private String commodityPyname;

    /** 审核状态 0:待审核1:审核通过 2:审核未通过 3:删除 */
    private Integer commodityState;

    /** 商品标题 */
    private String commodityTitle;

    /** 商品介绍 */
    private String content;

    /** 成本价 */
    private Double costPrice;

    /** 创建时间 */
    private Date createTime;

    /** 默认图片路径 */
    private String defaultPicPath;

    /** 配送方式 */
    private Integer deliveryType;

    /** 描述 */
    private String description;

    /** 是否禁止空运 */
    private Boolean forbidAirCargo;

    /** 是否为赠品 false为非赠品 true为赠品 */
    private Boolean giveaway;

    /** 市场价格 */
    private Double marketPrice;

    /** 否为热门商品 */
    private Boolean popular;

    /** 否可预售 false为不可预售 true为可预售 */
    private Boolean preSell;

    /** 产地省份 */
    private String province;

    /** 上下架状态 0:待上架 1:上架 2:下架 */
    private Integer publishState;

    /** 是否为推荐商品 */
    private Boolean recommend;

    /** 销量 */
    private Integer saleNum;

    /** 销售价 */
    private Double sellPrice;

    /** seo描述 */
    private String seoDescription;

    /** seo关键字 */
    private String seoKey;

    /** seo标题 */
    private String seoTitle;

    /** 简称 */
    private String shortName;

    /** 否为特惠商品 */
    private Boolean special;

    /** 商品单位 0:件 1:瓶 2:包 3:套 */
    private Integer unit;

    /** 体积 */
    private Double volume;

    /** 重量 */
    private Double weight;

    /** 品牌 */
    private Integer brandId;

    /** 商品分类 */
    private Integer catalogId;

    /** 商品所属价格段 */
    private Integer sectionId;

    /** 供应商 */
    private Integer sellerId;

    /** 商品说明介绍 */
    private String explainContent;

    /** 商品配置内容*/
    private String settingContent;

    //品牌
    private Brand brand;

    //价格段
    private PriceSection priceSection;

    //商家
    private Seller seller;

    //货品
    private List<Product> products = new ArrayList<Product>();

    //规格
    private List<CommoditySpec> commoditySpecs = new ArrayList<>();

    /** 包装清单 */
    private String packingList;

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark == null ? null : auditRemark.trim();
    }

    public Boolean getBarter() {
        return barter;
    }

    public void setBarter(Boolean barter) {
        this.barter = barter;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode == null ? null : commodityCode.trim();
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
    }

    public String getCommodityPyname() {
        return commodityPyname;
    }

    public void setCommodityPyname(String commodityPyname) {
        this.commodityPyname = commodityPyname == null ? null : commodityPyname.trim();
    }

    public Integer getCommodityState() {
        return commodityState;
    }

    public void setCommodityState(Integer commodityState) {
        this.commodityState = commodityState;
    }

    public String getCommodityTitle() {
        return commodityTitle;
    }

    public void setCommodityTitle(String commodityTitle) {
        this.commodityTitle = commodityTitle == null ? null : commodityTitle.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
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

    public Integer getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Integer deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Boolean getForbidAirCargo() {
        return forbidAirCargo;
    }

    public void setForbidAirCargo(Boolean forbidAirCargo) {
        this.forbidAirCargo = forbidAirCargo;
    }

    public Boolean getGiveaway() {
        return giveaway;
    }

    public void setGiveaway(Boolean giveaway) {
        this.giveaway = giveaway;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Boolean getPopular() {
        return popular;
    }

    public void setPopular(Boolean popular) {
        this.popular = popular;
    }

    public Boolean getPreSell() {
        return preSell;
    }

    public void setPreSell(Boolean preSell) {
        this.preSell = preSell;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public Integer getPublishState() {
        return publishState;
    }

    public void setPublishState(Integer publishState) {
        this.publishState = publishState;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
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

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription == null ? null : seoDescription.trim();
    }

    public String getSeoKey() {
        return seoKey;
    }

    public void setSeoKey(String seoKey) {
        this.seoKey = seoKey == null ? null : seoKey.trim();
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle == null ? null : seoTitle.trim();
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public Boolean getSpecial() {
        return special;
    }

    public void setSpecial(Boolean special) {
        this.special = special;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getPackingList() {
        return packingList;
    }

    public void setPackingList(String packingList) {
        this.packingList = packingList == null ? null : packingList.trim();
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public PriceSection getPriceSection() {
        return priceSection;
    }

    public void setPriceSection(PriceSection priceSection) {
        this.priceSection = priceSection;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<CommoditySpec> getCommoditySpecs() {
        return commoditySpecs;
    }

    public void setCommoditySpecs(List<CommoditySpec> commoditySpecs) {
        this.commoditySpecs = commoditySpecs;
    }

    public String getExplainContent() {
        return explainContent;
    }

    public void setExplainContent(String explainContent) {
        this.explainContent = explainContent;
    }

    public String getSettingContent() {
        return settingContent;
    }

    public void setSettingContent(String settingContent) {
        this.settingContent = settingContent;
    }
}