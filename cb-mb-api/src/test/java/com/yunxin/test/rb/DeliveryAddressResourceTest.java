package com.yunxin.test.rb;

import com.alibaba.fastjson.JSONObject;
import com.yunxin.cb.Application;
import com.yunxin.cb.mall.entity.meta.AddressType;
import com.yunxin.cb.mall.vo.DeliveryAddressVO;
import com.yunxin.cb.rest.mall.DeliveryAddressResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class DeliveryAddressResourceTest {
    private static final Logger log = LoggerFactory.getLogger(DeliveryAddressResourceTest.class);
    @Autowired
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }
    @Test
    public void getDeliveryAddress() throws Exception {
        log.info("查询收货地址列表 V1 start");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/v1/mall/deliveryAddress/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8).header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzQwNzE1MTUwNiIsImV4cCI6MTUzNTQzNzAyMCwianRpIjoiMzA2In0.0FPUMt6pw9v0LGXvMYgPb2F2Aji0DxXskDZZ3rxKQm-w3RmgYSqDYGYOys6fZxNxpItSpH-7eThdkwAHdhB5iQ"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("查询收货地址列表 V1 end result : " + content);
    }
    @Test
    public void getDeliveryAddressDetail() throws Exception{
        log.info("查询收货地址详情 V1 start");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/v1/mall/deliveryAddress/36")
                .contentType(MediaType.APPLICATION_JSON_UTF8).header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzQwNzE1MTUwNiIsImV4cCI6MTUzNTQzNzAyMCwianRpIjoiMzA2In0.0FPUMt6pw9v0LGXvMYgPb2F2Aji0DxXskDZZ3rxKQm-w3RmgYSqDYGYOys6fZxNxpItSpH-7eThdkwAHdhB5iQ"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("查询收货地址详情 V1 end result : " + content);
    }
    @Test
    public void addDeliveryAddress_notNull()throws Exception{
        log.info("新增收货地址 V1 start");
        DeliveryAddressVO deliveryAddressVO = new DeliveryAddressVO();
        deliveryAddressVO.setAddressType(AddressType.COMPANY);
        deliveryAddressVO.setCity("110100");
        deliveryAddressVO.setConsigneeAddress("中关村4号中天大厦A座555");
        deliveryAddressVO.setConsigneeMobile("18679307314");
        deliveryAddressVO.setConsigneeTelephone("111111");
        deliveryAddressVO.setConsigneeName("666");
        deliveryAddressVO.setDistrict("110109");
        deliveryAddressVO.setProvince("110000");
        deliveryAddressVO.setPostCode("335500");
        String deadVO = JSONObject.toJSONString(deliveryAddressVO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/v1/mall/deliveryAddress")
                .contentType(MediaType.APPLICATION_JSON).header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzQwNzE1MTUwNiIsImV4cCI6MTUzNTQzNzAyMCwianRpIjoiMzA2In0.0FPUMt6pw9v0LGXvMYgPb2F2Aji0DxXskDZZ3rxKQm-w3RmgYSqDYGYOys6fZxNxpItSpH-7eThdkwAHdhB5iQ")
                .content(deadVO))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("新增收货地址 V1 end result : " + content);
    }

    @Test
    public void addDeliveryAddress_defaultAddress()throws Exception{
        log.info("新增收货地址 V1 start");
        DeliveryAddressVO deliveryAddressVO = new DeliveryAddressVO();
        deliveryAddressVO.setAddressType(AddressType.COMPANY);
        deliveryAddressVO.setCity("110100");
        deliveryAddressVO.setConsigneeAddress("中关村4号中天大厦A座555");
        deliveryAddressVO.setConsigneeMobile("18679307314");
        deliveryAddressVO.setConsigneeTelephone("111111");
        deliveryAddressVO.setConsigneeName("666");
        deliveryAddressVO.setDistrict("110109");
        deliveryAddressVO.setProvince("110000");
        deliveryAddressVO.setPostCode("335500");
        deliveryAddressVO.setDefaultAddress(true);
        String deadVO = JSONObject.toJSONString(deliveryAddressVO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/v1/mall/deliveryAddress")
                .contentType(MediaType.APPLICATION_JSON).header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzQwNzE1MTUwNiIsImV4cCI6MTUzNTQzNzAyMCwianRpIjoiMzA2In0.0FPUMt6pw9v0LGXvMYgPb2F2Aji0DxXskDZZ3rxKQm-w3RmgYSqDYGYOys6fZxNxpItSpH-7eThdkwAHdhB5iQ")
                .content(deadVO))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("新增收货地址 V1 end result : " + content);
    }

    @Test
    public void addDeliveryAddress_otherNull()throws Exception{
        log.info("新增收货地址 V1 start");
        DeliveryAddressVO deliveryAddressVO = new DeliveryAddressVO();
        deliveryAddressVO.setAddressType(AddressType.COMPANY);
        deliveryAddressVO.setCity("");
        deliveryAddressVO.setConsigneeAddress("");
        deliveryAddressVO.setConsigneeMobile("");
        deliveryAddressVO.setConsigneeTelephone("");
        deliveryAddressVO.setConsigneeName("");
        deliveryAddressVO.setDistrict("");
        deliveryAddressVO.setProvince("");
        String deadVO = JSONObject.toJSONString(deliveryAddressVO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/v1/mall/deliveryAddress")
                .contentType(MediaType.APPLICATION_JSON).header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzQwNzE1MTUwNiIsImV4cCI6MTUzNTQzNzAyMCwianRpIjoiMzA2In0.0FPUMt6pw9v0LGXvMYgPb2F2Aji0DxXskDZZ3rxKQm-w3RmgYSqDYGYOys6fZxNxpItSpH-7eThdkwAHdhB5iQ")
                .content(deadVO))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("新增收货地址 V1 end result : " + content);
    }

    @Test
    public void addDeliveryAddress_mobile()throws Exception{
        log.info("新增收货地址 V1 start");
        DeliveryAddressVO deliveryAddressVO = new DeliveryAddressVO();
        deliveryAddressVO.setAddressType(AddressType.COMPANY);
        deliveryAddressVO.setCity("110100");
        deliveryAddressVO.setConsigneeAddress("中关村4号中天大厦A座555");
        deliveryAddressVO.setConsigneeMobile("1867930731");
        deliveryAddressVO.setConsigneeTelephone("111111");
        deliveryAddressVO.setConsigneeName("666");
        deliveryAddressVO.setDistrict("110109");
        deliveryAddressVO.setProvince("110000");
        deliveryAddressVO.setPostCode("335500");
        deliveryAddressVO.setDefaultAddress(true);
        String deadVO = JSONObject.toJSONString(deliveryAddressVO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/v1/mall/deliveryAddress")
                .contentType(MediaType.APPLICATION_JSON).header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzQwNzE1MTUwNiIsImV4cCI6MTUzNTQzNzAyMCwianRpIjoiMzA2In0.0FPUMt6pw9v0LGXvMYgPb2F2Aji0DxXskDZZ3rxKQm-w3RmgYSqDYGYOys6fZxNxpItSpH-7eThdkwAHdhB5iQ")
                .content(deadVO))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("新增收货地址 V1 end result : " + content);
    }

    @Test
    public void deleteDeliveryAddress() throws Exception{
        log.info("删除收货地址 V1 start");
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete("/v1/mall/deliveryAddress/36")
                .contentType(MediaType.APPLICATION_JSON_UTF8).header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzQwNzE1MTUwNiIsImV4cCI6MTUzNTQzNzAyMCwianRpIjoiMzA2In0.0FPUMt6pw9v0LGXvMYgPb2F2Aji0DxXskDZZ3rxKQm-w3RmgYSqDYGYOys6fZxNxpItSpH-7eThdkwAHdhB5iQ"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("删除收货地址 V1 end result : " + content);
    }

    @Test
    public void putDeliveryAddress()throws Exception{
        log.info("修改收货地址 V1 start");
        DeliveryAddressVO deliveryAddressVO = new DeliveryAddressVO();
        deliveryAddressVO.setAddressType(AddressType.COMPANY);
        deliveryAddressVO.setCity("110100");
        deliveryAddressVO.setConsigneeAddress("中关村4号中天大厦A座555");
        deliveryAddressVO.setConsigneeMobile("18679306666");
        deliveryAddressVO.setConsigneeTelephone("111111");
        deliveryAddressVO.setConsigneeName("666");
        deliveryAddressVO.setDistrict("110109");
        deliveryAddressVO.setProvince("110000");
        deliveryAddressVO.setPostCode("335500");
        String deadVO = JSONObject.toJSONString(deliveryAddressVO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put("/v1/mall/deliveryAddress/80")
                .contentType(MediaType.APPLICATION_JSON).header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzQwNzE1MTUwNiIsImV4cCI6MTUzNTQzNzAyMCwianRpIjoiMzA2In0.0FPUMt6pw9v0LGXvMYgPb2F2Aji0DxXskDZZ3rxKQm-w3RmgYSqDYGYOys6fZxNxpItSpH-7eThdkwAHdhB5iQ")
                .content(deadVO))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("修改收货地址 V1 end result : " + content);
    }

    @Test
    public void putDeliveryAddress_mobile()throws Exception{
        log.info("修改收货地址 V1 start");
        DeliveryAddressVO deliveryAddressVO = new DeliveryAddressVO();
        deliveryAddressVO.setAddressType(AddressType.COMPANY);
        deliveryAddressVO.setCity("110100");
        deliveryAddressVO.setConsigneeAddress("中关村4号中天大厦A座555");
        deliveryAddressVO.setConsigneeMobile("1867930666");
        deliveryAddressVO.setConsigneeTelephone("111111");
        deliveryAddressVO.setConsigneeName("666");
        deliveryAddressVO.setDistrict("110109");
        deliveryAddressVO.setProvince("110000");
        deliveryAddressVO.setPostCode("335500");
        String deadVO = JSONObject.toJSONString(deliveryAddressVO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put("/v1/mall/deliveryAddress/80")
                .contentType(MediaType.APPLICATION_JSON).header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzQwNzE1MTUwNiIsImV4cCI6MTUzNTQzNzAyMCwianRpIjoiMzA2In0.0FPUMt6pw9v0LGXvMYgPb2F2Aji0DxXskDZZ3rxKQm-w3RmgYSqDYGYOys6fZxNxpItSpH-7eThdkwAHdhB5iQ")
                .content(deadVO))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("修改收货地址 V1 end result : " + content);
    }

    @Test
    public void putDeliveryAddress_postCode()throws Exception{
        log.info("修改收货地址 V1 start");
        DeliveryAddressVO deliveryAddressVO = new DeliveryAddressVO();
        deliveryAddressVO.setAddressType(AddressType.COMPANY);
        deliveryAddressVO.setCity("110100");
        deliveryAddressVO.setConsigneeAddress("中关村4号中天大厦A座555");
        deliveryAddressVO.setConsigneeMobile("18679306666");
        deliveryAddressVO.setConsigneeTelephone("111111");
        deliveryAddressVO.setConsigneeName("666");
        deliveryAddressVO.setDistrict("110109");
        deliveryAddressVO.setProvince("110000");
        deliveryAddressVO.setPostCode("");
        String deadVO = JSONObject.toJSONString(deliveryAddressVO);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put("/v1/mall/deliveryAddress/80")
                .contentType(MediaType.APPLICATION_JSON).header("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJtb2JpbGUiOiIxMzQwNzE1MTUwNiIsImV4cCI6MTUzNTQzNzAyMCwianRpIjoiMzA2In0.0FPUMt6pw9v0LGXvMYgPb2F2Aji0DxXskDZZ3rxKQm-w3RmgYSqDYGYOys6fZxNxpItSpH-7eThdkwAHdhB5iQ")
                .content(deadVO))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        log.info("修改收货地址 V1 end result : " + content);
    }

}
