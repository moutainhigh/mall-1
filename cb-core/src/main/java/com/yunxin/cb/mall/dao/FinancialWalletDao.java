package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.FinancialWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @title: 负债记录Dao
 * @date: 2018/8/10 14:40
 */
public interface FinancialWalletDao extends JpaRepository<FinancialWallet, Integer>, JpaSpecificationExecutor<FinancialWallet> {
}
