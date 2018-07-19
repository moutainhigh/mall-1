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
public class DeliveryAddressResource extends BaseResource {

    @Resource
    private DeliveryAddressService deliveryAddressService;
    @ApiOperation(value = "通过用户ID查询收货地址列表")
    @ApiImplicitParams({
    })
    @PostMapping(value = "getDeliveryAddressList")
    public ResponseResult getDeliveryAddress(){
        List<DeliveryAddress> list = deliveryAddressService.selectByCustomerId(getCustomerId());
        return new ResponseResult(list);
    }
    @ApiOperation(value = "收货地址详情")
    @PostMapping(value = "getDeliveryAddress")
    public ResponseResult getDeliveryAddressDetail(@RequestParam(value = "addressId") int addressId){
        DeliveryAddress deliveryAddress = deliveryAddressService.selectByPrimaryKey(addressId);
        return new ResponseResult(deliveryAddress);
    }

    @ApiOperation(value = "添加收货地址")
    @PostMapping(value = "addDeliveryAddress")
    public ResponseResult addDeliveryAddress(@RequestBody DeliveryAddress deliveryAddress){
        int add_sunm = deliveryAddressService.insert(deliveryAddress);
        if(add_sunm <= 0){
            return new ResponseResult(Result.FAILURE);
        }
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "删除收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressId", value = "地址ID", required = true, paramType = "path", dataType = "int")
    })
    @GetMapping(value = "delectDeliveryAddress/{addressId}")
    public ResponseResult delectDeliveryAddress(@PathVariable(value = "addressId") int addressId){
        int delete_sunm = deliveryAddressService.deleteByPrimaryKey(addressId);
        if(delete_sunm <= 0){
            return new ResponseResult(Result.FAILURE);
        }
        return new ResponseResult(Result.SUCCESS);
    }
    @ApiOperation(value = "更新收货地址")
    @PostMapping(value = "upDeliveryAddress")
    public ResponseResult upDeliveryAddress(@RequestBody DeliveryAddress deliveryAddress){
        int update_sunm = deliveryAddressService.updateByPrimaryKey(deliveryAddress);
        if(update_sunm <= 0){
            return new ResponseResult(Result.FAILURE);
        }
        return new ResponseResult(Result.SUCCESS);
    }
}
