package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.ProductReturn;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by k001389 on 2015/1/30.
 */
public interface ProductReturnDao extends JpaRepository<ProductReturn, Integer>, JpaSpecificationExecutor<ProductReturn>, BaseDao<ProductReturn> {

    @Query("select p from ProductReturn p where p.order.orderId=?1")
    ProductReturn getProductReturnByOrderId(int orderId);

    @Query("select p from ProductReturn p where p.orderItem.itemId=?1")
    ProductReturn getProductReturnByOrderItemId(int orderItemId);

    @Query("select p from ProductReturn p left join fetch p.order o left join fetch p.orderItem oi left join fetch oi.product pd left join fetch pd.commodity c left join fetch p.customer where o.orderCode =?1")
    List<ProductReturn> getProductReturnsByOrderCode(String orderCode);

    @Query("select p from ProductReturn p left join fetch p.order o left join fetch p.orderItem oi left join fetch oi.product pd left join fetch pd.commodity c left join fetch p.customer where p.returnCode =?1")
    ProductReturn getProductReturnByReturnCode(String returnCode);
}
