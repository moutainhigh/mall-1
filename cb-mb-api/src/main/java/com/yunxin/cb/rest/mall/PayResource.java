package com.yunxin.cb.rest.mall;


import com.yunxin.cb.rest.BaseResource;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "商城订单支付接口")
@RestController
@RequestMapping(value = "/mall/pay")
public class PayResource extends BaseResource {


}
