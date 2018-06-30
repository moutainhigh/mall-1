package com.yunxin.cb.mall.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.meta.OrderState;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderFormVo {
    /**
     * ID
     */
    private int orderId;
    /**
     * 订单编号
     */
    private String orderCode;
    /**
     * 物流公司
     */
    private String logisticsCompany;
    /**
     * 快递单号
     */
    private String courierNumber;
    /**
     * 客户
     */
    private Customer customer;
    /**
     * 订单总价
     */
    private double totalPrice;

    /**
     * 状态
     */
    private OrderState orderState;


    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 联系人
     */
    private String contactName;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String district;
    /**
     * 邮编
     */
    private String zipCode;
    /**
     * 地址
     */
    private String address;
    /**
     * 电话
     */
    private String tel;
    /**
     * 手机
     */
    private String phone;

    /**
     * 订单完成时间
     */
    private Date finishTime;

    private List<OrderDetailVo> orderDetails = new ArrayList<>();

    /**
     * 湿度
     */
    private float humidityValue;

    private String statusName;
    /**
     * 运费
     */
    private float freightPrice;


    private boolean hasEvaluate;


    public boolean isHasEvaluate() {
        return hasEvaluate;
    }

    public void setHasEvaluate(boolean hasEvaluate) {
        this.hasEvaluate = hasEvaluate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
        this.statusName = orderState.getName();
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public List<OrderDetailVo> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailVo> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public float getHumidityValue() {
        return humidityValue;
    }

    public void setHumidityValue(float humidityValue) {
        this.humidityValue = humidityValue;
    }

    public float getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(float freightPrice) {
        this.freightPrice = freightPrice;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }


    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getCourierNumber() {
        return courierNumber;
    }

    public void setCourierNumber(String courierNumber) {
        this.courierNumber = courierNumber;
    }


}
