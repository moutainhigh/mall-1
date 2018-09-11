package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinancialWallet;
import com.yunxin.cb.mall.entity.OperationType;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public interface IFinancialWalletService {

    /**
     * @title: 分页查询负债记录
     * @param: [serviceRuleQuery]
     * @auther: pengcong
     * @date: 2018/8/10 14:46
     */
    Page<FinancialWallet> pageServiceFinancialWallet(PageSpecification<FinancialWallet> pageSpecification);

    FinancialWallet getFinancialWalletByCustomerId(Integer customerId);

    /**
     * @Author chenpeng
     * @Description 根据版本号修改钱包，增加钱包修改记录
     * @Date 2018/9/10 15:34
     **/
    boolean updateFinancialWalletOnVersion(FinancialWallet wallet, BigDecimal amount, OperationType type, String remark);
}
