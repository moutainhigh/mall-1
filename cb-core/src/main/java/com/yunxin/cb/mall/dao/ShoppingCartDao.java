/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.Product;
import com.yunxin.cb.mall.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author z001075
 */
public interface ShoppingCartDao extends JpaRepository<ShoppingCart, Integer>, JpaSpecificationExecutor<ShoppingCart> {

    @Query("select sc from ShoppingCart sc left join fetch sc.product p left join fetch p.commodity c where sc.customer=?1")
    public List<ShoppingCart> getAllShoppingCart(Customer customer);

    @Query("select sc from ShoppingCart sc left join fetch sc.product p left join fetch p.commodity c left join fetch c.catalog ca where sc.cartId=?1")
    ShoppingCart getShoppingCartById(int cartId);

    @Modifying
    @Query("delete from ShoppingCart sc where sc.customer.customerId=?1")
    public void emptyAllShoppingCart(int customer);

    @Query("select sc from ShoppingCart sc left join fetch sc.customer left join fetch sc.product p left join fetch p.commodity c where sc.customer.customerId=?1 and sc.product.productId=?2")
    public List<ShoppingCart> queryShoppingCartByCustomerAndProduct(int customerId, int productId);

    ShoppingCart findTopByCustomerAndProduct(Customer customer, Product product);

}
