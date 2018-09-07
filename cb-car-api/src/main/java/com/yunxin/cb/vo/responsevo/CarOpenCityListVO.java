package com.yunxin.cb.vo.responsevo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: lxc
 * @Date: 2018/9/7 15:02
 * @Description:
 */
@ApiModel(value="开通城市集合VO",description="开通城市List_VO对象(热门城市[cityList]和城市列表[hotCityList]) CarOpenCityListVO")
public class CarOpenCityListVO implements Serializable {

    //城市集合
    @ApiModelProperty(value="城市集合",name="cityList",example="")
    private List<CarOpenCityVO> cityList = new ArrayList<>();

    //热门城市集合
    @ApiModelProperty(value="热门城市集合",name="hotCityList",example="")
    private List<CarOpenCityVO> hotCityList = new ArrayList<>();

    public List<CarOpenCityVO> getCityList() {
        return cityList;
    }

    public void setCityList(List<CarOpenCityVO> cityList) {
        this.cityList = cityList;
    }

    public List<CarOpenCityVO> getHotCityList() {
        return hotCityList;
    }

    public void setHotCityList(List<CarOpenCityVO> hotCityList) {
        this.hotCityList = hotCityList;
    }
}
