package com.yunxin.cb.rest.rb;

import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.DeliveryAddress;
import com.yunxin.cb.mall.entity.Favorite;
import com.yunxin.cb.mall.service.DeliveryAddressService;
import com.yunxin.cb.mall.service.FavoriteService;
import com.yunxin.cb.mall.vo.DeliveryAddressVO;
import com.yunxin.cb.mall.vo.FavoriteVo;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.util.LogicUtils;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
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
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @title:  报帐demo接口
 * @auther: dengcg
 * @date: 2018/7/17 18:28
 */
@Api(description = "报帐demo接口")
@RestController
@RequestMapping(value = "/{version}/rb/demo")
public class DemoResource extends BaseResource {


    @ApiOperation(value = "通过用户ID查询收货地址列表 V1")
    @ApiImplicitParams({
    })
    @GetMapping(value = "deliveryAddress/list")
    @ApiVersion(1)
    public ResponseResult<List<DeliveryAddressVO>> getDeliveryAddress() {
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "收货地址详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressId", value = "地址ID", required = true, paramType = "path", dataType = "int")
    })
    @GetMapping(value = "deliveryAddress/{addressId}")
    @ApiVersion(1)
    public ResponseResult<DeliveryAddressVO> getDeliveryAddressDetail(@PathVariable(value = "addressId") int addressId) {
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "添加收货地址")
    @ApiImplicitParams({

    })
    @PostMapping(value = "deliveryAddress")
    @ApiVersion(1)
    public ResponseResult addDeliveryAddress(@RequestBody DeliveryAddressVO deliveryAddressVO) {
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "删除收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressId", value = "地址ID", required = true, paramType = "path", dataType = "int")
    })
    @DeleteMapping(value = "deliveryAddress/{addressId}")
    @ApiVersion(1)
    public ResponseResult delectDeliveryAddress(@PathVariable(value = "addressId") int addressId) {
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "更新收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressId", value = "地址ID", required = true, paramType = "path", dataType = "int")
    })
    @PutMapping(value = "deliveryAddress/{addressId}")
    @ApiVersion(1)
    public ResponseResult updateDeliveryAddress(@PathVariable(value = "addressId") int addressId, @RequestBody DeliveryAddressVO deliveryAddressVO) {
        return new ResponseResult(Result.SUCCESS);
    }
}