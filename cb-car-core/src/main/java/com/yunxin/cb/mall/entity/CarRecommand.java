package com.yunxin.cb.mall.entity;

import java.util.Date;

import com.yunxin.cb.mall.common.Entity;

/** 
 * 推荐管理 数据实体类
 */
public class CarRecommand extends Entity<Integer> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	private Integer id;
	//评估类型
	private Integer assessType;
	//推荐名称              
	private String recommandName;
	//最小值
	private Integer minValue;
	//最大值
	private Integer maxValue;
	//是否启用（0：否；1：是）
	private Integer enabled;
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
	
	public Integer getAssessType(){
		return assessType;
	}
	
	public void setAssessType(Integer assessType){
		this.assessType = assessType;
	}
	
	public String getRecommandName(){
		return recommandName;
	}
	
	public void setRecommandName(String recommandName){
		this.recommandName = recommandName;
	}
	
	public Integer getMinValue(){
		return minValue;
	}
	
	public void setMinValue(Integer minValue){
		this.minValue = minValue;
	}
	
	public Integer getMaxValue(){
		return maxValue;
	}
	
	public void setMaxValue(Integer maxValue){
		this.maxValue = maxValue;
	}
	
	public Integer getEnabled(){
		return enabled;
	}
	
	public void setEnabled(Integer enabled){
		this.enabled = enabled;
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
		return "CarRecommand ["
		 + "id = " + id + ", assessType = " + assessType + ", recommandName = " + recommandName + ", minValue = " + minValue
		 + ", maxValue = " + maxValue + ", enabled = " + enabled + ", createTime = " + createTime + ", createTimeStart = " + createTimeStart + ", createTimeEnd = " + createTimeEnd + ", updateTime = " + updateTime + ", updateTimeStart = " + updateTimeStart + ", updateTimeEnd = " + updateTimeEnd
		 + ", operateId = " + operateId
		+"]";
	}
}
