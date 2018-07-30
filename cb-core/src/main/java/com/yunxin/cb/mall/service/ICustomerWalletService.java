package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.CustomerTradingRecord;
import com.yunxin.cb.mall.entity.CustomerWallet;
import com.yunxin.cb.mall.entity.meta.BusinessType;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

/**
 * @author wangteng
 */
public interface ICustomerWalletService {
    /**
     * 获取钱包
     * @param customerId
     * @return
     */
    public CustomerWallet findCustomerWallet(int customerId);

    /**
     * 更新钱包
     * @param customerId
     * @param ratios
     * @param remark
     * @param businessType
     * @return
     */
    public CustomerWallet updateCustomerWallet(int customerId,Double ratios,String remark,BusinessType businessType,int price);

    /**
     * 钱包列表
     * @param specification
     * @return
     */
    public  Page<CustomerWallet> pageCustomerWallets(PageSpecification<CustomerWallet> specification);

    public  Page<CustomerTradingRecord> pageCustomerTradingRecord(PageSpecification<CustomerTradingRecord> specification);




}
