package com.yunxin.cb.search.repository;

import com.yunxin.cb.search.document.PriceSection;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PriceSectionEsDao extends ElasticsearchRepository<PriceSection, String> {

}
