/**
 *
 */
package com.yunxin.cb.mall.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 规格查询过滤
 * add by chenpeng 2018年8月20日
 */
public class SpecFilter implements java.io.Serializable {

    private static final long serialVersionUID = -8755916502668265494L;
    /**
     * ID
     */
    private Integer filterId;

    /**
     * 过滤器规格名称
     */
    private String filterName;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 状态：true 启用；false 停用
     */
    private Boolean enabled = true;

    private List<SpecFilterItem> filterItems = new ArrayList<>();

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

    public List<SpecFilterItem> getFilterItems() {
        return filterItems;
    }

    public void setFilterItems(List<SpecFilterItem> filterItems) {
        this.filterItems = filterItems;
    }
}
