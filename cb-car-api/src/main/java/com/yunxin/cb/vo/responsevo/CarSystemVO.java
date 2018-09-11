package com.yunxin.cb.vo.responsevo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;

@ApiModel(value="车系",description="车系VO对象 CarSystemVO")
public class CarSystemVO implements java.io.Serializable{
	
	private static final long serialVersionUID = 1l;

	@ApiModelProperty(value="车系id",name="id",example="1")
	private Integer id;
	@ApiModelProperty(value="品牌",name="carBrandVO",example="宝马")
	private CarBrandVO carBrandVO;
	@ApiModelProperty(value="车系名称",name="sysName",example="宝马（进口）7系")
	private String sysName;
	@ApiModelProperty(value="品类",name="category",example="SUV")
	private String category;
	@ApiModelProperty(value="最小价格",name="mixMonery",example="50000.00")
	private BigDecimal mixMonery;
	@ApiModelProperty(value="最大价格",name="maxMonery",example="100000.00")
	private BigDecimal maxMonery;
	@ApiModelProperty(value="介绍",name="introduce",example="介绍")
	private String introduce;
	@ApiModelProperty(value="图片路径",name="picPath",example="www.baidu.com/pic.jpg")
	private String picPath;
	@ApiModelProperty(value="说明",name="explainInfo",example="说明")
	private String explainInfo;
    @ApiModelProperty(value="默认车型",name="carModelVO",example="默认车型")
	private CarModelVO carModelVO;

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

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getMixMonery() {
		return mixMonery;
	}

	public void setMixMonery(BigDecimal mixMonery) {
		this.mixMonery = mixMonery;
	}

	public BigDecimal getMaxMonery() {
		return maxMonery;
	}

	public void setMaxMonery(BigDecimal maxMonery) {
		this.maxMonery = maxMonery;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getExplainInfo() {
		return explainInfo;
	}

	public void setExplainInfo(String explainInfo) {
		this.explainInfo = explainInfo;
	}

    public CarModelVO getCarModelVO() {
        return carModelVO;
    }

    public void setCarModelVO(CarModelVO carModelVO) {
        this.carModelVO = carModelVO;
    }
}
