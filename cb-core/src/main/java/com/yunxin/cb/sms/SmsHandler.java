package com.yunxin.cb.sms;

import com.yunxin.cb.config.GlobalConfig;
import com.yunxin.core.util.DateUtils;
import com.yunxin.core.util.LogicUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Vector;

/**
 * Created by Aidy_He on 16/3/4.
 */
@Component
public class SmsHandler {

    private static List<String> list = new Vector<String>();

    /***
     * 短信即时发送
     * @param content 内容
     * @param phones 手机号
     * @return
     * @throws UnsupportedEncodingException
     */
    protected static boolean sendSms(String ip, String content, String... phones) throws UnsupportedEncodingException {
        if (list.contains(ip)) {
            logResult("Repeat Access Ip Do Not Operation:" + ip);
            return false;
        }
        if (list.size() == 10) {
            list.clear();
        }
        logResult("Add Access Ip :" + ip);
        list.add(ip);

        if (LogicUtils.isNullOrEmpty(phones)) {
            throw new NullPointerException("Mobile Phone is empty!");
        }
        if (LogicUtils.isNullOrEmpty(content)) {
            throw new NullPointerException("SMS Content is empty!");
        }
        StringBuffer sbPhones = new StringBuffer();
        for (String phone : phones) {
            sbPhones.append(phone).append(",");
        }

        StringBuffer sbSmsParam = new StringBuffer(512);
        sbSmsParam.append("id=").append(SmsConfig.registerNo);
        sbSmsParam.append("&pwd=").append(SmsConfig.registerPwd);

        String sbp = sbPhones.toString();
        sbp = sbp.substring(0, sbp.lastIndexOf(","));
        logResult("SMS Send To Mobile Phone Number:" + sbp);

        sbSmsParam.append("&to=").append(sbp);
        sbSmsParam.append("&content=").append(URLEncoder.encode(content, "gb2312"));

        String status = HttpSend.postSend(SmsConfig.smsUrl, sbSmsParam.toString());
        if (LogicUtils.isNotNullAndEmpty(status)) {
            logResult("SMS Send ResultInfo:" + status + "!status=" + sendStatus(status));
            return true;
        }

        return false;
    }

    private static String sendStatus(String status) {
        String result = status.substring(0, 3);
        switch (result) {
            case "000": {
                return "短信提交成功";
            }
            case "-01": {
                return "账号余额不足";
            }
            case "-02": {
                return "未开通接口授权";
            }
            case "-03": {
                return "账号密码错误";
            }
            case "-04": {
                return "参数个数不对或者参数类型错误";
            }
            case "-12": {
                return "其他错误";
            }
            case "-110": {
                return "IP被限制";
            }
            default: {
                return "未知错误";
            }
        }
    }

    public static void logResult(String sWord) {
        String filePath = GlobalConfig.getProperty("sms.log_path") + File.separator + DateUtils.getCurrentDate() + "_sms_log.txt";
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter writer = null;
        try {
            writer = new FileWriter(file, true);
            writer.write(sWord + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Scheduled(cron = "0/30 * *  * * ? ")
    public void clear() {
        SmsHandler.list.clear();
    }
}
