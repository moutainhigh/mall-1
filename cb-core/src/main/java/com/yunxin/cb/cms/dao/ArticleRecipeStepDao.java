package com.yunxin.cb.cms.dao;

import com.yunxin.cb.cms.entity.ArticleRecipeStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface ArticleRecipeStepDao extends JpaRepository<ArticleRecipeStep, Integer>, JpaSpecificationExecutor<ArticleRecipeStep> {

    @Modifying
    @Query("delete from ArticleRecipeStep setp where setp.article.articleId =?1")
    void deleteByArticle(int articleId);
}
