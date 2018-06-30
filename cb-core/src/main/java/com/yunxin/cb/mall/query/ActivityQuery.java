package com.yunxin.cb.mall.query;

import com.yunxin.cb.mall.entity.Activity;
import com.yunxin.cb.mall.entity.meta.ActivityState;
import com.yunxin.core.persistence.PageSpecification;

/**
 * Created by k001389 on 2014/7/28.
 */
public class ActivityQuery extends PageSpecification<Activity> {
    private ActivityState status;

    public ActivityState getStatus() {
        return status;
    }

    public void setStatus(ActivityState status) {
        this.status = status;
    }
}
