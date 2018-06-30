package com.yunxin.cb.mall.query;

import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.core.persistence.PageSpecification;

public class ActivityComListQuery extends PageSpecification<Commodity> {
    private int activityId;
    private int goodsNumber;

    public int getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }


}
