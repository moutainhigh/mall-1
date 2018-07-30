package com.yunxin.cb.rest.mall;


import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Order;
import com.yunxin.cb.mall.entity.OrderItem;
import com.yunxin.cb.mall.entity.meta.OrderState;
import com.yunxin.cb.mall.service.OrderService;
import com.yunxin.cb.mall.vo.OrderConfirmProductVO;
import com.yunxin.cb.mall.vo.OrderConfirmVO;
import com.yunxin.cb.mall.vo.OrderDetailVO;
import com.yunxin.cb.mall.vo.TempOrderVO;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * 商城订单接口
* @author gws
* @date 2018/7/24 20:01
* @param
* @return
*/
@Api(description = "商城订单接口")
@RestController
@RequestMapping(value = "{version}/mall")
public class OrderResource extends BaseResource {

    private static Logger logger = LoggerFactory.getLogger(OrderResource.class);

    @Resource
    private OrderService orderService;

    @ApiOperation(value = "订单确认页面数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "货品id", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "buyNum", value = "购买数量", required = true, defaultValue = "1", paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "paymentType", value = "支付方式", required = true, paramType = "post", dataType = "String")})
    @ApiVersion(1)
    @PostMapping(value = "order/tempOrder")
    public ResponseResult<TempOrderVO> getTempOrder(@RequestParam(value = "productId")int productId,
                                                    @RequestParam(value = "buyNum", defaultValue = "1")int buyNum, @RequestParam(value = "paymentType")String paymentType) {
        TempOrderVO tempOrderVO = null;
        try {
            tempOrderVO = orderService.getTempOrder(getCustomerId(), productId, buyNum, paymentType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseResult(tempOrderVO);
    }

    @ApiOperation(value = "订单确认")
    @ApiImplicitParams({
            })
    @ApiVersion(1)
    @PostMapping(value = "order")
    public ResponseResult addOrder(@RequestBody OrderConfirmVO orderConfirmVO) throws Exception{
        logger.info("orderConfirmVO:" + orderConfirmVO.toString());
        Order order = new Order();
        try {
            BeanUtils.copyProperties(order, orderConfirmVO);
            order.setCustomerId(getCustomerId());
            if (orderConfirmVO.getOrderConfirmProductList() != null && !orderConfirmVO.getOrderConfirmProductList().isEmpty()) {
                Set<OrderItem> orderItems = new HashSet<OrderItem>();
                for (OrderConfirmProductVO orderConfirmProductVO : orderConfirmVO.getOrderConfirmProductList()) {
                    OrderItem orderItem = new OrderItem();
                    BeanUtils.copyProperties(orderItem, orderConfirmProductVO);
                    orderItems.add(orderItem);
                }
                order.setOrderItems(orderItems);
            }
        } catch (Exception e) {
            logger.info("addOrder failed", e);
            return new ResponseResult(Result.FAILURE);
        }
        Order result = orderService.createOrder(order);
        if (result == null) {
            return new ResponseResult(Result.FAILURE);
        }
        return new ResponseResult(result.getOrderId());
    }

    @ApiOperation(value = "查询用户订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页数", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "orderStatus", value = "订单状态", paramType = "post", dataType = "String")})
    @PostMapping(value = "order/pageList")
    public ResponseResult<PageFinder<OrderDetailVO>> pageOrder(@RequestParam(value = "pageNo") int pageNo, @RequestParam(value = "pageSize") int pageSize,
                                                               @RequestParam(value = "orderState", required = false) String orderState){
        Order order = new Order();
        Query q = new Query(pageNo, pageSize);
        order.setCustomerId(getCustomerId());
        if (StringUtils.isNotBlank(orderState)) {
            order.setOrderState(OrderState.valueOf(orderState));
        }
        q.setData(order);
        PageFinder<Order> pageFinder = orderService.pageOrder(q);
        PageFinder<OrderDetailVO> page = null;
        try {
            page = OrderDetailVO.dOconvertVOPage(pageFinder);
        } catch (Exception e) {
            logger.info("pageListOrder failed", e);
            return new ResponseResult(Result.FAILURE);
        }
        return new ResponseResult(page);
    }

    @ApiOperation(value = "根据订单id查询订单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, paramType = "path", dataType = "int")})
    @ApiVersion(1)
    @GetMapping(value = "order/{orderId}")
    public ResponseResult<OrderDetailVO> getOrder(@PathVariable(value = "orderId") int orderId){
        Order model = orderService.getByOrderIdAndCustomerId(orderId, getCustomerId());
        OrderDetailVO orderDetailVO = null;
        if (model != null) {
            try {
                orderDetailVO =  OrderDetailVO.dOconvertVO(model);
            } catch (Exception e) {
                logger.info("getOrder failed", e);
                return new ResponseResult(Result.FAILURE);
            }
        }
        return new ResponseResult(orderDetailVO);
    }

    @ApiOperation(value = "根据订单id取消订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "cancelReason", value = "取消原因", required = true, paramType = "post", dataType = "String")})
    @ApiVersion(1)
    @PutMapping(value = "order/cancelOrder")
    public ResponseResult cancelOrder(@RequestParam("orderId") int orderId, @RequestParam("cancelReason")String cancelReason)throws Exception{
        Order order = new Order();
        order.setOrderId(orderId);
        order.setCancelReason(cancelReason);
        order.setCustomerId(getCustomerId());
        Order result = orderService.cancelOrder(order);
        if (result == null) {
            return new ResponseResult(Result.FAILURE);
        }
        return new ResponseResult(Result.SUCCESS);
    }

    @ApiOperation(value = "根据订单id确认收货")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, paramType = "path", dataType = "int")})
    @ApiVersion(1)
    @PutMapping(value = "order/confirmOrder/{orderId}")
    public ResponseResult confirmOrder(@PathVariable(value = "orderId") int orderId)throws Exception{
        int result = orderService.confirmOrder(orderId, getCustomerId());
        if (result <= 0) {
            return new ResponseResult(Result.FAILURE);
        }
        return new ResponseResult(Result.SUCCESS);
    }

}
