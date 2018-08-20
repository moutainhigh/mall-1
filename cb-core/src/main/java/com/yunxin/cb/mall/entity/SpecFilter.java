/**
 *
 */
package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.serializer.JsonTimestampSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 规格查询过滤
 * add by chenpeng 2018年8月20日
 */
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class SpecFilter implements java.io.Serializable {

    private static final long serialVersionUID = -7598615606718276150L;

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12)
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

    /**
     * 修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    private Date updateTime;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = JsonTimestampSerializer.class)
    @Column(insertable = false, updatable = false)
    private Date createTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "specFilter", cascade = CascadeType.ALL)
    @OrderBy(value = "sortOrder")
    private List<SpecFilterItem> filterItems = new ArrayList<>();

    @Transient
    private int[] itemId;

    @Transient
    private String[] itemName;

    @Transient
    private int[] itemSortOrder;

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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<SpecFilterItem> getFilterItems() {
        return filterItems;
    }

    public void setFilterItems(List<SpecFilterItem> filterItems) {
        this.filterItems = filterItems;
    }

    public int[] getItemId() {
        return itemId;
    }

    public void setItemId(int[] itemId) {
        this.itemId = itemId;
    }

    public String[] getItemName() {
        return itemName;
    }

    public void setItemName(String[] itemName) {
        this.itemName = itemName;
    }

    public int[] getItemSortOrder() {
        return itemSortOrder;
    }

    public void setItemSortOrder(int[] itemSortOrder) {
        this.itemSortOrder = itemSortOrder;
    }
}
