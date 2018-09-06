package com.yunxin.cb.mall.entity;

import java.util.Date;

import com.yunxin.cb.mall.common.Entity;

/** 
 * 试驾信息 数据实体类
 */
public class CarTestDrive extends Entity<Integer> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	private Integer id;
	//试驾人
	private String driveName;
	//手机号
	private String mobile;
	//地区ID（关联开通城市表的主键）
	private Integer cityId;
	//城市名称
	private String cityName;
	//车系ID
	private Integer sysId;
	//门店ID
	private Integer agentId;
	//状态（0：未试驾；1：已试驾）
	private Boolean driveState;
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
	//是否逻辑删除
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
	
	public String getDriveName(){
		return driveName;
	}
	
	public void setDriveName(String driveName){
		this.driveName = driveName;
	}
	
	public String getMobile(){
		return mobile;
	}
	
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	public Integer getCityId(){
		return cityId;
	}
	
	public void setCityId(Integer cityId){
		this.cityId = cityId;
	}
	
	public String getCityName(){
		return cityName;
	}
	
	public void setCityName(String cityName){
		this.cityName = cityName;
	}
	
	public Integer getSysId(){
		return sysId;
	}
	
	public void setSysId(Integer sysId){
		this.sysId = sysId;
	}
	
	public Integer getAgentId(){
		return agentId;
	}
	
	public void setAgentId(Integer agentId){
		this.agentId = agentId;
	}
	
	public Boolean getDriveState(){
		return driveState;
	}
	
	public void setDriveState(Boolean driveState){
		this.driveState = driveState;
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
		return "CarTestDrive ["
		 + "id = " + id + ", driveName = " + driveName + ", mobile = " + mobile + ", cityId = " + cityId
		 + ", cityName = " + cityName + ", sysId = " + sysId + ", agentId = " + agentId + ", driveState = " + driveState
		 + ", createTime = " + createTime + ", createTimeStart = " + createTimeStart + ", createTimeEnd = " + createTimeEnd + ", updateTime = " + updateTime + ", updateTimeStart = " + updateTimeStart + ", updateTimeEnd = " + updateTimeEnd + ", isDelete = " + isDelete + ", operateId = " + operateId
		+"]";
	}
}
