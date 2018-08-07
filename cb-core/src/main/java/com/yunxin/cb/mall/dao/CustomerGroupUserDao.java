package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.CustomerGroupUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomerGroupUserDao  extends JpaRepository<CustomerGroupUser, Integer>, JpaSpecificationExecutor<CustomerGroupUser> {

}
