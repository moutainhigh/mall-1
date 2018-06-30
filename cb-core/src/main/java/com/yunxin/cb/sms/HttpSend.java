package com.yunxin.cb.sms;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Aidy_He on 16/3/4.
 */
public class HttpSend {

    public static String getSend(String strUrl, String param) {
        URL url = null;
        HttpURLConnection connection = null;
        SmsHandler.logResult("SMS Connection Start ...");
        try {
            url = new URL(strUrl + "?" + param);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            reader.close();
            String result = buffer.toString();
            SmsHandler.logResult("SMS Connection Success！Read Result:" + result);
            return result;
        } catch (IOException e) {
            SmsHandler.logResult("SMS Connection Exception！" + e.getMessage());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
                SmsHandler.logResult("SMS Connection End ...");
            }
        }
    }

    public static String postSend(String strUrl, String param) {
        URL url = null;
        HttpURLConnection connection = null;
        SmsHandler.logResult("SMS Connection Start ...");
        try {
            url = new URL(strUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.connect();

            //POST方法时使用
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(param);
            out.flush();
            out.close();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                reader.close();
                String result = buffer.toString();
                SmsHandler.logResult("SMS Connection Success！Read Result:" + result);
                return result;
            } else {
                SmsHandler.logResult("SMS Connection Failure！");
                return null;
            }
        } catch (IOException e) {
            SmsHandler.logResult("SMS Connection Exception！" + e.getMessage());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            SmsHandler.logResult("SMS Connection End ...");
        }
    }

    /**
     * 转为16进制方法
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String paraTo16(String str) throws UnsupportedEncodingException {
        String hs = "";

        byte[] byStr = str.getBytes("UTF-8");
        for (int i = 0; i < byStr.length; i++) {
            String temp = "";
            temp = (Integer.toHexString(byStr[i] & 0xFF));
            if (temp.length() == 1) temp = "%0" + temp;
            else temp = "%" + temp;
            hs = hs + temp;
        }
        return hs.toUpperCase();
    }
}
