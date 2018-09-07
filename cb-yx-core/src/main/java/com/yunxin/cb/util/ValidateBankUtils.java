package com.yunxin.cb.util;

import com.yunxin.cb.mall.vo.BankInfoVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @title: 银行卡四要素验证工具类
 * @auther: eleven
 * @date: 2018/8/9 11:16
 */
@Component
public class ValidateBankUtils {

    private static final Log log = LogFactory.getLog(ValidateBankUtils.class);

    private static String host;
    private static String path;
    private static String method;
    private static String appcode;

    public static String validateBank(BankInfoVO bankInfoVO){
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("ReturnBankInfo", "YES");
        bodys.put("cardNo", bankInfoVO.getBankCardNumber());
        bodys.put("idNo", bankInfoVO.getCustomerCardNo());
        bodys.put("name", bankInfoVO.getCardholder());
        bodys.put("phoneNo", bankInfoVO.getMobile());
        String result="";
        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            log.info("response is "+response.toString());
            //获取response的body
            result = EntityUtils.toString(response.getEntity());
            log.info("response body is "+result);
        } catch (Exception e) {
            log.error("error is "+e);
        }
        return result;
    }

    public String getHost() {
        return host;
    }

    @Value("${validateBank.host}")
    public void setHost(String host) {
        ValidateBankUtils.host = host;
    }

    public String getPath() {
        return path;
    }

    @Value("${validateBank.path}")
    public void setPath(String path) {
        ValidateBankUtils.path = path;
    }

    public String getMethod() {
        return method;
    }

    @Value("${validateBank.method}")
    public void setMethod(String method) {
        ValidateBankUtils.method = method;
    }

    public String getAppcode() {
        return appcode;
    }

    @Value("${validateBank.appcode}")
    public void setAppcode(String appcode) {
        ValidateBankUtils.appcode = appcode;
    }
}
