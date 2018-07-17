package com.yunxin.cb.test;

import com.yunxin.cb.sms.SmsHelper;

import java.io.UnsupportedEncodingException;

public class SMSTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        SmsHelper.sendMobileValidCode("localhost", "888888", "13316815481,18665815689,15814072909");
    }
}
