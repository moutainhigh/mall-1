/**
 *
 */
package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.AuditState;
import com.yunxin.cb.mall.entity.meta.OrderState;
import com.yunxin.cb.mall.exception.BusinessNotInStockException;
import com.yunxin.cb.mall.exception.ProductBarterException;
import com.yunxin.cb.mall.exception.ProductReturnException;
import com.yunxin.cb.mall.vo.ConfirmOrder;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * @author gonglei
 */
public interface IOrderService {

    public Page<Order> pageCustomerOrders(PageSpecification<Order> query, Customer customer, OrderState orderState);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Page<Order> pageOrders(PageSpecification<Order> query, OrderState orderState);

    /***
     * 换货订单
     * @param query
     * @return
     */
    public Page<ProductBarter> pageBarterOrders(PageSpecification<ProductBarter> query);

    /***
     * 退货订单
     * @param query
     * @return
     */
    public Page<ProductReturn> pageReturnOrders(PageSpecification<ProductReturn> query);

    /***
     * 创建订单
     * @param order
     * @return
     */
    public Order createOrder(ConfirmOrder order) throws Exception;

    public Order getOrderById(int orderId);

    public Order getOrderByCode(String orderCode);

    public Order updateOrder(Order order);

    public void removeOrderById(int orderId);

    public Order getOrderDetailById(int orderId);

    public Order getOrderDetailByCode(String orderCode);

    /**
     * 订单调价
     *
     * @param orderId
     * @param changePrice
     * @return
     */
    public Order changeOrderPrice(int orderId, double changePrice);

    /***
     * 修改订单收货地址
     * @param orderId
     * @param deliveryAddress
     * @return
     */
    public Order changeOrderDelivery(int orderId, DeliveryAddress deliveryAddress);

    /***
     * 修改订单状态
     * @param orderState
     * @param orderId
     */
    public String updateOrderState(OrderState orderState, int orderId);

    /***
     * 设置订单发货的物流及单号
     * @param orderId
     * @param logisticId
     * @param courierNumber
     * @return
     */
    public Order editOrderLogistic(int orderId, int logisticId, String courierNumber);

    public List<OrderItem> getOrderItemsByOrderId(int orderId);

    public List<OrderItem> getOrderItemsByOrderCode(String orderCode);

    public OrderItem getOrderItemById(int itemId);

    /**
     * 待付款订单超过24H未支付 则将其状态设为已取消
     */
    public void cancelTimeOutOrders();

    boolean cancelOrder(int orderId, String cancelReason);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<Order> getLastedOrders(int limit);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    double getOrderTotalPriceSum(Date beginDate, Date endDate);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    double getPayedOrderTotalPriceSum(Date beginDate, Date endDate);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long getOrderCount(Date beginDate, Date endDate);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long getPayedOrderCount(Date beginDate, Date endDate);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long getOrderCount();

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long getDisctinctCommodityCount();

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long getOrderProductCount();

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    double getOrderTotalPrice();

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long getPayedOrderCount();

    public long getOrderCountByCustomerId(int customerId);

    /***
     * 用戶提交换货申请
     * @param productBarter
     * @return
     */
    public String applyOrderProductBarter(ProductBarter productBarter) throws EntityExistException;

    public void productBarterAudit(int barterId, AuditState auditState, String auditRemark);

    public void confirmReceivedBarterProduct(int barterId) throws ProductBarterException;

    public boolean productBarter(int orderItemId) throws ProductBarterException, BusinessNotInStockException;

    public List<ProductBarter> getProductBartersByOrderCode(String orderCode);

    /***
     * 用户提交退货退款申请
     * @param productReturn
     * @return
     * @throws EntityExistException
     */
    String applyOrderProductReturn(ProductReturn productReturn) throws EntityExistException;

    public void productReturnAudit(int returnId, AuditState auditState, String auditRemark);

    public List<ProductReturn> getProductReturnsByOrderCode(String orderCode);

    public ProductReturn getProductReturnByReturnCode(String returnCode);

    public void confirmReceivedReturnProduct(int returnId) throws ProductReturnException;

    String returnRefundOrder(ProductReturn productReturn);

    /***
     * 修改订单项评价状态
     * @param itemId
     */
    public void updateOrderItemEvaluate(int itemId);

    /***
     * 贷款订单
     * @param query
     * @return
     */
    public Page<OrderLoanApply> pageLoanOrders(PageSpecification<OrderLoanApply> query);

    /**
     * 贷款审核
    * @author gws
    * @date 2018/7/25 15:14
    * @param
    * @return void
    */
    public void orderLoanApplyAudit(int loanId, AuditState auditState, String auditRemark);
}
