package com.yunxin.cb.sinolife;

import com.yunxin.cb.insurance.entity.InsuranceOrder;
import com.yunxin.cb.ruokuai.RuoKuai;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SinoLifeService {

    private static Logger logger = LoggerFactory.getLogger(SinoLifeService.class);

    private String account = "AA00000000236002@sinolifeagent.com";

    private String pwd = "FUde236002";

    /**
     * 提交订单
     * @param insuranceOrder
     */
    public void submitOrder(InsuranceOrder insuranceOrder) throws Exception{
        //1.进入登录页，获取COOKIE
        Map<String, String> cookies = getCookie();
        cookies = getAjax();
        //2.获取验证码
        byte[] data = JsoupDoloadPicture.downloadImg("https://www.sino-life.com/elogin/getVerifyCode.do", cookies);
        String code = RuoKuai.createByData("107803","6cd4293bae4c4522ad83b5b56d19ad00","3040","tanggangyi","tgy123456",data);
        //3.登录
        cookies = login(account, pwd, code, cookies);
        //4.提交订单
    }
    /**
     * 模拟登录获取cookie和sessionid
     *
     */
    public Map<String, String> login(String account, String pwd, String code, Map<String, String> cookies) throws Exception {
        String urlLogin = "https://www.sino-life.com/elogin/Login.sso";
        Connection connect = Jsoup.connect(urlLogin);
        connect.timeout(5 * 100000);
        // 伪造请求头
        connect.header("Content-Type", "application/x-www-form-urlencoded");
        connect.header("Host", "www.sino-life.com").header("Referer",
                "https://www.sino-life.com/elogin/LOGIN/domain-1/login.html");
        connect.header("User-Agent",
                "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
        // 携带登陆信息
        connect.data("toURL", "https://www.sino-life.com/eportal/index.do")
                .data("username", account)
                .data("password", pwd)
                .data("code", code);
        connect.cookies(cookies);
        // 请求url获取响应信息
        Connection.Response res = connect.ignoreContentType(true).method(Connection.Method.POST).execute();// 执行请求
        // 获取返回的cookie
        cookies = res.cookies();
        logger.info("cookie info:");
        for (Map.Entry<String, String> entry : cookies.entrySet()) {
            logger.info(entry.getKey() + "-" + entry.getValue());
        }
        System.out.println(res.body());
        return cookies;
    }

    /**
     * 获取登录信息
     * 主要就是访问一下主页面，获取一个cookie
     */
    public Map<String, String> getCookie() throws Exception {
        String url = "https://www.sino-life.com/elogin/LOGIN/domain-1/login.html";
        Connection connect = Jsoup.connect(url);
        // 伪造请求头
        connect.header("Accept", "application/json, text/javascript, */*; q=0.01").header("Accept-Encoding",
                "gzip, deflate");
        connect.header("Accept-Language", "zh-CN,zh;q=0.9").header("Connection", "keep-alive");
        connect.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        connect.header("Host", "www.sino-life.com").header("Referer", "http://www.sino-life.com");
        connect.header("User-Agent",
                "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
                .header("X-Requested-With", "XMLHttpRequest");

        // 请求url获取响应信息
        Connection.Response res = connect.ignoreContentType(true).method(Connection.Method.POST).execute();// 执行请求
        // 获取返回的cookie
        Map<String, String> cookies = res.cookies();
        logger.info("cookie info:");
        for (Map.Entry<String, String> entry : cookies.entrySet()) {
            logger.info(entry.getKey() + "-" + entry.getValue());
        }
        return cookies;
    }
    /**
     * 获取登录信息
     * 主要就是访问一下主页面，获取一个cookie
     */
    public Map<String, String> getAjax() throws Exception {
        String url = "https://www.sino-life.com/elogin/Ajax.sso?format=json&callback=jQuery17208830859364525903_1531230048758&SF_OP=LogoutSsoSession&_=1531230048781";
        Connection connect = Jsoup.connect(url);
        // 伪造请求头
        connect.header("Accept", "application/json, text/javascript, */*; q=0.01").header("Accept-Encoding",
                "gzip, deflate");
        connect.header("Accept-Language", "zh-CN,zh;q=0.9").header("Connection", "keep-alive");
        connect.header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        connect.header("Host", "www.sino-life.com").header("Referer", "http://www.sino-life.com");
        connect.header("User-Agent",
                "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
                .header("X-Requested-With", "XMLHttpRequest");

        // 请求url获取响应信息
        Connection.Response res = connect.ignoreContentType(true).method(Connection.Method.POST).execute();// 执行请求
        // 获取返回的cookie
        Map<String, String> cookies = res.cookies();
        logger.info("cookie info:");
        for (Map.Entry<String, String> entry : cookies.entrySet()) {
            logger.info(entry.getKey() + "-" + entry.getValue());
        }
        System.out.println(res.body());
        return cookies;
    }



}
