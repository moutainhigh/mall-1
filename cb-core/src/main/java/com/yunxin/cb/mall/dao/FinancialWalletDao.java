package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.FinancialWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @title: 负债记录Dao
 * @date: 2018/8/10 14:40
 */
public interface FinancialWalletDao extends JpaRepository<FinancialWallet, Integer>, JpaSpecificationExecutor<FinancialWallet> {

    @Query("select f from FinancialWallet f left join fetch f.customer c where c.customerId=?1")
    FinancialWallet findByCustomerId(int customerId);

    @Modifying
    @Query("update " +
            "FinancialWallet c " +
            "set " +
            "c.freezingAmount = :#{#wallet.freezingAmount}," +
            "c.creditAmount = :#{#wallet.creditAmount}," +
            "c.debtCar = :#{#wallet.debtCar}," +
            "c.debtCredit = :#{#wallet.debtCredit}," +
            "c.version = c.version + 1 " +
            "where " +
            "c.walletId= :#{#wallet.walletId} " +
            "and " +
            "c.version = :#{#wallet.version}")
    int updateFinancialWalletOnVersion(@Param("wallet") FinancialWallet wallet);
}
