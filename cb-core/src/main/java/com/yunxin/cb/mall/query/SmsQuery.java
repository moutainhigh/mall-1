package com.yunxin.cb.mall.query;

import com.yunxin.cb.mall.entity.SmsLog;
import com.yunxin.core.persistence.PageSpecification;

/**
 * Created by z001392 on 2014/8/13.
 */
public class SmsQuery extends PageSpecification<SmsLog> {
    private int smsId;
    private String smsCode;
    private String smsPhone;

    public int getSmsId() {
        return smsId;
    }

    public void setSmsId(int smsId) {
        this.smsId = smsId;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getSmsPhone() {
        return smsPhone;
    }

    public void setSmsPhone(String smsPhone) {
        this.smsPhone = smsPhone;
    }
}
