package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.common.Entity;

/** 
 * UserRole 数据实体类
 */
public class UserRole extends Entity<Integer> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	private Integer userId;
	private Integer roleId;
	
	/*Auto generated properties end*/
	
	
	
	/*Customized properties start*/
	
	/*Customized properties end*/
	
	
	
	/*Auto generated methods start*/
	
	@Override
	public Integer getPK(){
		return roleId;
	}
	
	public Integer getUserId(){
		return userId;
	}
	
	public void setUserId(Integer userId){
		this.userId = userId;
	}
	
	public Integer getRoleId(){
		return roleId;
	}
	
	public void setRoleId(Integer roleId){
		this.roleId = roleId;
	}
	
	
	/*Auto generated methods end*/
	
	
	
	/*Customized methods start*/
	
	/*Customized methods end*/
	
	
	@Override
	public String toString() {
		return "UserRole ["
		 + "userId = " + userId + ", roleId = " + roleId
		+"]";
	}
}
