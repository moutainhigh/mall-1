package com.yunxin.cb.mall.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by gonglei on 16/1/22.
 */
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class CommodityCategory implements java.io.Serializable {

    private static final long serialVersionUID = -3113613325145218114L;

    private int cocaId;

    private Commodity commodity;

    private Category category;

    /**
     * 推荐值，值越大，超靠前
     */
    private int recommendValue;

    private Set<FilterItem> filterItems = new HashSet<>();

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false, precision = 12, scale = 0)
    public int getCocaId() {
        return cocaId;
    }

    public void setCocaId(int cocaId) {
        this.cocaId = cocaId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMODITY_ID", nullable = false, insertable = true, updatable = true)
    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", nullable = false, insertable = true, updatable = true)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(nullable = false, scale = 8, precision = 0)
    public int getRecommendValue() {
        return recommendValue;
    }

    public void setRecommendValue(int recommendValue) {
        this.recommendValue = recommendValue;
    }

    //关系维护者
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "commodity_category_filter", joinColumns = {@JoinColumn(name = "COCA_ID", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "ITEM_ID", nullable = false, updatable = false)})
    public Set<FilterItem> getFilterItems() {
        return filterItems;
    }

    public void setFilterItems(Set<FilterItem> filterItems) {
        this.filterItems = filterItems;
    }
}
