package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Order;
import com.yunxin.cb.mall.vo.TempOrderVO;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;

public interface OrderService {

    /***
     * 获取临时订单（订单确认页数据）
     * @param customerId
     * @param productId
     * @param buyNum
     * @param paymentType
     * @return
     */
    public TempOrderVO getTempOrder(int customerId, int productId, int buyNum, String paymentType) throws Exception;

    /***
     * 创建订单
     * @param order
     * @return
     */
    public Order createOrder(Order order) throws Exception;

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
     * 根据订单id取消订单
     * @param order
     * @return
     */
    public Order cancelOrder(Order order) throws Exception;

    /***
     * 根据订单id确认收货订单
     * @param orderId
     * @param customerId
     * @return
     */
    public int confirmOrder(Integer orderId, Integer customerId) throws Exception;
}
