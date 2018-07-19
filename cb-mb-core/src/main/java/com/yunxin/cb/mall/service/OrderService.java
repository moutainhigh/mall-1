package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Order;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;

public interface OrderService {


    /***
     * 创建订单
     * @param order
     * @return
     */
    public Order createOrder(Integer productId, Order order) throws Exception;

    /**
     * 查询订单分页列表
     * @param q
     * @return
     */
    public PageFinder<Order> pageOrder(Query q);

    /**
     * 根据订单id和当前用户id查询订单详情
     * @param orderId
     * @param customerId
     * @return
     */
    public Order getByOrderIdAndCustomerId(Integer orderId, Integer customerId);

    /***
     *
     * @param orderId
     * @return
     */
    public Order cancelOrder(Integer orderId);
}
