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
    @GetMapping(value = "getCommodity/{commodityId}")
    public ResponseResult getCommodity(@PathVariable(value = "commodityId") int commodityId){
       return new ResponseResult(new Commodity());
    }
}
