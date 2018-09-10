package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.meta.BusinessType;
import com.yunxin.cb.mall.entity.meta.OperationType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value="交易记录信息",description="交易记录信息VO对象 CustomerTradingRecordVO")
public class CustomerTradingRecordVO implements java.io.Serializable{
    private static final long serialVersionUID = 1872359766814469998L;

    @ApiModelProperty(value="客户id",name="customerId",example="1")
    private Integer customerId;

    @ApiModelProperty(value="业务类型",name="businessType",example="BALANBCE(\"余额\"), LOAN_EXPECTED_RETURN(\"贷款预期收益\"), LOAN_QUOTA(\"贷款额度\"), LOAN_ARREARS_AMOUNT(\"贷款欠款金额\"),GIVE_THE_THUMBS_UP(\"点赞收益\"),LOAN_EXPECTED_RETURN_FIFTY(\"贷款预期收益提升50%\")")
    private BusinessType businessType;

    @ApiModelProperty(value="操作类型",name="operationType",example="ADD(\"+\"), SUBTRACT(\"-\")")
    private OperationType operationType;

    @ApiModelProperty(value="操作余额 ",name="amount",example="100")
    private Double amount;

    @ApiModelProperty(value="创建时间 ",name="createTime",example="2018-10-05")
    private Date createTime;

    @ApiModelProperty(value="备注 ",name="remark",example="123")
    private String remark;


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        return "CustomerTradingRecordVO{" +
                "customerId=" + customerId +
                ", businessType=" + businessType +
                ", operationType=" + operationType +
                ", amount=" + amount +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}