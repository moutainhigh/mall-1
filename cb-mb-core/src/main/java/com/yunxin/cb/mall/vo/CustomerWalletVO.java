package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value="钱包信息",description="钱包信息VO对象 CustomerWalletVO")
public class CustomerWalletVO implements java.io.Serializable{

    private static final long serialVersionUID = 1872359766814469998L;

    @ApiModelProperty(value="客户id",name="customerId",example="1")
    private Integer customerId;

    @ApiModelProperty(value="可用余额",name="availableBalance",example="100")
    private Double availableBalance;

    @ApiModelProperty(value="预期收益金额",name="expectedReturnAmount",example="100")
    private Double expectedReturnAmount;

    @ApiModelProperty(value="可贷额度",name="loanQuota",example="100")
    private Double loanQuota;

    @ApiModelProperty(value="欠款金额 ",name="arrearsAmount",example="100")
    private Double arrearsAmount;

    @ApiModelProperty(value="创建时间 ",name="createTime",example="2018-05-05")
    private Date createTime;

    @ApiModelProperty(value="创建时间 ",name="updateTime",example="2018-05-05")
    private Date updateTime;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public Double getExpectedReturnAmount() {
        return expectedReturnAmount;
    }

    public void setExpectedReturnAmount(Double expectedReturnAmount) {
        this.expectedReturnAmount = expectedReturnAmount;
    }

    public Double getLoanQuota() {
        return loanQuota;
    }

    public void setLoanQuota(Double loanQuota) {
        this.loanQuota = loanQuota;
    }

    public Double getArrearsAmount() {
        return arrearsAmount;
    }

    public void setArrearsAmount(Double arrearsAmount) {
        this.arrearsAmount = arrearsAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CustomerWalletVO{" +
                "customerId=" + customerId +
                ", availableBalance=" + availableBalance +
                ", expectedReturnAmount=" + expectedReturnAmount +
                ", loanQuota=" + loanQuota +
                ", arrearsAmount=" + arrearsAmount +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
