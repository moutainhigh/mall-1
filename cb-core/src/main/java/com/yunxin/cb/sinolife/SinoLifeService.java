package com.yunxin.cb.sinolife;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SinoLifeService {

    private String account;

    private String pwd;

    private boolean login(){
        Map<String, String> headers = new HashMap<>();
        headers.put("Host","www.sino-life.com");
        headers.put("Upgrade-Insecure-Requests","1");
        headers.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headers.put("Content-Type","application/x-www-form-urlencoded");
        headers.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.146 Safari/537.36");
        headers.put("Referer","https://www.sino-life.com/elogin/LOGIN/domain-1/login.html");
        Map<String, String> params = new HashMap<>();
//        //进入登录页
//        String response = HttpsUtils.doPost("https://www.sino-life.com/elogin/LOGIN/domain-1/login.html", new HashMap<>(), headers);
//        //获取验证码
//        HttpsUtils.doGet("");
        params.put("toURL","http://www.sino-life.com/eportal/index.do");
        params.put("username","http://www.sino-life.com/eportal/index.do");
        params.put("password","http://www.sino-life.com/eportal/index.do");
        params.put("code","http://www.sino-life.com/eportal/index.do");
//        //登录
//        response = HttpsUtils.doPost("https://www.sino-life.com/elogin/Login.sso", params, headers);
//        //提交保单

        try {
            Connection.Response res = Jsoup.connect(
                    "https://www.sino-life.com/elogin/Login.sso").headers(headers).data(params)//进行模拟登陆
                    .method(Connection.Method.POST).timeout(10000).execute();//设置请求时间和登陆用的用户名，密码。
            Document doc = res.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return true;
    }
}
