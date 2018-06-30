package com.yunxin.cb.mall.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  每月的总订单量(总或已付款)
 * <p>
 * Created by chenxing on 16/4/11.
 */
@Entity
@Table(name = "statistics_month_order_view")
public class StatisticsMonthOrderView {


    private int orderViewId;

    /**
     * 年
     */
    private int year;
    /**
     * 月
     */
    private int month;
    /**
     * 订单量
     */
    private long orderNum;

    @Id
    @Column(unique = true, nullable = false)
    public int getOrderViewId() {
        return orderViewId;
    }

    public void setOrderViewId(int orderDayViewId) {
        orderDayViewId = orderDayViewId;
    }


    @Column
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Column
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Column
    public long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(long orderNum) {
        this.orderNum = orderNum;
    }
}
