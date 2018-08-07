package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.CustomerGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomerGroupDao extends JpaRepository<CustomerGroup, Integer>, JpaSpecificationExecutor<CustomerGroup> {
}
