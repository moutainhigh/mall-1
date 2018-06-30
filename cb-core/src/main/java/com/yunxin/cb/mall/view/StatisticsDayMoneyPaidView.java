package com.yunxin.cb.mall.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;

import javax.persistence.*;
import java.util.Date;

/**
 * 每天的总订单金额(已付款)
 * <p>
 * Created by chenxing on 16/4/11.
 */
@Entity
@Table(name = "statistics_day_money_paid_view")
public class StatisticsDayMoneyPaidView {


    private int moneyViewId;

    private Date createTime;
    /**
     * 年
     */
    private int year;
    /**
     * 月
     */
    private int month;
    /**
     * 天
     */
    private int day;
    /**
     * 订单金额
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

    @Column
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Temporal(TemporalType.DATE)
    @Column()
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
