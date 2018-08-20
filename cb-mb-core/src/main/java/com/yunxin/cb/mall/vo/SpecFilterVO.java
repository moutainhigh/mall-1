/**
 *
 */
package com.yunxin.cb.mall.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 规格查询过滤
 * add by chenpeng 2018年8月20日
 */
@ApiModel(value = "规格查询过滤", description = "规格查询过滤VO对象 SpecFilterVO")
public class SpecFilterVO implements java.io.Serializable {

    private static final long serialVersionUID = -4269185329310256710L;

    @ApiModelProperty(value="规格查询过滤ID",name="filterId",example="1")
    private Integer filterId;

    @ApiModelProperty(value="过滤器规格名称",name="filterName",example="颜色")
    private String filterName;

    @ApiModelProperty(value="排序",name="sortOrder",example="1")
    private Integer sortOrder;

    @ApiModelProperty(value="状态",name="enabled",example="true")
    private Boolean enabled;

    @ApiModelProperty(value="规格值列表",name="filterItems")
    private List<SpecFilterItemVO> filterItems = new ArrayList<>();

    public Integer getFilterId() {
        return filterId;
    }

    public void setFilterId(Integer filterId) {
        this.filterId = filterId;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<SpecFilterItemVO> getFilterItems() {
        return filterItems;
    }

    public void setFilterItems(List<SpecFilterItemVO> filterItems) {
        this.filterItems = filterItems;
    }
}
