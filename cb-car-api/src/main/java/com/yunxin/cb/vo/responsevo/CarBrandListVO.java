package com.yunxin.cb.vo.responsevo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value="品牌列表集合",description="品牌列表集合VO对象 CarBrandVO")
public class CarBrandListVO implements java.io.Serializable{
    private static final long serialVersionUID = 3473099803823395986L;
    @ApiModelProperty(value="热门品牌列表",name="hotBrandList",example="热门品牌列表")
    private List<CarBrandVO> hotBrandList;
    @ApiModelProperty(value="所有品牌列表",name="allBrandList",example="所有品牌列表")
    private List<CarBrandVO> allBrandList;

    public List<CarBrandVO> getHotBrandList() {
        return hotBrandList;
    }

    public void setHotBrandList(List<CarBrandVO> hotBrandList) {
        this.hotBrandList = hotBrandList;
    }

    public List<CarBrandVO> getAllBrandList() {
        return allBrandList;
    }

    public void setAllBrandList(List<CarBrandVO> allBrandList) {
        this.allBrandList = allBrandList;
    }
}
