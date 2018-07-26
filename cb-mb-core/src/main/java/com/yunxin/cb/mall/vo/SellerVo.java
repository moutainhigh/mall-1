package com.yunxin.cb.mall.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @title: 商家VO
 * @auther: eleven
 * @date: 2018/7/24 17:38
 */
@ApiModel(value="商家VO",description="商家VO对象 Seller")
public class SellerVo implements java.io.Serializable {

    private static final long serialVersionUID = 9155583067018402136L;
    /** 商家id */
    @ApiModelProperty(value="商家id",name="sellerId",example="1")
    private Integer sellerId;

    /** 创建时间 */
    @ApiModelProperty(value="创建时间",name="createTime",example="2018-07-24 17:41:13")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 商家简介 */
    @ApiModelProperty(value="商家简介",name="introduction",example="十年老店 值得信赖")
    private String introduction;

    /** 联系人 */
    @ApiModelProperty(value="联系人",name="linkman",example="王妮ma")
    private String linkman;

    /** 手机 */
    @ApiModelProperty(value="手机",name="mobile",example="13588565598")
    private String mobile;

    /** 备注 */
    @ApiModelProperty(value="备注",name="remark",example="无")
    private String remark;

    /** 商家地址 */
    @ApiModelProperty(value="商家地址",name="sellerAddress",example="广东省深圳市富德大厦507")
    private String sellerAddress;

    /** 商家编码 */
    @ApiModelProperty(value="商家编码",name="sellerCode",example="221568")
    private String sellerCode;

    /** 商家名称 */
    @ApiModelProperty(value="商家名称",name="sellerName",example="BMW4S店")
    private String sellerName;

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    @Override
    public String toString() {
        return "SellerVo{" +
                "sellerId=" + sellerId +
                ", createTime=" + createTime +
                ", introduction='" + introduction + '\'' +
                ", linkman='" + linkman + '\'' +
                ", mobile='" + mobile + '\'' +
                ", remark='" + remark + '\'' +
                ", sellerAddress='" + sellerAddress + '\'' +
                ", sellerCode='" + sellerCode + '\'' +
                ", sellerName='" + sellerName + '\'' +
                '}';
    }
}
