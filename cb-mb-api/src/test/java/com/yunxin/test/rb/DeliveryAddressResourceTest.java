package com.yunxin.test.rb;

import com.alibaba.fastjson.JSONObject;
import com.yunxin.cb.mall.entity.meta.AddressType;
import com.yunxin.cb.mall.vo.DeliveryAddressVO;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


public class DeliveryAddressResourceTest extends MockHttpUtils {

    @Test
    public void getDeliveryAddress() throws Exception {
        log.info("查询收货地址列表 V1 start");
        String url = "/v1/mall/deliveryAddress/list";
        commonMvcPerFormGet(url,"",MediaType.APPLICATION_JSON_UTF8_VALUE,"SUCCESS",null);
    }
    @Test
    public void getDeliveryAddressDetail() throws Exception{
        log.info("查询收货地址详情 V1 start");
        String url = "/v1/mall/deliveryAddress/96";
        commonMvcPerFormGet(url,"",MediaType.APPLICATION_JSON_UTF8_VALUE,"SUCCESS",null);
    }
    @Test
    public void addDeliveryAddress_notNull()throws Exception{
        log.info("新增收货地址 V1 start");
        String url = "/v1/mall/deliveryAddress";
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
        commonMvcPerFormPost(url,deadVO,MediaType.APPLICATION_JSON_VALUE,"SUCCESS",null);
    }

    @Test
    public void addDeliveryAddress_defaultAddress()throws Exception{
        log.info("新增收货地址 V1 start");
        String url = "/v1/mall/deliveryAddress";
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
        commonMvcPerFormPost(url,deadVO,MediaType.APPLICATION_JSON_VALUE,"SUCCESS",null);
    }

    @Test
    public void addDeliveryAddress_otherNull()throws Exception{
        log.info("新增收货地址 V1 start");
        String url = "/v1/mall/deliveryAddress";
        DeliveryAddressVO deliveryAddressVO = new DeliveryAddressVO();
        deliveryAddressVO.setAddressType(AddressType.COMPANY);
        deliveryAddressVO.setCity("");
        deliveryAddressVO.setConsigneeAddress("");
        deliveryAddressVO.setConsigneeMobile("18679307314");
        deliveryAddressVO.setConsigneeTelephone("");
        deliveryAddressVO.setConsigneeName("");
        deliveryAddressVO.setDistrict("");
        deliveryAddressVO.setProvince("");
        String deadVO = JSONObject.toJSONString(deliveryAddressVO);
        commonMvcPerFormPost(url,deadVO,MediaType.APPLICATION_JSON_VALUE,"FAILURE",null);
    }

    @Test
    public void addDeliveryAddress_mobile()throws Exception{
        log.info("新增收货地址 V1 start");
        String url = "/v1/mall/deliveryAddress";
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
        commonMvcPerFormPost(url,deadVO,MediaType.APPLICATION_JSON_VALUE,"FAILURE",null);
    }

    @Test
    public void deleteDeliveryAddress() throws Exception{
        log.info("删除收货地址 V1 start");
        String url = "/v1/mall/deliveryAddress/96";
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        commonMvcPerFormDelete(url,"",MediaType.APPLICATION_JSON_VALUE,"SUCCESS",map);
    }

    @Test
    public void putDeliveryAddress()throws Exception{
        log.info("修改收货地址 V1 start");
        String url = "/v1/mall/deliveryAddress/95";
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
        commonMvcPerFormPut(url,deadVO,MediaType.APPLICATION_JSON_VALUE,"SUCCESS",null);
    }

    @Test
    public void putDeliveryAddress_mobile()throws Exception{
        log.info("修改收货地址 V1 start");
        String url = "/v1/mall/deliveryAddress/95";
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
        commonMvcPerFormPut(url,deadVO,MediaType.APPLICATION_JSON_VALUE,"FAILURE",null);
    }

    @Test
    public void putDeliveryAddress_postCode()throws Exception{
        log.info("修改收货地址 V1 start");
        String url = "/v1/mall/deliveryAddress/95";
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
        commonMvcPerFormPut(url,deadVO,MediaType.APPLICATION_JSON_VALUE,"FAILURE",null);
    }

}
