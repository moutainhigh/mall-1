package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

@ApiModel(value = "用户借款申请初始页面数据VO", description = "用户借款申请初始页面数据VO FinacialLoanVO")
public class FinacialLoanInitDateVO {
    private static final long serialVersionUID = -2695946271501714063L;

    @ApiModelProperty(value = "可贷额度", name = "amount", example = "100")
    private BigDecimal amount;

    @ApiModelProperty(value = "借款期限列表", name = "finacialLoanConfigVOList", example = "finacialLoanConfigVOList")
    private List<FinacialLoanConfigVO> finacialLoanConfigVOList;

    @ApiModelProperty(value = "用户绑定的银行卡", name = "bankInfoVO", example = "bankInfoVO")
    private BankInfoVO bankInfoVO;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<FinacialLoanConfigVO> getFinacialLoanConfigVOList() {
        return finacialLoanConfigVOList;
    }

    public void setFinacialLoanConfigVOList(List<FinacialLoanConfigVO> finacialLoanConfigVOList) {
        this.finacialLoanConfigVOList = finacialLoanConfigVOList;
    }

    public BankInfoVO getBankInfoVO() {
        return bankInfoVO;
    }

    public void setBankInfoVO(BankInfoVO bankInfoVO) {
        this.bankInfoVO = bankInfoVO;
    }

    @Override
    public String toString() {
        return "FinacialLoanInitDateVO{" +
                "amount=" + amount +
                ", finacialLoanConfigVOList=" + finacialLoanConfigVOList +
                ", bankInfoVO=" + bankInfoVO +
                '}';
    }
}