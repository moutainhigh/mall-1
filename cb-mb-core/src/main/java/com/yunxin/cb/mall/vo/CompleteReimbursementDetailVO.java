package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@ApiModel(value="完成报账详情对象",description="完成报账详情对象 CompleteReimbursementDetailVO")
public class CompleteReimbursementDetailVO implements Serializable {
    private static final long serialVersionUID = -6469509272706114102L;

    @ApiModelProperty(value="报账单号",name="reimbursementNo",example="52525525")
    private String reimbursementNo;
    @ApiModelProperty(value="货品信息",name="list",example="货品信息")
    private List<ReimbursementProductVO> list;
    @ApiModelProperty(value="到账金额",name="accountAmount",example="111")
    private BigDecimal accountAmount;
    @ApiModelProperty(value="报账信息状态",name="orderState",example="1")
    private int orderState;
    @ApiModelProperty(value="操作时间",name="operationTime",example="2018-08-08 15:20:20")
    private Date operationTime;
    @ApiModelProperty(value="支付方式",name="payment",example="钱包")
    private int payment;
    @ApiModelProperty(value="自动还款金额",name="repayment",example="100")
    private BigDecimal repayment;
    @ApiModelProperty(value="实际到账金额",name="accountAmount",example="111")
    private BigDecimal actualAccount;

    public String getReimbursementNo() {
        return reimbursementNo;
    }

    public void setReimbursementNo(String reimbursementNo) {
        this.reimbursementNo = reimbursementNo;
    }

    public List<ReimbursementProductVO> getList() {
        return list;
    }

    public void setList(List<ReimbursementProductVO> list) {
        this.list = list;
    }

    public BigDecimal getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public BigDecimal getRepayment() {
        return repayment;
    }

    public void setRepayment(BigDecimal repayment) {
        this.repayment = repayment;
    }

    public BigDecimal getActualAccount() {
        return actualAccount;
    }

    public void setActualAccount(BigDecimal actualAccount) {
        this.actualAccount = actualAccount;
    }
}
