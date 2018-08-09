package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author wangteng  感恩统计
 */
@ApiModel(value="感恩统计",description="感恩统计 CustomerGratitudeVo")
public class CustomerGratitudeVo implements Serializable {

    @ApiModelProperty(value="感恩人",name="recommendName",example="张三")
    private String recommendName;
    @ApiModelProperty(value="感恩我的",name="gratitudeMe",example="101")
    private int gratitudeMe;
    @ApiModelProperty(value="未感恩的",name="noGratitude",example="101")
    private int noGratitude;
    @ApiModelProperty(value="未付款的",name="unpaid",example="101")
    private int unpaid;
    @ApiModelProperty(value="未购买的",name="notPurchased",example="101")
    private int notPurchased;
    @ApiModelProperty(value="所有感恩",name="allGratitude",example="101")
    private int allGratitude;

    public int getGratitudeMe() {
        return gratitudeMe;
    }

    public void setGratitudeMe(int gratitudeMe) {
        this.gratitudeMe = gratitudeMe;
    }

    public int getNoGratitude() {
        return noGratitude;
    }

    public void setNoGratitude(int noGratitude) {
        this.noGratitude = noGratitude;
    }

    public int getUnpaid() {
        return unpaid;
    }

    public void setUnpaid(int unpaid) {
        this.unpaid = unpaid;
    }

    public int getNotPurchased() {
        return notPurchased;
    }

    public void setNotPurchased(int notPurchased) {
        this.notPurchased = notPurchased;
    }

    public int getAllGratitude() {
        return allGratitude;
    }

    public void setAllGratitude(int allGratitude) {
        this.allGratitude = allGratitude;
    }

    public String getRecommendName() {
        return recommendName;
    }

    public void setRecommendName(String recommendName) {
        this.recommendName = recommendName;
    }
}
