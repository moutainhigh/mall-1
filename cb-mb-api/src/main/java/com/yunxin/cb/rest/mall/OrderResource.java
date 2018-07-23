package com.yunxin.cb.rest.mall;


import com.yunxin.cb.mall.entity.Order;
import com.yunxin.cb.mall.service.OrderService;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
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
            @ApiImplicitParam(name = "paymentType", value = "支付方式", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "consigneeName", value = "收货人姓名", required = true, paramType = "post", dataType = "String"),
            @ApiImplicitParam(name = "consigneeMobile", value = "收货人手机号码", required = true, paramType = "post", dataType = "String"),
            @ApiImplicitParam(name = "consigneeAddress", value = "收货人地址", required = true, paramType = "post", dataType = "String"),
            @ApiImplicitParam(name = "productId", value = "货品id", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "productNum", value = "购买数量", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "buyerMessage", value = "买家留言", paramType = "post", dataType = "String")
            })
    @PostMapping(value = "saveOrder")
    public ResponseResult saveOrder(@RequestBody Order order) throws Exception{
        order.setCustomerId(getCustomerId());
        Order result = orderService.createOrder(order);
        if (result == null) {
            return new ResponseResult(Result.FAILURE);
        }
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "查询用户订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页数", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "orderStatus", value = "订单状态", paramType = "json", dataType = "int")})
    @PostMapping(value = "pageOrder")
    public ResponseResult pageOrder(Query q, @RequestBody Order order){
        order.setCustomerId(getCustomerId());
        q.setData(order);
        PageFinder<Order> pageFinder = orderService.pageOrder(q);
        return new ResponseResult(pageFinder);
    }

    @ApiOperation(value = "根据订单id查询订单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, paramType = "get", dataType = "int")})
    @GetMapping(value = "getOrder")
    public ResponseResult getOrder(@RequestParam(value = "orderId") int orderId){
        Order order = orderService.getByOrderIdAndCustomerId(orderId, getCustomerId());
        return new ResponseResult(order);
    }

    @ApiOperation(value = "根据订单id取消订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "cancelReason", value = "取消原因", paramType = "post", dataType = "int")})
    @PostMapping(value = "cancelOrder")
    public ResponseResult cancelOrder(@RequestBody Order order)throws Exception{
        order.setCustomerId(getCustomerId());
        Order result = orderService.cancelOrder(order);
        if (result == null) {
            return new ResponseResult(Result.FAILURE);
        }
        return new ResponseResult(Result.SUCCESS);
    }

}
