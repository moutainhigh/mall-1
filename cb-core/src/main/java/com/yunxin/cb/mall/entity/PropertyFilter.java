/**
 *
 */
package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunxin.core.web.json.serializer.JsonDateSerializer;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author sheh
 * 查询过滤器
 */

@JsonAutoDetect
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class PropertyFilter implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8619230855481911680L;

    /**
     * ID
     */

    private int filterId;

    /**
     * 过滤器名称
     */

    private String filterName;

    /**
     * 排序
     */

    private int sortOrder;

    /**
     * 创建时间
     */

    private Date createTime;

    /**
     * 状态：true 启用；false 停用
     */
    private boolean enabled = true;

    /**
     * 商品种类
     */

    private Category category;

    private List<FilterItem> filterItems = new ArrayList<>();

    private int[] itemId;

    private String[] itemName;

    private int[] itemSortOrder;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getFilterId() {
        return filterId;
    }

    public void setFilterId(int segId) {
        this.filterId = segId;
    }

    @Column(nullable = false, length = 10)
    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String minPrice) {
        this.filterName = minPrice;
    }

    @Column(nullable = false, precision = 4)
    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int maxPrice) {
        this.sortOrder = maxPrice;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(length = 7, nullable = false)
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(nullable = false, length = 1)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enable) {
        this.enabled = enable;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category catalog) {
        this.category = catalog;
    }


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "propertyFilter", cascade = CascadeType.ALL)
    @OrderBy(value = "sortOrder")
    public List<FilterItem> getFilterItems() {
        return filterItems;
    }

    public void setFilterItems(List<FilterItem> filterItems) {
        this.filterItems = filterItems;
    }

    @Transient
    public int[] getItemId() {
        return itemId;
    }

    public void setItemId(int[] itemId) {
        this.itemId = itemId;
    }

    @Transient
    public String[] getItemName() {
        return itemName;
    }

    public void setItemName(String[] itemName) {
        this.itemName = itemName;
    }

    @Transient
    public int[] getItemSortOrder() {
        return itemSortOrder;
    }

    public void setItemSortOrder(int[] itemSortOrder) {
        this.itemSortOrder = itemSortOrder;
    }
}
