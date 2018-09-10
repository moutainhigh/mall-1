package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.meta.FinancialLogPayType;
import com.yunxin.cb.mall.entity.meta.OperationType;
import com.yunxin.cb.mall.entity.meta.PayState;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 我的账单详情VO
 */
@ApiModel(value="账单详情VO",description="账单详情VO对象 FinancialLogDetailVO")
public class FinancialLogDetailVO implements java.io.Serializable{

    private static final long serialVersionUID = 6284257505587256161L;

    /** 交易金额 */
    @ApiModelProperty(value="交易金额",name="amount",example="100.00")
    private BigDecimal amount;

    /** 交易类型：0.收入，1.支出 */
    @ApiModelProperty(value="交易类型",name="type",example="1")
    private OperationType type;

    /** 支付方式：0.微信，1.支付宝，2.报账，3.还款 */
    @ApiModelProperty(value="支付方式",name="payType",example="1")
    private FinancialLogPayType payType;

    /** 交易时间 */
    @ApiModelProperty(value="交易时间",name="createTime",example="2018-08-09 17:52:50")
    private Date createTime;

    /** 交易状态 */
    @ApiModelProperty(value="交易状态",name="state",example="1")
    private PayState state;

    /** 交易订单号 */
    @ApiModelProperty(value="交易订单号",name="transactionNo",example="200012145")
    private String transactionNo;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public FinancialLogPayType getPayType() {
        return payType;
    }

    public void setPayType(FinancialLogPayType payType) {
        this.payType = payType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public PayState getState() {
        return state;
    }

    public void setState(PayState state) {
        this.state = state;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }
}
