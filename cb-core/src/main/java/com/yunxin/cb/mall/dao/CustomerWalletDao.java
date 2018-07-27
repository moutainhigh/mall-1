package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.CustomerWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
* @author gws
* @date 2018/7/24 20:12
* @param
* @return
*/
public interface CustomerWalletDao extends JpaRepository<CustomerWallet, Integer>, JpaSpecificationExecutor<CustomerWallet> {
    @Query("select c from CustomerWallet c left join fetch c.customer d where d.customerId=?1")
    public CustomerWallet getCustomerWalletByCustomer(int customerId);
}
