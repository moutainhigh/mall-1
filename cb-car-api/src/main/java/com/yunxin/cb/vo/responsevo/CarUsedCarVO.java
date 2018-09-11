package com.yunxin.cb.vo.responsevo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ApiModel(value="二手车",description="二手车VO对象 CarUsedCarVO")
public class CarUsedCarVO implements java.io.Serializable{
	
	private static final long serialVersionUID = 1l;

	@ApiModelProperty(value="二手车id",name="id",example="1")
	private Integer id;
	@ApiModelProperty(value="车型名称（第三方数据）",name="reModelId",example="1")
	private String reModelName;
	@ApiModelProperty(value="申请人ID",name="applyId",example="1")
	private Integer applyId;
	@ApiModelProperty(value="用户手机号",name="applyMobile",example="13711112522")
	private String applyMobile;
	@ApiModelProperty(value="用户类型（1：后台；2：APP）",name="userType",example="2")
	private Integer userType;
	@ApiModelProperty(value="车主名",name="uname",example="张三")
	private String uname;
	@ApiModelProperty(value="车牌号",name="carNum",example="粤B 888888")
	private String carNum;
	@JsonFormat(pattern="yyyy-MM-dd")
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
	@ApiModelProperty(value="实际评估价格",name="actualAssessPrice",example="130000")
	private BigDecimal actualAssessPrice;
	@ApiModelProperty(value="是否大修（0：否；1：是）",name="overhaul",example="1")
	private Integer overhaul;
	@ApiModelProperty(value="评估人",name="assessmentName",example="李四")
	private String assessmentName;
	@ApiModelProperty(value="手机号",name="mobile",example="13811112222")
	private String mobile;
	@ApiModelProperty(value="是否联系（0：否；1：是）",name="contact",example="1")
	private Integer contact;
	@ApiModelProperty(value="过户（0：否；1：是）",name="transfer",example="0")
	private Integer transfer;
	@ApiModelProperty(value="参考范围",name="referenceRange",example="14.03-16.32")
	private String referenceRange;
	@ApiModelProperty(value="推荐置换车系",name="recommandList",example="推荐置换车系")
	private List<CarSystemVO> recommandList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReModelName() {
		return reModelName;
	}

	public void setReModelName(String reModelName) {
		this.reModelName = reModelName;
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

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
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

	public BigDecimal getActualAssessPrice() {
		return actualAssessPrice;
	}

	public void setActualAssessPrice(BigDecimal actualAssessPrice) {
		this.actualAssessPrice = actualAssessPrice;
	}

	public Integer getOverhaul() {
		return overhaul;
	}

	public void setOverhaul(Integer overhaul) {
		this.overhaul = overhaul;
	}

	public String getAssessmentName() {
		return assessmentName;
	}

	public void setAssessmentName(String assessmentName) {
		this.assessmentName = assessmentName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getContact() {
		return contact;
	}

	public void setContact(Integer contact) {
		this.contact = contact;
	}

	public Integer getTransfer() {
		return transfer;
	}

	public void setTransfer(Integer transfer) {
		this.transfer = transfer;
	}

	public String getReferenceRange() {
		return referenceRange;
	}

	public void setReferenceRange(String referenceRange) {
		this.referenceRange = referenceRange;
	}

	public List<CarSystemVO> getRecommandList() {
		return recommandList;
	}

	public void setRecommandList(List<CarSystemVO> recommandList) {
		this.recommandList = recommandList;
	}
}
