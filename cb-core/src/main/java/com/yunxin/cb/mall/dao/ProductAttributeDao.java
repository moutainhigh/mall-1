package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductAttributeDao extends JpaRepository<ProductAttribute, Integer>, JpaSpecificationExecutor<ProductAttribute> {

    @Query("select pa from ProductAttribute pa left join fetch pa.product p left join fetch pa.attribute a where p.productId=?1 order by pa.proAttrId asc")
    public List<ProductAttribute> findAllPropertyByProduct(int productId);

    @Modifying
    @Query("delete from ProductAttribute p  where p.product.productId=?1")
    public void emptyProductProperty(int productId);


    //屈磊批量修改官网属性用
//    @Query("from ProductPropty p left join fetch Product where p.propertyId in(?1)")
    List<ProductAttribute> findByProAttrIdIn(List<Integer> propIds);

    @Query("select p from ProductAttribute p left join fetch p.product p2 left join fetch p2.commodity c where p.proAttrId =?1")
    List<ProductAttribute> findByPropertyId(int propId);
}

