/*
 * Powered By [microlink-framework]
 * Web Site: http://www.microlinktech.net
 * Since 2015 - 2018
 */


package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
*  *
 * @author aidy www.microlinktech.net
 * @version 1.0
 * @since 1.0
 *
*/
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class CustomerWallet implements Serializable {

private static final long serialVersionUID = 1L;


    //columns START
    /**
    * 客户钱包id
    */
    @Max(9999999999L)
    private int customerId;
    /**
    * 可用余额
    */
    private Double availableBalance;
    /**
    * 预期收益金额
    */

    private Double expectedReturnAmount;
    /**
    * 可贷额度
    */

    private Double loanQuota;
    /**
    * 欠款金额
    */

    private Double arrearsAmount;
    /**
    * 创建时间
    */

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;
    /**
    * 更新时间
    */

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date updateTime;
    //columns END


	public CustomerWallet(){
	}

	public CustomerWallet(
		int customerId
	){
		this.customerId = customerId;
	}


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public int getCustomerId() {
    return this.customerId;
    }

    public void setCustomerId(int customerId) {
    this.customerId = customerId;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    public Double getAvailableBalance() {
    return this.availableBalance;
    }

    public void setAvailableBalance(Double availableBalance) {
    this.availableBalance = availableBalance;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    public Double getExpectedReturnAmount() {
    return this.expectedReturnAmount;
    }

    public void setExpectedReturnAmount(Double expectedReturnAmount) {
    this.expectedReturnAmount = expectedReturnAmount;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    public Double getLoanQuota() {
    return this.loanQuota;
    }

    public void setLoanQuota(Double loanQuota) {
    this.loanQuota = loanQuota;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    public Double getArrearsAmount() {
    return this.arrearsAmount;
    }

    public void setArrearsAmount(Double arrearsAmount) {
    this.arrearsAmount = arrearsAmount;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    public Date getCreateTime() {
    return this.createTime;
    }

    public void setCreateTime(Date createTime) {
    this.createTime = createTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 19)
    public Date getUpdateTime() {
    return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
    }


    private Customer customer;
    public void setCustomer(Customer customer){
    this.customer = customer;
    }

    @OneToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CUSTOMER_ID",nullable = false, insertable = true, updatable = true) 
    })
    public Customer getCustomer() {
    return customer;
    }



}