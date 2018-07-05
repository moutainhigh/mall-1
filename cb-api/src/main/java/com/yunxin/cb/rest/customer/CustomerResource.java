package com.yunxin.cb.rest.customer;

import com.yunxin.cb.insurance.service.IInsuranceProductService;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.vo.ResponseResult;
import com.yunxin.cb.vo.ResultType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/customer")
public class CustomerResource {

    @Resource
    private ICustomerService customerService;

    @PostMapping(value = "register")
    public ResponseResult register(@RequestBody Customer customer){
        return new ResponseResult(ResultType.SUCCESS);
    }

    @GetMapping(value="getVerificationCode")
    public ResponseResult getVerificationCode(){
        return new ResponseResult(ResultType.SUCCESS);
    }
}
