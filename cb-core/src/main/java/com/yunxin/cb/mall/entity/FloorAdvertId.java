package com.yunxin.cb.mall.entity;

import javax.persistence.Column;

public class FloorAdvertId implements java.io.Serializable{

    private int floorId;
    private int advertId;
    public FloorAdvertId() {

    }
    public FloorAdvertId(int floorId, int advertId) {
        this.floorId = floorId;
        this.advertId = advertId;
    }

    @Column(nullable = false)
    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }
    @Column(nullable = false)
    public int getAdvertId() {
        return advertId;
    }

    public void setAdvertId(int advertId) {
        this.advertId = advertId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FloorAdvertId that = (FloorAdvertId) o;
        return floorId == that.floorId &&
                advertId == that.advertId;
    }

    @Override
    public int hashCode() {
        int result = floorId;
        result = 31 * result + advertId;
        return result;
    }
}
