package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 规格查询过滤-值
 * add by chenpeng 2018年8月20日
 */
@ApiModel(value = "规格查询过滤-值", description = "规格查询过滤值VO对象 SpecFilterItemVO")
public class SpecFilterItemVO implements java.io.Serializable {

    private static final long serialVersionUID = -1257220357937673647L;

    @ApiModelProperty(value="规格查询过滤值ID",name="itemId",example="1")
    private Integer itemId;

    @ApiModelProperty(value="规格查询过滤值",name="itemValue",example="白色")
    private String itemValue;

    @ApiModelProperty(value="排序",name="sortOrder",example="1")
    private Integer sortOrder;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

}
