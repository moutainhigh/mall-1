package com.yunxin.cb.sms;

import com.yunxin.core.util.Md5Utils;

public class SendParam {
    private String clientid;

    private String password;

    private String mobile;

    private String smstype;

    private String content;

    private String sendtime;

    private String extend;

    private String uid;

    public SendParam() {
    }

    public SendParam(String mobile, String smstype, String content) {
        this.clientid = SmsConfig.registerNo;
        this.password = Md5Utils.hash(SmsConfig.registerPwd);
        this.mobile = mobile;
        this.smstype = smstype;
        this.content = content;
    }

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSmstype() {
        return smstype;
    }

    public void setSmstype(String smstype) {
        this.smstype = smstype;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
