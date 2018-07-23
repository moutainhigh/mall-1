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
@RequestMapping(value = "/auth/mall")
public class DeliveryAddressResource extends BaseResource {

    @Resource
    private DeliveryAddressService deliveryAddressService;
    @ApiOperation(value = "通过用户ID查询收货地址列表")
    @ApiImplicitParams({
    })
    @GetMapping(value = "deliveryAddress/list")
    public ResponseResult getDeliveryAddress(){
        List<DeliveryAddress> list = deliveryAddressService.selectByCustomerId(getCustomerId());
        return new ResponseResult(list);
    }
    @ApiOperation(value = "收货地址详情")
    @GetMapping(value = "deliveryAddress/{addressId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressId", value = "地址ID", required = true, paramType = "path", dataType = "int")
    })
    public ResponseResult getDeliveryAddressDetail(@PathVariable(value = "addressId") int addressId){
        int customerId = getCustomerId();
        DeliveryAddress deliveryAddress = deliveryAddressService.selectByPrimaryKey(addressId,customerId);
        return new ResponseResult(deliveryAddress);
    }

    @ApiOperation(value = "添加收货地址")
    @PostMapping(value = "deliveryAddress/{addressId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressId", value = "地址ID", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "deliveryAddress", value = "地址对象", required = true, paramType = "RequestBody", dataType = "DeliveryAddress")
    })
    public ResponseResult addDeliveryAddress(@PathVariable(value = "addressId") int addressId,@RequestBody DeliveryAddress deliveryAddress){
        deliveryAddressService.insert(deliveryAddress);
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "删除收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressId", value = "地址ID", required = true, paramType = "path", dataType = "int")
    })
    @DeleteMapping(value = "deliveryAddress/{addressId}")
    public ResponseResult delectDeliveryAddress(@PathVariable(value = "addressId") int addressId){
        deliveryAddressService.deleteByPrimaryKey(addressId);
        return new ResponseResult(Result.SUCCESS);
    }
    @ApiOperation(value = "更新收货地址")
    @PutMapping(value = "deliveryAddress/{addressId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressId", value = "地址ID", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "deliveryAddress", value = "地址对象", required = true, paramType = "RequestBody", dataType = "DeliveryAddress")
    })
    public ResponseResult updateDeliveryAddress(@PathVariable(value = "addressId") int addressId,@RequestBody DeliveryAddress deliveryAddress){
        deliveryAddressService.updateByPrimaryKey(deliveryAddress);
        return new ResponseResult(Result.SUCCESS);
    }
}
