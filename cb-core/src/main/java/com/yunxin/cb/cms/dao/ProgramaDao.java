package com.yunxin.cb.cms.dao;

import com.yunxin.cb.cms.entity.Programa;
import com.yunxin.cb.cms.entity.SpecialSubject;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.*;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * Created by gonglei on 16/1/26.
 */
public interface ProgramaDao extends JpaRepository<Programa, Integer>, JpaSpecificationExecutor<Programa>, BaseDao<Programa> {

    List<Programa> findByArticleChannel_ChannelIdOrderBySortOrderAsc(int channelId);

    List<Programa> findByArticleChannel_ChannelCodeOrderBySortOrderAsc(String channelCode);

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Programa> findByEnabledOrderBySortOrderAsc(boolean enabled);

    @Query("select p from Programa p left join fetch p.articleChannel where p.programaId=?1")
    Programa fetchChannelById(int programaId);

    @Modifying
    @Query("update Programa p set p.articleAmount=(select count(c.articleId) from Article c where c.programa.programaId = p.programaId) where p.programaId=?1")
    public void updateArticleAmount(int programaId);

    @Query("select sub from SpecialSubject sub left join fetch sub.programa pro where  pro.programaId=?1")
    List<SpecialSubject> findSpecialSubjectsByProgramaId(int programaId);
}
