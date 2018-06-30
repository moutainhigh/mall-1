package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Integral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IntegralDao extends JpaRepository<Integral, Integer>, JpaSpecificationExecutor<Integral> {


    public List<Integral> findByCustomer_CustomerId(int customerId);

}
