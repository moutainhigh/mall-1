package com.yunxin.cb.rest.mall;


import com.yunxin.cb.mall.entity.Order;
import com.yunxin.cb.mall.service.OrderService;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.vo.OrderVo;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(description = "商城订单接口")
@RestController
@RequestMapping(value = "/mall/order")
public class OrderResource extends BaseResource {

    @Resource
    private OrderService orderService;

    @ApiOperation(value = "订单确认")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderVo", value = "订单确认对象", required = true, paramType = "post", dataType = "OrderVo")
    })
    @PostMapping(value = "saveOrder")
    public ResponseResult saveOrder(@RequestBody OrderVo orderVo){
        try {
            Order order = new Order();
            order.setCustomerId(getCustomerId());
            order.setPaymentType(orderVo.getPaymentType());
            Order result = orderService.createOrder(orderVo.getProductId(), order);
            if (result == null) {
                return new ResponseResult(Result.FAILURE);
            }
        }catch (Exception e){
            return new ResponseResult(Result.FAILURE);
        }
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "查询用户订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderStatus", value = "订单状态", paramType = "get", dataType = "int")
    })
    @GetMapping(value = "listOrder")
    public ResponseResult listOrder(@RequestParam(value = "orderStatus") int orderStatus, @ModelAttribute("customerId") int customerId){
        return new ResponseResult(null);
    }

    @ApiOperation(value = "根据订单id查询订单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, paramType = "path", dataType = "int")
    })
    @GetMapping(value = "getOrder/{orderId}")
    public ResponseResult getOrder(@PathVariable(value = "orderId") int orderId){
        return new ResponseResult(null);
    }

    @ApiOperation(value = "根据订单id取消订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, paramType = "path", dataType = "int")
    })
    @GetMapping(value = "cancelOrder/{orderId}")
    public ResponseResult cancelOrder(@PathVariable(value = "orderId") int orderId){
        return new ResponseResult(null);
    }

}
