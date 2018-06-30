package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.CouponSchema;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Aidy_He on 16/1/25.
 */
public interface CouponSchemaDao extends JpaRepository<CouponSchema, Integer>, JpaSpecificationExecutor<CouponSchema>, BaseDao<CouponSchema> {
}
