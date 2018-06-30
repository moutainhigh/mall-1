package com.yunxin.cb.mall.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by Aidy_He on 16/2/23.
 */
@Embeddable
public class ActivityCommodityId implements java.io.Serializable {

    private int activityId;
    private int commodityId;

    public ActivityCommodityId() {
    }

    public ActivityCommodityId(int activityId, int commodityId) {
        this.commodityId = commodityId;
        this.activityId = activityId;
    }

    @Column(nullable = false)
    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    @Column(nullable = false)
    public int getCommodityId() {
        return this.commodityId;
    }

    public void setCommodityId(int userId) {
        this.commodityId = userId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActivityCommodityId that = (ActivityCommodityId) o;

        if (activityId != that.activityId) return false;
        return commodityId == that.commodityId;

    }

    @Override
    public int hashCode() {
        int result = activityId;
        result = 31 * result + commodityId;
        return result;
    }

}
