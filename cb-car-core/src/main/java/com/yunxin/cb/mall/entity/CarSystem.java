package com.yunxin.cb.mall.entity;

import java.util.Date;

import com.yunxin.cb.mall.common.Entity;

/** 
 * 汽车车系 数据实体类
 */
public class CarSystem extends Entity<Integer> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	private Integer id;
	//品牌ID  
	private Integer brandId;
	//车系名称
	private String sysName;
	//车系编号
	private String sysCode;
	//基础数据ID
	private Integer baseDataId;
	//是否热门
	private Integer isHot;
	//是否主打车（0：否；1：是）
	private Integer mainCar;
	//审核状态（0：未审核；1：通过；2：未通过）
	private Integer examineState;
	//上架状态（0：否；1：是）
	private Integer publishState;
	//介绍
	private String introduce;
	//默认图片
	private String defaultPic;
	//说明
	private String explainInfo;
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
	
	public String getSysName(){
		return sysName;
	}
	
	public void setSysName(String sysName){
		this.sysName = sysName;
	}
	
	public String getSysCode(){
		return sysCode;
	}
	
	public void setSysCode(String sysCode){
		this.sysCode = sysCode;
	}
	
	public Integer getBaseDataId(){
		return baseDataId;
	}
	
	public void setBaseDataId(Integer baseDataId){
		this.baseDataId = baseDataId;
	}
	
	public Integer getIsHot(){
		return isHot;
	}
	
	public void setIsHot(Integer isHot){
		this.isHot = isHot;
	}
	
	public Integer getMainCar(){
		return mainCar;
	}
	
	public void setMainCar(Integer mainCar){
		this.mainCar = mainCar;
	}
	
	public Integer getExamineState(){
		return examineState;
	}
	
	public void setExamineState(Integer examineState){
		this.examineState = examineState;
	}
	
	public Integer getPublishState(){
		return publishState;
	}
	
	public void setPublishState(Integer publishState){
		this.publishState = publishState;
	}
	
	public String getIntroduce(){
		return introduce;
	}
	
	public void setIntroduce(String introduce){
		this.introduce = introduce;
	}
	
	public String getDefaultPic(){
		return defaultPic;
	}
	
	public void setDefaultPic(String defaultPic){
		this.defaultPic = defaultPic;
	}
	
	public String getExplainInfo(){
		return explainInfo;
	}
	
	public void setExplainInfo(String explainInfo){
		this.explainInfo = explainInfo;
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
		return "CarSystem ["
		 + "id = " + id + ", brandId = " + brandId + ", sysName = " + sysName + ", sysCode = " + sysCode
		 + ", baseDataId = " + baseDataId + ", isHot = " + isHot + ", mainCar = " + mainCar + ", examineState = " + examineState
		 + ", publishState = " + publishState + ", introduce = " + introduce + ", defaultPic = " + defaultPic + ", explainInfo = " + explainInfo
		 + ", createTime = " + createTime + ", createTimeStart = " + createTimeStart + ", createTimeEnd = " + createTimeEnd + ", updateTime = " + updateTime + ", updateTimeStart = " + updateTimeStart + ", updateTimeEnd = " + updateTimeEnd + ", isDelete = " + isDelete + ", operateId = " + operateId
		+"]";
	}
}
