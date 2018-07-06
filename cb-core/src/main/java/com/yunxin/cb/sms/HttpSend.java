package com.yunxin.cb.sms;

import org.apache.http.util.TextUtils;

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

    //发送JSON字符串 如果成功则返回成功标识。
    public static String doJsonPost(String urlPath, String Json) {
        String result = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            // 设置文件类型:
            conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            // 设置接收类型否则返回415错误
            //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
            conn.setRequestProperty("accept","application/json");
            // 往服务器里面发送数据
            if (Json != null && !TextUtils.isEmpty(Json)) {
                byte[] writebytes = Json.getBytes();
                // 设置文件长度
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(Json.getBytes());
                outwritestream.flush();
                outwritestream.close();
                SmsHandler.logResult( "doJsonPost: conn"+conn.getResponseCode());
            }
            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                result = reader.readLine();
                SmsHandler.logResult("SMS Connection Success！Read Result:" + result);
            }
        } catch (Exception e) {
            SmsHandler.logResult("SMS Connection Exception！" + e.getMessage());
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
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
