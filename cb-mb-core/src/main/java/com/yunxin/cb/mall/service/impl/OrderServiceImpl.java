package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.Order;
import com.yunxin.cb.mall.entity.OrderItem;
import com.yunxin.cb.mall.entity.Product;
import com.yunxin.cb.mall.entity.meta.OrderState;
import com.yunxin.cb.mall.entity.meta.PaymentType;
import com.yunxin.cb.mall.mapper.OrderItemMapper;
import com.yunxin.cb.mall.mapper.OrderMapper;
import com.yunxin.cb.mall.mapper.ProductMapper;
import com.yunxin.cb.mall.service.OrderService;
import com.yunxin.cb.util.UUIDGeneratorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderItemMapper orderItemMapper;

    @Resource
    private ProductMapper productMapper;

    /***
     * 创建订单
     * @param order
     * @return
     */
    @Override
    @Transactional
    public Order createOrder(Integer productId, Order order) throws Exception {
        //根据货品id查询货品
        Product product = productMapper.selectByPrimaryKey(productId);
        //判断货品是否存在，且库存足够
        if (product == null || product.getStoreNum() <= 0) {
            //库存不足
            return null;
        }
        //支付方式
        if (order.getPaymentType()== PaymentType.LOAN.ordinal()) {
            //贷款购车需要判断用户额度（接口调用）
        }
        //添加订单数据
        Date createTime = new Date();
        order.setCreateTime(createTime);
        order.setUpdateTime(createTime);
        order.setOrderCode(UUIDGeneratorUtil.getUUCode());
        order.setOrderState(OrderState.PENDING_PAYMENT.ordinal());
        order.setProdQuantity(1);
        order.setTotalPrice(Double.valueOf(product.getSalePrice()));
        order.setFeeTotal(order.getTotalPrice());
        orderMapper.insert(order);
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(order.getOrderId());
        orderItem.setBuyerMessage(order.getBuyerMessage());
        orderItem.setOrderItemPrice(product.getSalePrice());
        orderItem.setProductId(product.getProductId());
        orderItem.setSalePrice(product.getSalePrice());
        orderItem.setEvaluate(false);
        orderItem.setProductNum(1);
        //orderItem.setProductImg();
        orderItem.setCreateTime(createTime);
        orderItemMapper.insert(orderItem);
        //减少库存
        product.setStoreNum(product.getStoreNum() - 1);
        productMapper.updateByPrimaryKey(product);
        return order;
    }

    @Override
    public Order cancelOrder(Integer orderId) {
        //更改订单为取消状态
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order != null){
            Set<OrderItem> orderItems = order.getOrderItems();
            if (orderItems != null && !orderItems.isEmpty()) {
                for (OrderItem orderItem : orderItems) {
                    //更新库存
                    Product product = productMapper.selectByPrimaryKey(orderItem.getProductId());
                    //增加库存
                    product.setStoreNum(product.getStoreNum() + 1);
                    productMapper.updateByPrimaryKey(product);
                }
            } else {
                return null;
            }
        }
        return order;
    }
}

