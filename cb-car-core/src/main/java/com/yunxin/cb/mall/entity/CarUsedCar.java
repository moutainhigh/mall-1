package com.yunxin.cb.mall.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.yunxin.cb.mall.common.Entity;

/** 
 * 二手车管理 数据实体类
 */
public class CarUsedCar extends Entity<Integer> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	private Integer id;
	//品牌ID（第三方数据）
	private Integer reBrandId;
	//车系ID（第三方数据）
	private Integer reSysId;
	//车型ID（第三方数据）
	private Integer reModelId;
	//申请人ID
	private Integer applyId;
	//用户手机号
	private String applyMobile;
	//用户类型（1：后台；2：APP）
	private Integer userType;
	//车主名
	private String uname;
	//车牌号
	private String carNum;
	//上牌时间
	private Date onCardsTime;
	//上牌时间 时间范围起（查询用）
	private Date onCardsTimeStart;
	//上牌时间 时间范围止（查询用）
	private Date onCardsTimeEnd;	
	//上牌城市
	private String onCardsCity;
	//里程数
	private Double kilometre;
	//参考价格
	private BigDecimal referencePrice;
	//购买价格
	private BigDecimal buyPrice;
	//实际评估价格
	private BigDecimal actualAssessPrice;
	//是否大修（0：否；1：是）
	private Integer overhaul;
	//评估人
	private String assessmentName;
	//手机号
	private String mobile;
	//是否联系（0：否；1：是）
	private Integer contact;
	//过户（0：否；1：是）
	private Integer transfer;
	//创建时间
	private Date createTime;
	//创建时间 时间范围起（查询用）
	private Date createTimeStart;
	//创建时间 时间范围止（查询用）
	private Date createTimeEnd;	
	//修改时间
	private Date updateTime;
	//修改时间 时间范围起（查询用）
	private Date updateTimeStart;
	//修改时间 时间范围止（查询用）
	private Date updateTimeEnd;	
	//是否逻辑删除（0：否；1：是）
	private Integer isDelete;
	//操作人ID
	private Integer operateId;
	
	/*Auto generated properties end*/
	
	
	
	/*Customized properties start*/
	
	/*Customized properties end*/
	
	
	
	/*Auto generated methods start*/
	
	@Override
	public Integer getPK(){
		return id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getReBrandId(){
		return reBrandId;
	}
	
	public void setReBrandId(Integer reBrandId){
		this.reBrandId = reBrandId;
	}
	
	public Integer getReSysId(){
		return reSysId;
	}
	
	public void setReSysId(Integer reSysId){
		this.reSysId = reSysId;
	}
	
	public Integer getReModelId(){
		return reModelId;
	}
	
	public void setReModelId(Integer reModelId){
		this.reModelId = reModelId;
	}
	
	public Integer getApplyId(){
		return applyId;
	}
	
	public void setApplyId(Integer applyId){
		this.applyId = applyId;
	}
	
	public String getApplyMobile(){
		return applyMobile;
	}
	
	public void setApplyMobile(String applyMobile){
		this.applyMobile = applyMobile;
	}
	
	public Integer getUserType(){
		return userType;
	}
	
	public void setUserType(Integer userType){
		this.userType = userType;
	}
	
	public String getUname(){
		return uname;
	}
	
	public void setUname(String uname){
		this.uname = uname;
	}
	
	public String getCarNum(){
		return carNum;
	}
	
	public void setCarNum(String carNum){
		this.carNum = carNum;
	}
	
	public Date getOnCardsTime(){
		return onCardsTime;
	}
	
	public void setOnCardsTime(Date onCardsTime){
		this.onCardsTime = onCardsTime;
	}
	
	public Date getOnCardsTimeStart(){
		return onCardsTimeStart;
	}
	
	public void setOnCardsTimeStart(Date onCardsTimeStart){
		this.onCardsTimeStart = onCardsTimeStart;
	}
	
	public Date getOnCardsTimeEnd(){
		return onCardsTimeEnd;
	}
	
	public void setOnCardsTimeEnd(Date onCardsTimeEnd){
		this.onCardsTimeEnd = onCardsTimeEnd;
	}	
	
	public String getOnCardsCity(){
		return onCardsCity;
	}
	
	public void setOnCardsCity(String onCardsCity){
		this.onCardsCity = onCardsCity;
	}
	
	public Double getKilometre(){
		return kilometre;
	}
	
	public void setKilometre(Double kilometre){
		this.kilometre = kilometre;
	}
	
	public BigDecimal getReferencePrice(){
		return referencePrice;
	}
	
	public void setReferencePrice(BigDecimal referencePrice){
		this.referencePrice = referencePrice;
	}
	
	public BigDecimal getBuyPrice(){
		return buyPrice;
	}
	
	public void setBuyPrice(BigDecimal buyPrice){
		this.buyPrice = buyPrice;
	}
	
	public BigDecimal getActualAssessPrice(){
		return actualAssessPrice;
	}
	
	public void setActualAssessPrice(BigDecimal actualAssessPrice){
		this.actualAssessPrice = actualAssessPrice;
	}
	
	public Integer getOverhaul(){
		return overhaul;
	}
	
	public void setOverhaul(Integer overhaul){
		this.overhaul = overhaul;
	}
	
	public String getAssessmentName(){
		return assessmentName;
	}
	
	public void setAssessmentName(String assessmentName){
		this.assessmentName = assessmentName;
	}
	
	public String getMobile(){
		return mobile;
	}
	
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	public Integer getContact(){
		return contact;
	}
	
	public void setContact(Integer contact){
		this.contact = contact;
	}
	
	public Integer getTransfer(){
		return transfer;
	}
	
	public void setTransfer(Integer transfer){
		this.transfer = transfer;
	}
	
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	public Date getCreateTimeStart(){
		return createTimeStart;
	}
	
	public void setCreateTimeStart(Date createTimeStart){
		this.createTimeStart = createTimeStart;
	}
	
	public Date getCreateTimeEnd(){
		return createTimeEnd;
	}
	
	public void setCreateTimeEnd(Date createTimeEnd){
		this.createTimeEnd = createTimeEnd;
	}	
	
	public Date getUpdateTime(){
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Date getUpdateTimeStart(){
		return updateTimeStart;
	}
	
	public void setUpdateTimeStart(Date updateTimeStart){
		this.updateTimeStart = updateTimeStart;
	}
	
	public Date getUpdateTimeEnd(){
		return updateTimeEnd;
	}
	
	public void setUpdateTimeEnd(Date updateTimeEnd){
		this.updateTimeEnd = updateTimeEnd;
	}	
	
	public Integer getIsDelete(){
		return isDelete;
	}
	
	public void setIsDelete(Integer isDelete){
		this.isDelete = isDelete;
	}
	
	public Integer getOperateId(){
		return operateId;
	}
	
	public void setOperateId(Integer operateId){
		this.operateId = operateId;
	}
	
	
	/*Auto generated methods end*/
	
	
	
	/*Customized methods start*/
	
	/*Customized methods end*/
	
	
	@Override
	public String toString() {
		return "CarUsedCar ["
		 + "id = " + id + ", reBrandId = " + reBrandId + ", reSysId = " + reSysId + ", reModelId = " + reModelId
		 + ", applyId = " + applyId + ", applyMobile = " + applyMobile + ", userType = " + userType + ", uname = " + uname
		 + ", carNum = " + carNum + ", onCardsTime = " + onCardsTime + ", onCardsTimeStart = " + onCardsTimeStart + ", onCardsTimeEnd = " + onCardsTimeEnd + ", onCardsCity = " + onCardsCity + ", kilometre = " + kilometre
		 + ", referencePrice = " + referencePrice + ", buyPrice = " + buyPrice + ", actualAssessPrice = " + actualAssessPrice + ", overhaul = " + overhaul
		 + ", assessmentName = " + assessmentName + ", mobile = " + mobile + ", contact = " + contact + ", transfer = " + transfer
		 + ", createTime = " + createTime + ", createTimeStart = " + createTimeStart + ", createTimeEnd = " + createTimeEnd + ", updateTime = " + updateTime + ", updateTimeStart = " + updateTimeStart + ", updateTimeEnd = " + updateTimeEnd + ", isDelete = " + isDelete + ", operateId = " + operateId
		+"]";
	}
}
