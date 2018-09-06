package com.yunxin.cb.mall.entity;

import com.yunxin.cb.mall.common.Entity;

/** 
 * 汽车配置信息 数据实体类
 */
public class CarModelSpec extends Entity<Long> {
	
	private static final long serialVersionUID = 1l;
	
	/*Auto generated properties start*/
	
	private Long id;
	//车型ID
	private Integer modelId;
	//配置ID
	private Integer specId;
	//配置值
	private String specValue;
	
	/*Auto generated properties end*/
	
	
	
	/*Customized properties start*/
	
	/*Customized properties end*/
	
	
	
	/*Auto generated methods start*/
	
	@Override
	public Long getPK(){
		return id;
	}
	
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Integer getModelId(){
		return modelId;
	}
	
	public void setModelId(Integer modelId){
		this.modelId = modelId;
	}
	
	public Integer getSpecId(){
		return specId;
	}
	
	public void setSpecId(Integer specId){
		this.specId = specId;
	}
	
	public String getSpecValue(){
		return specValue;
	}
	
	public void setSpecValue(String specValue){
		this.specValue = specValue;
	}
	
	
	/*Auto generated methods end*/
	
	
	
	/*Customized methods start*/
	
	/*Customized methods end*/
	
	
	@Override
	public String toString() {
		return "CarModelSpec ["
		 + "id = " + id + ", modelId = " + modelId + ", specId = " + specId + ", specValue = " + specValue
		+"]";
	}
}
