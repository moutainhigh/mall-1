package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.CommodityEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CommodityEsDao extends ElasticsearchRepository<CommodityEs,Integer> {

        public List<CommodityEs> findByCommodityName(String commodityName);
}
