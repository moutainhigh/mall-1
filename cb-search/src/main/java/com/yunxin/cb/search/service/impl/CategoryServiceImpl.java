package com.yunxin.cb.search.service.impl;

import com.yunxin.cb.search.repository.CategoryDao;
import com.yunxin.cb.search.service.CategoryService;
import com.yunxin.cb.search.vo.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryDao categoryDao;
    @Override
    public void addCategory(Category category) {
        categoryDao.save(category);
    }

    @Override
    public Page<Category> search(String categoryName, Pageable pageable) {
        return categoryDao.findByCategoryNameLike(categoryName,pageable);
    }

    @Override
    public void deleteById(Integer id) {
        categoryDao.deleteById(id);
    }
}
