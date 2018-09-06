package com.yunxin.cb.mall.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.yunxin.cb.mall.common.Entity;

/** 
 * 价格段置换 数据实体类
 */
public class CarSetPrice extends Entity<Integer> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	private Integer id;
	//最小价格
	private BigDecimal priMin;
	//最大价格
	private BigDecimal priMax;
	//级别
	private Integer level;
	//置换手续费
	private BigDecimal priValue;
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
	
	public BigDecimal getPriMin(){
		return priMin;
	}
	
	public void setPriMin(BigDecimal priMin){
		this.priMin = priMin;
	}
	
	public BigDecimal getPriMax(){
		return priMax;
	}
	
	public void setPriMax(BigDecimal priMax){
		this.priMax = priMax;
	}
	
	public Integer getLevel(){
		return level;
	}
	
	public void setLevel(Integer level){
		this.level = level;
	}
	
	public BigDecimal getPriValue(){
		return priValue;
	}
	
	public void setPriValue(BigDecimal priValue){
		this.priValue = priValue;
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
		return "CarSetPrice ["
		 + "id = " + id + ", priMin = " + priMin + ", priMax = " + priMax + ", level = " + level
		 + ", priValue = " + priValue + ", createTime = " + createTime + ", createTimeStart = " + createTimeStart + ", createTimeEnd = " + createTimeEnd + ", updateTime = " + updateTime + ", updateTimeStart = " + updateTimeStart + ", updateTimeEnd = " + updateTimeEnd + ", isDelete = " + isDelete
		 + ", operateId = " + operateId
		+"]";
	}
}
