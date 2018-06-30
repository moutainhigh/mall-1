package com.yunxin.cb.mall.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by gonglei on 16/1/22.
 */
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class FloorCategory implements java.io.Serializable {

    private static final long serialVersionUID = -3113613325145218114L;

    @EmbeddedId
    private FloorCategoryId id;

    private HomeFloor homeFloor;

    private Category category;

    /**
     * 推荐值，值越大，超靠前
     */
    private int sortOrder;

    @EmbeddedId
    @AttributeOverrides({@AttributeOverride(name = "floorId", column = @Column(name = "FLOOR_ID", nullable = false)), @AttributeOverride(name = "categoryId", column = @Column(name = "CATEGORY_ID", nullable = false))})
    public FloorCategoryId getId() {
        return id;
    }

    public void setId(FloorCategoryId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FLOOR_ID", nullable = false, insertable = false, updatable = false)
    public HomeFloor getHomeFloor() {
        return homeFloor;
    }

    public void setHomeFloor(HomeFloor commodity) {
        this.homeFloor = commodity;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", nullable = false, insertable = false, updatable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(nullable = false, scale = 8, precision = 0)
    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int recommendValue) {
        this.sortOrder = recommendValue;
    }
}
