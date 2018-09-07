package com.yunxin.cb.vo.responsevo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/** 
 * 已开通城市表Vo
 */
public class CarOpenCityVO implements Serializable {
	
	private static final long serialVersionUID = 1l;
	
	//ID
	@ApiModelProperty(value="主键id",name="id",example="1")
	private Integer id;
	//城市名称
	@ApiModelProperty(value="城市名称",name="cityName",example="深圳")
	private String cityName;
	//首字母
	@ApiModelProperty(value="首字母",name="initial",example="A")
	private String initial;
	//城市编码
	@ApiModelProperty(value="城市编码",name="cityCode",example="100045")
	private String cityCode;


	public Integer getId(){
		return id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}

	public String getCityName(){
		return cityName;
	}
	
	public void setCityName(String cityName){
		this.cityName = cityName;
	}
	
	public String getInitial(){
		return initial;
	}
	
	public void setInitial(String initial){
		this.initial = initial;
	}

	public String getCityCode(){
		return cityCode;
	}
	
	public void setCityCode(String cityCode){
		this.cityCode = cityCode;
	}

	@Override
	public String toString() {
		return "CarOpenCityVO{" +
				"id=" + id +
				", cityName='" + cityName + '\'' +
				", initial='" + initial + '\'' +
				", cityCode='" + cityCode + '\'' +
				'}';
	}
}
