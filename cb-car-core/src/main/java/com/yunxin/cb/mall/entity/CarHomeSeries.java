package com.yunxin.cb.mall.entity;

import java.util.Date;

import com.yunxin.cb.mall.common.Entity;

/** 
 * 汽车之家的车系,与品牌相关 数据实体类
 */
public class CarHomeSeries extends Entity<Integer> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	//汽车车系id
	private Integer id;
	//汽车品牌id
	private Integer brandId;
	//汽车车系分组
	private String seriesGroup;
	//汽车车系
	private String series;
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
	
	public Integer getBrandId(){
		return brandId;
	}
	
	public void setBrandId(Integer brandId){
		this.brandId = brandId;
	}
	
	public String getSeriesGroup(){
		return seriesGroup;
	}
	
	public void setSeriesGroup(String seriesGroup){
		this.seriesGroup = seriesGroup;
	}
	
	public String getSeries(){
		return series;
	}
	
	public void setSeries(String series){
		this.series = series;
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
		return "CarHomeSeries ["
		 + "id = " + id + ", brandId = " + brandId + ", seriesGroup = " + seriesGroup + ", series = " + series
		 + ", isDisplay = " + isDisplay + ", createTime = " + createTime + ", createTimeStart = " + createTimeStart + ", createTimeEnd = " + createTimeEnd + ", updateTime = " + updateTime + ", updateTimeStart = " + updateTimeStart + ", updateTimeEnd = " + updateTimeEnd
		+"]";
	}
}
