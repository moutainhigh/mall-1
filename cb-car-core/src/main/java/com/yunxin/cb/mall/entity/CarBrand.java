package com.yunxin.cb.mall.entity;

import java.util.Date;

import com.yunxin.cb.mall.common.Entity;

/** 
 * 品牌 数据实体类
 */
public class CarBrand extends Entity<Integer> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	private Integer id;
	//品牌中文名称
	private String brandName;
	//品牌英文名称
	private String brandEnName;
	//首字母
	private String initial;
	//品牌编号
	private String brandNo;
	//国家(分类表,国家分类id)
	private String catalogId;
	//是否显示(0否,1是)
	private Integer isDisplay;
	//是否热门品牌(0否,1是)
	private Integer isHot;
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
	//默认图片
	private String defaultPic;
	
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
	
	public String getBrandName(){
		return brandName;
	}
	
	public void setBrandName(String brandName){
		this.brandName = brandName;
	}
	
	public String getBrandEnName(){
		return brandEnName;
	}
	
	public void setBrandEnName(String brandEnName){
		this.brandEnName = brandEnName;
	}
	
	public String getInitial(){
		return initial;
	}
	
	public void setInitial(String initial){
		this.initial = initial;
	}
	
	public String getBrandNo(){
		return brandNo;
	}
	
	public void setBrandNo(String brandNo){
		this.brandNo = brandNo;
	}
	
	public String getCatalogId(){
		return catalogId;
	}
	
	public void setCatalogId(String catalogId){
		this.catalogId = catalogId;
	}
	
	public Integer getIsDisplay(){
		return isDisplay;
	}
	
	public void setIsDisplay(Integer isDisplay){
		this.isDisplay = isDisplay;
	}
	
	public Integer getIsHot(){
		return isHot;
	}
	
	public void setIsHot(Integer isHot){
		this.isHot = isHot;
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
	
	public String getDefaultPic(){
		return defaultPic;
	}
	
	public void setDefaultPic(String defaultPic){
		this.defaultPic = defaultPic;
	}
	
	
	/*Auto generated methods end*/
	
	
	
	/*Customized methods start*/
	
	/*Customized methods end*/
	
	
	@Override
	public String toString() {
		return "CarBrand ["
		 + "id = " + id + ", brandName = " + brandName + ", brandEnName = " + brandEnName + ", initial = " + initial
		 + ", brandNo = " + brandNo + ", catalogId = " + catalogId + ", isDisplay = " + isDisplay + ", isHot = " + isHot
		 + ", remark = " + remark + ", createTime = " + createTime + ", createTimeStart = " + createTimeStart + ", createTimeEnd = " + createTimeEnd + ", updateTime = " + updateTime + ", updateTimeStart = " + updateTimeStart + ", updateTimeEnd = " + updateTimeEnd + ", operateId = " + operateId
		 + ", defaultPic = " + defaultPic
		+"]";
	}
}
