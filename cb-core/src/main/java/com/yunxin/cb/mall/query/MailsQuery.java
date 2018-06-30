package com.yunxin.cb.mall.query;

import com.yunxin.cb.mall.entity.Mails;
import com.yunxin.core.persistence.PageSpecification;

public class MailsQuery extends PageSpecification<Mails> {
    private int customerId;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

}
