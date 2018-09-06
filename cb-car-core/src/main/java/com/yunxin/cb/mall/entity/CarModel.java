package com.yunxin.cb.mall.entity;

import java.util.Date;

import com.yunxin.cb.mall.common.Entity;

/** 
 * 车型信息 数据实体类
 */
public class CarModel extends Entity<Integer> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	private Integer id;
	//品牌ID
	private Integer brandId;
	//车系ID
	private Integer sysId;
	//排量ID
	private Integer disId;
	//车型编号
	private String modelCode;
	//车型名称
	private String modelName;
	//品类ID
	private Integer catalogId;
	//座位ID
	private Integer seatId;
	//结构ID
	private Integer structuId;
	//是否置换（0：否；1：是）
	private Integer substitutuion;
	//上架状态（0：否；1：是）
	private Integer publishState;
	//审核状态（0：未审核；1：通过；2：未通过）
	private Integer examineState;
	//介绍
	private String introduce;
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
	
	public Integer getBrandId(){
		return brandId;
	}
	
	public void setBrandId(Integer brandId){
		this.brandId = brandId;
	}
	
	public Integer getSysId(){
		return sysId;
	}
	
	public void setSysId(Integer sysId){
		this.sysId = sysId;
	}
	
	public Integer getDisId(){
		return disId;
	}
	
	public void setDisId(Integer disId){
		this.disId = disId;
	}
	
	public String getModelCode(){
		return modelCode;
	}
	
	public void setModelCode(String modelCode){
		this.modelCode = modelCode;
	}
	
	public String getModelName(){
		return modelName;
	}
	
	public void setModelName(String modelName){
		this.modelName = modelName;
	}
	
	public Integer getCatalogId(){
		return catalogId;
	}
	
	public void setCatalogId(Integer catalogId){
		this.catalogId = catalogId;
	}
	
	public Integer getSeatId(){
		return seatId;
	}
	
	public void setSeatId(Integer seatId){
		this.seatId = seatId;
	}
	
	public Integer getStructuId(){
		return structuId;
	}
	
	public void setStructuId(Integer structuId){
		this.structuId = structuId;
	}
	
	public Integer getSubstitutuion(){
		return substitutuion;
	}
	
	public void setSubstitutuion(Integer substitutuion){
		this.substitutuion = substitutuion;
	}
	
	public Integer getPublishState(){
		return publishState;
	}
	
	public void setPublishState(Integer publishState){
		this.publishState = publishState;
	}
	
	public Integer getExamineState(){
		return examineState;
	}
	
	public void setExamineState(Integer examineState){
		this.examineState = examineState;
	}
	
	public String getIntroduce(){
		return introduce;
	}
	
	public void setIntroduce(String introduce){
		this.introduce = introduce;
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
		return "CarModel ["
		 + "id = " + id + ", brandId = " + brandId + ", sysId = " + sysId + ", disId = " + disId
		 + ", modelCode = " + modelCode + ", modelName = " + modelName + ", catalogId = " + catalogId + ", seatId = " + seatId
		 + ", structuId = " + structuId + ", substitutuion = " + substitutuion + ", publishState = " + publishState + ", examineState = " + examineState
		 + ", introduce = " + introduce + ", createTime = " + createTime + ", createTimeStart = " + createTimeStart + ", createTimeEnd = " + createTimeEnd + ", updateTime = " + updateTime + ", updateTimeStart = " + updateTimeStart + ", updateTimeEnd = " + updateTimeEnd + ", isDelete = " + isDelete
		 + ", operateId = " + operateId
		+"]";
	}
}
