package com.yunxin.cb.vo.requestvo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/** 
 * 试驾信息Vo
 */
public class CarTestDriveReqVO implements Serializable {
	
	private static final long serialVersionUID = 1l;
	
	@ApiModelProperty(value="试驾人",name="driveName",example="张三")
	private String driveName;
	@ApiModelProperty(value="手机号",name="mobile",example="13711112222")
	private String mobile;
	@ApiModelProperty(value="地区ID",name="cityId",example="22")
	private String cityId;
	@ApiModelProperty(value="城市名称",name="brandName",example="深圳")
	private String cityName;
	@ApiModelProperty(value="车系ID",name="sysId",example="1")
	private Integer sysId;
	@ApiModelProperty(value="门店ID",name="agentId",example="2")
	private Integer agentId;
	@ApiModelProperty(value="图片路径",name="picPath",example="xxxx.png")
	private String picPath;

	public String getDriveName() {
		return driveName;
	}

	public void setDriveName(String driveName) {
		this.driveName = driveName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getSysId() {
		return sysId;
	}

	public void setSysId(Integer sysId) {
		this.sysId = sysId;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
}
