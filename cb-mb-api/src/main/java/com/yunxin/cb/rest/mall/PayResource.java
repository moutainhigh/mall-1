package com.yunxin.cb.rest.mall;


import com.jpay.vo.AjaxResult;
import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.meta.ChannelType;
import com.yunxin.cb.mall.entity.meta.TerminalType;
import com.yunxin.cb.mall.service.PaymentService;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(description = "商城订单支付接口")
@RestController
@RequestMapping(value = "{version}/mall")
public class PayResource extends BaseResource {
    @Resource
    private PaymentService payService;

    @ApiOperation(value = "app支付")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "channcelType", value = "支付渠道", required = true, paramType = "post", dataType = "String")})
    @ApiVersion(1)
    @PostMapping(value = "appPay")
    public ResponseResult appPay(@RequestParam(value = "orderId") int orderId, @RequestParam(value = "channcelType") ChannelType channcelType){
        try {
            AjaxResult result = payService.pay(orderId, getCustomerId(), channcelType, TerminalType.APP);
            if (result != null && result.getCode() == 0) {
                return new ResponseResult(result.getData());
            }
            return new ResponseResult(Result.FAILURE);
        } catch (Exception e) {
            logger.error("getTempOrder failed ", e);
            return new ResponseResult(Result.FAILURE);
        }
    }
}
