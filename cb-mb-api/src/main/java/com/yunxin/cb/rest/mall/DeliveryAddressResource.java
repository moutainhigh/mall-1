package com.yunxin.cb.rest.mall;

import com.yunxin.cb.mall.entity.DeliveryAddress;
import com.yunxin.cb.mall.service.DeliveryAddressService;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(description = "收货地址接口")
@RestController
@RequestMapping(value = "/mall/deliveryAddress")
@SessionAttributes("customerId")
public class DeliveryAddressResource extends BaseResource {

    @Resource
    private DeliveryAddressService deliveryAddressService;
    @ApiOperation(value = "通过用户ID查询收货地址列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = true, paramType = "path", dataType = "int")
    })
    @GetMapping(value = "getDeliveryAddressList")
    public ResponseResult getDeliveryAddress(@ModelAttribute(value = "customerId") int customerId){
        List<DeliveryAddress> list = deliveryAddressService.selectByCustomerId(customerId);
        return new ResponseResult(list);
    }
    @ApiOperation(value = "收货地址详情")
    @GetMapping(value = "getDeliveryAddress/{address_id}")
    public ResponseResult getDeliveryAddressDetail(@PathVariable(value = "address_id") int address_id,@ModelAttribute(value = "customerId") int customerId){
        DeliveryAddress deliveryAddress = deliveryAddressService.selectByPrimaryKey(address_id);
        return new ResponseResult(deliveryAddress);
    }

    @ApiOperation(value = "添加收货地址")
    @PostMapping(value = "addDeliveryAddress")
    public ResponseResult addDeliveryAddress(@RequestBody DeliveryAddress deliveryAddress, @ModelAttribute("customerId") int customerId){
        int add_sunm = deliveryAddressService.insert(deliveryAddress);
        if(add_sunm <= 0){
            return new ResponseResult(Result.FAILURE);
        }
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "删除收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address_id", value = "地址ID", required = true, paramType = "path", dataType = "int")
    })
    @GetMapping(value = "delectDeliveryAddress/{address_id}")
    public ResponseResult delectDeliveryAddress(@PathVariable(value = "address_id") int address_id,@ModelAttribute("customerId") int customerId){
        int delete_sunm = deliveryAddressService.deleteByPrimaryKey(address_id);
        if(delete_sunm <= 0){
            return new ResponseResult(Result.FAILURE);
        }
        return new ResponseResult(Result.SUCCESS);
    }
    @ApiOperation(value = "更新收货地址")
    @PostMapping(value = "upDeliveryAddress")
    public ResponseResult upDeliveryAddress(@RequestBody DeliveryAddress deliveryAddress, @ModelAttribute("customerId") int customerId){
        int update_sunm = deliveryAddressService.updateByPrimaryKey(deliveryAddress);
        if(update_sunm <= 0){
            return new ResponseResult(Result.FAILURE);
        }
        return new ResponseResult(Result.SUCCESS);
    }
}
