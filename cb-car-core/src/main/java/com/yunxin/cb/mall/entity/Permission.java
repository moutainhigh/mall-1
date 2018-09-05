package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.common.Entity;

/** 
 * 资源权限 数据实体类
 */
public class Permission extends Entity<Integer> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	private Integer permissionId;
	private Integer roleId;
	private String privilegeCode;
	
	/*Auto generated properties end*/
	
	
	
	/*Customized properties start*/
	
	/*Customized properties end*/
	
	
	
	/*Auto generated methods start*/
	
	@Override
	public Integer getPK(){
		return permissionId;
	}
	
	public Integer getPermissionId(){
		return permissionId;
	}
	
	public void setPermissionId(Integer permissionId){
		this.permissionId = permissionId;
	}
	
	public Integer getRoleId(){
		return roleId;
	}
	
	public void setRoleId(Integer roleId){
		this.roleId = roleId;
	}
	
	public String getPrivilegeCode(){
		return privilegeCode;
	}
	
	public void setPrivilegeCode(String privilegeCode){
		this.privilegeCode = privilegeCode;
	}
	
	
	/*Auto generated methods end*/
	
	
	
	/*Customized methods start*/
	
	/*Customized methods end*/
	
	
	@Override
	public String toString() {
		return "Permission ["
		 + "permissionId = " + permissionId + ", roleId = " + roleId + ", privilegeCode = " + privilegeCode
		+"]";
	}
}
