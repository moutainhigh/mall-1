package com.yunxin.cb.rest.mall;


import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.Order;
import com.yunxin.cb.mall.entity.OrderItem;
import com.yunxin.cb.mall.entity.meta.OrderState;
import com.yunxin.cb.mall.entity.meta.PaymentType;
import com.yunxin.cb.mall.exception.CommonException;
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
import io.swagger.annotations.*;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * 商城订单接口
 *
 * @param
 * @author gws
 * @date 2018/7/24 20:01
 * @return
 */
@Api(description = "商城订单接口")
@RestController
@RequestMapping(value = "{version}/mall")
public class OrderResource extends BaseResource {

    @Resource
    private OrderService orderService;

    @ApiOperation(value = "获取预下单数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "货品id", required = true, paramType = "form", dataType = "int"),
            @ApiImplicitParam(name = "buyNum", value = "购买数量", required = true, defaultValue = "1", paramType = "form", dataType = "int")})
    @ApiVersion(1)
    @PostMapping(value = "order/tempOrder")
    public ResponseResult<TempOrderVO> getTempOrder(@RequestParam(value = "productId") int productId,
            @RequestParam(value = "buyNum", defaultValue = "1") int buyNum, @RequestParam(value = "paymentType") PaymentType paymentType) {
        try {
            TempOrderVO tempOrderVO = orderService.getTempOrder(getCustomerId(), productId, buyNum, paymentType);
            return new ResponseResult(tempOrderVO);
        } catch (CommonException e) {
            logger.info("getTempOrder failed", e);
            return new ResponseResult(Result.FAILURE, e.getMessage());
        } catch (Exception e) {
            logger.error("getTempOrder failed ", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "订单确认")
    @ApiImplicitParams({
    })
    @ApiVersion(1)
    @PostMapping(value = "order")
    public ResponseResult addOrder(@RequestBody OrderConfirmVO orderConfirmVO){
        try {
            logger.info("orderConfirmVO:" + orderConfirmVO.toString());
            Order order = new Order();
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
            Order result = orderService.createOrder(order);
            if (result == null) {
                return new ResponseResult(Result.FAILURE);
            }
            return new ResponseResult(result.getOrderId());
        } catch (CommonException e) {
            logger.info("addOrder failed", e);
            return new ResponseResult(Result.FAILURE, e.getMessage());
        } catch (Exception e) {
            logger.error("addOrder failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "查询用户订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", required = true, paramType = "query", dataType = "int")})
    @PostMapping(value = "order/pageList")
    public ResponseResult<PageFinder<OrderDetailVO>> pageOrder(@RequestParam(value = "pageNo") int pageNo,
            @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "orderState", required = false) OrderState orderState) {
        try {
            Order order = new Order();
            Query q = new Query(pageNo, pageSize);
            order.setCustomerId(getCustomerId());
            order.setOrderState(orderState);
            q.setData(order);
            PageFinder<Order> pageFinder = orderService.pageOrder(q);
            PageFinder<OrderDetailVO> page = OrderDetailVO.dOconvertVOPage(pageFinder);
            return new ResponseResult(page);
        } catch (Exception e) {
            logger.error("pageListOrder failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "根据订单id查询订单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, paramType = "path", dataType = "int")})
    @ApiVersion(1)
    @GetMapping(value = "order/{orderId}")
    public ResponseResult<OrderDetailVO> getOrder(@PathVariable(value = "orderId") int orderId) {
        try {
            OrderDetailVO orderDetailVO = null;
            Order model = orderService.getByOrderIdAndCustomerId(orderId, getCustomerId());
            if (model != null) {
                orderDetailVO = OrderDetailVO.dOconvertVO(model);
            }
            return new ResponseResult(orderDetailVO);
        } catch (Exception e) {
            logger.error("getOrder failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "根据订单id取消订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, paramType = "form", dataType = "int"),
            @ApiImplicitParam(name = "cancelReason", value = "取消原因", required = true, paramType = "form", dataType = "String")})
    @ApiVersion(1)
    @PutMapping(value = "order/cancelOrder")
    public ResponseResult cancelOrder(@RequestParam("orderId") int orderId, @RequestParam("cancelReason") String cancelReason) {
        try {
            Order order = new Order();
            order.setOrderId(orderId);
            order.setCancelReason(cancelReason);
            order.setCustomerId(getCustomerId());
            Order result = orderService.cancelOrder(order);
            if (result == null) {
                return new ResponseResult(Result.FAILURE);
            }
            return new ResponseResult(Result.SUCCESS);
        } catch (CommonException e) {
            logger.info("cancelOrder failed", e);
            return new ResponseResult(Result.FAILURE, e.getMessage());
        } catch (Exception e) {
            logger.error("cancelOrder failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "根据订单id确认收货")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, paramType = "path", dataType = "int")})
    @ApiVersion(1)
    @PutMapping(value = "order/confirmOrder/{orderId}")
    public ResponseResult confirmOrder(@PathVariable(value = "orderId") int orderId) {
        try {
            int result = orderService.confirmOrder(orderId, getCustomerId());
            if (result <= 0) {
                return new ResponseResult(Result.FAILURE);
            }
            return new ResponseResult(Result.SUCCESS);
        } catch (CommonException e) {
            logger.info("confirmOrder failed", e);
            return new ResponseResult(Result.FAILURE, e.getMessage());
        } catch (Exception e) {
            logger.error("confirmOrder failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }
}
