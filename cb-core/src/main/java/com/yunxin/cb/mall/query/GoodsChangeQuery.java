package com.yunxin.cb.mall.query;

import com.yunxin.cb.mall.entity.GoodsChange;
import com.yunxin.cb.mall.entity.meta.GoodsChangeStatus;
import com.yunxin.core.persistence.PageSpecification;

/**
 * Created by k001389 on 2014/7/29.
 */
public class GoodsChangeQuery extends PageSpecification<GoodsChange> {
    private String contactName;
    private GoodsChangeStatus status;

    public GoodsChangeStatus getStatus() {
        return status;
    }

    public void setStatus(GoodsChangeStatus status) {
        this.status = status;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}
