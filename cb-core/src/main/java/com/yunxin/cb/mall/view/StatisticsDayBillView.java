package com.yunxin.cb.mall.view;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 账单统计
 * <p>
 */
@Entity
@Table(name = "statistics_day_bill_view")
public class StatisticsDayBillView {


    @Id
    private int billId;

    @Temporal(TemporalType.DATE)
    @Column
    @JsonSerialize(using = JsonTimestampSerializer.class)
    private Date createTime;
    /**
     * 年
     */
    @Column
    private int year;
    /**
     * 月
     */
    @Column
    private int month;
    /**
     * 天
     */
    @Column
    private int day;
    /**
     * 金额
     */
    @Column
    private BigDecimal amount;

    /**
     * 交易类型：1.支出，2.收入
     */
    @Column
    private Integer type;

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
