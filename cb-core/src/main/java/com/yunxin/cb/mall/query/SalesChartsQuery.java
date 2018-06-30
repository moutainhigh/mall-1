package com.yunxin.cb.mall.query;

import com.yunxin.cb.mall.entity.OrderItem;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SalesChartsQuery extends PageSpecification<OrderItem> {

    private int year;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date queryDate;


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Date getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(Date queryDate) {
        this.queryDate = queryDate;
    }

}
