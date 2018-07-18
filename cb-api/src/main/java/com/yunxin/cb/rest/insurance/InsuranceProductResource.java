package com.yunxin.cb.rest.insurance;

import com.yunxin.cb.insurance.service.IInsuranceProductService;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Api(description = "保险产品接口")
@RestController
@RequestMapping(value = "/insurance/product")
public class InsuranceProductResource {

    @Resource
    private IInsuranceProductService insuranceProductService;

    @ApiOperation(value = "获取所有保险产品")
    @GetMapping(value = "getInsuranceProducts")
    public ResponseResult getInsuranceProducts(){
        return new ResponseResult(insuranceProductService.getInsuranceProducts());
    }

    @ApiOperation(value = "根据id获取一个保险产品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "prodId", value = "保险产品Id", required = true, paramType = "form", dataType = "int")
    })
    @PostMapping(value = "getResponseResultByProdId")
    public ResponseResult getResponseResultByProdId(int prodId){
        return new ResponseResult(insuranceProductService.getInsuranceProductById(prodId));
    }



}
