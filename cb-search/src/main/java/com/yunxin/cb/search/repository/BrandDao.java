package com.yunxin.cb.search.repository;

import com.yunxin.cb.search.vo.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BrandDao extends ElasticsearchRepository<Brand, Integer> {

    Page<Brand> findByBrandTitleLike(String brandTitle, Pageable pageable);
}
