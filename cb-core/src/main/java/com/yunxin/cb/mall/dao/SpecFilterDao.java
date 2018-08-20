/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.SpecFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author chenpeng
 * @date 2018年8月20日
 */
public interface SpecFilterDao extends JpaRepository<SpecFilter, Integer>, JpaSpecificationExecutor<SpecFilter> {

    @Query("select ps from SpecFilter ps left join fetch ps.filterItems where ps.filterId=?1")
    SpecFilter getFilterDetailById(Integer id);

}
