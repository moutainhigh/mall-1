package com.yunxin.test.rb;

import com.yunxin.cb.Application;
import com.yunxin.cb.meta.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@AutoConfigureMockMvc
@Transactional(transactionManager = "transactionManager")
@Rollback(value = true)
public class PayResourceTest extends MockHttpUtils {

    @Test
    public void appPayTest() throws Exception {
        log.info("app支付 V1 start");
        //mock进行模拟
        String url = "/v1/mall/appPay";
        String content = "";
        String contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE;
        String acceptStatus = Result.SUCCESS.name();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("orderId","403");
        map.add("channcelType","ALIPAY");
        commonMvcPerFormPost(url, content, contentType, acceptStatus, map);
        log.info("app支付 V1 end result : " + content);
    }


    @Test
    public void appPayTest_orderId() throws Exception {
        log.info("app支付 V1 start");
        //mock进行模拟
        String url = "/v1/mall/appPay";
        String content = "";
        String contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE;
        String acceptStatus = Result.FAILURE.name();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("orderId","1");
        map.add("channcelType","ALIPAY");
        commonMvcPerFormPost(url, content, contentType, acceptStatus, map);
        log.info("app支付 V1 end result : " + content);
    }

}
