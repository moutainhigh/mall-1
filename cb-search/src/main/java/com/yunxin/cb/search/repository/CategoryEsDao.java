package com.yunxin.cb.search.repository;

import com.yunxin.cb.search.document.Category;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CategoryEsDao extends ElasticsearchRepository<Category, String> {


}
