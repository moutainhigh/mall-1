package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.meta.CapitalType;
import com.yunxin.cb.mall.entity.meta.TransactionType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "用户预期收益交易VO", description = "用户预期收益交易VO FinacialExpectBillVO")
public class FinacialExpectBillVO implements java.io.Serializable{
    private static final long serialVersionUID = -2695946271501714063L;
    /**  */
    private Integer finacialExpectId;

    @ApiModelProperty(value = "客户ID", name = "customerId", example = "1")
    private Integer customerId;

    @ApiModelProperty(value = "资金类型", name = "type", example = "ADD")
    private CapitalType type;

    @ApiModelProperty(value = "交易类型", name = "transactionType", example = "INSURANCE_REBATE")
    private TransactionType transactionType;

    @ApiModelProperty(value = "交易描述", name = "transactionDesc", example = "**购买保险50%")
    private String transactionDesc;

    @ApiModelProperty(value = "交易金", name = "amount", example = "5000")
    private BigDecimal amount;

    @ApiModelProperty(value = "交易时间 ", name = "createTime", example = "2018-08-08")
    private Date createTime;

    public Integer getFinacialExpectId() {
        return finacialExpectId;
    }

    public void setFinacialExpectId(Integer finacialExpectId) {
        this.finacialExpectId = finacialExpectId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public CapitalType getType() {
        return type;
    }

    public void setType(CapitalType type) {
        this.type = type;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc == null ? null : transactionDesc.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}