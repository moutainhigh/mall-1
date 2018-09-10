package com.yunxin.cb.mall.entity;

import org.springframework.stereotype.Repository;

/**
 * @title: 规格实体类
 * @auther: eleven
 * @date: 2018/7/18 17:40
 */
public class Spec {
    /** 规格id */
    private Integer specId;

    /** 备注 */
    private String remark;

    /** 规格名称 */
    private String specName;

    /** 商品分类id */
    private Integer catalogId;

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName == null ? null : specName.trim();
    }

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }
}