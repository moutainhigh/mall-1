package com.yunxin.cb.rest.mall;


import com.yunxin.cb.mall.entity.ProductReturn;
import com.yunxin.cb.mall.service.ProductReturnService;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.util.page.Query;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(value = "applyOrderProductReturn")
    public ResponseResult applyOrderProductReturn(@RequestBody ProductReturn productReturn) throws Exception{
        productReturn.setCustomerId(getCustomerId());
        productReturnService.applyOrderProductReturn(productReturn);
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "退货分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", paramType = "post", dataType = "int")})
    @PostMapping(value = "pageProductReturn")
    public ResponseResult pageProductReturn(Query q, @RequestBody ProductReturn productReturn){
        q.setData(productReturn);
        return new ResponseResult(productReturnService.pageProductReturn(q));
    }

    @ApiOperation(value = "退货列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", paramType = "post", dataType = "int")})
    @PostMapping(value = "listProductReturn")
    public ResponseResult listProductReturn(@RequestBody ProductReturn productReturn){
        Query q = new Query();
        q.setData(productReturn);
        return new ResponseResult(productReturnService.listProductReturn(q));
    }

    @ApiOperation(value = "退货详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productReturnId", value = "退货id", required = true, paramType = "post", dataType = "int")})
    @PostMapping(value = "getProductReturn")
    public ResponseResult getProductReturn(@RequestParam Integer productReturnId){
        ProductReturn productReturn = productReturnService.getProductReturn(productReturnId, getCustomerId());
        return new ResponseResult(productReturn);
    }


}
