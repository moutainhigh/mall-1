package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.util.TextUtil;
import io.swagger.annotations.ApiModelProperty;

public class RecommendCustomerVo {
    /**
     * id
     */
    @ApiModelProperty(value = "客户ID", name = "customerId", example = "1")
    private int customerId;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 邀请码
     */
    private String invitationCode;
    /**
     * 用户头像
     */
    private String avatarUrl;
    /**
     * 昵称
     */
    private String nickName;

    public RecommendCustomerVo() {

    }

    public RecommendCustomerVo(Customer customer) {
        this.customerId = customer.getCustomerId();
        this.mobile = customer.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
        this.invitationCode = customer.getInvitationCode();
        this.avatarUrl = customer.getAvatarUrl();
        this.nickName = TextUtil.checkNameLength(customer.getNickName());
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
