package com.yunxin.cb.rest.mall;


import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@Api(description = "商城商品接口")
@RestController
@RequestMapping(value = "/mall/commodity")
@SessionAttributes("customerId")
public class CommodityResource extends BaseResource {


    @ApiOperation(value = "通过商品ID查询商品详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commodityId", value = "商品ID", required = true, paramType = "path", dataType = "int")
    })
    @GetMapping(value = "getCommdity/{commodityId}")
    public ResponseResult getCommdity(@PathVariable(value = "commodityId") int commodityId){
       return new ResponseResult(new Commodity());
    }

    @ApiOperation(value = "获取用户收藏夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "客户ID", required = true, paramType = "path", dataType = "int")
    })
    @GetMapping(value = "getCustomerFavorite/{customerId}")
    public ResponseResult delFavorite(@PathVariable(value = "customerId") int commodityId){
        return new ResponseResult(new Commodity());
    }

    @ApiOperation(value = "商品添加收藏夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commodityId", value = "商品ID", required = true, paramType = "path", dataType = "int")
    })
    @GetMapping(value = "addFavorite/{commodityId}")
    public ResponseResult addFavorite(@PathVariable(value = "commodityId") int commodityId){
        return new ResponseResult(new Commodity());
    }

    @ApiOperation(value = "商品移出收藏夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commodityId", value = "商品ID", required = true, paramType = "path", dataType = "int")
    })
    @GetMapping(value = "delFavorite/{commodityId}")
    public ResponseResult delFavorite(@PathVariable(value = "commodityId") int commodityId){
        return new ResponseResult(new Commodity());
    }

}
