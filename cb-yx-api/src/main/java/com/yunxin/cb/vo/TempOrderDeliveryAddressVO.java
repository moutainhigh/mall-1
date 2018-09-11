package com.yunxin.cb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * @title: 临时订单默认收货地址VO
 * @auther: gws
 * @date: 2018/9/10 17:25
 */
@ApiModel(value="默认收货地址",description="默认收货地址VO对象 DeliveryAddress")
public class TempOrderDeliveryAddressVO{

    /**
     * 地址ID
     */
    @ApiModelProperty(value="地址ID",name="addressId")
    private int addressId;

    /**
     * 收货人姓名
     */
    @NotBlank(message="收货人地址不能为空")
    @ApiModelProperty(value="收货人姓名",name="consigneeName",example="李白")
    private String consigneeName;

    /**
     * 手机
     */
    @NotBlank
    @Pattern(regexp = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$",message="手机号码不正确")
    @ApiModelProperty(value="手机号码",name="consigneeMobile",example="13800138000")
    private String consigneeMobile;

    /**
     * 省
     */
    @NotBlank(message="省份不能为空")
    @ApiModelProperty(value="省",name="province",example="广东")
    private String province;

    /**
     * 市
     */
    @NotBlank(message="市不能为空")
    @ApiModelProperty(value="市",name="city",example="深圳")
    private String city;

    /**
     * 区，县
     */
    @NotBlank(message="区，县不能为空")
    @ApiModelProperty(value="区，县",name="city",example="福田")
    private String district;


    /**
     * 收货人地址
     */
    @NotBlank(message="收货人地址不能为空")
    @ApiModelProperty(value="详细地址",name="consigneeAddress",example="XX路XX小区")
    private String consigneeAddress;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile;
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

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    @Override
    public String toString() {
        return "TempOrderDeliveryAddressVO{" +
                "addressId=" + addressId +
                ", consigneeName='" + consigneeName + '\'' +
                ", consigneeMobile='" + consigneeMobile + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", consigneeAddress='" + consigneeAddress + '\'' +
                '}';
    }
}
