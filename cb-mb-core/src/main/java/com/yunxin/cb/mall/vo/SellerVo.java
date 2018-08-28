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

    /** 固定电话 */
    @ApiModelProperty(value="固定电话",name="telephone",example="0755-82573921")
    private String telePhone;

    /** 备注 */
    @ApiModelProperty(value="备注",name="remark",example="无")
    private String remark;

    /**省*/
    @ApiModelProperty(value="省",name="province",example="234443")
    private String province;
    @ApiModelProperty(value="省名",name="province",example="广东省")
    private String provinceName;
    /**市*/
    @ApiModelProperty(value="市",name="city",example="234234")
    private String city;
    @ApiModelProperty(value="市名",name="city",example="深圳市")
    private String cityName;
    /**区*/
    @ApiModelProperty(value="区",name="district",example="324344")
    private String district;
    @ApiModelProperty(value="区名",name="district",example="福田区")
    private String districtName;

    /** 商家地址 */
    @ApiModelProperty(value="商家地址",name="sellerAddress",example="广东省深圳市富德大厦507")
    private String sellerAddress;

    /** 商家编码 */
    @ApiModelProperty(value="商家编码",name="sellerCode",example="221568")
    private String sellerCode;

    /** 商家名称 */
    @ApiModelProperty(value="商家名称",name="sellerName",example="BMW4S店")
    private String sellerName;

    /** 商家X轴坐标：经度 */
    @ApiModelProperty(value="经度 ",name="positionX",example="5.55")
    private String positionX;

    /** 商家Y轴坐标：纬度*/
    @ApiModelProperty(value="纬度",name="positionY",example="3.22")
    private String positionY;

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

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
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

    public String getPositionX() {
        return positionX;
    }

    public void setPositionX(String positionX) {
        this.positionX = positionX;
    }

    public String getPositionY() {
        return positionY;
    }

    public void setPositionY(String positionY) {
        this.positionY = positionY;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @Override
    public String toString() {
        return "SellerVo{" +
                "sellerId=" + sellerId +
                ", createTime=" + createTime +
                ", introduction='" + introduction + '\'' +
                ", linkman='" + linkman + '\'' +
                ", mobile='" + mobile + '\'' +
                ", telePhone='" + telePhone + '\'' +
                ", remark='" + remark + '\'' +
                ", province='" + province + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", city='" + city + '\'' +
                ", cityName='" + cityName + '\'' +
                ", district='" + district + '\'' +
                ", districtName='" + districtName + '\'' +
                ", sellerAddress='" + sellerAddress + '\'' +
                ", sellerCode='" + sellerCode + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", positionX='" + positionX + '\'' +
                ", positionY='" + positionY + '\'' +
                '}';
    }
}
