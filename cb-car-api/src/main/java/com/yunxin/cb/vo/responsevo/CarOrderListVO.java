package com.yunxin.cb.vo.responsevo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/** 
 * 汽车订单表VO
 */
@ApiModel(value="汽车订单列表VO",description="汽车订单列表_VO对象 CarOrderListVO")
public class CarOrderListVO implements Serializable {
	
	private static final long serialVersionUID = 1l;
	@ApiModelProperty(value="主键id",name="id",example="1")
	private Long id;
	//订单编号
	@ApiModelProperty(value="订单编号",name="orderCode",example="1")
	private String orderCode;
	//购买类型(1全款,2置换)
	@ApiModelProperty(value="购买类型(1全款,2置换)",name="buyType",example="1")
	private Integer buyType;
	//商品名称
	@ApiModelProperty(value="图片路径",name="carName",example="商品名称")
	private String carName;
	//图片路径
	@ApiModelProperty(value="图片路径",name="carImageUrl",example="http:ddd.ddd.dd v xx.jpg")
	private String carImageUrl;
	//商品颜色
	@ApiModelProperty(value="商品颜色",name="carColor",example="白色")
	private String carColor;
    //商品总数量
	@ApiModelProperty(value="商品总数量",name="totalNum",example="1")
    private Integer totalNum;
	//订单总额
	@ApiModelProperty(value="订单总额",name="totalPrice",example="112000")
	private BigDecimal totalPrice;
	//前端订单状态(1未支付,2待收货,3已完成,4已取消)
	@ApiModelProperty(value="前端订单状态(1未支付,2待收货,3已完成,4已取消)",name="appOrderState",example="1")
	private Integer appOrderState;
	//前端支付状态(1未支付,2已支付,3确认提车)
	@ApiModelProperty(value="前端支付状态(1未支付,2已支付,3确认提车)",name="appPaymentState",example="1")
	private Integer appPaymentState;
	//新车ID
	@ApiModelProperty(value="新车ID",name="carModelId",example="1")
	private Integer carModelId;
	//提车门店ID
	@ApiModelProperty(value="提车门店id",name="carAgencyLiftId",example="1")
	private Integer carAgencyLiftId;
	//提车门店名称
	@ApiModelProperty(value="提车门店名称",name="carAgencyLiftName",example="4S店")
	private String carAgencyLiftName;

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public String getOrderCode(){
		return orderCode;
	}
	
	public void setOrderCode(String orderCode){
		this.orderCode = orderCode;
	}
	
	public Integer getBuyType(){
		return buyType;
	}
	
	public void setBuyType(Integer buyType){
		this.buyType = buyType;
	}
	
	public String getCarName(){
		return carName;
	}
	
	public void setCarName(String carName){
		this.carName = carName;
	}
	
	public BigDecimal getTotalPrice(){
		return totalPrice;
	}
	
	public void setTotalPrice(BigDecimal totalPrice){
		this.totalPrice = totalPrice;
	}
	
	public Integer getCarModelId(){
		return carModelId;
	}
	
	public void setCarModelId(Integer carModelId){
		this.carModelId = carModelId;
	}
	
	public Integer getCarAgencyLiftId(){
		return carAgencyLiftId;
	}
	
	public void setCarAgencyLiftId(Integer carAgencyLiftId){
		this.carAgencyLiftId = carAgencyLiftId;
	}

	public String getCarImageUrl() {
		return carImageUrl;
	}

	public void setCarImageUrl(String carImageUrl) {
		this.carImageUrl = carImageUrl;
	}

	public String getCarColor() {
		return carColor;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public String getCarAgencyLiftName() {
		return carAgencyLiftName;
	}

	public void setCarAgencyLiftName(String carAgencyLiftName) {
		this.carAgencyLiftName = carAgencyLiftName;
	}

	public Integer getAppOrderState() {
		return appOrderState;
	}

	public void setAppOrderState(Integer appOrderState) {
		this.appOrderState = appOrderState;
	}

	public Integer getAppPaymentState() {
		return appPaymentState;
	}

	public void setAppPaymentState(Integer appPaymentState) {
		this.appPaymentState = appPaymentState;
	}

	@Override
	public String toString() {
		return "CarOrderListVO{" +
				"id=" + id +
				", orderCode='" + orderCode + '\'' +
				", buyType=" + buyType +
				", carName='" + carName + '\'' +
				", carImageUrl='" + carImageUrl + '\'' +
				", carColor='" + carColor + '\'' +
				", totalNum=" + totalNum +
				", totalPrice=" + totalPrice +
				", appOrderState=" + appOrderState +
				", appPaymentState=" + appPaymentState +
				", carModelId=" + carModelId +
				", carAgencyLiftId=" + carAgencyLiftId +
				", carAgencyLiftName='" + carAgencyLiftName + '\'' +
				'}';
	}
}
