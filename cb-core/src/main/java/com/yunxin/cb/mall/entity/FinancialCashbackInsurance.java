package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @Author chenpeng
 * @Description 保险返利 计划
 * @Date 2018/9/11 10:16
 **/
@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class FinancialCashbackInsurance implements java.io.Serializable {

    private static final long serialVersionUID = 6580675957901505137L;

    public enum State {
        PROCESSING, //返利中
        COMPLETE    //返利完成
    }

    /**
     * 保险返现id
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer cashbackId;

    /**
     * 用户
     */
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
     * 保险单号
     */
    private String insuranceNo;

    /**
     * 返现金额
     */
    private BigDecimal cashbackTotal;

    /**
     * 返现设置比率，多期以逗号(,)隔开
     */
    private String rate;

    /**
     * 下一期返利期数
     */
    private Integer nextTerm;

    /**
     * 下一期返利年份
     */
    private Integer nextYear;

    /**
     * 状态
     */
    private State state;

    /**
     * 返现时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    private Date createTime;


    public Integer getCashbackId() {
        return cashbackId;
    }

    public void setCashbackId(Integer cashbackId) {
        this.cashbackId = cashbackId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getInsuranceNo() {
        return insuranceNo;
    }

    public void setInsuranceNo(String insuranceNo) {
        this.insuranceNo = insuranceNo;
    }

    public BigDecimal getCashbackTotal() {
        return cashbackTotal;
    }

    public void setCashbackTotal(BigDecimal cashbackTotal) {
        this.cashbackTotal = cashbackTotal;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Integer getNextTerm() {
        return nextTerm;
    }

    public void setNextTerm(Integer nextTerm) {
        this.nextTerm = nextTerm;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Integer getNextYear() {
        return nextYear;
    }

    public void setNextYear(Integer nextYear) {
        this.nextYear = nextYear;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
