package com.yunxin.cb.cms.dao;

import com.yunxin.cb.cms.entity.Article;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by gonglei on 16/1/14.
 */
public interface ArticleDao extends ArticlePlusDao, JpaRepository<Article, Integer>, JpaSpecificationExecutor<Article>, BaseDao<Article> {

    @Modifying
    @Query("update Article a set a.enabled = ?1 where a.articleId=?2")
    void enableArticleById(boolean enabled, int articleId);

    @Query("select  art from Article art left join fetch art.specialSubject left join fetch art.programa pro left join fetch pro.articleChannel where art.articleId=?1")
    Article fetchAllById(int articleId);

    @Query("select  art from Article  art where art.programa.programaId=?1")
    List<Article> findByProgramaId(int programaId);

    Stream<Article> findAllByEnabled(boolean enable);
}

interface ArticlePlusDao {


    void indexAll(Stream<Article> articleStream);

    List<Article> searchArticles(String text);

    Page<Article> pageSearchArticles(String text, Pageable pageable);
}
