package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author wangteng  感恩统计
 */
@ApiModel(value="感恩列表",description="感恩列表 CustomerGratitudeDataVo")
public class CustomerGratitudeDataVo implements Serializable {

    @ApiModelProperty(value="头像",name="headPath",example="hhtp://www.baidu.com")
    private String headPath;
    @ApiModelProperty(value="用户名",name="userName",example="张三")
    private String userName;
    @ApiModelProperty(value="感恩状态",name="gratitudeType",example="感恩我")
    private String gratitudeType;
    @ApiModelProperty(value="保险产品",name="gratitudeType",example="生命保险10万")
    private String productName;

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGratitudeType() {
        return gratitudeType;
    }

    public void setGratitudeType(String gratitudeType) {
        this.gratitudeType = gratitudeType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
