package com.yunxin.cb.rest.mall;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.DeliveryAddress;
import com.yunxin.cb.mall.service.DeliveryAddressService;
import com.yunxin.cb.mall.vo.DeliveryAddressVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.security.interceptor.AuthInterceptor;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Api(description = "收货地址接口")
@RestController
@RequestMapping(value = "/{version}/mall")
public class DeliveryAddressResource extends BaseResource {

    private static Logger logger = LoggerFactory.getLogger(DeliveryAddressResource.class);

    @Resource
    private DeliveryAddressService deliveryAddressService;

    @ApiOperation(value = "通过用户ID查询收货地址列表 V1")
    @ApiImplicitParams({
    })
    @GetMapping(value = "deliveryAddress/list")
    @ApiVersion(1)
    @IgnoreAuthentication
    public ResponseResult<List<DeliveryAddressVO>> getDeliveryAddress() {
        try {
            List<DeliveryAddress> list = deliveryAddressService.selectByCustomerId(1);
            List<DeliveryAddressVO> volist = new ArrayList<>();
            for (DeliveryAddress deliveryAddress : list) {
                DeliveryAddressVO deliveryAddressVO = new DeliveryAddressVO();
                BeanUtils.copyProperties(deliveryAddressVO, deliveryAddress);
                volist.add(deliveryAddressVO);
            }
            return new ResponseResult(volist);
        } catch (Exception e) {
            logger.info("addDeliveryAddress failed", e);
            return new ResponseResult(Result.FAILURE);
        }

    }

    @ApiOperation(value = "通过用户ID查询收货地址列表 V2")
    @ApiImplicitParams({
    })
    @GetMapping(value = "deliveryAddress/list")
    @ApiVersion(2)
    @IgnoreAuthentication
    public ResponseResult<List<DeliveryAddressVO>> getDeliveryAddressV2() {
        try {
            List<DeliveryAddress> list = deliveryAddressService.selectByCustomerId(getCustomerId());
            List<DeliveryAddressVO> volist = new ArrayList<>();
            for (DeliveryAddress deliveryAddress : list) {
                DeliveryAddressVO deliveryAddressVO = new DeliveryAddressVO();
                BeanUtils.copyProperties(deliveryAddressVO, deliveryAddress);
                volist.add(deliveryAddressVO);
            }
            return new ResponseResult(volist);
        } catch (Exception e) {
            logger.info("addDeliveryAddress failed", e);
            return new ResponseResult(Result.FAILURE);
        }

    }

    @ApiOperation(value = "收货地址详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressId", value = "地址ID", required = true, paramType = "path", dataType = "int")
    })
    @GetMapping(value = "deliveryAddress/{addressId}")
    @ApiVersion(1)
    public ResponseResult<DeliveryAddressVO> getDeliveryAddressDetail(@PathVariable(value = "addressId") int addressId) {
        try {
            int customerId = getCustomerId();
            DeliveryAddress deliveryAddress = deliveryAddressService.selectByPrimaryKey(addressId, customerId);
            DeliveryAddressVO deliveryAddressVO = new DeliveryAddressVO();
            BeanUtils.copyProperties(deliveryAddressVO, deliveryAddress);
            return new ResponseResult(deliveryAddressVO);
        } catch (Exception e) {
            logger.info("addDeliveryAddress failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "添加收货地址")
    @ApiImplicitParams({

    })
    @PostMapping(value = "deliveryAddress")
    @ApiVersion(1)
    public ResponseResult addDeliveryAddress(@RequestBody DeliveryAddressVO deliveryAddressVO) {
        try {
            logger.info("deliveryAddressVO:" + deliveryAddressVO.toString());
            DeliveryAddress deliveryAddress = new DeliveryAddress();
            BeanUtils.copyProperties(deliveryAddress, deliveryAddressVO);
            deliveryAddressService.insert(deliveryAddress);
        } catch (Exception e) {
            logger.info("addDeliveryAddress failed", e);
            return new ResponseResult(Result.FAILURE);
        }
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "删除收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressId", value = "地址ID", required = true, paramType = "path", dataType = "int")
    })
    @DeleteMapping(value = "deliveryAddress/{addressId}")
    @ApiVersion(1)
    public ResponseResult delectDeliveryAddress(@PathVariable(value = "addressId") int addressId) {
        deliveryAddressService.deleteByPrimaryKey(addressId);
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "更新收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressId", value = "地址ID", required = true, paramType = "path", dataType = "int")
    })
    @PutMapping(value = "deliveryAddress/{addressId}")
    @ApiVersion(1)
    public ResponseResult updateDeliveryAddress(@PathVariable(value = "addressId") int addressId, @RequestBody DeliveryAddressVO deliveryAddressVO) {
        try {
            logger.info("input Parameter deliveryAddressVO:" + deliveryAddressVO.toString());
            DeliveryAddress deliveryAddress = new DeliveryAddress();
            BeanUtils.copyProperties(deliveryAddress, deliveryAddressVO);
            deliveryAddressService.updateByPrimaryKey(deliveryAddress);
        } catch (Exception e) {
            logger.info("updateDeliveryAddress failed", e);
            return new ResponseResult(Result.FAILURE);
        }
        return new ResponseResult(Result.SUCCESS);

    }
}
