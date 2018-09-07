package com.yunxin.cb.mall.entity;

/**
 * @title: 价格段实体类
 * @auther: eleven
 * @date: 2018/7/19 19:52
 */
public class PriceSection {
    /** 价格段id */
    private Integer sectionId;

    /** 状态：true 启用；false 停用 */
    private Boolean enabled;

    /** 结束价格 */
    private Integer endPrice;

    /** 备注 */
    private String remark;

    /** 价格段编号 */
    private String sectionNo;

    /** 起始价格 */
    private Integer startPrice;

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(Integer endPrice) {
        this.endPrice = endPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSectionNo() {
        return sectionNo;
    }

    public void setSectionNo(String sectionNo) {
        this.sectionNo = sectionNo == null ? null : sectionNo.trim();
    }

    public Integer getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Integer startPrice) {
        this.startPrice = startPrice;
    }
}