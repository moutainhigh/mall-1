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
@Table(name = "statistics_month_money_paid_view")
public class StatisticsMonthMoneyPaidView {


    private int moneyViewId;

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
    private double orderPrice;

    @Id
    @Column(unique = true, nullable = false)
    public int getMoneyViewId() {
        return moneyViewId;
    }

    public void setMoneyViewId(int orderDayViewId) {
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
    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderNum) {
        this.orderPrice = orderNum;
    }

}
