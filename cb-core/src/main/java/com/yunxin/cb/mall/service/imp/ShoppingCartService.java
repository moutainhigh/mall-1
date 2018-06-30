/**
 *
 */
package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.ShoppingCartDao;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.Product;
import com.yunxin.cb.mall.entity.ShoppingCart;
import com.yunxin.cb.mall.service.IProductService;
import com.yunxin.cb.mall.service.IShoppingCartService;
import com.yunxin.core.util.LogicUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author z001075
 */
@Service
@Transactional
public class ShoppingCartService implements IShoppingCartService {

    @Resource
    private ShoppingCartDao shoppingCartDao;

    @Resource
    private IProductService productService;

    @Override
    public ShoppingCart addShoppingCart(ShoppingCart shoppingCart) {
        ShoppingCart cart = shoppingCartDao.findTopByCustomerAndProduct(shoppingCart.getCustomer(), shoppingCart.getProduct());
        if (cart != null) {
            cart.setCartNum(cart.getCartNum() + shoppingCart.getCartNum());
            return cart;
        } else {
            shoppingCart.setCreateTime(new Date());
            return shoppingCartDao.save(shoppingCart);
        }
    }

    @Override
    public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart)
            throws Exception {
        ShoppingCart oldShoppingCart = shoppingCartDao.findOne(shoppingCart
                .getCartId());
        oldShoppingCart.setCartNum(shoppingCart.getCartNum());
        return oldShoppingCart;
    }

    @Override
    public void removeShoppingCart(int cartId) {
        shoppingCartDao.delete(cartId);
    }

    @Override
    public void removeShoppingCarts(List<ShoppingCart> carts) {
        if (LogicUtils.isNotNullAndEmpty(carts)) {
            for (ShoppingCart c : carts) {
                shoppingCartDao.delete(c);
            }
        }
    }

    @Override
    public void addShoppingCarts(List<Integer> products, Customer customer) throws Exception {
        for (int productId : products) {
            if (productId != 0) {
                Product p = productService.getProductById(productId);
                ShoppingCart cart = new ShoppingCart();
                if (LogicUtils.isNotNull(p)) {
                    cart.setCartNum(1);
                    Product cp = new Product();
                    cp.setSalePrice(p.getSalePrice());
                    cp.setMarketPrice(p.getMarketPrice());
                    cp.setDefaultPicPath(p.getDefaultPicPath());
                    cp.setProductId(p.getProductId());
                    cp.setProductName(p.getProductName());
                    cart.setProduct(cp);
                    if (customer != null) {
                        cart.setCustomer((Customer) customer);
                        addShoppingCart(cart);
                    }
                }
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShoppingCart> getAllShoppingCarts(Customer customer) {
        List<ShoppingCart> shoppingCarts = shoppingCartDao.getAllShoppingCart(customer);
        return shoppingCarts;
    }

    @Override
    public void emptyAllShoppingCart(Customer customer) {
        shoppingCartDao.emptyAllShoppingCart(customer.getCustomerId());
    }

    @Override
    public void addSessionShoppingCartList(List<ShoppingCart> shoppingCarts,
                                           Customer customer) throws Exception {
        if (LogicUtils.isNotNull(customer) && customer.getCustomerId() > 0) {
            if (LogicUtils.isNotNullAndEmpty(shoppingCarts)) {
                for (ShoppingCart cart : shoppingCarts) {
                    cart.setCreateTime(new Date());
                    cart.setCustomer(customer);
                    this.addShoppingCart(cart);
                }
            }
        }
    }

    @Override
    public List<ShoppingCart> addShoppingCartList(List<ShoppingCart> shoppingCarts,
                                                  Customer customer) throws Exception {
        if (LogicUtils.isNotNull(customer) && customer.getCustomerId() > 0) {
            shoppingCartDao.emptyAllShoppingCart(customer.getCustomerId());
            if (LogicUtils.isNotNullAndEmpty(shoppingCarts)) {
                List<ShoppingCart> newList = new ArrayList<ShoppingCart>();
                for (ShoppingCart cart : shoppingCarts) {
                    cart.setCreateTime(new Date());
                    cart = this.addShoppingCart(cart);
                    newList.add(cart);
                }
                return newList;
            }
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public int countShoppingCart(Customer customer) {
        List<ShoppingCart> list = shoppingCartDao.getAllShoppingCart(customer);
        if (LogicUtils.isNotNullAndEmpty(list)) {
            return list.size();
        }
        return 0;
    }

    @Override
    public List<ShoppingCart> addShoppingCartListByProdIds(List<Integer> prodIds, Customer customer)
            throws Exception {
        List<ShoppingCart> shopList = new ArrayList<ShoppingCart>();
        for (int pid : prodIds) {
            Product prod = productService.getProductById(pid);
            ShoppingCart cart = new ShoppingCart();
            cart.setProduct(prod);
            cart.setCartNum(1);
            cart.setCreateTime(new Date());
            cart.setCustomer(customer);
            shopList.add(cart);
        }
        return addShoppingCartList(shopList, customer);
    }

    @Override
    @Transactional(readOnly = true)
    public ShoppingCart getShoppingCartById(int cartId) {
        return shoppingCartDao.getShoppingCartById(cartId);
    }
}
