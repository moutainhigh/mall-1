package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.CustomerWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author gws
* @date 2018/7/24 20:12
* @param
* @return
*/
public interface CustomerWalletDao extends JpaRepository<CustomerWallet, Integer>, JpaSpecificationExecutor<CustomerWallet> {

}
