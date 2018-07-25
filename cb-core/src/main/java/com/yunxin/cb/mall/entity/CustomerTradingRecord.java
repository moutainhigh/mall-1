/*
 * Powered By [microlink-framework]
 * Web Site: http://www.microlinktech.net
 * Since 2015 - 2018
 */


package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.meta.BusinessType;
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
public class CustomerTradingRecord implements Serializable {

private static final long serialVersionUID = 1L;


    //columns START
    /**
    * 交易流水id
    */
    @Max(9999999999L)
    private int tradeRecodeId;
    /**
     * 客户
     */
    private Customer customer;

    /**
     * 业务类型
     */
    private BusinessType businessType;

    /**
     * 操作类型:0增加，1减少
     */
    private OperationType operationType;

    /**
    * 操作金额
    */
    private Double amount;

    /**
    * 创建时间
    */

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;
    /**
    * 备注
    */
    private String remark;
    //columns END


	public CustomerTradingRecord(){
	}

	public CustomerTradingRecord(
		int tradeRecodeId
	){
		this.tradeRecodeId = tradeRecodeId;
	}

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, insertable = true, updatable = true, length = 10)
    public int getTradeRecodeId() {
        return this.tradeRecodeId;
    }

    public void setTradeRecodeId(int tradeRecodeId) {
        this.tradeRecodeId = tradeRecodeId;
    }

    @Column(nullable = true, precision = 2)
    @Enumerated(EnumType.ORDINAL)
    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    @Column(nullable = false, precision = 1)
    @Enumerated(EnumType.ORDINAL)
    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    @Column(unique = false, nullable = true, insertable = true, updatable = true, length = 22)
    public Double getAmount() {
    return this.amount;
    }

    public void setAmount(Double amount) {
    this.amount = amount;
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

    @Column(length = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setCustomer(Customer customer){
    this.customer = customer;
    }

    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "CUSTOMER_ID",nullable = false, insertable = true, updatable = true) 
    })
    public Customer getCustomer() {
    return customer;
    }



}