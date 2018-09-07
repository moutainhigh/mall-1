package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class ReimbursementSuccessVO implements Serializable {
    private static final long serialVersionUID = 1840132262018611706L;

    @ApiModelProperty(value="合计",name="accountSalePrice",example="111")
    private BigDecimal accountSalePrice;
    @ApiModelProperty(value="到账金额",name="accountAmount",example="111")
    private BigDecimal accountAmount;
    @ApiModelProperty(value="税",name="tax",example="111")
    private BigDecimal tax;
    @ApiModelProperty(value="纳税点",name="taxPoint",example="111")
    private String taxPoint;

    public BigDecimal getAccountSalePrice() {
        return accountSalePrice;
    }

    public void setAccountSalePrice(BigDecimal accountSalePrice) {
        this.accountSalePrice = accountSalePrice;
    }

    public BigDecimal getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public String getTaxPoint() {
        return taxPoint;
    }

    public void setTaxPoint(String taxPoint) {
        this.taxPoint = taxPoint;
    }
}
