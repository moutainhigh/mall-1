package com.yunxin.cb.mall.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class FloorAdvert implements Serializable {

    @EmbeddedId
    private FloorAdvertId id;

    private Advertisement advertisement;

    private HomeFloor homeFloor;
    /**
     * 推荐值，值越大，超靠前
     */
    private int sortOrder;
    @EmbeddedId
    @AttributeOverrides({@AttributeOverride(name = "advertId", column = @Column(name = "ADVERT_ID", nullable = false)), @AttributeOverride(name = "floorId", column = @Column(name = "FLOOR_ID", nullable = false))})
    public FloorAdvertId getId() {
        return id;
    }

    public void setId(FloorAdvertId id) {
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADVERT_ID", nullable = false, insertable = false, updatable = false)
    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FLOOR_ID", nullable = false, insertable = false, updatable = false)
    public HomeFloor getHomeFloor() {
        return homeFloor;
    }

    public void setHomeFloor(HomeFloor homeFloor) {
        this.homeFloor = homeFloor;
    }
    @Column(nullable = false, scale = 8, precision = 0)
    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }
}
