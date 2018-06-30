package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.FilterItem;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by chenxing on 2016/1/20.
 */
public interface FilterItemDao extends JpaRepository<FilterItem, Integer>, JpaSpecificationExecutor<FilterItem>, BaseDao<FilterItem> {

    @Query("select fi from FilterItem  fi left join fi.commodityCategories cc where cc.cocaId=?1")
    List<FilterItem> findByCommodityCategoryId(int cocaId);
}
