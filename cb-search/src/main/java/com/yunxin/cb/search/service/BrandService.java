package com.yunxin.cb.search.service;

import com.yunxin.cb.search.vo.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrandService {

    /**
     * 添加品牌
     * @param brand
     */
    void addBrand(Brand brand);

    /**
     * 基于品牌名称进行搜索，返回分页
     */
    Page<Brand> search(String brandTitle, Pageable pageable);

    /**
     * 根据品牌id删除品牌
     * @param id
     */
    void deleteById(Integer id);
}
