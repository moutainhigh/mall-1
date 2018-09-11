package com.yunxin.cb.mall.entity;

import java.beans.Transient;
import java.util.Date;
import com.yunxin.cb.mall.common.Entity;

/** 
 * 基础数据表 数据实体类
 */
public class CarBaseData extends Entity<Integer> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	//主键
	private Integer id;
	//基础数据名称
	private String baseDataName;
	//基础数据编码
	private String baseDataCode;
	//父基础数据
	private Integer parentBaseDataId;
	//父基础对象
	private CarBaseData parentBaseData;
	//状态(0否,1是)
	private Integer enabled;
	//排序
	private Integer sortOrder;
	//备注
	private String remark;
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
	
	public String getBaseDataName(){
		return baseDataName;
	}
	
	public void setBaseDataName(String baseDataName){
		this.baseDataName = baseDataName;
	}
	
	public String getBaseDataCode(){
		return baseDataCode;
	}
	
	public void setBaseDataCode(String baseDataCode){
		this.baseDataCode = baseDataCode;
	}
	
	public Integer getParentBaseDataId(){
		return parentBaseDataId;
	}
	
	public void setParentBaseDataId(Integer parentBaseDataId){
		this.parentBaseDataId = parentBaseDataId;
	}
	
	public Integer getEnabled(){
		return enabled;
	}
	
	public void setEnabled(Integer enabled){
		this.enabled = enabled;
	}
	
	public Integer getSortOrder(){
		return sortOrder;
	}
	
	public void setSortOrder(Integer sortOrder){
		this.sortOrder = sortOrder;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
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

	public CarBaseData getParentBaseData() {
		return parentBaseData;
	}

	public void setParentBaseData(CarBaseData parentBaseData) {
		this.parentBaseData = parentBaseData;
	}

	/*Auto generated methods end*/

	/*@Transient
	public Integer getParentId() {
		if (parentBaseData == null) {
			return null;
		}
		return parentBaseData.getId();
	}*/
	
	/*Customized methods start*/
	
	/*Customized methods end*/
	
	
	@Override
	public String toString() {
		return "CarBaseData ["
		 + "id = " + id + ", baseDataName = " + baseDataName + ", baseDataCode = " + baseDataCode + ", parentBaseDataId = " + parentBaseDataId
		 + ", enabled = " + enabled + ", sortOrder = " + sortOrder + ", remark = " + remark + ", createTime = " + createTime + ", createTimeStart = " + createTimeStart + ", createTimeEnd = " + createTimeEnd
		 + ", updateTime = " + updateTime + ", updateTimeStart = " + updateTimeStart + ", updateTimeEnd = " + updateTimeEnd + ", operateId = " + operateId
		+"]";
	}
}
