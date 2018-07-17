package com.yunxin.cb.rest.mall;


import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@Api(description = "商城订单接口")
@RestController
@RequestMapping(value = "/mall/order")
@SessionAttributes("customerId")
public class OrderResource extends BaseResource {

    @ApiOperation(value = "订单确认")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commodityId", value = "商品ID", required = true, paramType = "path", dataType = "int")
    })
    @GetMapping(value = "saveOrder/{commodityId}")
    public ResponseResult saveOrder(@(value = "commodityId") int commodityId, @ModelAttribute("customerId") int customerId){
        return new ResponseResult(null);
    }

    @ApiOperation(value = "查询用户订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderStatus", value = "订单状态", required = true, paramType = "path", dataType = "int")
    })
    @GetMapping(value = "listOrder/{orderStatus}")
    public ResponseResult listOrder(@PathVariable(value = "orderStatus") int orderStatus, @ModelAttribute("customerId") int customerId){
        return new ResponseResult(null);
    }

    @ApiOperation(value = "根据订单id查询订单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, paramType = "path", dataType = "int")
    })
    @GetMapping(value = "getOrder/{orderId}")
    public ResponseResult getOrder(@PathVariable(value = "orderId") int orderId, @ModelAttribute("customerId") int customerId){
        return new ResponseResult(null);
    }

    @ApiOperation(value = "根据订单id取消订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, paramType = "path", dataType = "int")
    })
    @GetMapping(value = "cancelOrder/{orderId}")
    public ResponseResult cancelOrder(@PathVariable(value = "orderId") int orderId, @ModelAttribute("customerId") int customerId){
        return new ResponseResult(null);
    }

}
