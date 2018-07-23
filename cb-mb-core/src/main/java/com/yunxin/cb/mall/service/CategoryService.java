package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 根据ID查询
     * @param categoryId
     * @return
     */
    Category selectByPrimaryKey(Integer categoryId);

    /**
     * 根据分类ID查询子类
     * @param parentCategoryId
     * @return
     */
    List<Category> selectByParentCategoryId(Integer parentCategoryId);
}
