package com.yunxin.cb.cms.service;

import com.yunxin.cb.cms.entity.ArticleChannel;
import com.yunxin.cb.cms.entity.Programa;
import com.yunxin.cb.cms.entity.SpecialSubject;
import com.yunxin.cb.cms.entity.meta.ArticleChannelType;
import com.yunxin.cb.mall.exception.CommonException;
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
public interface IProgramaService {

    ArticleChannel addArticleChannel(ArticleChannel articleChannel) throws EntityExistException;

    SpecialSubject addSpecialSubject(SpecialSubject specialSubject) throws EntityExistException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Page<SpecialSubject> pageSpecialSubjects(PageSpecification<SpecialSubject> pageSpecification);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    SpecialSubject getSpecialSubjectById(int subjectId);

    @Transactional(readOnly = true)
    List<ArticleChannel> getArticleChannels();

    @Transactional(readOnly = true)
    List<ArticleChannel> getArticleChannels(boolean enabled);

    List<ArticleChannel> getArticleChannels(ArticleChannelType channelType);

    @Transactional(readOnly = true)
    List<Programa> getProgramas(boolean enabled);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<SpecialSubject> getSpecialSubjects(boolean enabled);

    @Transactional(readOnly = true)
    List<SpecialSubject> getSpecialSubjects(boolean enabled, boolean recommend);


    @Transactional(readOnly = true)
    List<SpecialSubject> getSpecialSubjects(Programa programa, boolean enabled, boolean recommend, int limit);

    @Transactional(readOnly = true)
    List<Programa> getProgramasByChannelId(int channelId);

    @Transactional(readOnly = true)
    List<Programa> getProgramasByChannelCode(String channelCode);

    ArticleChannel updateArticleChannel(ArticleChannel articleChannel) throws EntityExistException;

    SpecialSubject updateSpecialSubject(SpecialSubject specialSubject) throws EntityExistException;

    ArticleChannel getArticleChannelById(int articleCategoryId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    ArticleChannel getArticleChannelByCode(String channelCode);

    /**
     * 逻辑删除
     *
     * @param articleCategoryId
     */
    void enableArticleChannelById(int articleCategoryId, boolean enabled);

    void enableSpecialSubjectById(int subjectId, boolean enabled);

    void removeArticleChannelById(int channelId) throws CommonException;

    void removeSpecialSubjectById(int subjectId);

    Programa addPrograma(Programa programa) throws EntityExistException;

    @Transactional(readOnly = true)
    Programa getProgramaById(int programaId);

    @Transactional(readOnly = true)
    Programa getProgramaFetchChannelById(int programaId);

    Programa updatePrograma(Programa programa) throws EntityExistException;

    void removeProgramaById(int programaId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<SpecialSubject> getSpecialSubjectsByProgramaId(int programaId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long getArticleChannelCount();

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long getProgramaCount();

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long getSubjectCount();

    public List<ArticleChannel> getArticleChannelsForMobile();
}
