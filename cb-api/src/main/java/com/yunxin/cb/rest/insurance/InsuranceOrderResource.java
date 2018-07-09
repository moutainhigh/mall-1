package com.yunxin.cb.rest.insurance;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.yunxin.cb.common.utils.CachedUtil;
import com.yunxin.cb.insurance.entity.InsuranceOrder;
import com.yunxin.cb.insurance.service.IInsuranceOrderService;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import com.yunxin.cb.vo.VerificationCode;
import com.yunxin.core.annotation.CustomJsonFilter;
import com.yunxin.core.persistence.PageSpecification;
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
@CrossOrigin
@RequestMapping(value = "/insurance/order")
@SessionAttributes("customerId")
public class InsuranceOrderResource extends BaseResource {

    @Resource
    private IInsuranceOrderService insuranceOrderService;

    @Resource
    private ICustomerService customerService;

    @ApiOperation(value ="保存订单")
    @PostMapping(value = "saveOrder")
    @JsonFilter(value = "data.insuranceOrderBeneficiarys,data.insuranceOrderInformedMatters")
    public ResponseResult saveOrder(@RequestBody InsuranceOrder insuranceOrder, @RequestParam String code, @ModelAttribute("customerId") int customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if(customer == null){
            return new ResponseResult(Result.FAILURE, "链接错误或用户不存在");
        }
        //校验验证码
        VerificationCode verificationCode = (VerificationCode) CachedUtil.getInstance().getContext(insuranceOrder.getInsuranceOrderPolicyholderBank().getBankMobile());
        //验证码不存在
        if (verificationCode == null){
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


    @ApiOperation(value ="查询用户订单列表")
    @PostMapping(value = "getOrders")
    public ResponseResult getOrders(@RequestBody PageSpecification<InsuranceOrder> query, @ModelAttribute("customerId") int customerId) {
        PageSpecification.FilterDescriptor filterDescriptor = new PageSpecification.FilterDescriptor();
        filterDescriptor.setField("customer.customerId");
        filterDescriptor.setLogic("and");
        filterDescriptor.setOperator("eq");
        filterDescriptor.setValue(customerId);
        query.getFilter().getFilters().add(filterDescriptor);
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value ="查询用户订单详情")
    @PostMapping(value = "getOrder/{orderCode}")
    public ResponseResult getOrders(@PathVariable String orderCode, @ModelAttribute("customerId") int customerId) {
        return new ResponseResult(Result.SUCCESS);
    }
}
