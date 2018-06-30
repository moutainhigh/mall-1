package com.yunxin.cb.cms.service.imp;

import com.yunxin.cb.cms.dao.ArticleChannelDao;
import com.yunxin.cb.cms.dao.ProgramaDao;
import com.yunxin.cb.cms.dao.SpecialSubjectDao;
import com.yunxin.cb.cms.entity.*;
import com.yunxin.cb.cms.entity.meta.ArticleChannelType;
import com.yunxin.cb.cms.service.IProgramaService;
import com.yunxin.cb.mall.exception.CommonException;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
public class ProgramaService implements IProgramaService {

    @Resource
    private ArticleChannelDao articleChannelDao;

    @Resource
    private ProgramaDao programaDao;

    @Resource
    private SpecialSubjectDao specialSubjectDao;

    @Override
    public ArticleChannel addArticleChannel(ArticleChannel articleChannel) throws EntityExistException {

        if (!articleChannelDao.isOrUnique(articleChannel, ArticleChannel_.channelCode, ArticleChannel_.channelName)) {
            throw new EntityExistException("频道编码或频道名称已存在");
        }
        articleChannel.setCreateTime(new Date());
        StringBuffer cTypes = new StringBuffer();
        for (String cType : articleChannel.getChannelTypes()) {
            cTypes.append(cType).append(",");
        }
        articleChannel.setArticleChannelTypes(cTypes.toString());
        return articleChannelDao.save(articleChannel);
    }

    @Override
    public SpecialSubject addSpecialSubject(SpecialSubject specialSubject) throws EntityExistException {

        if (!specialSubjectDao.isUnique(specialSubject, SpecialSubject_.subjectName)) {
            throw new EntityExistException("文章分类名称已存在");
        }
        specialSubject.setCreateTime(new Date());
        return specialSubjectDao.save(specialSubject);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<SpecialSubject> pageSpecialSubjects(PageSpecification<SpecialSubject> pageSpecification) {
        pageSpecification.setCustomSpecification(new CustomSpecification<SpecialSubject>() {
            public void buildFetch(Root<SpecialSubject> root) {
                root.fetch(SpecialSubject_.programa, JoinType.LEFT).fetch(Programa_.articleChannel, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<SpecialSubject> root, CriteriaQuery<?> Article, CriteriaBuilder builder, List<Predicate> predicates) {
                Path<Programa> statusPath = root.get(SpecialSubject_.programa);
                builder.desc(root.get(SpecialSubject_.orderTime));
            }
        });
        Page<SpecialSubject> pages = specialSubjectDao.findAll(pageSpecification, pageSpecification.getPageRequest());
        return pages;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public SpecialSubject getSpecialSubjectById(int subjectId) {
        return specialSubjectDao.findOne(subjectId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<ArticleChannel> getArticleChannels() {
        return articleChannelDao.findAll(new Sort(Sort.Direction.ASC, "sortOrder"));
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<ArticleChannel> getArticleChannels(boolean enabled) {
        return articleChannelDao.findByEnabledOrderBySortOrderAsc(enabled);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<ArticleChannel> getArticleChannels(ArticleChannelType channelType) {
        return articleChannelDao.findAll(new Specification<ArticleChannel>() {
            @Override
            public Predicate toPredicate(Root<ArticleChannel> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                predicates.add(criteriaBuilder.equal(root.get(ArticleChannel_.enabled), true));
                String cTypePath = null;
                switch (channelType) {
                    case WEB_SITE: {
                        cTypePath = "WEB_SITE";
                    }
                    case PAD: {
                        cTypePath = "PAD";
                    }
                    case MOBILE: {
                        cTypePath = "MOBILE";
                    }
                    default: {
                        cTypePath = "WEB_SITE";
                    }
                }
                predicates.add(criteriaBuilder.like(root.get(ArticleChannel_.articleChannelTypes), "%" + cTypePath + "%"));

                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(ArticleChannel_.sortOrder)));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Programa> getProgramas(boolean enabled) {
        return programaDao.findByEnabledOrderBySortOrderAsc(enabled);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<SpecialSubject> getSpecialSubjects(boolean enabled) {
        return specialSubjectDao.findByEnabledOrderBySortOrderAsc(enabled);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<SpecialSubject> getSpecialSubjects(boolean enabled, boolean recommend) {
        return specialSubjectDao.findByEnabledAndRecommendOrderBySortOrderAsc(enabled, recommend);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<SpecialSubject> getSpecialSubjects(Programa programa, boolean enabled, boolean recommend, int limit) {
        Pageable pageable = new PageRequest(0, limit);
        return specialSubjectDao.findByProgramaAndEnabledAndRecommendOrderBySortOrderAsc(programa, enabled, recommend, pageable);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Programa> getProgramasByChannelId(int channelId) {
        return programaDao.findByArticleChannel_ChannelIdOrderBySortOrderAsc(channelId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Programa> getProgramasByChannelCode(String channelCode) {
        return programaDao.findByArticleChannel_ChannelCodeOrderBySortOrderAsc(channelCode);
    }

    @Override
    public ArticleChannel updateArticleChannel(ArticleChannel articleChannel) throws EntityExistException {
        if (!articleChannelDao.isOrUnique(articleChannel, ArticleChannel_.channelCode, ArticleChannel_.channelName)) {
            throw new EntityExistException("频道编码或频道名称已存在");
        }
        StringBuffer cTypes = new StringBuffer();
        for (String cType : articleChannel.getChannelTypes()) {
            cTypes.append(cType).append(",");
        }
        articleChannel.setArticleChannelTypes(cTypes.toString());
        ArticleChannel oldArticleChannel = articleChannelDao.findOne(articleChannel.getChannelId());
        AttributeReplication.copying(articleChannel, oldArticleChannel,
                ArticleChannel_.channelName,
                ArticleChannel_.channelCode,
                ArticleChannel_.channelPosition,
                ArticleChannel_.channelStyle,
                ArticleChannel_.channelVideoURL,
                ArticleChannel_.channelURL,
                ArticleChannel_.tipName,
                ArticleChannel_.channelKey,
                ArticleChannel_.sortOrder,
                ArticleChannel_.description,
                ArticleChannel_.seoKey,
                ArticleChannel_.seoTitle,
                ArticleChannel_.seoDescription,
                ArticleChannel_.operaImgPath,
                ArticleChannel_.enabled,
                ArticleChannel_.articleChannelTypes);
        return oldArticleChannel;
    }

    @Override
    public SpecialSubject updateSpecialSubject(SpecialSubject specialSubject) throws EntityExistException {
        if (!specialSubjectDao.isUnique(specialSubject, SpecialSubject_.subjectName)) {
            throw new EntityExistException("专题名称已存在");
        }
        SpecialSubject specialSubject1 = specialSubjectDao.findOne(specialSubject.getSubjectId());
        AttributeReplication.copying(specialSubject, specialSubject1,
                SpecialSubject_.subjectName,
                SpecialSubject_.shortName,
                SpecialSubject_.description,
                SpecialSubject_.programa,
                SpecialSubject_.recommend,
                SpecialSubject_.publishDate,
                SpecialSubject_.orderTime,
                SpecialSubject_.enabled);

        return specialSubject1;
    }

    @Override
    @Transactional(readOnly = true)
    public ArticleChannel getArticleChannelById(int channelId) {
        return articleChannelDao.findByChannelId(channelId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public ArticleChannel getArticleChannelByCode(String channelCode) {
        return articleChannelDao.findByChannelCode(channelCode);
    }


    @Override
    public void enableArticleChannelById(int channelId, boolean enabled) {
        articleChannelDao.enableArticleChannelById(enabled, channelId);
    }

    @Override
    public void enableSpecialSubjectById(int subjectId, boolean enabled) {
        specialSubjectDao.enableSpecialSubjectById(enabled, subjectId);
    }


    @Override
    public void removeArticleChannelById(int channelId) throws CommonException {
        List<Programa> programas = programaDao.findByArticleChannel_ChannelIdOrderBySortOrderAsc(channelId);
        if (programas.size() > 0) {
            throw new CommonException("This Channel has programas!");
        }
        articleChannelDao.delete(channelId);
    }

    @Override
    public void removeSpecialSubjectById(int subjectId) {
        specialSubjectDao.delete(subjectId);
    }

    @Override
    public Programa addPrograma(Programa programa) throws EntityExistException {
        if (!programaDao.isOrUnique(programa, Programa_.programaCode)) {
            throw new EntityExistException("编码已存在");
        }
        // 同一个频道下不能有相同名称的栏目。不同频道下允许存在相同名称的栏目
        if (!programaDao.isUnique(programa, Programa_.programaName, Programa_.articleChannel)) {
            throw new EntityExistException("名称已存在");
        }
        programa.setCreateTime(new Date());
        return programaDao.save(programa);
    }

    @Override
    @Transactional(readOnly = true)
    public Programa getProgramaById(int programaId) {
        return programaDao.findOne(programaId);
    }

    @Override
    @Transactional(readOnly = true)
    public Programa getProgramaFetchChannelById(int programaId) {
        return programaDao.fetchChannelById(programaId);
    }

    @Override
    public Programa updatePrograma(Programa programa) throws EntityExistException {
        if (!programaDao.isOrUnique(programa, Programa_.programaCode)) {
            throw new EntityExistException("编码已存在");
        }
        // 同一个频道下不能有相同名称的栏目。不同频道下允许存在相同名称的栏目
        if (!programaDao.isUnique(programa, Programa_.programaName, Programa_.articleChannel)) {
            throw new EntityExistException("名称已存在");
        }
        Programa oldPrograma = programaDao.findOne(programa.getProgramaId());
        AttributeReplication.copying(programa, oldPrograma,
                Programa_.programaName, Programa_.programaCode, Programa_.articleChannel,
                Programa_.sortOrder,
                Programa_.templateName,
                Programa_.description,
                Programa_.seoKey,
                Programa_.seoTitle,
                Programa_.seoDescription,
                Programa_.operaImgPath,
                Programa_.enabled,
                Programa_.recommend);

        return oldPrograma;
    }

    @Override
    public void removeProgramaById(int programaId) {
        programaDao.delete(programaId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<SpecialSubject> getSpecialSubjectsByProgramaId(int programaId) {
        return programaDao.findSpecialSubjectsByProgramaId(programaId);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<ArticleChannel> getArticleChannelsForMobile() {
        return articleChannelDao.getArticleChannelsForMobile();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getArticleChannelCount() {
        return articleChannelDao.count();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getProgramaCount() {
        return programaDao.count();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getSubjectCount() {
        return specialSubjectDao.count();
    }

}
