package com.yunxin.cb.sms;

/**
 * Created by Aidy_He on 16/3/4.
 */
public class SmsConfig {
    protected static final String registerNo = "b00ic4";   //注册号
    protected static final String registerPwd = "yx123456";                 //密码
    protected static final String smsUrl = "https://api.ucpaas.com/sms-partner/access/b00ic4/sendsms";


    // 内容不能随便修改,需要找客服备案,末尾自动加签名
    public static final String VALID_CODE_CONTENT = "【水晶球】您的验证码是：#XXXXXX#，5分钟内有效，请勿向任何人提供您收到的验证码。";


}
