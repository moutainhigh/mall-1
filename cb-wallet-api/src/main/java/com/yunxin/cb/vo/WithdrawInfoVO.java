package com.yunxin.cb.vo;

import com.yunxin.cb.mall.vo.BankInfoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @title: 提现申请页面VO
 * @auther: eleven
 * @date: 2018/8/8 15:42
 */
@ApiModel(value="提现申请页面VO",description="提现申请页面VO")
public class WithdrawInfoVO implements java.io.Serializable{

    private static final long serialVersionUID = -244395950984398748L;

    @ApiModelProperty(value="用户所有银行卡",name="bankInfoVOS",example="1")
    private List<BankInfoVO> bankInfoVOS;

    @ApiModelProperty(value="提现手续费率",name="freeRate",example="0.1")
    private BigDecimal freeRate;

    @ApiModelProperty(value="可用余额",name="balance",example="100")
    private BigDecimal balance;

    public List<BankInfoVO> getBankInfoVOS() {
        return bankInfoVOS;
    }

    public void setBankInfoVOS(List<BankInfoVO> bankInfoVOS) {
        this.bankInfoVOS = bankInfoVOS;
    }

    public BigDecimal getFreeRate() {
        return freeRate;
    }

    public void setFreeRate(BigDecimal freeRate) {
        this.freeRate = freeRate;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "WithdrawInfoVO{" +
                "bankInfoVOS=" + bankInfoVOS +
                ", freeRate=" + freeRate +
                ", balance=" + balance +
                '}';
    }
}
