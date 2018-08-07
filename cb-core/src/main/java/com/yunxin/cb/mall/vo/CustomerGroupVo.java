package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModelProperty;

public class CustomerGroupVo  implements java.io.Serializable {
    @ApiModelProperty(value="群组名",name="groupName",example="划拳")
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
