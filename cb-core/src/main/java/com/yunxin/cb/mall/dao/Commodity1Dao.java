/**
 *
 */
package com.yunxin.cb.mall.dao;


import com.yunxin.cb.mall.vo.Commodity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author chengchenggang
 */
public interface Commodity1Dao extends ElasticsearchRepository<Commodity,Integer>{

    public List<Commodity> findByCommodityName(String commodityName);

}

