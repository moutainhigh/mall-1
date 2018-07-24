package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.meta.AddressType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * create by dengchenggang
 */
@ApiModel(value="收货地址",description="收货地址VO对象 DeliveryAddress")
public class DeliveryAddressVO implements java.io.Serializable{

    private static final long serialVersionUID = 4771113259952152508L;


    /**
     *
     */
    @ApiModelProperty(value="地址ID",name="addressId",example="枚举:家庭、公司、其他")
    private int addressId;

    /**
     * 收货地址类型
     */
    @ApiModelProperty(value="地址类型",name="addressType",example="1")
    private AddressType addressType;

    /**
     * 市
     */
    @ApiModelProperty(value="市",name="city",example="深圳")
    private String city;

    /**
     * 收货人地址
     */
    @ApiModelProperty(value="详细地址",name="consigneeAddress",example="XX路XX小区")
    private String consigneeAddress;

    /**
     * 手机
     */
    @ApiModelProperty(value="手机号码",name="consigneeMobile",example="13800138000")
    private String consigneeMobile;

    /**
     * 收货人姓名
     */
    @ApiModelProperty(value="收货人姓名",name="consigneeName",example="李白")
    private String consigneeName;

    /**
     * 固话
     */
    @ApiModelProperty(value="固话",name="consigneeTelephone",example="075582345678")
    private String consigneeTelephone;

    /**
     * 默认收货地址
     */
    @ApiModelProperty(value="是否默认收货地址",name="defaultAddress",example="true")
    private Boolean defaultAddress;

    /**
     * 区，县
     */
    @ApiModelProperty(value="区，县",name="city",example="福田")
    private String district;

    /**
     * 邮政编码
     */
    @ApiModelProperty(value="邮政编码",name="postCode",example="518000")
    private String postCode;

    /**
     * 省
     */
    @ApiModelProperty(value="省",name="province",example="广东")
    private String province;

    /**
     * 备注备注
     */
    @ApiModelProperty(value="备注",name="remark",example="老家地址")
    private String remark;

    /**
     * 客户ID
     */
    @ApiModelProperty(value="用户ID",name="customerId",example="1")
    private Integer customerId;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeTelephone() {
        return consigneeTelephone;
    }

    public void setConsigneeTelephone(String consigneeTelephone) {
        this.consigneeTelephone = consigneeTelephone;
    }

    public Boolean getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(Boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "DeliveryAddressVO{" +
                "addressId=" + addressId +
                ", addressType=" + addressType +
                ", city='" + city + '\'' +
                ", consigneeAddress='" + consigneeAddress + '\'' +
                ", consigneeMobile='" + consigneeMobile + '\'' +
                ", consigneeName='" + consigneeName + '\'' +
                ", consigneeTelephone='" + consigneeTelephone + '\'' +
                ", defaultAddress=" + defaultAddress +
                ", district='" + district + '\'' +
                ", postCode='" + postCode + '\'' +
                ", province='" + province + '\'' +
                ", remark='" + remark + '\'' +
                ", customerId=" + customerId +
                '}';
    }
}
