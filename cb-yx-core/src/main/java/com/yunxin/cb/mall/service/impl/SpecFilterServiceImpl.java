package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.SpecFilter;
import com.yunxin.cb.mall.mapper.SpecFilterMapper;
import com.yunxin.cb.mall.service.SpecFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecFilterServiceImpl implements SpecFilterService {

    @Autowired
    private SpecFilterMapper specFilterMapper;

    @Override
    public List<SpecFilter> getEnableAll() {
        return specFilterMapper.selectEnableAll();
    }
}
