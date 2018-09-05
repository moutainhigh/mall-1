package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @title: 账单数据VO
 * @auther: eleven
 * @date: 2018/8/9 19:08
 */
@ApiModel(value="账单数据VO",description="账单数据VO对象 FinancialLogDataVO")
public class FinancialLogDataVO {

    @ApiModelProperty(value="账单数据",name="financialLogVOS",example="financialLogVOS")
    private List<FinancialLogVO> financialLogVO;

    @ApiModelProperty(value="收入",name="addTotalAmount",example="100.00")
    private BigDecimal addTotalAmount;

    @ApiModelProperty(value="支出",name="subTotalAmount",example="100.00")
    private BigDecimal subTotalAmount;

    public List<FinancialLogVO> getFinancialLogVO() {
        return financialLogVO;
    }

    public void setFinancialLogVO(List<FinancialLogVO> financialLogVO) {
        this.financialLogVO = financialLogVO;
    }

    public BigDecimal getAddTotalAmount() {
        return addTotalAmount;
    }

    public void setAddTotalAmount(BigDecimal addTotalAmount) {
        this.addTotalAmount = addTotalAmount;
    }

    public BigDecimal getSubTotalAmount() {
        return subTotalAmount;
    }

    public void setSubTotalAmount(BigDecimal subTotalAmount) {
        this.subTotalAmount = subTotalAmount;
    }

    @Override
    public String toString() {
        return "FinancialLogDataVO{" +
                "financialLogVO=" + financialLogVO +
                ", addTotalAmount=" + addTotalAmount +
                ", subTotalAmount=" + subTotalAmount +
                '}';
    }
}
