package com.yunxin.cb.rest.insurance;

import com.yunxin.cb.insurance.service.IInsuranceProductService;
import com.yunxin.cb.vo.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/insurance/product")
public class InsuranceProductResource {

    @Resource
    private IInsuranceProductService insuranceProductService;

    @GetMapping(value = "getInsuranceProducts")
    public ResponseResult getInsuranceProducts(){
        return new ResponseResult(insuranceProductService.getInsuranceProducts());
    }



}
