package com.yunxin.cb.mall.entity;

import java.util.Date;

import com.yunxin.cb.mall.common.Entity;

/** 
 * Attachment 数据实体类
 */
public class Attachment extends Entity<Integer> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	private Integer attachId;
	//业务对象类型
	private String objectType;
	//业务对象ID
	private Integer objectId;
	//业务应用场景编码
	private String businessScenario;
	//文件名ID
	private String inputId;
	//访问路径
	private String filePath;
	//附件名称
	private String fileName;
	//文件类型
	private String fileType;
	//文件大小
	private Integer fileSize;
	//文件存储系统标识
	private String fsGuid;
	//上传时间
	private Date createTime;
	//上传时间 时间范围起（查询用）
	private Date createTimeStart;
	//上传时间 时间范围止（查询用）
	private Date createTimeEnd;	
	//上传人ID
	private Integer staffId;
	//上传人
	private String staffName;
	//状态
	private String state;
	//描述
	private String description;
	
	/*Auto generated properties end*/
	
	
	
	/*Customized properties start*/
	
	/*Customized properties end*/
	
	
	
	/*Auto generated methods start*/
	
	@Override
	public Integer getPK(){
		return attachId;
	}
	
	public Integer getAttachId(){
		return attachId;
	}
	
	public void setAttachId(Integer attachId){
		this.attachId = attachId;
	}
	
	public String getObjectType(){
		return objectType;
	}
	
	public void setObjectType(String objectType){
		this.objectType = objectType;
	}
	
	public Integer getObjectId(){
		return objectId;
	}
	
	public void setObjectId(Integer objectId){
		this.objectId = objectId;
	}
	
	public String getBusinessScenario(){
		return businessScenario;
	}
	
	public void setBusinessScenario(String businessScenario){
		this.businessScenario = businessScenario;
	}
	
	public String getInputId(){
		return inputId;
	}
	
	public void setInputId(String inputId){
		this.inputId = inputId;
	}
	
	public String getFilePath(){
		return filePath;
	}
	
	public void setFilePath(String filePath){
		this.filePath = filePath;
	}
	
	public String getFileName(){
		return fileName;
	}
	
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	
	public String getFileType(){
		return fileType;
	}
	
	public void setFileType(String fileType){
		this.fileType = fileType;
	}
	
	public Integer getFileSize(){
		return fileSize;
	}
	
	public void setFileSize(Integer fileSize){
		this.fileSize = fileSize;
	}
	
	public String getFsGuid(){
		return fsGuid;
	}
	
	public void setFsGuid(String fsGuid){
		this.fsGuid = fsGuid;
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
	
	public Integer getStaffId(){
		return staffId;
	}
	
	public void setStaffId(Integer staffId){
		this.staffId = staffId;
	}
	
	public String getStaffName(){
		return staffName;
	}
	
	public void setStaffName(String staffName){
		this.staffName = staffName;
	}
	
	public String getState(){
		return state;
	}
	
	public void setState(String state){
		this.state = state;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	
	/*Auto generated methods end*/
	
	
	
	/*Customized methods start*/
	
	/*Customized methods end*/
	
	
	@Override
	public String toString() {
		return "Attachment ["
		 + "attachId = " + attachId + ", objectType = " + objectType + ", objectId = " + objectId + ", businessScenario = " + businessScenario
		 + ", inputId = " + inputId + ", filePath = " + filePath + ", fileName = " + fileName + ", fileType = " + fileType
		 + ", fileSize = " + fileSize + ", fsGuid = " + fsGuid + ", createTime = " + createTime + ", createTimeStart = " + createTimeStart + ", createTimeEnd = " + createTimeEnd + ", staffId = " + staffId
		 + ", staffName = " + staffName + ", state = " + state + ", description = " + description
		+"]";
	}
}
