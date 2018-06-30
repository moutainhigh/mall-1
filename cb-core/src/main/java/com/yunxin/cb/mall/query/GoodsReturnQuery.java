package com.yunxin.cb.mall.query;

import com.yunxin.cb.mall.entity.GoodsReturn;
import com.yunxin.cb.mall.entity.meta.GoodsReturnStatus;
import com.yunxin.core.persistence.PageSpecification;

/**
 * Created by k001389 on 2014/7/29.
 */
public class GoodsReturnQuery extends PageSpecification<GoodsReturn> {
    private String contactName;
    private GoodsReturnStatus status;

    public GoodsReturnStatus getStatus() {
        return status;
    }

    public void setStatus(GoodsReturnStatus status) {
        this.status = status;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}
