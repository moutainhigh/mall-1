package com.yunxin.cb.sms;

import com.alibaba.fastjson.JSON;
import com.yunxin.cb.sms.request.SmsSendRequest;
import com.yunxin.cb.sms.response.SmsSendResponse;
import com.yunxin.cb.sms.util.ChuangLanSmsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * Created by tanggangyi
 */
public class SmsHelper {

    private static Logger logger = LoggerFactory.getLogger(SmsHelper.class);

    /****
     * 发送验证码
     *
     * @param content 长度6位随机字符串
     * @param phones  手机号码字符数组
     * @throws UnsupportedEncodingException
     */
    public static boolean sendMobileValidCode(String ip, String content, String... phones) {
        StringBuffer sbPhones = new StringBuffer();
        for (String phone : phones) {
            sbPhones.append(phone).append(",");
        }
        SmsSendRequest smsSingleRequest = new SmsSendRequest(SmsConfig.account, SmsConfig.pswd, SmsConfig.VALID_CODE_CONTENT.replace("#XXXXXX#", content), sbPhones.toString(),"true");
        String requestJson = JSON.toJSONString(smsSingleRequest);
        logger.info("before request string is: " + requestJson);
        String response = ChuangLanSmsUtil.sendSmsByPost(SmsConfig.smsUrl, requestJson);
        logger.info("response after request result is :" + response);
        SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
        return smsSingleResponse.getCode().equals("0");
    }

}
