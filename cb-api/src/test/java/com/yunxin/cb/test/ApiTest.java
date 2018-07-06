package com.yunxin.cb.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunxin.cb.mall.entity.Customer;
import org.junit.Test;

import static com.yunxin.cb.mall.entity.meta.CustomerType.PLATFORM_SELF;

public class ApiTest {

//    @Test
    public void register() throws JsonProcessingException {
        String url = "http://localhost:8158/api/noAuth/register";
        Customer customer = new Customer();
        customer.setAccountName("13316815481");
        customer.setMobile("13316815481");
        customer.setCustomerType(PLATFORM_SELF);
        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(customer);
        System.out.println(url);
        System.out.println(body);
        String response = HttpUtils.doJsonPost(url, body);
        System.out.println(response);
    }

    public static void main(String[] args) throws JsonProcessingException {
        ApiTest api = new ApiTest();
        api.register();
    }
}
