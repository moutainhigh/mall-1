package com.yunxin.cb.mall.entity;

import java.util.Date;

import com.yunxin.cb.mall.common.Entity;

/** 
 * 已开通城市表 数据实体类
 */
public class CarOpenCity extends Entity<Integer> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	//ID
	private Integer id;
	//省份名称
	private String provinceName;
	//城市名称
	private String cityName;
	//首字母
	private String initial;
	//省份编码
	private String provinceCode;
	//城市编码
	private String cityCode;
	//是否显示(0否,1是)
	private Integer isDisplay;
	//是否热门品牌(0否,1是)
	private Integer isHot;
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
	
	public String getProvinceName(){
		return provinceName;
	}
	
	public void setProvinceName(String provinceName){
		this.provinceName = provinceName;
	}
	
	public String getCityName(){
		return cityName;
	}
	
	public void setCityName(String cityName){
		this.cityName = cityName;
	}
	
	public String getInitial(){
		return initial;
	}
	
	public void setInitial(String initial){
		this.initial = initial;
	}
	
	public String getProvinceCode(){
		return provinceCode;
	}
	
	public void setProvinceCode(String provinceCode){
		this.provinceCode = provinceCode;
	}
	
	public String getCityCode(){
		return cityCode;
	}
	
	public void setCityCode(String cityCode){
		this.cityCode = cityCode;
	}
	
	public Integer getIsDisplay(){
		return isDisplay;
	}
	
	public void setIsDisplay(Integer isDisplay){
		this.isDisplay = isDisplay;
	}
	
	public Integer getIsHot(){
		return isHot;
	}
	
	public void setIsHot(Integer isHot){
		this.isHot = isHot;
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
		return "CarOpenCity ["
		 + "id = " + id + ", provinceName = " + provinceName + ", cityName = " + cityName + ", initial = " + initial
		 + ", provinceCode = " + provinceCode + ", cityCode = " + cityCode + ", isDisplay = " + isDisplay + ", isHot = " + isHot
		 + ", createTime = " + createTime + ", createTimeStart = " + createTimeStart + ", createTimeEnd = " + createTimeEnd + ", updateTime = " + updateTime + ", updateTimeStart = " + updateTimeStart + ", updateTimeEnd = " + updateTimeEnd + ", operateId = " + operateId
		+"]";
	}
}
