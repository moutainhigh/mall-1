package com.yunxin.cb.search.repository;

import com.yunxin.cb.search.vo.CommodityEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CommodityEsDao extends ElasticsearchRepository<CommodityEs,Integer> {

        public List<CommodityEs> findByCommodityName(String commodityName);

}
