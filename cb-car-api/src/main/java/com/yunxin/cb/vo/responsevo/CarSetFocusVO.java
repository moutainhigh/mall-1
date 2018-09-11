package com.yunxin.cb.vo.responsevo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="配置中心（置换方案）VO",description="配置中心VO对象 CarAgencyVO")
public class CarSetFocusVO implements java.io.Serializable{

	private static final long serialVersionUID = 1l;

	@ApiModelProperty(value="设置名称",name="setName",example="全款购车比例")
	private String setName;
	@ApiModelProperty(value="设置类型",name="setType",example="全款购车")
	private String setType;
	@ApiModelProperty(value="设置值",name="setValue",example="100%")
	private String setValue;

	public String getSetName() {
		return setName;
	}

	public void setSetName(String setName) {
		this.setName = setName;
	}

	public String getSetType() {
		return setType;
	}

	public void setSetType(String setType) {
		this.setType = setType;
	}

	public String getSetValue() {
		return setValue;
	}

	public void setSetValue(String setValue) {
		this.setValue = setValue;
	}
}
