/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.*;
import com.yunxin.core.orm.BaseDaoImpl;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author z001075
 */
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductPlusDao {

    @Override
    public List<Product> findBySalesNum(Catalog catalog, int limit) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
//		root.fetch(Product_.commodity, JoinType.LEFT).fetch(Commodity_.category);
        Path<Catalog> deptPath = root.<Commodity>get(Product_.commodity).get(Commodity_.catalog);
        criteriaQuery.where(criteriaBuilder.like(deptPath.get(Catalog_.catalogCode), catalog.getCatalogCode()));
        TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
        if (limit > 0) {
            typedQuery.setMaxResults(limit);
        }
        List<Product> products = typedQuery.getResultList();
        return products;
    }

}
