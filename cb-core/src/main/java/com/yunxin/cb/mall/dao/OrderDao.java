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

}

