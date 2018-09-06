package com.yunxin.cb.mall.entity;

import java.util.Date;

import com.yunxin.cb.mall.common.Entity;

/** 
 * 广告 数据实体类
 */
public class CarAdvertisements extends Entity<Integer> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	private Integer id;
	//标题
	private String title;
	//类型
	private String adverType;
	//位置
	private String position;
	//开始时间
	private Date stateTime;
	//开始时间 时间范围起（查询用）
	private Date stateTimeStart;
	//开始时间 时间范围止（查询用）
	private Date stateTimeEnd;	
	//结束时间
	private Date endTime;
	//结束时间 时间范围起（查询用）
	private Date endTimeStart;
	//结束时间 时间范围止（查询用）
	private Date endTimeEnd;	
	//是否启用（0：否；1：是）
	private Integer enabled;
	//跳转类型（0：不跳转：1：内部；2：外部）
	private Integer jumpType;
	//跳转路径
	private String jumpUrl;
	//品牌ID  
	private Integer brandId;
	//车系ID
	private Integer sysId;
	//内容
	private String content;
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
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getAdverType(){
		return adverType;
	}
	
	public void setAdverType(String adverType){
		this.adverType = adverType;
	}
	
	public String getPosition(){
		return position;
	}
	
	public void setPosition(String position){
		this.position = position;
	}
	
	public Date getStateTime(){
		return stateTime;
	}
	
	public void setStateTime(Date stateTime){
		this.stateTime = stateTime;
	}
	
	public Date getStateTimeStart(){
		return stateTimeStart;
	}
	
	public void setStateTimeStart(Date stateTimeStart){
		this.stateTimeStart = stateTimeStart;
	}
	
	public Date getStateTimeEnd(){
		return stateTimeEnd;
	}
	
	public void setStateTimeEnd(Date stateTimeEnd){
		this.stateTimeEnd = stateTimeEnd;
	}	
	
	public Date getEndTime(){
		return endTime;
	}
	
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}
	
	public Date getEndTimeStart(){
		return endTimeStart;
	}
	
	public void setEndTimeStart(Date endTimeStart){
		this.endTimeStart = endTimeStart;
	}
	
	public Date getEndTimeEnd(){
		return endTimeEnd;
	}
	
	public void setEndTimeEnd(Date endTimeEnd){
		this.endTimeEnd = endTimeEnd;
	}	
	
	public Integer getEnabled(){
		return enabled;
	}
	
	public void setEnabled(Integer enabled){
		this.enabled = enabled;
	}
	
	public Integer getJumpType(){
		return jumpType;
	}
	
	public void setJumpType(Integer jumpType){
		this.jumpType = jumpType;
	}
	
	public String getJumpUrl(){
		return jumpUrl;
	}
	
	public void setJumpUrl(String jumpUrl){
		this.jumpUrl = jumpUrl;
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
	
	public String getContent(){
		return content;
	}
	
	public void setContent(String content){
		this.content = content;
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
		return "CarAdvertisements ["
		 + "id = " + id + ", title = " + title + ", adverType = " + adverType + ", position = " + position
		 + ", stateTime = " + stateTime + ", stateTimeStart = " + stateTimeStart + ", stateTimeEnd = " + stateTimeEnd + ", endTime = " + endTime + ", endTimeStart = " + endTimeStart + ", endTimeEnd = " + endTimeEnd + ", enabled = " + enabled + ", jumpType = " + jumpType
		 + ", jumpUrl = " + jumpUrl + ", brandId = " + brandId + ", sysId = " + sysId + ", content = " + content
		 + ", createTime = " + createTime + ", createTimeStart = " + createTimeStart + ", createTimeEnd = " + createTimeEnd + ", updateTime = " + updateTime + ", updateTimeStart = " + updateTimeStart + ", updateTimeEnd = " + updateTimeEnd + ", isDelete = " + isDelete + ", operateId = " + operateId
		+"]";
	}
}
