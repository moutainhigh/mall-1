package com.yunxin.cb.search.service;

import com.yunxin.cb.search.document.Commodity;
import com.yunxin.cb.search.vo.SearchVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommodityService {

    /**
     * 添加商品
     * @param commodity
     */
    void addCommodity(Commodity commodity);

    /**
     * 根据商品id删除商品
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 基于keyword进行搜索，返回分页
     */
    Page<Commodity> keywordSearch(String keyword, Pageable pageable) throws Exception ;

    /**
     * 分类/条件搜索
     *
     * @param searchVo
     * @param pageable
     * @return
     * @throws Exception
     */
    Page<Commodity> categorySearch(SearchVo searchVo, Pageable pageable) throws Exception;

}
