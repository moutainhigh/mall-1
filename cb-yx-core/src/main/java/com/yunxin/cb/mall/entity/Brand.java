package com.yunxin.cb.mall.entity;

import java.util.Date;

public class Brand {
    /** 品牌id */
    private Integer brandId;

    /** 品牌编码 */
    private String brandNo;

    /** 品牌中文名称 */
    private String brandName;

    /** 品牌英文名称 */
    private String brandEnName;

    /** 品牌标题 */
    private String brandTitle;

    /** 品牌关键字 */
    private String brandKey;

    /** 品牌网站 */
    private String website;

    /** 品牌描述 */
    private String description;

    /** 搜索描述 */
    private String seoDescription;

    /** 搜索关键字 */
    private String seoKey;

    /** 搜索标题 */
    private String seoTitle;

    /** 是否显示 */
    private Boolean display;

    /** 是否启用（1启用，0禁用） */
    private Boolean enabled;

    /** 是否热门品牌 */
    private Boolean hot;

    /** 热门品牌排序 */
    private Integer sort;

    /** 大图图片路径 */
    private String bigPicPath;

    /** 中等图片路径 */
    private String mediumPicPath;

    /** 小图图片路径 */
    private String smallPicPath;

    /** 创建时间 */
    private Date createTime;

    /** 备注 */
    private String remark;

    /** 运营分类id */
    private Integer categoryId;

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandNo() {
        return brandNo;
    }

    public void setBrandNo(String brandNo) {
        this.brandNo = brandNo == null ? null : brandNo.trim();
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getBrandEnName() {
        return brandEnName;
    }

    public void setBrandEnName(String brandEnName) {
        this.brandEnName = brandEnName == null ? null : brandEnName.trim();
    }

    public String getBrandTitle() {
        return brandTitle;
    }

    public void setBrandTitle(String brandTitle) {
        this.brandTitle = brandTitle == null ? null : brandTitle.trim();
    }

    public String getBrandKey() {
        return brandKey;
    }

    public void setBrandKey(String brandKey) {
        this.brandKey = brandKey == null ? null : brandKey.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getHot() {
        return hot;
    }

    public void setHot(Boolean hot) {
        this.hot = hot;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getBigPicPath() {
        return bigPicPath;
    }

    public void setBigPicPath(String bigPicPath) {
        this.bigPicPath = bigPicPath == null ? null : bigPicPath.trim();
    }

    public String getMediumPicPath() {
        return mediumPicPath;
    }

    public void setMediumPicPath(String mediumPicPath) {
        this.mediumPicPath = mediumPicPath == null ? null : mediumPicPath.trim();
    }

    public String getSmallPicPath() {
        return smallPicPath;
    }

    public void setSmallPicPath(String smallPicPath) {
        this.smallPicPath = smallPicPath == null ? null : smallPicPath.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}