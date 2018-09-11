package com.yunxin.cb.rest.mall;


import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.meta.OrderState;
import com.yunxin.cb.mall.service.OrderService;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.vo.OrderDetailVO;
import com.yunxin.cb.vo.OrderListItemVO;
import com.yunxin.cb.vo.OrderListVO;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * 商城订单接口
 *
 * @param
 * @author gws
 * @date 2018/7/24 20:01
 * @return
 */
@Api(description = "商城订单接口")
@Validated
@RestController
@RequestMapping(value = "{version}/mall")
public class OrderResource extends BaseResource {

    @Resource
    private OrderService orderService;

    @ApiOperation(value = "查询用户订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页数", required = true, paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页行数", required = true, paramType = "query", dataType = "Integer")})
    @ApiVersion(1)
    @PostMapping(value = "order/pageList")
    public ResponseResult<PageFinder<OrderListVO>> pageOrder(@RequestParam(value = "pageNo") int pageNo,
                                                             @RequestParam(value = "pageSize") int pageSize, @RequestParam(value = "orderState", required = false) OrderState orderState) {
        try {
//            Order order = new Order();
//            Query q = new Query(pageNo, pageSize);
//            order.setCustomerId(getCustomerId());
//            order.setOrderState(orderState);
//            q.setData(order);
//            PageFinder<Order> pageFinder = orderService.pageOrder(q);
//            PageFinder<OrderDetailVO> page = OrderDetailVO.dOconvertVOPage(pageFinder);
            List<OrderListVO> orderList = new ArrayList<OrderListVO>();
            OrderListVO orderListVO = new OrderListVO();
            orderListVO.setOrderId(1);
            orderListVO.setOrderCode("11111111111");
            orderListVO.setOrderState(OrderState.PENDING_PAYMENT);
            orderListVO.setFeeTotal(400d);
            orderListVO.setProdQuantity(2);
            orderListVO.setSellerId(1);
            orderListVO.setSellerName("商家1");
            List<OrderListItemVO> orderListItemVO = new ArrayList<OrderListItemVO>();
            OrderListItemVO orderItemVO = new OrderListItemVO();
            orderItemVO.setCommodityId(1);
            orderItemVO.setCommodityName("商品1");
            orderItemVO.setCommodityTitle("商品标题1");
            orderItemVO.setItemId(1);
            orderItemVO.setOrderItemPrice(400f);
            orderItemVO.setProductNum(2);
            orderItemVO.setProductId(1);
            orderItemVO.setProductImg("http://test.resource.999shuijingqiu.com/COMMODITY/1534574506619");
            orderItemVO.setProductName("货品1");
            orderItemVO.setSalePrice(200f);
            orderListItemVO.add(orderItemVO);
            orderListVO.setOrderListItemVO(orderListItemVO);
            orderList.add(orderListVO);
            PageFinder<OrderListVO> page = new PageFinder<OrderListVO> (1, 10, 1);
            page.setData(orderList);
            return new ResponseResult(page);
        } catch (Exception e) {
            logger.error("pageListOrder failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "根据订单id查询订单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, paramType = "path", dataType = "Integer")})
    @ApiVersion(1)
    @GetMapping(value = "order/{orderId}")
    public ResponseResult<OrderDetailVO> getOrder(@PathVariable(value = "orderId") int orderId) {
        try {
//            OrderDetailVO orderDetailVO = null;
//            Order model = orderService.getByOrderIdAndCustomerId(orderId, getCustomerId());
//            if (model != null) {
//                orderDetailVO = OrderDetailVO.dOconvertVO(model);
//                orderDetailVO.setPaymentType(model.getPaymentType().getName());
//                if (orderDetailVO.getPayOvertimeTime() == 0 && OrderState.PENDING_PAYMENT.equals(orderDetailVO.getOrderState())) { //超时订单
//                   //目前不需要定时取消
//                    //orderService.updateOrderStatusTimeOut(orderId, model.getOrderCode(), getCustomerId());
//                    //orderDetailVO.setOrderState(OrderState.CANCELED);
//                }
//            }else {
//                return new ResponseResult(Result.FAILURE, "订单不存在");
//            }
            OrderDetailVO orderDetailVO = new OrderDetailVO();
            orderDetailVO.setOrderId(1);
            orderDetailVO.setOrderCode("11111111111");
            orderDetailVO.setOrderState(OrderState.PENDING_PAYMENT);
            orderDetailVO.setFeeTotal(400d);
            orderDetailVO.setProdQuantity(2);
            orderDetailVO.setSellerId(1);
            orderDetailVO.setSellerName("商家1");
            orderDetailVO.setProvince("110000");
            orderDetailVO.setCity("110100");
            orderDetailVO.setDistrict("110109");
            orderDetailVO.setConsigneeAddress("深圳市福田区金田路富德生命保险大厦507室");
            orderDetailVO.setConsigneeName("张三");
            orderDetailVO.setConsigneeMobile("13598969958");
            orderDetailVO.setCreateTime(new Date());
            orderDetailVO.setDeliverTime(new Date());
            orderDetailVO.setPaymentTime(new Date());
            orderDetailVO.setFinishTime(new Date());
            Set<OrderListItemVO> orderItemDetails = new HashSet<OrderListItemVO>();
            OrderListItemVO orderItemVO = new OrderListItemVO();
            orderItemVO.setCommodityId(1);
            orderItemVO.setCommodityName("商品1");
            orderItemVO.setCommodityTitle("商品标题1");
            orderItemVO.setItemId(1);
            orderItemVO.setOrderItemPrice(400f);
            orderItemVO.setProductNum(2);
            orderItemVO.setProductId(1);
            orderItemVO.setProductImg("http://test.resource.999shuijingqiu.com/COMMODITY/1534574506619");
            orderItemVO.setProductName("货品1");
            orderItemVO.setSalePrice(200f);
            orderItemDetails.add(orderItemVO);
            orderDetailVO.setOrderItemDetails(orderItemDetails);
            return new ResponseResult(orderDetailVO);
        } catch (Exception e) {
            logger.error("getOrder failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "根据订单id取消订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, paramType = "form", dataType = "Integer"),
            @ApiImplicitParam(name = "cancelReason", value = "取消原因", required = true, paramType = "form", dataType = "String")})
    @ApiVersion(1)
    @PutMapping(value = "order/cancelOrder")
    public ResponseResult cancelOrder(
            @RequestParam("orderId") int orderId,
            @NotBlank(message = "取消原因不能为空")
            @RequestParam("cancelReason") String cancelReason) {
        try {
//            Order order = new Order();
//            order.setOrderId(orderId);
//            order.setCancelReason(cancelReason);
//            order.setCustomerId(getCustomerId());
//            Order result = orderService.cancelOrder(order);
//            if (result == null) {
//                return new ResponseResult(Result.FAILURE);
//            }
            return new ResponseResult(Result.SUCCESS);
//        } catch (CommonException e) {
//            logger.info("cancelOrder failed", e);
//            return new ResponseResult(Result.FAILURE, e.getMessage());
        } catch (Exception e) {
            logger.error("cancelOrder failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }

    @ApiOperation(value = "根据订单id确认收货")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, paramType = "path", dataType = "Integer")})
    @ApiVersion(1)
    @PutMapping(value = "order/confirmOrder/{orderId}")
    public ResponseResult confirmOrder(@PathVariable(value = "orderId") int orderId) {
        try {
//            int result = orderService.confirmOrder(orderId, getCustomerId());
//            if (result <= 0) {
//                return new ResponseResult(Result.FAILURE);
//            }
            return new ResponseResult(Result.SUCCESS);
//        } catch (CommonException e) {
//            logger.info("confirmOrder failed", e);
//            return new ResponseResult(Result.FAILURE, e.getMessage());
        } catch (Exception e) {
            logger.error("confirmOrder failed", e);
            return new ResponseResult(Result.FAILURE);
        }
    }
}
