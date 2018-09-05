package com.yunxin.cb.mall.entity;

import java.util.Date;

import com.yunxin.cb.mall.common.Entity;

/** 
 * Favorite 数据实体类
 */
public class Favorite extends Entity<Integer> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	private Integer favoriteId;
	private Date createTime;
	// 时间范围起（查询用）
	private Date createTimeStart;
	// 时间范围止（查询用）
	private Date createTimeEnd;	
	private Float salePrice;
	private Integer commodityId;
	private Integer customerId;
	//货品id
	private Integer productId;
	
	/*Auto generated properties end*/
	
	
	
	/*Customized properties start*/
	
	/*Customized properties end*/
	
	
	
	/*Auto generated methods start*/
	
	@Override
	public Integer getPK(){
		return favoriteId;
	}
	
	public Integer getFavoriteId(){
		return favoriteId;
	}
	
	public void setFavoriteId(Integer favoriteId){
		this.favoriteId = favoriteId;
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
	
	public Float getSalePrice(){
		return salePrice;
	}
	
	public void setSalePrice(Float salePrice){
		this.salePrice = salePrice;
	}
	
	public Integer getCommodityId(){
		return commodityId;
	}
	
	public void setCommodityId(Integer commodityId){
		this.commodityId = commodityId;
	}
	
	public Integer getCustomerId(){
		return customerId;
	}
	
	public void setCustomerId(Integer customerId){
		this.customerId = customerId;
	}
	
	public Integer getProductId(){
		return productId;
	}
	
	public void setProductId(Integer productId){
		this.productId = productId;
	}
	
	
	/*Auto generated methods end*/
	
	
	
	/*Customized methods start*/
	
	/*Customized methods end*/
	
	
	@Override
	public String toString() {
		return "Favorite ["
		 + "favoriteId = " + favoriteId + ", createTime = " + createTime + ", createTimeStart = " + createTimeStart + ", createTimeEnd = " + createTimeEnd + ", salePrice = " + salePrice + ", commodityId = " + commodityId
		 + ", customerId = " + customerId + ", productId = " + productId
		+"]";
	}
}
