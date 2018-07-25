package com.yunxin.cb.search.repository;

import com.yunxin.cb.search.vo.Brand;
import com.yunxin.cb.search.vo.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CategoryDao extends ElasticsearchRepository<Category, Integer> {

    Page<Category> findByCategoryNameLike(String categoryName, Pageable pageable);
}
