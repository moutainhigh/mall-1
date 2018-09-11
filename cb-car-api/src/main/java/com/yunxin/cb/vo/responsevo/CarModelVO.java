package com.yunxin.cb.vo.responsevo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

@ApiModel(value="汽车车型",description="汽车车型VO对象 CarModelVO")
public class CarModelVO implements java.io.Serializable{
	
	private static final long serialVersionUID = 1l;

	@ApiModelProperty(value="车型id",name="id",example="1")
	private Integer id;
	@ApiModelProperty(value="品牌",name="carBrandVO",example="品牌")
	private CarBrandVO carBrandVO;
	@ApiModelProperty(value="车系",name="carSystemVO",example="车系")
	private CarSystemVO carSystemVO;
	@ApiModelProperty(value="分组名称",name="groupName",example="2.0升 涡轮增压 258马力")
	private String groupName;
	@ApiModelProperty(value="车型名称",name="modelName",example="迈腾 2016款 1.8TSI 智享领先型")
	private String modelName;
	@ApiModelProperty(value="排量",name="carDisplacementVO",example="排量")
	private CarDisplacementVO carDisplacementVO;
	@ApiModelProperty(value="参数配置",name="carDisplacementVO",example="参数配置")
	private List<CarBaseDataVO> carBaseDataVOList;
	@ApiModelProperty(value="价格",name="monery",example="200000.00")
	private BigDecimal monery;
	@ApiModelProperty(value="图片路径",name="picPathList",example="www.baidu.com/pic.jpg")
	private List<String> picPathList;
	@ApiModelProperty(value="代理商VO",name="carAgencyVO",example="代理商VO")
	private CarAgencyVO carAgencyVO;
	@ApiModelProperty(value="配置中心（置换方案）VO",name="carSetFocusVO",example="配置中心（置换方案）VO")
	private List<CarSetFocusVO> carSetFocusVO;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CarBrandVO getCarBrandVO() {
		return carBrandVO;
	}

	public void setCarBrandVO(CarBrandVO carBrandVO) {
		this.carBrandVO = carBrandVO;
	}

	public CarSystemVO getCarSystemVO() {
		return carSystemVO;
	}

	public void setCarSystemVO(CarSystemVO carSystemVO) {
		this.carSystemVO = carSystemVO;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public CarDisplacementVO getCarDisplacementVO() {
		return carDisplacementVO;
	}

	public void setCarDisplacementVO(CarDisplacementVO carDisplacementVO) {
		this.carDisplacementVO = carDisplacementVO;
	}

	public List<CarBaseDataVO> getCarBaseDataVOList() {
		return carBaseDataVOList;
	}

	public void setCarBaseDataVOList(List<CarBaseDataVO> carBaseDataVOList) {
		this.carBaseDataVOList = carBaseDataVOList;
	}

	public BigDecimal getMonery() {
		return monery;
	}

	public void setMonery(BigDecimal monery) {
		this.monery = monery;
	}

	public List<String> getPicPathList() {
		return picPathList;
	}

	public void setPicPathList(List<String> picPathList) {
		this.picPathList = picPathList;
	}

	public CarAgencyVO getCarAgencyVO() {
		return carAgencyVO;
	}

	public void setCarAgencyVO(CarAgencyVO carAgencyVO) {
		this.carAgencyVO = carAgencyVO;
	}

	public List<CarSetFocusVO> getCarSetFocusVO() {
		return carSetFocusVO;
	}

	public void setCarSetFocusVO(List<CarSetFocusVO> carSetFocusVO) {
		this.carSetFocusVO = carSetFocusVO;
	}
}
