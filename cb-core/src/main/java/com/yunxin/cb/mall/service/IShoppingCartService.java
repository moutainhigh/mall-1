/**
 *
 */
package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.ShoppingCart;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author z001075
 */
public interface IShoppingCartService {
    /**
     * 添加购物车项
     *
     * @param shoppingCart
     * @return
     * @throws Exception
     */
    public ShoppingCart addShoppingCart(ShoppingCart shoppingCart);

    /**
     * 添加购物车集合，删除原有数据
     *
     * @param shoppingCart
     * @param customer
     * @throws Exception
     */
    public List<ShoppingCart> addShoppingCartList(List<ShoppingCart> shoppingCart, Customer customer) throws Exception;

    /**
     * 根据货品id添加购物车
     *
     * @param prodIds
     * @param customer
     * @throws Exception
     */
    public List<ShoppingCart> addShoppingCartListByProdIds(List<Integer> prodIds, Customer customer) throws Exception;

    /**
     * 修改购物车
     *
     * @param shoppingCart
     * @return
     * @throws Exception
     */
    public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) throws Exception;

    /**
     * 删除购物车项
     *
     * @param cartId
     */
    public void removeShoppingCart(int cartId);

    /**
     * 获取用户购物车数据
     *
     * @param customer
     * @return
     */
    public List<ShoppingCart> getAllShoppingCarts(Customer customer);

    /**
     * 清空购物车
     *
     * @param customer
     */
    public void emptyAllShoppingCart(Customer customer);

    /**
     * 购物车数量
     *
     * @param customer
     * @return
     */
    public int countShoppingCart(Customer customer);

    /**
     * 将缓存中的购物车项加入数据库,不删除原有数据
     *
     * @param shoppingCarts
     * @param customer
     * @throws Exception
     */
    public void addSessionShoppingCartList(List<ShoppingCart> shoppingCarts, Customer customer) throws Exception;

    /**
     * 批量删除
     *
     * @param carts
     */
    public void removeShoppingCarts(List<ShoppingCart> carts);

    /**
     * 根据货品号添加购物车
     *
     * @param products
     * @param customer
     * @throws Exception
     */
    public void addShoppingCarts(List<Integer> products, Customer customer) throws Exception;

    @Transactional(readOnly = true)
    ShoppingCart getShoppingCartById(int cartId);
}
