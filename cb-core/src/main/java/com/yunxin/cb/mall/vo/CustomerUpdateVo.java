package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author wangteng  用户更改
 */
@ApiModel(value="用户更改",description="用户更改 CustomerUpdateVo")
public class CustomerUpdateVo implements Serializable {
    /**
     * 证件正面照
     */
    @ApiModelProperty(value="证件正面照",name="cardPositiveImg",example="")
    private String cardPositiveImg;
    /**
     * 证件反面照
     */
    @ApiModelProperty(value="证件反面照",name="cardNegativeImg",example="")
    private String cardNegativeImg;
    /**
     * 银行照片
     */
    @ApiModelProperty(value="银行照片",name="bankCardImg",example="")
    private String bankCardImg;
    /**
     * 真实姓名
     */
    @ApiModelProperty(value="真实姓名",name="realName",example="")
    private String realName;
    /**
     * 证件类型
     */
    @ApiModelProperty(value="证件类型",name="cardType",example="")
    private String cardType;

    public String getCardPositiveImg() {
        return cardPositiveImg;
    }

    public void setCardPositiveImg(String cardPositiveImg) {
        this.cardPositiveImg = cardPositiveImg;
    }

    public String getCardNegativeImg() {
        return cardNegativeImg;
    }

    public void setCardNegativeImg(String cardNegativeImg) {
        this.cardNegativeImg = cardNegativeImg;
    }

    public String getBankCardImg() {
        return bankCardImg;
    }

    public void setBankCardImg(String bankCardImg) {
        this.bankCardImg = bankCardImg;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
}
