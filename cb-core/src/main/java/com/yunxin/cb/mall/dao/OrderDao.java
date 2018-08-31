/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Order;
import com.yunxin.cb.mall.entity.meta.OrderState;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * @author z001075
 */
public interface OrderDao extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order>, BaseDao<Order> {


    @Modifying
    @Query("update Order o set o.orderState=?1 where o.orderId=?2")
    public void updateOrderState(OrderState orderState, int orderId);

    public Order findTopByOrderCode(String orderCode);

    @Query("select coalesce(sum(o.totalPrice),0) from Order o where o.orderState<>?1")
    public double getOrderTotalPrice(OrderState orderState);

    @Query("select coalesce(count(o.orderId),0) from Order o where o.orderState<>?1")
    public long getPayedOrderCount(OrderState orderState);

    @Query("select coalesce(count(o.orderId),0) from Order o inner join o.customer c where c.customerId =?1")
    public long getOrderCountByCustomerId(int customerId);
//
//
////    @Modifying
////    @Query("update Order  o set o.courierNumber=?2, o.logisticsCompany=?3 where  o.orderId=?1")
////    public void addNumber(int orderId, String courierNumber, String logisticsCode);

    @Query("select coalesce(sum(o.totalPrice),0) from Order o where o.createTime between ?1 and ?2")
    double getOrderTotalPriceSum(Date beginDate, Date endDate);

    @Query("select coalesce(sum(o.totalPrice),0) from Order o where o.createTime between ?1 and ?2 and o.orderState<>?3")
    double getOrderTotalPriceSum(Date beginDate, Date endDate, OrderState orderState);

    @Query("select coalesce(count(o.orderId),0) from Order o where o.createTime between ?1 and ?2")
    long getOrderCount(Date beginDate, Date endDate);

    @Query("select coalesce(count(o.orderId),0) from Order o where o.createTime between ?1 and ?2 and o.orderState<>?3")
    long getOrderCount(Date beginDate, Date endDate, OrderState orderState);

    @Query("select od from Order od left join fetch od.customer order by od.createTime desc")
    public List<Order> findByCreateTime(Pageable pageable);


    @Query("select o from Order o left join fetch o.logistic where o.orderId=?1")
    public Order getOrderAndLogisticByOrderId(int orderId);

    /**
     * 查询超时未付款订单，并将其订单状态设为已取消
     */
    @Modifying
    @Query("update Order od set od.orderState=7 where od.orderState=?1 and od.createTime<=?2")
    public void cancelTimeOutOrders(OrderState ofs, Date date);

    /**
     * 查询定时取消订单的订单列表
     */
    @Query("select od from Order od where od.orderState=?1 and od.createTime<=?2")
    public List<Order> findOrderByOrderStateAndCreateTime(OrderState ofs, Date date);

    /**
     * 查询超时未确认收货订单，并将其订单状态设为已收货
     */
    @Modifying
    @Query("update Order od set od.orderState=?1 where od.orderState=?2 and od.deliverTime<=?3")
    public void taskDeliverTimeOrders(OrderState setState,OrderState whereState, Date date);

    /**
     * 查询超时未确认收货订单，并将其订单状态设为已收货
     */
    @Query("select od from Order od where od.orderState=?1 and od.deliverTime<=?2")
    public List<Order> findOrderByOrderStateAndDeliverTime(OrderState ofs, Date date);

    /**
     * 查询已收货订单，并将其订单状态设为已完成
     */
    @Modifying
    @Query("update Order od set od.orderState=?1 where od.orderState=?2 and od.collectTime<=?3")
    public void taskCollectTimeOrders(OrderState setState,OrderState whereState, Date date);

    /**
     * 查询已收货订单，并将其订单状态设为已完成
     */
    @Query("select od from Order od left join fetch od.orderItems odi " +
            " left join fetch odi.product p left join fetch p.commodity c where od.orderState=?1 and od.collectTime<=?2")
    public List<Order> findOrderByOrderStateAndCollectTime(OrderState ofs, Date date);


}

