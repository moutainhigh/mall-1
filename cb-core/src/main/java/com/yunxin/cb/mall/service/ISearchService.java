package com.yunxin.cb.mall.service;

import com.yunxin.cb.cms.entity.Article;
import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.exception.CommonException;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gonglei on 16/2/15.
 */
public interface ISearchService {

    void indexAllCommodities();

    List<Commodity> searchCommodities(String text);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Page<Commodity> pageSearchCommodities(String text, int page, int pageSize) throws CommonException;

    public void indexAllArticles();

    List<Article> searchArticles(String text);

    Page<Article> pageSearchArticles(String text, int page, int pageSize) throws CommonException;
}
