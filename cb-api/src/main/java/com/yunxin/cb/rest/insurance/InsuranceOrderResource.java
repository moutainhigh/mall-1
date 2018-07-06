package com.yunxin.cb.rest.insurance;

import com.yunxin.cb.insurance.entity.InsuranceOrder;
import com.yunxin.cb.insurance.service.IInsuranceOrderService;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单接口
 * create by dengchenggang
 */
@Api(description = "订单接口")
@RestController
@RequestMapping(value = "/insurance/order")
public class InsuranceOrderResource {

    @Resource
    private IInsuranceOrderService insuranceOrderService;

    @ApiOperation(value ="保存订单")
    @PostMapping(value = "saveOrder")
    public ResponseResult saveOrder(@RequestBody InsuranceOrder insuranceOrder)
    {
        return new ResponseResult(insuranceOrderService.addInsuranceOrder(insuranceOrder));
    }


}
