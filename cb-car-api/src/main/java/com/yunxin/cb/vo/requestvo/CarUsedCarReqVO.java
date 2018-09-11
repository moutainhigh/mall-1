package com.yunxin.cb.vo.requestvo;

import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ApiModel(value="二手车",description="二手车VO对象 CarUsedCarVO")
public class CarUsedCarReqVO implements java.io.Serializable{

	private static final long serialVersionUID = 1l;

	@ApiModelProperty(value="二手车id",name="id",example="1")
	private Integer id;
	@ApiModelProperty(value="品牌ID（第三方数据）",name="reBrandId",example="1")
	private Integer reBrandId;
	@ApiModelProperty(value="车系ID（第三方数据）",name="reSysId",example="1")
	private Integer reSysId;
	@ApiModelProperty(value="车型ID（第三方数据）",name="reModelId",example="1")
	private Integer reModelId;
	@ApiModelProperty(value="申请人",name="applyId",example="1")
	private Integer applyId;
	@NotBlank
	@ApiModelProperty(value="用户手机号",name="applyMobile",example="13711112522")
	private String applyMobile;
	@NotBlank
	@ApiModelProperty(value="车主名",name="uname",example="张三")
	private String uname;
	@ApiModelProperty(value="车牌号",name="carNum",example="粤B 888888")
	private String carNum;
	@ApiModelProperty(value="上牌时间",name="onCardsTime",example="2016-01-01")
	private Date onCardsTime;
	@ApiModelProperty(value="上牌城市",name="onCardsCity",example="深圳")
	private String onCardsCity;
	@ApiModelProperty(value="里程数",name="kilometre",example="2000")
	private Double kilometre;
	@ApiModelProperty(value="参考价格",name="referencePrice",example="160000.00")
	private BigDecimal referencePrice;
	@ApiModelProperty(value="购买价格",name="buyPrice",example="300000")
	private BigDecimal buyPrice;
	@NotNull
	@ApiModelProperty(value="是否大修（0：否；1：是）",name="overhaul",example="1")
	private Integer overhaul;
	@ApiModelProperty(value="图片路径",name="picPath",example="图片路径")
	private List<String> picPath;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getReBrandId() {
		return reBrandId;
	}

	public void setReBrandId(Integer reBrandId) {
		this.reBrandId = reBrandId;
	}

	public Integer getReSysId() {
		return reSysId;
	}

	public void setReSysId(Integer reSysId) {
		this.reSysId = reSysId;
	}

	public Integer getReModelId() {
		return reModelId;
	}

	public void setReModelId(Integer reModelId) {
		this.reModelId = reModelId;
	}

	public Integer getApplyId() {
		return applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	public String getApplyMobile() {
		return applyMobile;
	}

	public void setApplyMobile(String applyMobile) {
		this.applyMobile = applyMobile;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public Date getOnCardsTime() {
		return onCardsTime;
	}

	public void setOnCardsTime(Date onCardsTime) {
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

	public BigDecimal getReferencePrice() {
		return referencePrice;
	}

	public void setReferencePrice(BigDecimal referencePrice) {
		this.referencePrice = referencePrice;
	}

	public BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Integer getOverhaul() {
		return overhaul;
	}

	public void setOverhaul(Integer overhaul) {
		this.overhaul = overhaul;
	}

	public List<String> getPicPath() {
		return picPath;
	}

	public void setPicPath(List<String> picPath) {
		this.picPath = picPath;
	}
}
