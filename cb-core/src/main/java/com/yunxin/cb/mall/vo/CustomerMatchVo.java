package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author wangteng  匹配通讯录
 */
@ApiModel(value="匹配通讯录",description="匹配通讯录 CustomerMatchVo")
public class CustomerMatchVo implements Serializable {

    @ApiModelProperty(value="客户ID",name="customerId",example="1")
    private int customerId;
    @ApiModelProperty(value = "姓名", name = "realName", example = "张三")
    private String realName;
    @ApiModelProperty(value = "手机", name = "mobile", example = "13823221027")
    private String mobile;
    @ApiModelProperty(value = "昵称", name = "nickName", example = "孤独与酒")
    private String nickName;
    @ApiModelProperty(value = "头像", name = "avatarUrl", example = "http://www.baidu.com")
    private String avatarUrl;
    @ApiModelProperty(value = "用户名", name = "accountName", example = "fcgvxpwza4")
    private String accountName;

    public CustomerMatchVo(){

    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
