package com.yunxin.cb.rest.insurance;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.yunxin.cb.insurance.entity.InsuranceOrder;
import com.yunxin.cb.insurance.service.IInsuranceOrderService;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.redis.RedisService;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.rest.customer.MainResource;
import com.yunxin.cb.vo.ResponseResult;
import com.yunxin.cb.vo.VerificationCode;
import com.yunxin.core.persistence.PageSpecification;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单接口
 * create by dengchenggang
 */
@Api(description = "订单接口")
@RestController
@RequestMapping(value = "/insurance/order")
public class InsuranceOrderResource extends BaseResource {
    private static Logger logger = LoggerFactory.getLogger(MainResource.class);
    @Resource
    private IInsuranceOrderService insuranceOrderService;

    @Resource
    private ICustomerService customerService;

    @Resource
    private RedisService redisService;

    @ApiOperation(value = "保存订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "post", dataType = "String")})
    @PostMapping(value = "saveOrder")
    @JsonFilter(value = "data.insuranceOrderBeneficiarys,data.insuranceOrderInformedMatters")
    public ResponseResult saveOrder(@RequestBody InsuranceOrder insuranceOrder, @RequestParam String code) {
        logger.info("getCustomerId---------------"+getCustomerId());
        Customer customer = customerService.getCustomerById(getCustomerId());
        if (customer == null) {
            return new ResponseResult(Result.FAILURE, "链接错误或用户不存在");
        }
        //校验验证码
        VerificationCode verificationCode = (VerificationCode) redisService.getVerificationCode(insuranceOrder.getInsuranceOrderPolicyholderBank().getBankMobile());
        //验证码不存在
        if (verificationCode == null) {
            return new ResponseResult(Result.FAILURE, "验证码不存在");
        }
        //验证码超过5分钟，失效
        if ((System.currentTimeMillis() - verificationCode.getSendTime()) > 300000) {
            return new ResponseResult(Result.FAILURE, "验证码失效");
        }
        //验证码错误
        if (!verificationCode.getCode().equals(code)) {
            return new ResponseResult(Result.FAILURE, "验证码错误");
        }

        insuranceOrder.setCustomer(customer);
        insuranceOrder = insuranceOrderService.addInsuranceOrder(insuranceOrder);
        return new ResponseResult(insuranceOrder.getOrderCode());
    }


    @ApiOperation(value = "查询用户订单列表")
    @ApiImplicitParams({})
    @PostMapping(value = "getOrders")
    public ResponseResult getOrders(@RequestBody PageSpecification<InsuranceOrder> query) {
        PageSpecification.FilterDescriptor filterDescriptor = new PageSpecification.FilterDescriptor();
        filterDescriptor.setField("customer.customerId");
        filterDescriptor.setLogic("and");
        filterDescriptor.setOperator("eq");

        filterDescriptor.setValue(getCustomerId());
        query.getFilter().getFilters().add(filterDescriptor);
        Page<InsuranceOrder> pagelist = insuranceOrderService.pageInsuranceOrder(query);
        return new ResponseResult(pagelist);

    }

    @ApiOperation(value = "查询用户订单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderCode", value = "订单号", required = true, paramType = "post", dataType = "String")})
    @PostMapping(value = "getOrder/{orderCode}")
    public ResponseResult getOrders(@PathVariable String orderCode) {

        InsuranceOrder  insuranceOrder =insuranceOrderService.getInsuranceOrderDetailByOrderCode(orderCode);
        if(insuranceOrder!=null)
        {
            if(insuranceOrder.getCustomer().getCustomerId()!=getCustomerId())
            {
                return new ResponseResult(Result.FAILURE,"该订单不存在");
            }
        }
        return new ResponseResult(insuranceOrder);
    }
}
