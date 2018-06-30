package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.cms.dao.ArticleDao;
import com.yunxin.cb.cms.entity.Article;
import com.yunxin.cb.mall.dao.CommodityDao;
import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.entity.meta.PublishState;
import com.yunxin.cb.mall.exception.CommonException;
import com.yunxin.cb.mall.service.ISearchService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by gonglei on 16/2/15.
 */
@Service
@Transactional
public class SearchService implements ISearchService {

    @Resource
    private CommodityDao commodityDao;

    @Resource
    private ArticleDao articleDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public void indexAllCommodities() {
        Stream<Commodity> commodityStream = commodityDao.findByPublishState(PublishState.UP_SHELVES);
        commodityDao.indexAll(commodityStream);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Commodity> searchCommodities(String text) {
        return commodityDao.searchCommodities(text);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Commodity> pageSearchCommodities(String text, int page, int pageSize) throws CommonException {
        if (StringUtils.isBlank(text)) {
            throw new CommonException("参数text为空");
        }
        Pageable pageable = new PageRequest(page, pageSize);
        return commodityDao.pageSearchCommodities(text, pageable);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public void indexAllArticles() {
        Stream<Article> articleStream = articleDao.findAllByEnabled(true);
        articleDao.indexAll(articleStream);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Article> searchArticles(String text) {
        return articleDao.searchArticles(text);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Article> pageSearchArticles(String text, int page, int pageSize) throws CommonException {
        if (StringUtils.isBlank(text)) {
            throw new CommonException("参数text为空");
        }
        Pageable pageable = new PageRequest(page, pageSize);
        return articleDao.pageSearchArticles(text, pageable);
    }
}
