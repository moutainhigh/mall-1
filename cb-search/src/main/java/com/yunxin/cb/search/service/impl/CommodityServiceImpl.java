package com.yunxin.cb.search.service.impl;


import com.yunxin.cb.search.document.Commodity;
import com.yunxin.cb.search.repository.CommodityDao;
import com.yunxin.cb.search.service.CommodityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tanggangyi
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    private static final Logger logger = LoggerFactory.getLogger(CommodityServiceImpl.class);

    @Resource
    private CommodityDao commodityDao;

    @Override
    public void addCommodity(Commodity commodity) {
        commodityDao.save(commodity);
    }

    @Override
    public Page<Commodity> search(String content, Pageable pageable) {
        Page<Commodity> page = commodityDao.findByCommodityNameLike(content, pageable);
        Page<Commodity> page2 =commodityDao.findAll(pageable);
        return page2;
    }

    public Iterable<Commodity> findAll() {
        Iterable<Commodity> iterable = commodityDao.findAll();
        return iterable ;

    }

    @Override
    public void deleteById(Integer id) {
        commodityDao.deleteById(id);
    }

}