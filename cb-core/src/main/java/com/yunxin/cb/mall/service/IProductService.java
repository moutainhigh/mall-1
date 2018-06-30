/**
 *
 */
package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.entity.Product;
import com.yunxin.cb.mall.entity.meta.ProductState;
import com.yunxin.cb.mall.entity.meta.PublishState;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author gonglei
 */
public interface IProductService {

    Page<Product> pageProducts(PageSpecification<Product> query);

    public Product addProduct(Product product);

    // public Product addProduct(Product product, List<Goods> goodsList);

    public Product updateProduct(Product prodcut);

    public Product writeReason(Product prodcut);

    public Product getProductById(int prodcutId);

    public Product getProductDetailById(int productId);

    public void removeProductById(int prodcutId);

    public void updateProductStatusById(int productId, ProductState status);

    public void updateProductByProductId(int depositoryNum, int productId);


    public List<Product> getProductsByAddTime(int limit);

    public List<Product> getProductsByCommodity(String categoryName);

    public List<Product> findBySalesNum(String categoryName);

    public List<Product> getSameBrandProductsByProductId(int productId);

    public List<Product> getSameCategoryProductsByProductId(int productId);

    public List<Product> getProductsInIds(List<Integer> productIds);

    List<Product> getProductsByCommodity(Commodity commodity);

    public Product getProductByProductName(String productName);


    Product getProductByProductNameAndStatusNot(String productName, ProductState status);

    @Transactional(readOnly = true)
    List<Product> getProductsFetchAllByCommodityId(int commodityId, PublishState publishState);

    @Transactional(propagation = Propagation.SUPPORTS)
    List<Product> getProductsFetchAllByCommodityId(int commodityId);

    List<Product> getProductsByCommodityAndStatus(Commodity commodity, ProductState status);


    /**
     * 根据商品及所选属性生成货品
     *
     * @param commodity
     * @param propValues
     * @return
     */
    public Product generateProductByCommodity(Commodity commodity, String[] propValues);


    void deleteProduct(int productId);

    public Product setProductDefaultImgPathNull(int productId);

    public Product findOne(int productId);

    public List<Product> findByStatusAndCommodity_commodityId(ProductState productState, int commodityId);


    public List<Product> getProductsByProductNameInAndProductStatusNot(String[] productNames, ProductState productState);


    public void addProduct(List<Product> productList);

    /***
     * 货品上下架
     * @param productId
     * @param publishState
     * @return
     */
    public boolean upOrDownShelvesProduct(int productId, PublishState publishState);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long getProductCount();
}
