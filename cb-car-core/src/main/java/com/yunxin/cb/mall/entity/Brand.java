package com.yunxin.cb.mall.entity;

import java.util.Date;

import com.yunxin.cb.mall.common.Entity;

/** 
 * Brand 数据实体类
 */
public class Brand extends Entity<Integer> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	private Integer brandId;
	private String brandEnName;
	private String brandKey;
	private String brandName;
	private String brandNo;
	private String brandTitle;
	private Date createTime;
	// 时间范围起（查询用）
	private Date createTimeStart;
	// 时间范围止（查询用）
	private Date createTimeEnd;	
	private String description;
	private Boolean display;
	private Boolean enabled;
	private Boolean hot;
	private String picPath;
	private String remark;
	private String seoDescription;
	private String seoKey;
	private String seoTitle;
	private String website;
	private Integer categoryId;
	//热门品牌排序
	private Integer sort;
	
	/*Auto generated properties end*/
	
	
	
	/*Customized properties start*/
	
	/*Customized properties end*/
	
	
	
	/*Auto generated methods start*/
	
	@Override
	public Integer getPK(){
		return brandId;
	}
	
	public Integer getBrandId(){
		return brandId;
	}
	
	public void setBrandId(Integer brandId){
		this.brandId = brandId;
	}
	
	public String getBrandEnName(){
		return brandEnName;
	}
	
	public void setBrandEnName(String brandEnName){
		this.brandEnName = brandEnName;
	}
	
	public String getBrandKey(){
		return brandKey;
	}
	
	public void setBrandKey(String brandKey){
		this.brandKey = brandKey;
	}
	
	public String getBrandName(){
		return brandName;
	}
	
	public void setBrandName(String brandName){
		this.brandName = brandName;
	}
	
	public String getBrandNo(){
		return brandNo;
	}
	
	public void setBrandNo(String brandNo){
		this.brandNo = brandNo;
	}
	
	public String getBrandTitle(){
		return brandTitle;
	}
	
	public void setBrandTitle(String brandTitle){
		this.brandTitle = brandTitle;
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
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public Boolean getDisplay(){
		return display;
	}
	
	public void setDisplay(Boolean display){
		this.display = display;
	}
	
	public Boolean getEnabled(){
		return enabled;
	}
	
	public void setEnabled(Boolean enabled){
		this.enabled = enabled;
	}
	
	public Boolean getHot(){
		return hot;
	}
	
	public void setHot(Boolean hot){
		this.hot = hot;
	}
	
	public String getPicPath(){
		return picPath;
	}
	
	public void setPicPath(String picPath){
		this.picPath = picPath;
	}
	
	public String getRemark(){
		return remark;
	}
	
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getSeoDescription(){
		return seoDescription;
	}
	
	public void setSeoDescription(String seoDescription){
		this.seoDescription = seoDescription;
	}
	
	public String getSeoKey(){
		return seoKey;
	}
	
	public void setSeoKey(String seoKey){
		this.seoKey = seoKey;
	}
	
	public String getSeoTitle(){
		return seoTitle;
	}
	
	public void setSeoTitle(String seoTitle){
		this.seoTitle = seoTitle;
	}
	
	public String getWebsite(){
		return website;
	}
	
	public void setWebsite(String website){
		this.website = website;
	}
	
	public Integer getCategoryId(){
		return categoryId;
	}
	
	public void setCategoryId(Integer categoryId){
		this.categoryId = categoryId;
	}
	
	public Integer getSort(){
		return sort;
	}
	
	public void setSort(Integer sort){
		this.sort = sort;
	}
	
	
	/*Auto generated methods end*/
	
	
	
	/*Customized methods start*/
	
	/*Customized methods end*/
	
	
	@Override
	public String toString() {
		return "Brand ["
		 + "brandId = " + brandId + ", brandEnName = " + brandEnName + ", brandKey = " + brandKey + ", brandName = " + brandName
		 + ", brandNo = " + brandNo + ", brandTitle = " + brandTitle + ", createTime = " + createTime + ", createTimeStart = " + createTimeStart + ", createTimeEnd = " + createTimeEnd + ", description = " + description
		 + ", display = " + display + ", enabled = " + enabled + ", hot = " + hot + ", picPath = " + picPath
		 + ", remark = " + remark + ", seoDescription = " + seoDescription + ", seoKey = " + seoKey + ", seoTitle = " + seoTitle
		 + ", website = " + website + ", categoryId = " + categoryId + ", sort = " + sort
		+"]";
	}
}
