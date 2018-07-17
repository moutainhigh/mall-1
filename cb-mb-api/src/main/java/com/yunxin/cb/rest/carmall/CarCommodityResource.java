package com.yunxin.cb.rest.carmall;


import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(description = "汽车商城商品接口")
@RestController
@RequestMapping(value = "/mall/car")
@SessionAttributes("customerId")
public class CarCommodityResource extends BaseResource {


    @ApiOperation(value = "通过商品ID查询商品详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commodityId", value = "商品ID", required = true, paramType = "form", dataType = "int")
    })
    @PostMapping(value = "addFriendNotice/{commodityId}")
    public ResponseResult getCommdity(@PathVariable("commodityId") int commodityId){
       return new ResponseResult(new Commodity());
    }
}
