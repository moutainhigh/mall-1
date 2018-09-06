package com.yunxin.cb.mall.entity;

import java.util.Date;

import com.yunxin.cb.mall.common.Entity;

/** 
 * 推荐车系中间表 数据实体类
 */
public class CarRecommendSystem extends Entity<Integer> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	//ID
	private Integer id;
	//车系表ID
	private Integer systemId;
	//推荐管理表ID
	private Integer recommendId;
	//创建时间
	private Date createTime;
	//创建时间 时间范围起（查询用）
	private Date createTimeStart;
	//创建时间 时间范围止（查询用）
	private Date createTimeEnd;	
	
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
	
	public Integer getSystemId(){
		return systemId;
	}
	
	public void setSystemId(Integer systemId){
		this.systemId = systemId;
	}
	
	public Integer getRecommendId(){
		return recommendId;
	}
	
	public void setRecommendId(Integer recommendId){
		this.recommendId = recommendId;
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
	
	
	/*Auto generated methods end*/
	
	
	
	/*Customized methods start*/
	
	/*Customized methods end*/
	
	
	@Override
	public String toString() {
		return "CarRecommendSystem ["
		 + "id = " + id + ", systemId = " + systemId + ", recommendId = " + recommendId + ", createTime = " + createTime + ", createTimeStart = " + createTimeStart + ", createTimeEnd = " + createTimeEnd
		+"]";
	}
}
