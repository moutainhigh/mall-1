package com.yunxin.cb.search.repository;

import com.yunxin.cb.search.document.Spec;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SpecEsDao extends ElasticsearchRepository<Spec, String> {

}
