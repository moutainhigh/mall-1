package com.yunxin.cb.cms.service;

import com.yunxin.cb.cms.entity.*;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by chenxing on 2016/1/15.
 */
@Service
@Transactional
public interface IArticleService {

    Article addArticle(Article article) throws EntityExistException;

    Article updateArticle(Article article) throws EntityExistException;

    Article getArticleById(int articleId);

    List<ArticleRecipeStep> getRecipeStepsByArticleId(int articleId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Article getArticleFetchAllById(int articleId);


    /**
     * 逻辑删除
     *
     * @param articleId
     */
    void enableArticleById(int articleId, boolean enabled);

    void updatePicPath(int articleId, String picPath);

    @Transactional(readOnly = true)
    Page<Article> pageArticles(PageSpecification<Article> pageSpecification);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Page<Article> pageArticles(PageSpecification<Article> pageSpecification, Programa programa);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Page<Article> pageArticles(PageSpecification<Article> pageSpecification, Programa programa, int subjectId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Page<Article> pageArticles(PageSpecification<Article> pageSpecification, ArticleChannel channel);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Page<Article> pageArticles(PageSpecification<Article> pageSpecification, SpecialSubject specialSubject);

    int removeArticleById(int articleId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long getArticleCount();

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<Article> getArticleByProgramaId(int programaId);
}
