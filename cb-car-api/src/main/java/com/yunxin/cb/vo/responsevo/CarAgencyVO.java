package com.yunxin.cb.vo.responsevo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="代理商",description="代理商VO对象 CarAgencyVO")
public class CarAgencyVO implements java.io.Serializable{
	
	private static final long serialVersionUID = 1l;

	@ApiModelProperty(value="代理商Id",name="id",example="1")
	private Integer id;
	@ApiModelProperty(value="代理商名称",name="agencyName",example="深圳宝泰行宝马店")
	private String agencyName;
	@ApiModelProperty(value="详细地址",name="detailAddress",example="深圳市福田区生命保险大厦")
	private String detailAddress;
	@ApiModelProperty(value="纬度",name="positionX",example="5.55")
	private Double positionX;
	@ApiModelProperty(value="经度",name="positionY",example="3.22")
	private Double positionY;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public Double getPositionX() {
		return positionX;
	}

	public void setPositionX(Double positionX) {
		this.positionX = positionX;
	}

	public Double getPositionY() {
		return positionY;
	}

	public void setPositionY(Double positionY) {
		this.positionY = positionY;
	}
}
