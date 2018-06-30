package com.yunxin.cb.cms.service.imp;

import com.yunxin.cb.cms.dao.ArticleDao;
import com.yunxin.cb.cms.dao.ArticleRecipeStepDao;
import com.yunxin.cb.cms.dao.ProgramaDao;
import com.yunxin.cb.cms.dao.SpecialSubjectDao;
import com.yunxin.cb.cms.entity.*;
import com.yunxin.cb.cms.service.IArticleService;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.LogicUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenxing on 2016/1/15.
 */
@Service
@Transactional
public class ArticleService implements IArticleService {

    @Resource
    private ArticleDao articleDao;

    @Resource
    private ProgramaDao programaDao;

    @Resource
    private SpecialSubjectDao specialSubjectDao;

    @Resource
    private ArticleRecipeStepDao articleRecipeStepDao;

    @Override
    public Article addArticle(Article article) throws EntityExistException {
        if (!articleDao.isUnique(article, Article_.articleTitle)) {
            throw new EntityExistException("文章标题已存在");
        }
        article.setCreateTime(new Date());
        article.setPrograma(programaDao.findOne(article.getPrograma().getProgramaId()));
        if (null != article.getSpecialSubject()) {
            article.setSpecialSubject(specialSubjectDao.findOne(article.getSpecialSubject().getSubjectId()));
        }

        article = articleDao.save(article);

        String[] stepName = article.getStepName();
        String[] stepDescription = article.getStepDescription();
        String[] stepRemark = article.getStepRemark();
        String[] imagePath = article.getStepPicPath();
        int[] stepWhen = article.getStepWhen();
        int[] sortOrder = article.getStepOrder();
        if (LogicUtils.isNotNullAndEmpty(stepName)) {
            for (int i = 0; i < stepName.length; i++) {
                ArticleRecipeStep articleRecipeStep = new ArticleRecipeStep();
                articleRecipeStep.setStepName(stepName[i]);
                articleRecipeStep.setDescription(stepDescription[i]);
                articleRecipeStep.setRemark(stepRemark[i]);
                articleRecipeStep.setPicPath(imagePath[i]);
                articleRecipeStep.setStepWhen(stepWhen[i]);
                articleRecipeStep.setStepOrder(sortOrder[i]);
                articleRecipeStep.setArticle(article);
                articleRecipeStepDao.save(articleRecipeStep);
            }
        }


        programaDao.updateArticleAmount(article.getPrograma().getProgramaId());
        return article;
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Article getArticleById(int articleId) {
        return articleDao.findOne(articleId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<ArticleRecipeStep> getRecipeStepsByArticleId(int articleId) {
        return articleRecipeStepDao.findAll(new Specification<ArticleRecipeStep>() {
            @Override
            public Predicate toPredicate(Root<ArticleRecipeStep> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                predicates.add(criteriaBuilder.equal(root.get(ArticleRecipeStep_.article).get(Article_.articleId), articleId));
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(ArticleRecipeStep_.stepOrder)));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Article getArticleFetchAllById(int articleId) {
        return articleDao.fetchAllById(articleId);
    }


    @Override
    public void enableArticleById(int articleId, boolean enabled) {
        articleDao.enableArticleById(enabled, articleId);
    }

    @Override
    public Article updateArticle(Article article) throws EntityExistException {
        if (!articleDao.isUnique(article, Article_.articleTitle)) {
            throw new EntityExistException("文章标题已存在");
        }
        Article articleDB = articleDao.findById(article.getArticleId());
        AttributeReplication.copying(article, articleDB, Article_.articleTitle, Article_.articleCode, Article_.author, Article_.content, Article_.enabled,
                Article_.orderTime, Article_.origin, Article_.publishDate, Article_.remark,
                Article_.shortTitle, Article_.specialSubject, Article_.summary, Article_.titleColor);
        articleDB.setPrograma(programaDao.findOne(article.getPrograma().getProgramaId()));
        if (null != article.getSpecialSubject()) {
            article.setSpecialSubject(specialSubjectDao.findOne(article.getSpecialSubject().getSubjectId()));
        }
        articleRecipeStepDao.deleteByArticle(articleDB.getArticleId());
        String[] stepName = article.getStepName();
        String[] stepDescription = article.getStepDescription();
        String[] stepRemark = article.getStepRemark();
        String[] imagePath = article.getStepPicPath();
        int[] stepWhen = article.getStepWhen();
        int[] sortOrder = article.getStepOrder();
        if (LogicUtils.isNotNullAndEmpty(stepName)) {
            for (int i = 0; i < stepName.length; i++) {
                ArticleRecipeStep articleRecipeStep = new ArticleRecipeStep();
                articleRecipeStep.setStepName(stepName[i]);
                articleRecipeStep.setDescription(stepDescription[i]);
                articleRecipeStep.setRemark(stepRemark[i]);
                articleRecipeStep.setPicPath(imagePath[i]);
                articleRecipeStep.setStepWhen(stepWhen[i]);
                articleRecipeStep.setStepOrder(sortOrder[i]);
                articleRecipeStep.setArticle(article);
                articleRecipeStepDao.save(articleRecipeStep);
            }
        }

        return articleDB;
    }


    @Override
    public void updatePicPath(int articleId, String picPath) {
        Article articleDb = articleDao.findOne(articleId);
        articleDb.setPicPath(picPath);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Article> pageArticles(PageSpecification<Article> pageSpecification) {
        pageSpecification.setCustomSpecification(new CustomSpecification<Article>() {
            public void buildFetch(Root<Article> root) {
                root.fetch(Article_.programa, JoinType.LEFT).fetch(Programa_.articleChannel, JoinType.LEFT);
                root.fetch(Article_.specialSubject, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<Article> root, CriteriaQuery<?> Article, CriteriaBuilder builder, List<Predicate> predicates) {
                Path<Programa> statusPath = root.get(Article_.programa);
                builder.desc(root.get(Article_.orderTime));
            }
        });
        Page<Article> pages = articleDao.findAll(pageSpecification, pageSpecification.getPageRequest());
        return pages;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Article> pageArticles(PageSpecification<Article> pageSpecification, Programa programa) {
        pageSpecification.setCustomSpecification(new CustomSpecification<Article>() {

            public void buildFetch(Root<Article> root) {
                root.fetch(Article_.specialSubject, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<Article> root, CriteriaQuery<?> Article, CriteriaBuilder builder, List<Predicate> predicates) {
                Path<Programa> statusPath = root.get(Article_.programa);
                predicates.add(builder.equal(statusPath, programa));
                builder.desc(root.get(Article_.orderTime));
            }
        });
        Page<Article> pages = articleDao.findAll(pageSpecification, pageSpecification.getPageRequest());
        return pages;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Article> pageArticles(PageSpecification<Article> pageSpecification, Programa programa, int subjectId) {
        pageSpecification.setCustomSpecification(new CustomSpecification<Article>() {

            public void buildFetch(Root<Article> root) {
                root.fetch(Article_.specialSubject, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<Article> root, CriteriaQuery<?> Article, CriteriaBuilder builder, List<Predicate> predicates) {
                Path<Programa> statusPath = root.get(Article_.programa);
                predicates.add(builder.equal(statusPath, programa));
                predicates.add(builder.equal(root.get(Article_.specialSubject).get(SpecialSubject_.subjectId), subjectId));
                builder.desc(root.get(Article_.orderTime));
            }
        });
        Page<Article> pages = articleDao.findAll(pageSpecification, pageSpecification.getPageRequest());
        return pages;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Article> pageArticles(PageSpecification<Article> pageSpecification, ArticleChannel channel) {
        pageSpecification.setCustomSpecification(new CustomSpecification<Article>() {

            public void buildFetch(Root<Article> root) {
                root.fetch(Article_.specialSubject, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<Article> root, CriteriaQuery<?> Article, CriteriaBuilder builder, List<Predicate> predicates) {
                Path<Programa> statusPath = root.get(Article_.programa);
                predicates.add(builder.equal(statusPath.get(Programa_.articleChannel), channel));
                builder.desc(root.get(Article_.orderTime));
            }
        });
        Page<Article> pages = articleDao.findAll(pageSpecification, pageSpecification.getPageRequest());
        return pages;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Article> pageArticles(PageSpecification<Article> pageSpecification, SpecialSubject specialSubject) {
        pageSpecification.setCustomSpecification(new CustomSpecification<Article>() {
            public void buildFetch(Root<Article> root) {
                root.fetch(Article_.specialSubject, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<Article> root, CriteriaQuery<?> Article, CriteriaBuilder builder, List<Predicate> predicates) {
                Path<SpecialSubject> statusPath = root.get(Article_.specialSubject);
                predicates.add(builder.equal(statusPath, specialSubject));
                builder.desc(root.get(Article_.orderTime));
            }
        });
        Page<Article> pages = articleDao.findAll(pageSpecification, pageSpecification.getPageRequest());
        return pages;
    }

    @Override
    public int removeArticleById(int articleId) {
        int programaId = articleDao.findById(articleId).getPrograma().getProgramaId();
        articleDao.delete(articleId);
        programaDao.updateArticleAmount(programaId);
        return articleId;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getArticleCount() {
        return articleDao.count();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Article> getArticleByProgramaId(int programaId) {
        return articleDao.findByProgramaId(programaId);
    }
}
