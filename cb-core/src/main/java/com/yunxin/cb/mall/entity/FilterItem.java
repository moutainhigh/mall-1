package com.yunxin.cb.mall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by gonglei on 16/1/5.
 */
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class FilterItem implements java.io.Serializable {

    private int itemId;

    private PropertyFilter propertyFilter;

    private String itemName;

    private short sortOrder;

    private boolean checked;

    private Set<CommodityCategory> commodityCategories = new HashSet<>();

    public FilterItem(int itemId) {
        this.itemId = itemId;
    }

    public FilterItem() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Column(nullable = false, length = 255)
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILTER_ID", nullable = true)
    @JsonIgnore
    public PropertyFilter getPropertyFilter() {
        return propertyFilter;
    }

    public void setPropertyFilter(PropertyFilter propertyFilter) {
        this.propertyFilter = propertyFilter;
    }

    @Column(precision = 2)
    public short getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(short sortOrder) {
        this.sortOrder = sortOrder;
    }

    //@ManyToMany注释表示FilterItem是多对多关系的一边，mappedBy属性定义了FilterItem为双向关系的被维护端
    //CommodityCategory表是关系的维护者，owner side，有主导权，它有个外键指向FilterItem表。
    @ManyToMany(mappedBy = "filterItems")
    public Set<CommodityCategory> getCommodityCategories() {
        return commodityCategories;
    }

    public void setCommodityCategories(Set<CommodityCategory> commodityCategories) {
        this.commodityCategories = commodityCategories;
    }

    @Transient
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
