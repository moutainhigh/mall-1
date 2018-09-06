package com.yunxin.cb.mall.entity;

import java.util.Date;

import com.yunxin.cb.mall.common.Entity;

/** 
 * 汽车订单(取消/换货/退款/退款审核)原因 数据实体类
 */
public class CarOrderReason extends Entity<Long> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	private Long id;
	//订单ID
	private Long orderId;
	//原因类型(1取消,2换货,3退款,4退款审核)
	private Integer reasonType;
	//原因
	private String reason;
	//CREATE_TIME
	private Date createTime;
	//CREATE_TIME 时间范围起（查询用）
	private Date createTimeStart;
	//CREATE_TIME 时间范围止（查询用）
	private Date createTimeEnd;	
	//操作人ID
	private Integer operateId;
	//操作人类型（1：后台；2：APP）
	private Integer userType;
	
	/*Auto generated properties end*/
	
	
	
	/*Customized properties start*/
	
	/*Customized properties end*/
	
	
	
	/*Auto generated methods start*/
	
	@Override
	public Long getPK(){
		return id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getOrderId(){
		return orderId;
	}
	
	public void setOrderId(Long orderId){
		this.orderId = orderId;
	}
	
	public Integer getReasonType(){
		return reasonType;
	}
	
	public void setReasonType(Integer reasonType){
		this.reasonType = reasonType;
	}
	
	public String getReason(){
		return reason;
	}
	
	public void setReason(String reason){
		this.reason = reason;
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
	
	public Integer getOperateId(){
		return operateId;
	}
	
	public void setOperateId(Integer operateId){
		this.operateId = operateId;
	}
	
	public Integer getUserType(){
		return userType;
	}
	
	public void setUserType(Integer userType){
		this.userType = userType;
	}
	
	
	/*Auto generated methods end*/
	
	
	
	/*Customized methods start*/
	
	/*Customized methods end*/
	
	
	@Override
	public String toString() {
		return "CarOrderReason ["
		 + "id = " + id + ", orderId = " + orderId + ", reasonType = " + reasonType + ", reason = " + reason
		 + ", createTime = " + createTime + ", createTimeStart = " + createTimeStart + ", createTimeEnd = " + createTimeEnd + ", operateId = " + operateId + ", userType = " + userType
		+"]";
	}
}
