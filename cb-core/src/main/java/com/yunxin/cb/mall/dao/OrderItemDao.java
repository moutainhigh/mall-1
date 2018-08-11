/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author z001075
 */
public interface OrderItemDao extends JpaRepository<OrderItem, Integer>, JpaSpecificationExecutor<OrderItem> {

    @Query("select od from OrderItem od left join fetch od.product product left join fetch product.commodity left join od.activity where od.order.orderId=?1")
    public List<OrderItem> getOrderItemsByOrderId(int orderId);

    @Query("select od from OrderItem od left join fetch od.product product left join fetch product.commodity left join od.activity where od.order.orderCode=?1")
    public List<OrderItem> getOrderItemsByOrderCode(String orderCode);

    @Query("select o from OrderItem o  left join fetch o.order ord left join fetch o.product p left join fetch p.commodity where o.itemId=?1")
    public OrderItem getOrderItemById(int orderDetailId);


    @Query("select o from OrderItem o  left join fetch o.order ord left join fetch o.product p left join o.reimbursementOrders d   where d.reimbursement.reimbursementId=?1")
    public List<OrderItem> getOrderItemByReimbursement(int reimbursementId);

    @Query("select count(distinct product.commodity.commodityId) from OrderItem od left join od.product product")
    public Long getDisctinctCommodityCount();

    @Query("select sum(od.productNum) from OrderItem od")
    public Long getOrderProductCount();


    @Query("select od from OrderItem od left join fetch od.order o left join fetch od.activity ac where o.orderCode=?2 and od.product.productId=?1")
    OrderItem getOdByProIdAndOrderCode(int productId, String orderCode);

    /**
     * @Description:       根据主订单号Id查询所有相关明细
     * @author: lxc
     * @param orderId      主订单号Id
     * @Return java.util.List<com.yunxin.cb.mall.entity.OrderItem>:
     * @DateTime: 2018/8/10 19:24
     */
    List<OrderItem> findOrderItemsByOrder_OrderId(int orderId);
}

