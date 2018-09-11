package com.yunxin.cb.vo.responsevo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/** 
 * 汽车预下单详情VO 数据实体类
 * @author lxc
 * @date	2018-09-10 11:38
 */
@ApiModel(value="汽车预下单详情VO",description="汽车预下单详情VO_VO对象 CarPerpOrderDetailVo")
public class CarPerpOrderDetailVo implements Serializable {

	private static final long serialVersionUID = 1l;
	//购买类型(1全款,2置换)
	@ApiModelProperty(value="购买类型(1全款,2置换)",name="buyType",example="1")
	private Integer buyType;
	//商品名称
	@ApiModelProperty(value="商品名称",name="carName",example="2018 款 240 TURBO 自动两驱舒适版")
	private String carName;
	//图片路径
	@ApiModelProperty(value="图片路径",name="carImageUrl",example="http:ddd.ddd.dd v xx.jpg")
	private String carImageUrl;
	//商品颜色
	@ApiModelProperty(value="商品属性",name="carColor",example="天漠金 2.4L 自动")
	private String carColor;
	//商品总数量
	@ApiModelProperty(value="商品总数量",name="totalNum",example="1")
	private Integer totalNum;
	//订单总额
	@ApiModelProperty(value="订单总额",name="totalPrice",example="112000")
	private BigDecimal totalPrice;
	//新车ID
	@ApiModelProperty(value="新车ID",name="carModelId",example="1")
	private Integer carModelId;
	//提车门店ID
	@ApiModelProperty(value="提车门店id",name="carAgencyLiftId",example="1")
	private Integer carAgencyLiftId;
	//提车门店名称
	@ApiModelProperty(value="提车门店名称",name="carAgencyLiftName",example="深圳上通汽车4S店")
	private String carAgencyLiftName;
	//提车门店地址
	@ApiModelProperty(value="提车门店地址",name="carAgencyLiftAddress",example="深圳市福田区福中一路1001号生命保险大厦(金田路与福中一路交汇处)")
	private String carAgencyLiftAddress;
	//用户与提车门店的距离
	@ApiModelProperty(value="用户与门店的距离",name="distance",example="1200")
	private int distanceLift;

	//订金
	@ApiModelProperty(value="订金",name="earnest",example="5000")
	private BigDecimal earnest;
	//二手车信息ID
	@ApiModelProperty(value="二手车信息ID",name="carUsedId",example="1")
	private Integer carUsedId;
	//二手车名字
	@ApiModelProperty(value="二手车名字",name="usedCarName",example="2008 款 240 TURBO 自动两驱舒适版")
	private String usedCarName;
	//上牌时间
	@ApiModelProperty(value="上牌时间",name="onCardsTime",example="2016年9月")
	private String onCardsTime;
	//上牌城市
	@ApiModelProperty(value="上牌城市",name="onCardsCity",example="深圳")
	private String onCardsCity;
	//里程数
	@ApiModelProperty(value="里程数",name="kilometre",example="12400")
	private Double kilometre;
	//是否大修（0：否；1：是）
	@ApiModelProperty(value="是否大修（0：否；1：是）",name="overhaul",example="0")
	private Integer overhaul;
	//参考价格
	@ApiModelProperty(value="参考价格",name="referencePrice",example="120000")
	private BigDecimal referencePrice;
	//价格计算
	@ApiModelProperty(value="价格计算",name="countPrice",example="105.80万*30%+2万")
	private String countPrice;
	//新车打折价格
	@ApiModelProperty(value="新车打折价格",name="carDiscount",example="33.74万")
	private String carDiscount;

	public Integer getBuyType() {
		return buyType;
	}

	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
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

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getCarModelId() {
		return carModelId;
	}

	public void setCarModelId(Integer carModelId) {
		this.carModelId = carModelId;
	}

	public Integer getCarAgencyLiftId() {
		return carAgencyLiftId;
	}

	public void setCarAgencyLiftId(Integer carAgencyLiftId) {
		this.carAgencyLiftId = carAgencyLiftId;
	}

	public String getCarAgencyLiftName() {
		return carAgencyLiftName;
	}

	public void setCarAgencyLiftName(String carAgencyLiftName) {
		this.carAgencyLiftName = carAgencyLiftName;
	}

	public String getCarAgencyLiftAddress() {
		return carAgencyLiftAddress;
	}

	public void setCarAgencyLiftAddress(String carAgencyLiftAddress) {
		this.carAgencyLiftAddress = carAgencyLiftAddress;
	}

	public int getDistanceLift() {
		return distanceLift;
	}

	public void setDistanceLift(int distanceLift) {
		this.distanceLift = distanceLift;
	}

	public BigDecimal getEarnest() {
		return earnest;
	}

	public void setEarnest(BigDecimal earnest) {
		this.earnest = earnest;
	}

	public String getUsedCarName() {
		return usedCarName;
	}

	public void setUsedCarName(String usedCarName) {
		this.usedCarName = usedCarName;
	}

	public String getOnCardsTime() {
		return onCardsTime;
	}

	public void setOnCardsTime(String onCardsTime) {
		this.onCardsTime = onCardsTime;
	}

	public String getOnCardsCity() {
		return onCardsCity;
	}

	public void setOnCardsCity(String onCardsCity) {
		this.onCardsCity = onCardsCity;
	}

	public Double getKilometre() {
		return kilometre;
	}

	public void setKilometre(Double kilometre) {
		this.kilometre = kilometre;
	}

	public Integer getOverhaul() {
		return overhaul;
	}

	public void setOverhaul(Integer overhaul) {
		this.overhaul = overhaul;
	}

	public BigDecimal getReferencePrice() {
		return referencePrice;
	}

	public void setReferencePrice(BigDecimal referencePrice) {
		this.referencePrice = referencePrice;
	}

	public String getCountPrice() {
		return countPrice;
	}

	public void setCountPrice(String countPrice) {
		this.countPrice = countPrice;
	}

	public String getCarDiscount() {
		return carDiscount;
	}

	public void setCarDiscount(String carDiscount) {
		this.carDiscount = carDiscount;
	}

	public Integer getCarUsedId() {
		return carUsedId;
	}

	public void setCarUsedId(Integer carUsedId) {
		this.carUsedId = carUsedId;
	}

	@Override
	public String toString() {
		return "CarPerpOrderDetailVo{" +
				"buyType=" + buyType +
				", carName='" + carName + '\'' +
				", carImageUrl='" + carImageUrl + '\'' +
				", carColor='" + carColor + '\'' +
				", totalNum=" + totalNum +
				", totalPrice=" + totalPrice +
				", carModelId=" + carModelId +
				", carAgencyLiftId=" + carAgencyLiftId +
				", carAgencyLiftName='" + carAgencyLiftName + '\'' +
				", carAgencyLiftAddress='" + carAgencyLiftAddress + '\'' +
				", distanceLift=" + distanceLift +
				", earnest=" + earnest +
				", carUsedId=" + carUsedId +
				", usedCarName='" + usedCarName + '\'' +
				", onCardsTime='" + onCardsTime + '\'' +
				", onCardsCity='" + onCardsCity + '\'' +
				", kilometre=" + kilometre +
				", overhaul=" + overhaul +
				", referencePrice=" + referencePrice +
				", countPrice='" + countPrice + '\'' +
				", carDiscount='" + carDiscount + '\'' +
				'}';
	}
}
