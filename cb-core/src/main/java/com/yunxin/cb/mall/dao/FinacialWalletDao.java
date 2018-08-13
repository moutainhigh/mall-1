package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Feedback;
import com.yunxin.cb.mall.entity.FinacialWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface FinacialWalletDao extends JpaRepository<FinacialWallet, Integer>, JpaSpecificationExecutor<FinacialWallet> {

    @Query("select f from FinacialWallet f left join fetch f.customer c where c.customerId=?1")
    FinacialWallet findFinacialWalletByCustomerId(int customerId);
}
