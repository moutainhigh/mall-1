package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModelProperty;

public class FinancialLogRequestVO implements java.io.Serializable{
    private static final long serialVersionUID = 3091869189584420657L;

    /** 用户id */
    @ApiModelProperty(value="用户id",name="customerId",example="1")
    private Integer customerId;
    //查询使用字段：年月201808
    @ApiModelProperty(value="查询使用字段：年月201808",name="yearMonth",example="描述")
    private String yearMonth;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }
}
