package com.yunxin.cb.search.service.impl;

import com.yunxin.cb.search.repository.BrandDao;
import com.yunxin.cb.search.service.BrandService;
import com.yunxin.cb.search.vo.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class BrandServiceImpl implements BrandService {

    @Resource
    private BrandDao brandDao;
    @Override
    public void addBrand(Brand brand) {
        brandDao.save(brand);
    }

    @Override
    public Page<Brand> search(String brandTitle, Pageable pageable) {
        return brandDao.findByBrandTitleLike(brandTitle, pageable);
    }

    @Override
    public void deleteById(Integer id) {
        brandDao.deleteById(id);
    }
}
