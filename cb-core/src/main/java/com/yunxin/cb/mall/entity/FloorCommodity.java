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
public class FloorCommodity implements java.io.Serializable {

    private static final long serialVersionUID = -3113613325145218114L;

    @EmbeddedId
    private FloorCommodityId id;

    private Commodity commodity;

    private HomeFloor homeFloor;

    /**
     * 推荐值，值越大，超靠前
     */
    private int sortOrder;

    @EmbeddedId
    @AttributeOverrides({@AttributeOverride(name = "commodityId", column = @Column(name = "COMMODITY_ID", nullable = false)), @AttributeOverride(name = "floorId", column = @Column(name = "FLOOR_ID", nullable = false))})
    public FloorCommodityId getId() {
        return id;
    }

    public void setId(FloorCommodityId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMODITY_ID", nullable = false, insertable = false, updatable = false)
    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
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
