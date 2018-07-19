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
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
            //throw new Exception("库存不足");
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
        defaultValue(order);//添加默认数据

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
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PageFinder<Order> pageOrder(Query q) {
        PageFinder<Order> page = new PageFinder<Order>(q.getPageNo(), q.getPageSize());
        List<Order> list = null;
        long rowCount = 0L;
        try {
            //调用dao查询满足条件的分页数据
            list = orderMapper.pageList(q);
            //调用dao统计满足条件的记录总数
            rowCount = orderMapper.count(q);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        //如list为null时，则改为返回一个空列表
        list = list == null ? new ArrayList<Order>(0) : list;
        //将分页数据和记录总数设置到分页结果对象中
        page.setData(list);
        page.setRowCount(rowCount);//记录总数
        page.setPageCount((int)rowCount);//总页数
        return page;
    }

    @Override
    public Order getByOrderIdAndCustomerId(Integer orderId, Integer customerId){
        return orderMapper.selectByOrderIdAndCustomerId(orderId, customerId);
    }

    @Override
    public Order cancelOrder(Order order) {
        order = orderMapper.selectByOrderIdAndCustomerId(order.getOrderId(), order.getCustomerId());
        //待付款订单才可取消
        if (order != null && order.getOrderState() == OrderState.PENDING_PAYMENT.ordinal()){
            Set<OrderItem> orderItems = order.getOrderItems();
            if (orderItems != null && !orderItems.isEmpty()) {
                for (OrderItem orderItem : orderItems) {
                    //更新库存
                    Product product = productMapper.selectByPrimaryKey(orderItem.getProductId());
                    //增加库存
                    product.setStoreNum(product.getStoreNum() + orderItem.getProductNum());
                    productMapper.updateByPrimaryKey(product);
                }
            } else {
                return null;
            }
            //更改订单为取消状态
            order.setOrderState(OrderState.CANCELED.ordinal());
            orderMapper.updateByPrimaryKey(order);
        } else {
            //throw new Exception("该订单不可取消");
        }
        return order;
    }

    private void defaultValue(Order order) {
        order.setCouponsFee(0d);
        order.setDelivery(false);
        order.setDeliveryFeeTotal(0d);
        order.setDeliveryState(0);
        order.setDeliveryType(0);
        order.setScoreTotal(0);
        order.setProvince("0");
        order.setCity("0");
        order.setDistrict("0");
        order.setConsigneeAddress("");
        order.setConsigneeName("");
        order.setEnabled(true);
        order.setWeightTotal(0d);
        order.setVolumeTotal(0d);
        order.setUsedScore(0);
        order.setPostCode("");
        order.setPayByIntegral(0d);
        order.setDiscountTotal(0d);
        order.setDiscountDeliveryFeeTotal(0d);
        //order.setSellerId(0);
        //order.setLogisticId(0);
    }
}

