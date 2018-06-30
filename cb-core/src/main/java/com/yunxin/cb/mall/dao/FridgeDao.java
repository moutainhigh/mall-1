package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Fridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FridgeDao extends JpaRepository<Fridge, Integer>, JpaSpecificationExecutor<Fridge> {

    List<Fridge> findByCustomer_CustomerIdOrderByCreateTimeDesc(int customerId);
}
