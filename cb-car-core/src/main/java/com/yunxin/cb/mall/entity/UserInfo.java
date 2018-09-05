package com.yunxin.cb.mall.entity;

import java.util.Date;

import com.yunxin.cb.mall.common.Entity;

/** 
 * UserInfo 数据实体类
 */
public class UserInfo extends Entity<Integer> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	private Integer userId;
	private Date createTime;
	// 时间范围起（查询用）
	private Date createTimeStart;
	// 时间范围止（查询用）
	private Date createTimeEnd;	
	private String email;
	private Boolean enabled;
	private Date lastTime;
	// 时间范围起（查询用）
	private Date lastTimeStart;
	// 时间范围止（查询用）
	private Date lastTimeEnd;	
	private String mobile;
	private String password;
	private String position;
	private String realName;
	private String remark;
	private Boolean sex;
	private String userName;
	private Integer sellerId;
	
	/*Auto generated properties end*/
	
	
	
	/*Customized properties start*/
	
	/*Customized properties end*/
	
	
	
	/*Auto generated methods start*/
	
	@Override
	public Integer getPK(){
		return userId;
	}
	
	public Integer getUserId(){
		return userId;
	}
	
	public void setUserId(Integer userId){
		this.userId = userId;
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
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public Boolean getEnabled(){
		return enabled;
	}
	
	public void setEnabled(Boolean enabled){
		this.enabled = enabled;
	}
	
	public Date getLastTime(){
		return lastTime;
	}
	
	public void setLastTime(Date lastTime){
		this.lastTime = lastTime;
	}
	
	public Date getLastTimeStart(){
		return lastTimeStart;
	}
	
	public void setLastTimeStart(Date lastTimeStart){
		this.lastTimeStart = lastTimeStart;
	}
	
	public Date getLastTimeEnd(){
		return lastTimeEnd;
	}
	
	public void setLastTimeEnd(Date lastTimeEnd){
		this.lastTimeEnd = lastTimeEnd;
	}	
	
	public String getMobile(){
		return mobile;
	}
	
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPosition(){
		return position;
	}
	
	public void setPosition(String position){
		this.position = position;
	}
	
	public String getRealName(){
		return realName;
	}
	
	public void setRealName(String realName){
		this.realName = realName;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public Boolean getSex(){
		return sex;
	}
	
	public void setSex(Boolean sex){
		this.sex = sex;
	}
	
	public String getUserName(){
		return userName;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	public Integer getSellerId(){
		return sellerId;
	}
	
	public void setSellerId(Integer sellerId){
		this.sellerId = sellerId;
	}
	
	
	/*Auto generated methods end*/
	
	
	
	/*Customized methods start*/
	
	/*Customized methods end*/
	
	
	@Override
	public String toString() {
		return "UserInfo ["
		 + "userId = " + userId + ", createTime = " + createTime + ", createTimeStart = " + createTimeStart + ", createTimeEnd = " + createTimeEnd + ", email = " + email + ", enabled = " + enabled
		 + ", lastTime = " + lastTime + ", lastTimeStart = " + lastTimeStart + ", lastTimeEnd = " + lastTimeEnd + ", mobile = " + mobile + ", password = " + password + ", position = " + position
		 + ", realName = " + realName + ", remark = " + remark + ", sex = " + sex + ", userName = " + userName
		 + ", sellerId = " + sellerId
		+"]";
	}
}
