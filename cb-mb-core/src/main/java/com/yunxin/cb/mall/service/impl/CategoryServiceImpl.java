package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.Category;
import com.yunxin.cb.mall.mapper.CategoryMapper;
import com.yunxin.cb.mall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public Category selectByPrimaryKey(Integer categoryId) {
        return categoryMapper.selectByPrimaryKey(categoryId);
    }

    @Override
    public List<Category> selectByParentCategoryId(Integer parentCategoryId) {
        return categoryMapper.selectByParentCategoryId(parentCategoryId);
    }

    /**
     * 根据分类NO查询子类
     * @param parentCategoryId
     * @return
     */
    @Override
    public Category selectByParentCategoryNo(String categoryNo){
        return categoryMapper.selectByParentCategoryNo(categoryNo);
    }
}
