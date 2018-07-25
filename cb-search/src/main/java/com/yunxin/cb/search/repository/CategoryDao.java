package com.yunxin.cb.search.repository;

import com.yunxin.cb.search.vo.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryDao  {

    Page<Category> findByCategoryNameLike(String categoryName, Pageable pageable);
}
