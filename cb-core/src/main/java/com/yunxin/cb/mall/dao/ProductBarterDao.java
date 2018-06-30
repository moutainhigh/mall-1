package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.ProductBarter;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by k001389 on 2015/1/30.
 */
public interface ProductBarterDao extends JpaRepository<ProductBarter, Integer>, JpaSpecificationExecutor<ProductBarter>, BaseDao<ProductBarter> {

    @Modifying
    @Query("update ProductBarter od set od.barterState=2 where od.orderItem.itemId=?1")
    public void updateBarterStatus(int orderItemId);

    @Query("select p from ProductBarter p where p.order.orderId=?1")
    ProductBarter getProductBarterByOrderId(int orderId);

    @Query("select p from ProductBarter p where p.orderItem.itemId=?1")
    ProductBarter getProductBarterByOrderItemId(int orderItemId);

    @Query("select p from ProductBarter p left join fetch p.order o left join fetch p.orderItem oi left join fetch oi.product pd left join fetch pd.commodity c where o.orderCode =?1")
    List<ProductBarter> getProductBartersByOrderCode(String orderCode);
}
