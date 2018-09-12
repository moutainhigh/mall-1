package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.meta.FloorType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: hfy
 * @Date: 2018/9/11 17:15
 * @Description: 楼层VO
 */
@ApiModel(value = "楼层VO", description = "楼层VO FloorVO")
public class FloorVO<T> implements Serializable {

    private static final long serialVersionUID = 6896226262663362379L;
    @ApiModelProperty(value = "楼层类型", name = "floorType", example = "楼层类型")
    private FloorType floorType;

    @ApiModelProperty(value = "楼层元素集合", name = "list", example = "楼层元素集合")
    private List<T> list;

    public FloorType getFloorType() {
        return floorType;
    }

    public void setFloorType(FloorType floorType) {
        this.floorType = floorType;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
