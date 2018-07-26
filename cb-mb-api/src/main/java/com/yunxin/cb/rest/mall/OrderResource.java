package com.yunxin.cb.rest.mall;


import com.yunxin.cb.annotation.ApiVersion;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.OrderState;
import com.yunxin.cb.mall.entity.meta.PaymentType;
import com.yunxin.cb.mall.service.CommodityService;
import com.yunxin.cb.mall.service.DeliveryAddressService;
import com.yunxin.cb.mall.service.OrderService;
import com.yunxin.cb.mall.vo.*;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Map;
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
    @Resource
    private CommodityService commodityService;
    @Resource
    private DeliveryAddressService deliveryAddressService;

    @ApiOperation(value = "订单确认页面数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "货品id", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "buyNum", value = "购买数量", required = true, paramType = "post", dataType = "int"),
            @ApiImplicitParam(name = "paymentType", value = "支付方式", paramType = "post", dataType = "int")})
    @ApiVersion(1)
    @PostMapping(value = "order/tempOrder")
    public ResponseResult<TempOrderVO> getOrderConfrim(@RequestParam(value = "productId")int productId,
                                                       @RequestParam(value = "buyNum")int buyNum, @RequestParam(value = "paymentType")int paymentType) {
        //获取商品信息
        commodityService.getCommdityDetail(productId, 0);
        TempOrderVO tempOrderVO = new TempOrderVO();
        try {
            int customerId = getCustomerId();
            Map map = commodityService.getCommdityDetail(productId, customerId);
            if(map == null){
                return new ResponseResult(Result.FAILURE,"货品为空");
            }
            Commodity commodity = (Commodity)map.get("commodity");
            Product product = (Product)map.get("product");
            Seller seller = (Seller)map.get("seller");
            Map specs = (Map)map.get("specs");
            TempOrderItemVO tempOrderItemVO = null;
            SellerVo sellerVo = null;
            BeanUtils.copyProperties(tempOrderVO, commodity);
            if(!StringUtils.isEmpty(product)){
                tempOrderItemVO = new TempOrderItemVO();
                BeanUtils.copyProperties(tempOrderItemVO,product);
                tempOrderItemVO.setBuyNum(buyNum);
            }
            if(!StringUtils.isEmpty(seller)){
                sellerVo = new SellerVo();
                BeanUtils.copyProperties(sellerVo,seller);
            }
            //获取默认地址
            DeliveryAddress deliveryAddress = deliveryAddressService.selectDefaultByCustomerId(getCustomerId());
            if(!StringUtils.isEmpty(deliveryAddress)){
                DeliveryAddressVO deliveryAddressVO = new DeliveryAddressVO();
                BeanUtils.copyProperties(deliveryAddressVO, deliveryAddress);
                tempOrderVO.setDeliveryAddressVO(deliveryAddressVO);
            }
            tempOrderVO.setSellerVo(sellerVo);
            tempOrderVO.setSpecs(specs);
            tempOrderVO.setTempOrderItemVO(tempOrderItemVO);
            //支付方式
            for (PaymentType pay : PaymentType.values()){
                if (pay.ordinal() == paymentType) {
                    tempOrderVO.setPaymentType(pay);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
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
                                                               @RequestParam(value = "orderState") String orderState){
        Order order = new Order();
        Query q = new Query(pageNo, pageSize);
        order.setCustomerId(getCustomerId());
        order.setOrderState(OrderState.valueOf(orderState));
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
            @ApiImplicitParam(name = "cancelReason", value = "取消原因", paramType = "post", dataType = "String")})
    @ApiVersion(1)
    @PutMapping(value = "order/cancelOrder")
    public ResponseResult cancelOrder(int orderId, String cancelReason)throws Exception{
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
