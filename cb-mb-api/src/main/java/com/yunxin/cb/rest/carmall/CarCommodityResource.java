package com.yunxin.cb.rest.carmall;


import com.yunxin.cb.rest.BaseResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

@Api(description = "汽车商城商品接口")
@RestController
@RequestMapping(value = "/mall/car")
@SessionAttributes("customerId")
public class CarCommodityResource extends BaseResource {



}
