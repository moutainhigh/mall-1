package com.yunxin.cb.cms.dao;

import com.yunxin.cb.cms.entity.Programa;
import com.yunxin.cb.cms.entity.SpecialSubject;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * Created by gonglei on 16/1/26.
 */
public interface SpecialSubjectDao extends JpaRepository<SpecialSubject, Integer>, JpaSpecificationExecutor<SpecialSubject>, BaseDao<SpecialSubject> {


    @Modifying
    @Query("update SpecialSubject a set a.enabled = ?1 where a.subjectId=?2")
    void enableSpecialSubjectById(boolean enabled, int subjectId);

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<SpecialSubject> findByEnabledAndRecommendOrderBySortOrderAsc(boolean enabled, boolean recommend);

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<SpecialSubject> findByProgramaAndEnabledAndRecommendOrderBySortOrderAsc(Programa programa, boolean enabled, boolean recommend, Pageable pageable);

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<SpecialSubject> findByEnabledOrderBySortOrderAsc(boolean enabled);

}
