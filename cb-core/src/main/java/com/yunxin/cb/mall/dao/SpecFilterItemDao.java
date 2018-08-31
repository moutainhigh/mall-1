package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.SpecFilterItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by chenpeng on 2018年8月20日
 */
public interface SpecFilterItemDao extends JpaRepository<SpecFilterItem, Integer>, JpaSpecificationExecutor<SpecFilterItem> {

}
