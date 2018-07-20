package com.yunxin.cb.rest.mall;


import com.yunxin.cb.mall.entity.ProductReturn;
import com.yunxin.cb.mall.service.ProductReturnService;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(description = "商城退货接口")
@RestController
@RequestMapping(value = "/mall/productReturn")
public class ProductReturnResource extends BaseResource {

    @Resource
    private ProductReturnService productReturnService;

    @ApiOperation(value = "退货申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "itemId", value = "订单商品id", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "reason", value = "退货原因", paramType = "post", dataType = "String")})
    @PostMapping(value = "saveGoodsReturn")
    public ResponseResult saveGoodsReturn(@RequestBody ProductReturn productReturn) throws Exception{
        productReturnService.applyOrderProductReturn(productReturn);
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "退货查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsReturn", value = "退货查询对象", required = true, paramType = "post", dataType = "Object")})
    @PostMapping(value = "getGoodsReturn")
    public ResponseResult getGoodsReturn(@RequestBody ProductReturn productReturn){
        return new ResponseResult(null);
    }


}
