/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Category;
import com.yunxin.cb.mall.entity.PropertyFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author sheh
 */
public interface PropertyFilterDao extends JpaRepository<PropertyFilter, Integer>, JpaSpecificationExecutor<PropertyFilter> {

    @Query("select ps from PropertyFilter ps left join fetch ps.filterItems left join fetch ps.category where ps.filterId=?1")
    public PropertyFilter getFilterDetailById(int segId);

    @Query("select distinct ps from PropertyFilter ps left join fetch ps.filterItems where ps.category=?1 and ps.enabled=true order by ps.sortOrder asc")
    List<PropertyFilter> getEnabledFiltersByCategory(Category category);
}
