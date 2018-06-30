package com.yunxin.cb.mall.vo;

import java.util.Date;

/**
 * Created by w001499 on 2014/8/13.
 */
public class SmsLogVo {
    private int smsId;
    private String smsCode;
    private String smsPhone;
    private String smsContent;
    private Date smsTime;
    private int smsResult;
    private String smsRemark;

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

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public Date getSmsTime() {
        return smsTime;
    }

    public void setSmsTime(Date smsTime) {
        this.smsTime = smsTime;
    }

    public int getSmsResult() {
        return smsResult;
    }

    public void setSmsResult(int smsResult) {
        this.smsResult = smsResult;
    }

    public String getSmsRemark() {
        return smsRemark;
    }

    public void setSmsRemark(String smsRemark) {
        this.smsRemark = smsRemark;
    }
}
