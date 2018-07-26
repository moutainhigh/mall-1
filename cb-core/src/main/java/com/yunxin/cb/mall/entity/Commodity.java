/**
 *
 */
package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hankcs.lucene.HanLPAnalyzer;
import com.yunxin.cb.mall.entity.meta.CommodityState;
import com.yunxin.cb.mall.entity.meta.CommodityUnit;
import com.yunxin.cb.mall.entity.meta.DeliveryType;
import com.yunxin.cb.mall.entity.meta.PublishState;
import com.yunxin.core.util.LogicUtils;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author z001075  商品
 */
//@Indexed(index = "Commodity")
//@Analyzer(impl = HanLPAnalyzer.class)
//@Cacheable(true)
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class Commodity implements java.io.Serializable {

    /***/
    private static final long serialVersionUID = -3113613325145218113L;

    /**
     * 商品ID
     */
    private int commodityId;
    /**
     * 商品分类
     */
    private Catalog catalog;

    /**
     * 商品所属价格段
     */
    private PriceSection priceSection;
    /**
     * 品牌
     */
    private Brand brand;
    /**
     * 供应商
     */
    private Seller seller;
    /**
     * 商品编码
     */
    private String commodityCode;
    /**
     * 商品名
     */
    private String commodityName;
    /**
     * 商品拼音名
     */
    private String commodityPYName;
    /**
     * 简称
     */
    private String shortName;
    /**
     * 商品标题
     */
    private String commodityTitle;
    /**
     * 成本价
     */
    private double costPrice;
    /**
     * 销售价
     */
    private double sellPrice;
    /**
     * 市场价格
     */
    private double marketPrice;
    /**
     * 商品单位（件、瓶、包、套）
     */
    private CommodityUnit unit;
    /**
     * 产地省份
     */
    private String province;
    /**
     * 产地市区
     */
    private String city;
    /**
     * 默认图片路径
     */
    private String defaultPicPath;
    /**
     * seo关键字
     */
    private String seoKey;
    /**
     * seo标题
     */
    private String seoTitle;
    /**
     * seo描述
     */
    private String seoDescription;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 状态
     */
    private CommodityState commodityState;
    /**
     * 商品上下架
     */
    private PublishState publishState;
    /**
     * 是否为热门商品
     */
    private boolean popular;
    /**
     * 是否为特惠商品
     */
    private boolean special;
    /**
     * 是否为推荐商品
     */
    private boolean recommend;
    /**
     * 是否为赠品 false为非赠品 true为赠品
     */
    private boolean giveaway;
    /**
     * 是否可换购 false为非换购品 true为换购品
     */
    private boolean barter;
    /**
     * 是否可预售 false为不可预售 true为可预售
     */
    private boolean preSell;
    /**
     * 是否禁止空运
     */
    private boolean forbidAirCargo;
    /**
     * 商品介绍
     */
    private String content;
    /**
     * 销量
     */
    private int saleNum;
    /**
     * 包装清单
     ***/
    private String packingList;
    /**
     * 货品
     */
    private List<Product> products = new ArrayList<Product>();
    /**
     * 配送方式
     */
    private DeliveryType deliveryType;
    /**
     * 重量
     */
    private double weight;
    /**
     * 体积
     */
    private double volume;
    /**
     * 商品组合 ：特惠组合 ，推荐搭配等
     */
    private List<Combination> combinations = new ArrayList<>();

    /**
     * 商品分类
     */
    private Set<CommodityCategory> commodityCategories = new HashSet<>();

    /**
     * 商品规格
     */
    private Set<CommoditySpec> commoditySpecs = new HashSet<>();

    /**
     * 物流价格
     **/
    private Set<LogisticPrice> logisticPrices = new HashSet<>();

    /******************form 字段*****************/
    private int[] specId;

    private String[] specValue;

    /**
     * 图片字段
     */
    private String[] imagePath;
    /***
     * 审核备注
     */
    private String auditRemark;

    /**
     * 商品说明介绍
     */
    private String explainContent;

    @Column(nullable = true, length = 4098)
    public String getExplainContent() {
        return explainContent;
    }

    public void setExplainContent(String explainContent) {
        this.explainContent = explainContent;
    }

    public Commodity() {
    }

    public Commodity(int commodityId) {
        this.commodityId = commodityId;
    }

    @Id
    @DocumentId  /*以字段id作为搜索引擎文档id*/
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATALOG_ID", nullable = false)
    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRAND_ID", nullable = false)
//    @IndexedEmbedded(depth = 1)
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SELLER_ID", nullable = false)
    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @Column(nullable = false, length = 255)
    @Field(index = Index.YES, analyze = Analyze.YES, store = org.hibernate.search.annotations.Store.COMPRESS)
    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commName) {
        this.commodityName = commName;
    }

    @Column(length = 64)
    public String getCommodityPYName() {
        return commodityPYName;
    }

    public void setCommodityPYName(String commodityPYName) {
        this.commodityPYName = commodityPYName;
    }

    @Column(nullable = false, length = 64)
    @Field(index = Index.YES, analyze = Analyze.YES, store = org.hibernate.search.annotations.Store.COMPRESS)
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Column(nullable = false, length = 255)
    @Field(index = Index.YES, analyze = Analyze.YES, store = org.hibernate.search.annotations.Store.COMPRESS)
    public String getCommodityTitle() {
        return commodityTitle;
    }

    public void setCommodityTitle(String commodityTitle) {
        this.commodityTitle = commodityTitle;
    }

    @Column(nullable = false, length = 32)
    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    @Column(nullable = true, length = 255)
    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    @Column(nullable = true, length = 512)
    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    @Column(nullable = true, length = 512)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = true, length = 255)
    public String getSeoKey() {
        return seoKey;
    }

    public void setSeoKey(String seoKey) {
        this.seoKey = seoKey;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Column(nullable = false, precision = 1)
    @Enumerated(EnumType.ORDINAL)
    public CommodityState getCommodityState() {
        return commodityState;
    }

    public void setCommodityState(CommodityState commodityState) {
        this.commodityState = commodityState;
    }

    @Column(nullable = false, precision = 1)
    @Enumerated(EnumType.ORDINAL)
    public PublishState getPublishState() {
        return publishState;
    }

    public void setPublishState(PublishState publishState) {
        this.publishState = publishState;
    }

    @Column(nullable = true, precision = 1)
    public boolean isGiveaway() {
        return giveaway;
    }

    public void setGiveaway(boolean isGiveaway) {
        this.giveaway = isGiveaway;
    }

    @Column(nullable = true, precision = 1)
    public boolean isBarter() {
        return barter;
    }

    public void setBarter(boolean isBarter) {
        this.barter = isBarter;
    }

    @Column(nullable = true, precision = 1)
    public boolean isPreSell() {
        return preSell;
    }

    public void setPreSell(boolean isPreSell) {
        this.preSell = isPreSell;
    }

    @Column(nullable = true, precision = 1)
    public boolean isForbidAirCargo() {
        return forbidAirCargo;
    }

    public void setForbidAirCargo(boolean forbidAirCargo) {
        this.forbidAirCargo = forbidAirCargo;
    }

    @Column(nullable = true, length = 4098)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    @Column(nullable = false, precision = 12, scale = 2)
    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    @Column(nullable = true, precision = 12, scale = 2)
    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    public CommodityUnit getUnit() {
        return unit;
    }

    public void setUnit(CommodityUnit unit) {
        this.unit = unit;
    }

    @Column(length = 16)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(length = 16)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Column(nullable = true, length = 255)
    public String getDefaultPicPath() {
        return defaultPicPath;
    }

    public void setDefaultPicPath(String defaultPicPath) {
        this.defaultPicPath = defaultPicPath;
    }

    @Column(nullable = true, precision = 1)
    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }

    @Column(nullable = true, precision = 1)
    public boolean isSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }

    @Column(nullable = true, precision = 1)
    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    @Column(nullable = true, length = 12)
    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    @Column(nullable = true, length = 1024)
    public String getPackingList() {
        return packingList;
    }

    public void setPackingList(String packingList) {
        this.packingList = packingList;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "commodity", cascade = CascadeType.REMOVE)
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Column(nullable = true, precision = 1)
    @Enumerated(EnumType.ORDINAL)
    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    @Transient
    public String getDeliveryTypeName() {
        if (LogicUtils.isNotNull(deliveryType)) {
            return deliveryType.getName();
        }
        return null;
    }

    @Column(nullable = true, precision = 12, scale = 2)
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Column(nullable = true, precision = 12, scale = 2)
    public double getVolume() {
        return volume;
    }

    public void setVolume(double volumn) {
        this.volume = volumn;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "commodity", cascade = CascadeType.REMOVE)
    public List<Combination> getCombinations() {
        return combinations;
    }

    public void setCombinations(List<Combination> combinations) {
        this.combinations = combinations;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "commodity", cascade = CascadeType.REMOVE)
    public Set<CommoditySpec> getCommoditySpecs() {
        return commoditySpecs;
    }

    public void setCommoditySpecs(Set<CommoditySpec> commoditySpecs) {
        this.commoditySpecs = commoditySpecs;
    }


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "logistic_price_commodity", joinColumns = {@JoinColumn(name = "COMMODITY_ID", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "PRICE_ID", nullable = false, updatable = false)})
    public Set<LogisticPrice> getLogisticPrices() {
        return logisticPrices;
    }

    public void setLogisticPrices(Set<LogisticPrice> logisticPrices) {
        this.logisticPrices = logisticPrices;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SECTION_ID", nullable = false)
    public PriceSection getPriceSection() {
        return priceSection;
    }

    public void setPriceSection(PriceSection priceSection) {
        this.priceSection = priceSection;
    }

    @Transient
    public int[] getSpecId() {
        return specId;
    }

    public void setSpecId(int[] specId) {
        this.specId = specId;
    }

    @Transient
    public String[] getSpecValue() {
        return specValue;
    }

    public void setSpecValue(String[] specValue) {
        this.specValue = specValue;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "commodity", cascade = CascadeType.REMOVE)
    public Set<CommodityCategory> getCommodityCategories() {
        return commodityCategories;
    }

    public void setCommodityCategories(Set<CommodityCategory> commodityCategories) {
        this.commodityCategories = commodityCategories;
    }

    @Transient
    public String[] getImagePath() {
        return imagePath;
    }

    public void setImagePath(String[] imagePath) {
        this.imagePath = imagePath;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }
}
