package com.yunxin.cb.jwt;



/**
 * Created by gonglei on 17/9/15.
 */
public class Token {

    private int accountId;

    private String mobile;


    /**
     *  中间表id
     */
    private int stagingId;



    public Token() {

    }

    public Token(int accountId, String mobile, int stagingId) {
        this.accountId = accountId;
        this.mobile = mobile;
        this.stagingId = stagingId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getStagingId() {
        return stagingId;
    }

    public void setStagingId(int stagingId) {
        this.stagingId = stagingId;
    }
}
