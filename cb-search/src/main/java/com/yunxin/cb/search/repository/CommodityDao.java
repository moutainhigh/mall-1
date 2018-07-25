package com.yunxin.cb.search.repository;

import com.yunxin.cb.search.vo.Commodity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CommodityDao extends ElasticsearchRepository<Commodity, Integer> {


    Page<Commodity> findByCommodityNameLike(String content, Pageable pageable);

}
