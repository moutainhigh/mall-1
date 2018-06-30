package com.yunxin.cb.sms;

/**
 * Created by Aidy_He on 16/3/4.
 */
public class SmsConfig {
    protected static final String registerNo = "bersn";   //注册号
    protected static final String registerPwd = "bosheng123456";                 //密码
    protected static final String smsUrl = "http://service.winic.org:8009/sys_port/gateway/index.asp";


    // 内容不能随便修改,需要找客服备案,末尾自动加签名[易厨网]
    public static final String registerTemp = "您正在进行的是注册会员操作，如非本人操作请联系网站客服，否则请忽略。当前验证码为：#XXXXXX#，90秒内有效。";
    public static final String registerSuccessTemp = "恭喜您注册成为本站会员，欢迎关注本站智能厨具产品，为了获得更好的体验，请使用本站专售的平板电脑，谢谢。";
    public static final String findPasswordTemp = "您正在进行的是找回密码操作，如非本人操作请联系网站客服，否则请忽略。当前验证码为：#XXXXXX#，90秒内有效。";
    public static final String findPasswordSuccessTemp = "找回密码成功，请妥善保存您的密码。欢迎继续关注本站智能厨具产品，为了获得更好的体验，请使用本站专售的平板电脑，谢谢。";
    public static final String updatePasswordTemp = "您正在进行的是修改手机号码操作，如非本人操作请联系网站客服，否则请忽略。当前验证码为：#XXXXXX#，90秒内有效。";
    public static final String promotionTemp = "打造开心厨房美好生活。X月X日，XXXXXX活动隆重上线，详情请访问http://13906.com/hd。如需退订请发送信息QXTS到135xxxxxxx，否则请忽略。";


}
