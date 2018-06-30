/**
 *
 */
package com.yunxin.cb.mall.query;

import com.yunxin.cb.mall.entity.Category;
import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.entity.PriceSection;
import com.yunxin.core.persistence.PageSpecification;

/**
 * @author gonglei
 */
public class CommodityQuery extends PageSpecification<Commodity> {


    private Category category;

    private String[] itemId;

    private int sortIndex;

    private PriceSection priceSection;

    public CommodityQuery() {
    }

    public CommodityQuery(int page, int pageSize, Category category) {
        super(page, pageSize);
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String[] getItemId() {
        return itemId;
    }

    public void setItemId(String[] itemId) {
        this.itemId = itemId;
    }

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }

    public PriceSection getPriceSection() {
        return priceSection;
    }

    public void setPriceSection(PriceSection priceSection) {
        this.priceSection = priceSection;
    }
}
