/**
 *
 */
package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hankcs.lucene.HanLPAnalyzer;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 品牌
 */
//@Indexed(index = "Brand")
//@Analyzer(impl = HanLPAnalyzer.class)
//@Cacheable(true)
@Entity
@Table
@DynamicInsert
@DynamicUpdate
@NamedEntityGraph(name = "Brand.category", attributeNodes = @NamedAttributeNode("category"))
public class Brand implements java.io.Serializable {

    private int brandId;
    /**
     * 品牌编号
     */
    private String brandNo;
    /**
     * 品牌中文名称
     */
    private String brandName;
    /**
     * 品牌英文名称
     */
    private String brandEnName;
    /**
     * 品牌标题
     */
    private String brandTitle;
    /**
     * 品牌关键字
     */
    private String brandKey;
    /**
     * 品牌网站
     */
    private String website;
    /**
     * 是否显示
     */
    private boolean display;
    /**
     * 图片路径  150*58 png
     */
    private String picPath;
    /**
     * 品牌描述
     */
    private String description;
    /**
     * 搜索关键字
     */
    private String seoKey;
    /**
     * 搜索标题
     */
    private String seoTitle;
    /**
     * 搜索描述
     */
    private String seoDescription;
    /**
     * 是否热门品牌
     */
    private boolean hot;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 状态：true 启用；false 停用
     */
    private boolean enabled;
    /**
     * 备注
     */
    private String remark;

    private Category category;

    private Set<Commodity> commodities = new HashSet<>();

    public Brand() {
    }
    public Brand(int brandId) {
        this.brandId = brandId;
    }
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    @DocumentId
    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    @Column(nullable = false, length = 32)
    public String getBrandNo() {
        return brandNo;
    }

    public void setBrandNo(String brandNo) {
        this.brandNo = brandNo;
    }

    @Column(nullable = false, length = 128)
    @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.YES, store = org.hibernate.search.annotations.Store.COMPRESS)
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Column(length = 128)
    public String getBrandEnName() {
        return brandEnName;
    }

    public void setBrandEnName(String brandEnName) {
        this.brandEnName = brandEnName;
    }

    @Column(nullable = false, length = 128)
    public String getBrandTitle() {
        return brandTitle;
    }

    public void setBrandTitle(String brandTitle) {
        this.brandTitle = brandTitle;
    }

    @Column(nullable = false, length = 32)
    public String getBrandKey() {
        return brandKey;
    }

    public void setBrandKey(String brandKey) {
        this.brandKey = brandKey;
    }

    @Column(length = 255)
    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Column(nullable = false, precision = 1)
    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    @Column(nullable = true, length = 255)
    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
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

    @Column(nullable = false, precision = 1)
    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
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
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column(nullable = true, length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", nullable = true)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ContainedIn
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand", cascade = CascadeType.REMOVE)
    public Set<Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(Set<Commodity> commodities) {
        this.commodities = commodities;
    }
}
