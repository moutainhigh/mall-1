/**
 *
 */
package com.yunxin.cb.mall.entity;

import com.sun.org.apache.xml.internal.resolver.Catalog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author z001075  商品
 */

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
     * 重量
     */
    private double weight;
    /**
     * 体积
     */
    private double volume;



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


    public Commodity() {
    }

    public Commodity(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
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

    public String getCommodityPYName() {
        return commodityPYName;
    }

    public void setCommodityPYName(String commodityPYName) {
        this.commodityPYName = commodityPYName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCommodityTitle() {
        return commodityTitle;
    }

    public void setCommodityTitle(String commodityTitle) {
        this.commodityTitle = commodityTitle;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDefaultPicPath() {
        return defaultPicPath;
    }

    public void setDefaultPicPath(String defaultPicPath) {
        this.defaultPicPath = defaultPicPath;
    }

    public String getSeoKey() {
        return seoKey;
    }

    public void setSeoKey(String seoKey) {
        this.seoKey = seoKey;
    }

    public String getSeoTitle() {
        return seoTitle;
    }

    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle;
    }

    public String getSeoDescription() {
        return seoDescription;
    }

    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }

    public boolean isSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public boolean isGiveaway() {
        return giveaway;
    }

    public void setGiveaway(boolean giveaway) {
        this.giveaway = giveaway;
    }

    public boolean isBarter() {
        return barter;
    }

    public void setBarter(boolean barter) {
        this.barter = barter;
    }

    public boolean isPreSell() {
        return preSell;
    }

    public void setPreSell(boolean preSell) {
        this.preSell = preSell;
    }

    public boolean isForbidAirCargo() {
        return forbidAirCargo;
    }

    public void setForbidAirCargo(boolean forbidAirCargo) {
        this.forbidAirCargo = forbidAirCargo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    public String getPackingList() {
        return packingList;
    }

    public void setPackingList(String packingList) {
        this.packingList = packingList;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public int[] getSpecId() {
        return specId;
    }

    public void setSpecId(int[] specId) {
        this.specId = specId;
    }

    public String[] getSpecValue() {
        return specValue;
    }

    public void setSpecValue(String[] specValue) {
        this.specValue = specValue;
    }

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
