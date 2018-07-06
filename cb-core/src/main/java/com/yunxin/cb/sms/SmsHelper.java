package com.yunxin.cb.sms;

import java.io.UnsupportedEncodingException;

/**
 * Created by Aidy_He on 16/3/7.
 */
public class SmsHelper {

    /****
     * 发送验证码
     *
     * @param content 长度6位随机字符串
     * @param phones  手机号码字符数组
     * @throws UnsupportedEncodingException
     */
    public static boolean sendMobileValidCode(String ip, String content, String... phones) throws UnsupportedEncodingException {
        return SmsHandler.sendSms(ip, SmsConfig.VALID_CODE_CONTENT.replace("#XXXXXX#", content), phones);
    }

}
