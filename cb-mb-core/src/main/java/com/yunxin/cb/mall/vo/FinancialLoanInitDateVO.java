package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

@ApiModel(value = "用户借款申请初始页面数据VO", description = "用户借款申请初始页面数据VO FinancialLoanInitDateVO")
public class FinancialLoanInitDateVO {
    private static final long serialVersionUID = -2695946271501714063L;

    @ApiModelProperty(value = "可贷额度", name = "totalAmount", example = "100")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "借款期限列表", name = "financialLoanConfigVOList", example = "financialLoanConfigVOList")
    private List<FinancialLoanConfigVO> financialLoanConfigVOList;

    @ApiModelProperty(value = "用户绑定的银行卡", name = "bankInfoVO", example = "bankInfoVO")
    private BankInfoVO bankInfoVO;

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal amount) {
        this.totalAmount = amount;
    }

    public List<FinancialLoanConfigVO> getFinancialLoanConfigVOList() {
        return financialLoanConfigVOList;
    }

    public void setFinancialLoanConfigVOList(List<FinancialLoanConfigVO> financialLoanConfigVOList) {
        this.financialLoanConfigVOList = financialLoanConfigVOList;
    }

    public BankInfoVO getBankInfoVO() {
        return bankInfoVO;
    }

    public void setBankInfoVO(BankInfoVO bankInfoVO) {
        this.bankInfoVO = bankInfoVO;
    }

    @Override
    public String toString() {
        return "FinancialLoanInitDateVO{" +
                "amount=" + totalAmount +
                ", financialLoanConfigVOList=" + financialLoanConfigVOList +
                ", bankInfoVO=" + bankInfoVO +
                '}';
    }
}