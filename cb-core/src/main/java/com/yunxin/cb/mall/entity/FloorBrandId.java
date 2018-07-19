package com.yunxin.cb.mall.entity;

import javax.persistence.Column;

public class FloorBrandId implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private int floorId;
    private int brandId;

    public FloorBrandId() {
    }

    public FloorBrandId(int floorId, int brandId) {
        this.floorId = floorId;
        this.brandId = brandId;
    }

    @Column(nullable = false)
    public int getFloorId() {
        return this.floorId;
    }

    public void setFloorId(int userId) {
        this.floorId = userId;
    }

    @Column(nullable = false)
    public int getBrandId() {
        return this.brandId;
    }

    public void setBrandId(int musicId) {
        this.brandId = musicId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FloorBrandId that = (FloorBrandId) o;

        if (floorId != that.floorId) return false;
        return brandId == that.brandId;

    }

    @Override
    public int hashCode() {
        int result = floorId;
        result = 31 * result + brandId;
        return result;
    }

}
