package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author wangteng  匹配通讯录
 */
@ApiModel(value="匹配通讯录",description="匹配通讯录 CustomerMatchVo")
public class CustomerMatchsVo implements Serializable {

    @ApiModelProperty(value = "姓名", name = "realName", example = "张三")
    private String realName;
    @ApiModelProperty(value = "手机", name = "mobile", example = "13823221027")
    private String mobile;

    public CustomerMatchsVo(){

    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
