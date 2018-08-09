package com.yunxin.cb.sms;

/**
 * Created by Aidy_He on 16/3/4.
 */
public class SmsConfig {

    // 用户平台API账号(非登录账号,示例:N1234567)
    public static String account = "N4467000";
    // 用户平台API密码(非登录密码)
    public static String pswd = "Im1dsAzWoU4217";
    //请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
    protected static final String smsUrl = "http://smssh1.253.com/msg/send/json";
    // 短信内容
    public static final String VALID_CODE_CONTENT = "【云信】您的验证码是：#XXXXXX#，5分钟内有效，请勿向任何人提供您收到的验证码。";


}
