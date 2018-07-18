package com.yunxin.cb.mall.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class FloorBrand implements java.io.Serializable{
    private static final long serialVersionUID = -3113613325145218114L;

    @EmbeddedId
    private FloorBrandId id;

    private Brand brand;

    private HomeFloor homeFloor;

    /**
     * 推荐值，值越大，超靠前
     */
    private int sortOrder;

    @EmbeddedId
    @AttributeOverrides({@AttributeOverride(name = "brandId", column = @Column(name = "BRAND_ID", nullable = false)), @AttributeOverride(name = "floorId", column = @Column(name = "FLOOR_ID", nullable = false))})
    public FloorBrandId getId() {
        return id;
    }

    public void setId(FloorBrandId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRAND_ID", nullable = false, insertable = false, updatable = false)
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FLOOR_ID", nullable = false, insertable = false, updatable = false)
    public HomeFloor getHomeFloor() {
        return homeFloor;
    }

    public void setHomeFloor(HomeFloor category) {
        this.homeFloor = category;
    }

    @Column(nullable = false, scale = 8, precision = 0)
    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int recommendValue) {
        this.sortOrder = recommendValue;
    }
}
