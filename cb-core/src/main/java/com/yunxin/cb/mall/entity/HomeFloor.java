package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.yunxin.cb.mall.entity.meta.FloorLayout;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 首页楼层
 */
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class HomeFloor implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * Id
     */

    private int floorId;
    /**
     * 楼层名称
     */

    private String floorName;

    /**
     * 排序
     */

    private int sortOrder;

    private int categoryAmount;

    private int commodityAmount;
    private int brandAmount;

    private int advertAmount;

    /**
     * 模板ID
     */
    private FloorLayout floorLayout;

    /**
     * 图片路径
     */
    private String imagePath;

    /**
     * 图标路径
     */
    private String iconPath;
    /**
     * 备注
     */

    private String remark;

    /**
     * 状态：true 启用；false 停用
     */
    private boolean enabled;


    private Set<FloorCategory> floorCategories = new LinkedHashSet<>();

    private Set<FloorCommodity> floorCommodities = new LinkedHashSet<FloorCommodity>();

    private Set<FloorBrand> floorBrands = new LinkedHashSet<>();

    private Set<FloorAdvert> floorAdverts = new LinkedHashSet<>();
    private int[] categoryId;
    /**
     * 分类排序
     */
    private int[] categoryOrder;

    private int[] commodityId;
    /**
     * 商品排序
     */
    private int[] commodityOrder;

    private int[] brandId;
    /**
     * 品牌排序
     */
    private int[] brandOrder;

    private int[] advertId;

    private int[] advertOrder;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int serviceRuleId) {
        this.floorId = serviceRuleId;
    }


    @Column(nullable = false, length = 32)
    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String serviceRuleName) {
        this.floorName = serviceRuleName;
    }

    @Column(nullable = false)
    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int yearLimit) {
        this.sortOrder = yearLimit;
    }


    @Column(nullable = true, length = 255)
    public String getRemark() {
        return remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(nullable = false, length = 1)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean status) {
        this.enabled = status;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "homeFloor", cascade = CascadeType.REMOVE)
    @OrderBy(value = "sortOrder")
    public Set<FloorCategory> getFloorCategories() {
        return floorCategories;
    }

    public void setFloorCategories(Set<FloorCategory> goods) {
        this.floorCategories = goods;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "homeFloor", cascade = CascadeType.REMOVE)
    @OrderBy(value = "sortOrder")
    public Set<FloorCommodity> getFloorCommodities() {
        return floorCommodities;
    }

    public void setFloorCommodities(Set<FloorCommodity> floorCommodities) {
        this.floorCommodities = floorCommodities;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "homeFloor", cascade = CascadeType.REMOVE)
    @OrderBy(value = "sortOrder")
    public Set<FloorBrand> getFloorBrands() {
        return floorBrands;
    }

    public void setFloorBrands(Set<FloorBrand> floorBrands) {
        this.floorBrands = floorBrands;
    }

    @Column(nullable = false, length = 255)
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Column(nullable = false, length = 255)
    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    public FloorLayout getFloorLayout() {
        return floorLayout;
    }

    public void setFloorLayout(FloorLayout templateId) {
        this.floorLayout = templateId;
    }

    public int getCategoryAmount() {
        return categoryAmount;
    }

    public void setCategoryAmount(int categoryAmount) {
        this.categoryAmount = categoryAmount;
    }

    public int getCommodityAmount() {
        return commodityAmount;
    }

    public void setCommodityAmount(int commodityAmount) {
        this.commodityAmount = commodityAmount;
    }

    public int getBrandAmount() {
        return brandAmount;
    }

    public void setBrandAmount(int brandAmount) {
        this.brandAmount = brandAmount;
    }

    @Transient
    public int[] getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int[] categoryId) {
        this.categoryId = categoryId;
    }

    @Transient
    public int[] getCategoryOrder() {
        return categoryOrder;
    }

    public void setCategoryOrder(int[] categoryOrder) {
        this.categoryOrder = categoryOrder;
    }

    @Transient
    public int[] getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int[] commodityId) {
        this.commodityId = commodityId;
    }

    @Transient
    public int[] getCommodityOrder() {
        return commodityOrder;
    }

    public void setCommodityOrder(int[] commodityOrder) {
        this.commodityOrder = commodityOrder;
    }

    @Transient
    public int[] getBrandId() {
        return brandId;
    }

    public void setBrandId(int[] brandId) {
        this.brandId = brandId;
    }

    @Transient
    public int[] getBrandOrder() {
        return brandOrder;
    }

    public void setBrandOrder(int[] brandOrder) {
        this.brandOrder = brandOrder;
    }
    @Transient
    public int[] getAdvertId() {
        return advertId;
    }

    public void setAdvertId(int[] advertId) {
        this.advertId = advertId;
    }
    @Transient
    public int[] getAdvertOrder() {
        return advertOrder;
    }

    public void setAdvertOrder(int[] advertOrder) {
        this.advertOrder = advertOrder;
    }
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "homeFloor", cascade = CascadeType.REMOVE)
    @OrderBy(value = "sortOrder")
    public Set<FloorAdvert> getFloorAdverts() {
        return floorAdverts;
    }

    public void setFloorAdverts(Set<FloorAdvert> floorAdverts) {
        this.floorAdverts = floorAdverts;
    }

    public int getAdvertAmount() {
        return advertAmount;
    }

    public void setAdvertAmount(int advertAmount) {
        this.advertAmount = advertAmount;
    }
}
