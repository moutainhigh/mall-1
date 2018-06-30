package com.yunxin.cb.sms;

import java.io.UnsupportedEncodingException;

/**
 * Created by Aidy_He on 16/3/7.
 */
public class SmsHelper {

    /****
     * 发送注册验证码
     *
     * @param content 长度6位随机字符串
     * @param phones  手机号码字符数组
     * @throws UnsupportedEncodingException
     */
    public static boolean sendCustomerRegisterSms(String ip, String content, String... phones) throws UnsupportedEncodingException {
        return SmsHandler.sendSms(ip, SmsConfig.registerTemp.replace("#XXXXXX#", content), phones);
    }

    /****
     * 发送注册成功提示短信
     *
     * @param phones 手机号码字符数组
     * @throws UnsupportedEncodingException
     */
    public static boolean sendCustomerRegisterSuccessSms(String ip, String... phones) throws UnsupportedEncodingException {
        return SmsHandler.sendSms(ip, SmsConfig.registerSuccessTemp, phones);
    }

    /****
     * 发送找回密码验证码
     *
     * @param content 长度6位随机字符串
     * @param phones  手机号码字符数组
     * @throws UnsupportedEncodingException
     */
    public static boolean sendCustomerFindPwdSms(String ip, String content, String... phones) throws UnsupportedEncodingException {
        return SmsHandler.sendSms(ip, SmsConfig.findPasswordTemp.replace("#XXXXXX#", content), phones);
    }

    /****
     * 发送找回密码成功提示短信
     *
     * @param phones 手机号码字符数组
     * @throws UnsupportedEncodingException
     */
    public static boolean sendCustomerFindPwdSuccessSms(String ip, String... phones) throws UnsupportedEncodingException {
        return SmsHandler.sendSms(ip, SmsConfig.findPasswordSuccessTemp, phones);
    }


    /****
     * 发送修改手机号码验证码
     *
     * @param content 长度6位随机字符串
     * @param phones  手机号码字符数组
     * @throws UnsupportedEncodingException
     */
    public static boolean sendCustomerUpdatePasswordSms(String ip, String content, String... phones) throws UnsupportedEncodingException {
        return SmsHandler.sendSms(ip, SmsConfig.updatePasswordTemp.replace("#XXXXXX#", content), phones);
    }
}
