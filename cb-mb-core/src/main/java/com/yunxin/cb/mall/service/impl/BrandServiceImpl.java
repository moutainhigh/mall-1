package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.Brand;
import com.yunxin.cb.mall.mapper.BrandMapper;
import com.yunxin.cb.mall.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;
    @Override
    public Brand selectByPrimaryKey(Integer floorId) {
        return brandMapper.selectByPrimaryKey(floorId);
    }

    @Override
    public List<Brand> selectAll() {
        return brandMapper.selectAll();
    }
}
