package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Category;

public interface CategoryService {
    /**
     * 根据ID查询
     * @param categoryId
     * @return
     */
    Category selectByPrimaryKey(Integer categoryId);
}
