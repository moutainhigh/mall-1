package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinancialWithdraw;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

public interface IFinancialWithdrawService {

    /**
     * @title: 分页查询提现记录
     * @param: [serviceRuleQuery]
     * @return: org.springframework.data.domain.Page<com.yunxin.cb.mall.entity.FinancialWithdraw>
     * @auther: eleven
     * @date: 2018/8/10 14:46
     */
    Page<FinancialWithdraw> pageServiceFinancialWithdraw(PageSpecification<FinancialWithdraw> pageSpecification);

    /**
     * @title: 转账确认
     * @param: ids
     * @return: com.yunxin.cb.mall.entity.FinancialWithdraw
     * @auther: eleven
     * @date: 2018/8/10 14:44
     */
    int tansfer(String ids) throws Exception;
}
