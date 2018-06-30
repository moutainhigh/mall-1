package com.yunxin.cb.mall.query;

import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.core.persistence.PageSpecification;

import java.util.List;

public class WebSearchQuery extends PageSpecification<Commodity> {

    private int categoryId;

    private List<Integer> brandIds;

    private String keyword;

    private String sortField;
    //属性组
    private List<Integer> propers;
    //属性
    private List<Integer> properValues;
    //属性值
    private List<Integer> propertyValues;

    private String asc;


    private Double startPrice;

    private Double endPrice;

    // type 1.工艺品，家具   2 私人订制 高级定制
    private int type;

    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public Double getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(Double endPrice) {
        this.endPrice = endPrice;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAsc() {
        return asc;
    }

    public void setAsc(String asc) {
        this.asc = asc;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public List<Integer> getBrandIds() {
        return brandIds;
    }

    public void setBrandIds(List<Integer> brandIds) {
        this.brandIds = brandIds;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public List<Integer> getPropers() {
        return propers;
    }

    public void setPropers(List<Integer> propers) {
        this.propers = propers;
    }

    public List<Integer> getProperValues() {
        return properValues;
    }

    public void setProperValues(List<Integer> properValues) {
        this.properValues = properValues;
    }


    public List<Integer> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(List<Integer> propertyValues) {
        this.propertyValues = propertyValues;
    }
}



