package com.yunxin.cb.cms.dao;

import com.yunxin.cb.cms.entity.ArticleChannel;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.*;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * Created by chenxing on 2016/1/15.
 */
public interface ArticleChannelDao extends JpaRepository<ArticleChannel, Integer>, JpaSpecificationExecutor<ArticleChannel>, BaseDao<ArticleChannel> {


    @Query("select a from ArticleChannel a where a.channelId=?1")
    ArticleChannel findByChannelId(int channelId);


    @Query("select a from ArticleChannel a where a.channelCode=?1")
    ArticleChannel findByChannelCode(String channelCode);


    @Modifying
    @Query("update ArticleChannel a set a.enabled = ?1 where a.channelId=?2")
    void enableArticleChannelById(boolean enabled, int channelId);

    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<ArticleChannel> findByEnabledOrderBySortOrderAsc(boolean enabled);

    @Query("select a from ArticleChannel a where a.articleChannelTypes like '%PAD%' and a.enabled=true order by a.sortOrder asc")
    List<ArticleChannel> getArticleChannelsForMobile();
}
