/**
 *
 */
package com.yunxin.cb.mall.query;

import com.yunxin.cb.mall.entity.Catalog;
import com.yunxin.core.persistence.PageSpecification;


/**
 * @author z001376
 */
public class CategoryQuery extends PageSpecification<Catalog> {

    private int categoryId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
