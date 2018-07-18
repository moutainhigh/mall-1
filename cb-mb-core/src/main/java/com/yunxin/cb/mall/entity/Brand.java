package com.yunxin.cb.mall.entity;

import java.util.Date;

public class Brand implements java.io.Serializable{
    private static final long serialVersionUID = 1502774904693173299L;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column brand.BRAND_ID
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    private Integer brandId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column brand.BRAND_EN_NAME
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    private String brandEnName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column brand.BRAND_KEY
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    private String brandKey;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column brand.BRAND_NAME
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    private String brandName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column brand.BRAND_NO
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    private String brandNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column brand.BRAND_TITLE
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    private String brandTitle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column brand.CREATE_TIME
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column brand.DESCRIPTION
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column brand.DISPLAY
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    private Boolean display;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column brand.ENABLED
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    private Boolean enabled;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column brand.HOT
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    private Boolean hot;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column brand.PIC_PATH
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    private String picPath;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column brand.REMARK
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column brand.SEO_DESCRIPTION
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    private String seoDescription;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column brand.SEO_KEY
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    private String seoKey;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column brand.SEO_TITLE
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    private String seoTitle;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column brand.WEBSITE
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    private String website;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column brand.CATEGORY_ID
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    private Integer categoryId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column brand.BRAND_ID
     *
     * @return the value of brand.BRAND_ID
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public Integer getBrandId() {
        return brandId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column brand.BRAND_ID
     *
     * @param brandId the value for brand.BRAND_ID
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column brand.BRAND_EN_NAME
     *
     * @return the value of brand.BRAND_EN_NAME
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public String getBrandEnName() {
        return brandEnName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column brand.BRAND_EN_NAME
     *
     * @param brandEnName the value for brand.BRAND_EN_NAME
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public void setBrandEnName(String brandEnName) {
        this.brandEnName = brandEnName == null ? null : brandEnName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column brand.BRAND_KEY
     *
     * @return the value of brand.BRAND_KEY
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public String getBrandKey() {
        return brandKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column brand.BRAND_KEY
     *
     * @param brandKey the value for brand.BRAND_KEY
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public void setBrandKey(String brandKey) {
        this.brandKey = brandKey == null ? null : brandKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column brand.BRAND_NAME
     *
     * @return the value of brand.BRAND_NAME
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column brand.BRAND_NAME
     *
     * @param brandName the value for brand.BRAND_NAME
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column brand.BRAND_NO
     *
     * @return the value of brand.BRAND_NO
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public String getBrandNo() {
        return brandNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column brand.BRAND_NO
     *
     * @param brandNo the value for brand.BRAND_NO
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public void setBrandNo(String brandNo) {
        this.brandNo = brandNo == null ? null : brandNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column brand.BRAND_TITLE
     *
     * @return the value of brand.BRAND_TITLE
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public String getBrandTitle() {
        return brandTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column brand.BRAND_TITLE
     *
     * @param brandTitle the value for brand.BRAND_TITLE
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public void setBrandTitle(String brandTitle) {
        this.brandTitle = brandTitle == null ? null : brandTitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column brand.CREATE_TIME
     *
     * @return the value of brand.CREATE_TIME
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column brand.CREATE_TIME
     *
     * @param createTime the value for brand.CREATE_TIME
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column brand.DESCRIPTION
     *
     * @return the value of brand.DESCRIPTION
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column brand.DESCRIPTION
     *
     * @param description the value for brand.DESCRIPTION
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column brand.DISPLAY
     *
     * @return the value of brand.DISPLAY
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public Boolean getDisplay() {
        return display;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column brand.DISPLAY
     *
     * @param display the value for brand.DISPLAY
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public void setDisplay(Boolean display) {
        this.display = display;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column brand.ENABLED
     *
     * @return the value of brand.ENABLED
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column brand.ENABLED
     *
     * @param enabled the value for brand.ENABLED
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column brand.HOT
     *
     * @return the value of brand.HOT
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public Boolean getHot() {
        return hot;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column brand.HOT
     *
     * @param hot the value for brand.HOT
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public void setHot(Boolean hot) {
        this.hot = hot;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column brand.PIC_PATH
     *
     * @return the value of brand.PIC_PATH
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public String getPicPath() {
        return picPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column brand.PIC_PATH
     *
     * @param picPath the value for brand.PIC_PATH
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? null : picPath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column brand.REMARK
     *
     * @return the value of brand.REMARK
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column brand.REMARK
     *
     * @param remark the value for brand.REMARK
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column brand.SEO_DESCRIPTION
     *
     * @return the value of brand.SEO_DESCRIPTION
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public String getSeoDescription() {
        return seoDescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column brand.SEO_DESCRIPTION
     *
     * @param seoDescription the value for brand.SEO_DESCRIPTION
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription == null ? null : seoDescription.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column brand.SEO_KEY
     *
     * @return the value of brand.SEO_KEY
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public String getSeoKey() {
        return seoKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column brand.SEO_KEY
     *
     * @param seoKey the value for brand.SEO_KEY
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public void setSeoKey(String seoKey) {
        this.seoKey = seoKey == null ? null : seoKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column brand.SEO_TITLE
     *
     * @return the value of brand.SEO_TITLE
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public String getSeoTitle() {
        return seoTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column brand.SEO_TITLE
     *
     * @param seoTitle the value for brand.SEO_TITLE
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public void setSeoTitle(String seoTitle) {
        this.seoTitle = seoTitle == null ? null : seoTitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column brand.WEBSITE
     *
     * @return the value of brand.WEBSITE
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public String getWebsite() {
        return website;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column brand.WEBSITE
     *
     * @param website the value for brand.WEBSITE
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column brand.CATEGORY_ID
     *
     * @return the value of brand.CATEGORY_ID
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column brand.CATEGORY_ID
     *
     * @param categoryId the value for brand.CATEGORY_ID
     *
     * @mbg.generated Wed Jul 18 14:35:20 CST 2018
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}