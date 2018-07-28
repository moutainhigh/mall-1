package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

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
    @ApiModelProperty(value="真实姓名",name="realName",example="张三")
    private String realName;
    /**
     * 性别
     */
    @ApiModelProperty(value="性别",name="sex",example="true")
    private String sex;

    /**
     * 证件类型
     */
    @ApiModelProperty(value="证件类型",name="cardType",example="居民身份证")
    private String cardType;

    /**
     * 国籍
     */
    @ApiModelProperty(value="国籍",name="customerCountry",example="中国")
    private String customerCountry;
    /**
     * 证件有效期
     */
    @ApiModelProperty(value="证件有效期",name="customerCardPeroid",example="2020-08-12")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date customerCardPeroid;
    /**
     * 职业类别
     */
    @ApiModelProperty(value="职业类别",name="occupationalCategory",example="职业类别")
    private String occupationalCategory;

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

    public String getCustomerCountry() {
        return customerCountry;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public Date getCustomerCardPeroid() {
        return customerCardPeroid;
    }

    public void setCustomerCardPeroid(Date customerCardPeroid) {
        this.customerCardPeroid = customerCardPeroid;
    }

    public String getOccupationalCategory() {
        return occupationalCategory;
    }

    public void setOccupationalCategory(String occupationalCategory) {
        this.occupationalCategory = occupationalCategory;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public CustomerUpdateVo() {
    }
}
