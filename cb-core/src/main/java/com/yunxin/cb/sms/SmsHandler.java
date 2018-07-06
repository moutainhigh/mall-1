package com.yunxin.cb.sms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunxin.cb.config.GlobalConfig;
import com.yunxin.core.util.DateUtils;
import com.yunxin.core.util.LogicUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Vector;

/**
 * Created by Aidy_He on 16/3/4.
 */
public class SmsHandler {

    private final static Logger logger = LoggerFactory.getLogger(SmsHelper.class);

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

        SendParam sendParam = new SendParam(sbPhones.toString(), "4", content);
        ObjectMapper mapper = new ObjectMapper();
        String body = "";
        try {
            body = mapper.writeValueAsString(sendParam);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        logger.info(SmsConfig.smsUrl);
        logger.info(body);
        String status = HttpSend.doJsonPost(SmsConfig.smsUrl, body);
        logger.info(status);
        if (LogicUtils.isNotNullAndEmpty(status) && status.contains("\"code\":0")) {
            logResult("SMS Send ResultInfo:" + status + "!status=" + status);
            return true;
        }

        return false;
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
