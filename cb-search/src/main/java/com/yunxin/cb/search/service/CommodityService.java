package com.yunxin.cb.search.service;

import com.yunxin.cb.search.vo.Commodity;
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
     * 基于content进行搜索，返回分页
     */
    Page<Commodity> search(String content, Pageable pageable);
}
