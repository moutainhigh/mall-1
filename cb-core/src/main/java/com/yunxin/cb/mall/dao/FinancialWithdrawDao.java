package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.FinancialWithdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @title: 提现记录Dao
 * @auther: eleven
 * @date: 2018/8/10 14:40
 */
public interface FinancialWithdrawDao extends JpaRepository<FinancialWithdraw, Integer>, JpaSpecificationExecutor<FinancialWithdraw> {

    @Modifying
    @Query("update FinancialWithdraw f set f.state = ?1 where f.withdrawId in ?2")
    int tansfer(String ids);
}
