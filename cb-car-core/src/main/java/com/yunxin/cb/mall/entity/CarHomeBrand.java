package com.yunxin.cb.mall.entity;

import java.util.Date;

import com.yunxin.cb.mall.common.Entity;

/** 
 * 汽车之家品牌数据 数据实体类
 */
public class CarHomeBrand extends Entity<Integer> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	//ID
	private Integer id;
	//父节点ID
	private Integer parentId;
	//中文名称
	private String chinessName;
	//英文名称
	private String enName;
	//级别(1,品牌,2车系,3车型)
	private Integer level;
	//首字母
	private String initial;
	//是否显示(0否,1是)
	private Integer isDisplay;
	//CREATE_TIME
	private Date createTime;
	//CREATE_TIME 时间范围起（查询用）
	private Date createTimeStart;
	//CREATE_TIME 时间范围止（查询用）
	private Date createTimeEnd;	
	//UPDATE_TIME
	private Date updateTime;
	//UPDATE_TIME 时间范围起（查询用）
	private Date updateTimeStart;
	//UPDATE_TIME 时间范围止（查询用）
	private Date updateTimeEnd;	
	
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
	
	public Integer getParentId(){
		return parentId;
	}
	
	public void setParentId(Integer parentId){
		this.parentId = parentId;
	}
	
	public String getChinessName(){
		return chinessName;
	}
	
	public void setChinessName(String chinessName){
		this.chinessName = chinessName;
	}
	
	public String getEnName(){
		return enName;
	}
	
	public void setEnName(String enName){
		this.enName = enName;
	}
	
	public Integer getLevel(){
		return level;
	}
	
	public void setLevel(Integer level){
		this.level = level;
	}
	
	public String getInitial(){
		return initial;
	}
	
	public void setInitial(String initial){
		this.initial = initial;
	}
	
	public Integer getIsDisplay(){
		return isDisplay;
	}
	
	public void setIsDisplay(Integer isDisplay){
		this.isDisplay = isDisplay;
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
	
	
	/*Auto generated methods end*/
	
	
	
	/*Customized methods start*/
	
	/*Customized methods end*/
	
	
	@Override
	public String toString() {
		return "CarHomeBrand ["
		 + "id = " + id + ", parentId = " + parentId + ", chinessName = " + chinessName + ", enName = " + enName
		 + ", level = " + level + ", initial = " + initial + ", isDisplay = " + isDisplay + ", createTime = " + createTime + ", createTimeStart = " + createTimeStart + ", createTimeEnd = " + createTimeEnd
		 + ", updateTime = " + updateTime + ", updateTimeStart = " + updateTimeStart + ", updateTimeEnd = " + updateTimeEnd
		+"]";
	}
}
