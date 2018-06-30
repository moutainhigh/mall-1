/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Catalog;
import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.entity.Product;
import com.yunxin.cb.mall.entity.meta.ProductState;
import com.yunxin.cb.mall.entity.meta.PublishState;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author z001075
 */
public interface ProductDao extends ProductPlusDao, JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product>, BaseDao<Product> {


    @Modifying
    @Query("update Product c set c.productState = ?1 where c.productId=?2")
    public void updateProductStatusById(ProductState productState, int productId);

    @Query("select p from Product p left join fetch p.commodity c left join fetch c.catalog left join fetch c.brand where p.productId=?1")
    public Product findProductDetail(int productId);

    @Query("update Product p set p.storeNum=?1 where p.productId=?2")
    public void updateProductByProductId(int depositoryNum, int productId);

    @Query("select p.storeNum from Product p where p.productId=?1")
    int checkStoreNumByProductId(int productId);

    @Query("select p from Product p order by p.addTime desc")
    public List<Product> findByOrderByAddTime_desc(Pageable pageable);

    @Query("select p from Product p left join fetch p.commodity where p.productId in ?1")
    List<Product> findByProductIdIn(List<Integer> productId);

    @Query("select p from Product p where p.commodity.catalog.catalogName=?1 order by p.addTime ")
    public List<Product> findByCommodity(String categoryCode, Pageable pageable);

    public List<Product> findByCommodityOrderByProductNameAsc(Commodity commodity);

    @Query("select distinct p from Product p left join fetch p.store left join fetch p.productAttributes pa left join fetch pa.attribute attr left join fetch attr.attributeGroup where p.commodity.commodityId=?1 and p.publishState=?2 order by p.productName ")
    List<Product> findByCommodityOrderByProductNameAsc(int commodityId, PublishState publishState);

    @Query("select distinct p from Product p left join fetch p.store left join fetch p.productAttributes pa left join fetch pa.attribute attr left join fetch attr.attributeGroup where p.commodity.commodityId=?1 order by p.productName ")
    List<Product> findByCommodityOrderByProductNameAsc(int commodityId);

    @Query("select p from Product p where p.commodity.brand.brandId=?1 order by p.addTime ")
    public List<Product> findByBrandId(int brandId, Pageable pageable);

    @Query("select c.brand.brandId from Commodity c,Product p where c.commodityId=p.commodity.commodityId and p.productId=?1")
    public int findBrandIdByProductId(int productId);


    @Query("select p from Product p where p.commodity.brand.brandId=?1")
    public List<Product> findSameBrandProductsByBrandId(int brandId);

    @Query("select c.catalog.catalogId from Commodity c,Product p where c.commodityId=p.commodity.commodityId and p.productId=?1")
    public int findCategoryIdByProductId(int productId);

    @Query("select p from Product p where p.commodity.catalog.catalogId=?1")
    public List<Product> findSameCategoryProductsByCategoryId(int categoryId);

    public Product findByProductName(String productName);

    public Product findByProductNameAndProductStateNot(String productName, ProductState status);

    @Query("select b from Product b where b.productName=?1 and b.productId<>?2")
    public Product findByProductNameAndProductIdNot(String productName, int productId);


    public List<Product> findByCommodityAndProductStateOrderByProductNameAsc(Commodity commodity, ProductState status);

    @Query("select p from Product p left join fetch p.commodity c  left join fetch c.brand b where p.productId=?1")
    public Product finByProductId(int productId);


    @Query("select distinct (p.commodity.commodityId) from  Product p where p.productState=?1")
    List<Integer> findByProductState(ProductState on);

    @Query("select p.productId from Product p  where p.commodity.commodityId=?1")
    List<Integer> findpodcutIdsCommodityId(int activityScopeValue);


    public List<Product> findByProductStateAndCommodity_commodityId(ProductState on, int commodityId);

    List<Product> findByProductNameInAndProductStateNot(String[] productNames, ProductState productState);

    /***
     * 根据商品ID获取货品集合
     * @param commodityId
     * @return
     */
    List<Product> findByCommodity_commodityId(int commodityId);

    /***
     * 根据货品ID集合修改货品上下架状态
     * @param publishState
     * @param prodIds
     */
    @Modifying
    @Query("update Product c set c.publishState=?1 where c.productId in (?2)")
    void updateUpOrDownShelvesInProductId(PublishState publishState, int[] prodIds);

}

interface ProductPlusDao {
    public List<Product> findBySalesNum(Catalog catalog, int limit);
}
