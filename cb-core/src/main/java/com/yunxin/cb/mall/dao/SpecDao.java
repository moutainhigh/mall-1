package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Spec;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by gonglei on 16/1/19.
 */
public interface SpecDao extends JpaRepository<Spec, Integer>, JpaSpecificationExecutor<Spec>, BaseDao<Spec> {

    List<Spec> findByCatalog_CatalogId(int catalogId);
}
