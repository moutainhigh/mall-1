package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Order;

public interface OrderService {


    /***
     * 创建订单
     * @param order
     * @return
     */
    public Order createOrder(Integer productId, Order order) throws Exception;

    /***
     * 取消订单
     * @param orderId
     * @return
     */
    public Order cancelOrder(Integer orderId);
}
