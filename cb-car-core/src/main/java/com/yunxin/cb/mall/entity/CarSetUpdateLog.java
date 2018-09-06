package com.yunxin.cb.mall.entity;

import java.util.Date;

import com.yunxin.cb.mall.common.Entity;

/** 
 * 设置中心修改记录 数据实体类
 */
public class CarSetUpdateLog extends Entity<Integer> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	private Integer id;
	//帐号
	private String account;
	//姓名
	private String name;
	//设置名称
	private String setName;
	//设置类型
	private String setType;
	//初始值
	private String initValue;
	//设置值
	private String setValue;
	//是否逻辑删除（0：否；1：是）
	private Integer isDelete;
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
	
	public String getAccount(){
		return account;
	}
	
	public void setAccount(String account){
		this.account = account;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getSetName(){
		return setName;
	}
	
	public void setSetName(String setName){
		this.setName = setName;
	}
	
	public String getSetType(){
		return setType;
	}
	
	public void setSetType(String setType){
		this.setType = setType;
	}
	
	public String getInitValue(){
		return initValue;
	}
	
	public void setInitValue(String initValue){
		this.initValue = initValue;
	}
	
	public String getSetValue(){
		return setValue;
	}
	
	public void setSetValue(String setValue){
		this.setValue = setValue;
	}
	
	public Integer getIsDelete(){
		return isDelete;
	}
	
	public void setIsDelete(Integer isDelete){
		this.isDelete = isDelete;
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
		return "CarSetUpdateLog ["
		 + "id = " + id + ", account = " + account + ", name = " + name + ", setName = " + setName
		 + ", setType = " + setType + ", initValue = " + initValue + ", setValue = " + setValue + ", isDelete = " + isDelete
		 + ", createTime = " + createTime + ", createTimeStart = " + createTimeStart + ", createTimeEnd = " + createTimeEnd
		+"]";
	}
}
