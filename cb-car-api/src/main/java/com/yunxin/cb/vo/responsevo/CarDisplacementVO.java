package com.yunxin.cb.vo.responsevo;

import com.yunxin.cb.mall.common.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value="排量",description="排量VO对象 CarDisplacementVO")
public class CarDisplacementVO implements java.io.Serializable{
	
	private static final long serialVersionUID = 1l;

	@ApiModelProperty(value="车系Id",name="sysId",example="1")
	private Integer sysId;
	@ApiModelProperty(value="车系",name="carSystemVO",example="车系")
	private CarSystemVO carSystemVO;
	@ApiModelProperty(value="排量",name="disName",example="2.0T 涡轮增压 185马力")
	private String disName;

	public Integer getSysId() {
		return sysId;
	}

	public void setSysId(Integer sysId) {
		this.sysId = sysId;
	}

	public CarSystemVO getCarSystemVO() {
		return carSystemVO;
	}

	public void setCarSystemVO(CarSystemVO carSystemVO) {
		this.carSystemVO = carSystemVO;
	}

	public String getDisName() {
		return disName;
	}

	public void setDisName(String disName) {
		this.disName = disName;
	}
}
