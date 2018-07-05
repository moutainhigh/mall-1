package com.yunxin.cb.vo;


import java.io.Serializable;

public class VerificationCode implements Serializable{


    private String mobile;


    private String code;


    private long sendTime;


    public VerificationCode(String mobile, String code, long sendTime){
        this.mobile = mobile;
        this.code = code;
        this.sendTime = sendTime;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

}
