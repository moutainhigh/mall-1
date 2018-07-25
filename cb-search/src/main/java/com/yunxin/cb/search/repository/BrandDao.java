package com.yunxin.cb.search.repository;

import com.yunxin.cb.search.vo.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrandDao  {

    Page<Brand> findByBrandTitleLike(String brandTitle, Pageable pageable);
}
