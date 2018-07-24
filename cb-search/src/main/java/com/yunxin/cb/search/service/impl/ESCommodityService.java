package com.yunxin.cb.search.service.impl;



import com.yunxin.cb.search.repository.CommodityEsDao;
import com.yunxin.cb.search.service.IESCommodityService;
import com.yunxin.cb.search.vo.CommodityEs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author gonglei
 */
@Service
@EnableElasticsearchRepositories
public class ESCommodityService implements IESCommodityService {

    private static final Logger logger = LoggerFactory.getLogger(ESCommodityService.class);

    @Resource
    private CommodityEsDao commodityEsDao;



    public void addCommodity()
    {
        CommodityEs commodity=new CommodityEs();
        commodity.setCommodityId(7);
        commodity.setCommodityName("统一绿茶2");
        commodityEsDao.save(commodity);

        commodity=new CommodityEs();
        commodity.setCommodityId(8);
        commodity.setCommodityName("加多宝2");
        commodityEsDao.save(commodity);


        commodity=new CommodityEs();
        commodity.setCommodityId(9);
        commodity.setCommodityName("可口可乐2");
        commodityEsDao.save(commodity);
    }

    public Iterable<CommodityEs> findAll()
    {
        return commodityEsDao.findAll();
    }

    public List<CommodityEs> findByCommodityName(String commodityName)
    {
        return commodityEsDao.findByCommodityName(commodityName);
    }



}
