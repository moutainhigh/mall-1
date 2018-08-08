package com.yunxin.cb.rest.insurance;

import com.yunxin.cb.insurance.entity.InsuranceInformedMatter;
import com.yunxin.cb.insurance.entity.InsuranceProduct;
import com.yunxin.cb.insurance.service.IInsuranceInformedMatterService;
import com.yunxin.cb.insurance.service.IInsuranceProductService;
import com.yunxin.cb.mall.entity.Attachment;
import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.cb.mall.service.IAttachmentService;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Api(description = "保险产品接口")
@RestController
@RequestMapping(value = "/insurance/product")
public class InsuranceProductResource {

    @Resource
    private IInsuranceProductService insuranceProductService;
    @Resource
    private IInsuranceInformedMatterService insuranceInformedMatterService;
    @Resource
    private IAttachmentService attachmentService;

    @ApiOperation(value = "获取所有保险产品")
    @GetMapping(value = "getInsuranceProducts")
    public ResponseResult<InsuranceProduct> getInsuranceProducts(){
        return new ResponseResult(insuranceProductService.getInsuranceProducts());
    }

    @ApiOperation(value = "根据id获取一个保险产品的事项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "prodId", value = "保险产品Id", required = true, paramType = "form", dataType = "int")
    })
    @PostMapping(value = "getResponseResultByProdId")
    public ResponseResult getResponseResultByProdId(int prodId){
        Set<InsuranceInformedMatter> list=insuranceProductService.getInsuranceProductById(prodId).getInsuranceInformedMatters();
        return new ResponseResult(list);
    }


    @ApiOperation(value = "根据id获取一个保险产品详情图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "prodId", value = "保险产品Id", required = true, paramType = "form", dataType = "int")
    })
    @GetMapping(value = "getImagesByProdId/{prodId}")
    public ResponseResult getImagesByProdId(@PathVariable int prodId){
        List<String> imgs=new ArrayList<>();
        List<Attachment> listAttachment=attachmentService.findAttachmentByObjectTypeAndObjectId(ObjectType.INSURANCEPRODUCTDETAIL,prodId);
        listAttachment.stream().forEach(p ->{
            imgs.add(p.getFilePath());
        });
        return new ResponseResult(imgs);
    }


}
