package com.yunxin.cb.jwt;


/**
 * Created by gonglei on 17/9/15.
 */
public class Token {

    private int accountId;

    private String mobile;

    public Token() {

    }

    public Token(int accountId, String mobile) {
        this.accountId = accountId;
        this.mobile = mobile;
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
}
