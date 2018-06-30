/**
 * @author qulei
 */
package com.yunxin.cb.mall.query;

import com.yunxin.cb.mall.entity.OrderItem;
import com.yunxin.core.persistence.PageSpecification;

public class HotSalesQuery extends PageSpecification<OrderItem> {

    private int topNum;
    private int year;
    private int page;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTopNum() {
        return topNum;
    }

    public void setTopNum(int topNum) {
        this.topNum = topNum;
    }

}
