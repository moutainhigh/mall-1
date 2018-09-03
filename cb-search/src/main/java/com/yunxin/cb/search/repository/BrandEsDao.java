package com.yunxin.cb.search.repository;

import com.yunxin.cb.search.document.Brand;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BrandEsDao extends ElasticsearchRepository<Brand, String> {

}
