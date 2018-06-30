package com.yunxin.cb.mall.query;

import com.yunxin.cb.mall.entity.PropertyFilter;
import com.yunxin.core.persistence.PageSpecification;

public class PriceSegQuery extends PageSpecification<PropertyFilter> {
    private int categoryId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

}
