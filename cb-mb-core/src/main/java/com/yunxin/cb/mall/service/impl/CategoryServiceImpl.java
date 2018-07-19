package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.Category;
import com.yunxin.cb.mall.mapper.CategoryMapper;
import com.yunxin.cb.mall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public Category selectByPrimaryKey(Integer categoryId) {
        return categoryMapper.selectByPrimaryKey(categoryId);
    }
}
