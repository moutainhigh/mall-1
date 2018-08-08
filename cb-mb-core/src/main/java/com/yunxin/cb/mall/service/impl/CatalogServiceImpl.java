package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.Catalog;
import com.yunxin.cb.mall.mapper.CatalogMapper;
import com.yunxin.cb.mall.service.CatalogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class CatalogServiceImpl implements CatalogService {
    @Resource
    private CatalogMapper catalogMapper;
    @Override
    public List<Catalog> selectAll() {
        return catalogMapper.selectAll();
    }
}
