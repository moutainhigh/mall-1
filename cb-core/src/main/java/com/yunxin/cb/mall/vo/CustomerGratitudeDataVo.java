package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author wangteng  感恩统计
 */
@ApiModel(value="感恩列表",description="感恩列表 CustomerGratitudeDataVo")
public class CustomerGratitudeDataVo implements Serializable {
    @ApiModelProperty(value="内容",name="customerName",example="张三买了个大保险")
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
