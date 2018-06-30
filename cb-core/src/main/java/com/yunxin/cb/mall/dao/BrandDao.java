/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Brand;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.*;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * @author gonglei
 */
public interface BrandDao extends JpaRepository<Brand, Integer>, JpaSpecificationExecutor<Brand>, BaseDao<Brand> {


    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Brand> findByEnabledOrderByBrandNameAsc(boolean del);

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Brand> findByEnabledAndDisplayAndHotOrderByBrandNameAsc(boolean del, boolean display, boolean hot);

    @EntityGraph(value = "Brand.category", type = EntityGraph.EntityGraphType.LOAD)
    Brand findByBrandId(int brandId);

    @Modifying
    @Query("update Brand a set a.enabled = ?1 where a.brandId=?2")
    void enableBrandById(boolean enabled, int brandId);
}
