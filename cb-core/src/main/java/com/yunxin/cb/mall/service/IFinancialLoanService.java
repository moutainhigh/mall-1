package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinancialLoan;
import com.yunxin.cb.mall.entity.meta.LoanState;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IFinancialLoanService {

    /**
     * @title: 分页查询贷款记录
     * @param: [serviceRuleQuery]
     * @auther: eleven
     * @date: 2018/8/10 14:46
     */
    public Page<FinancialLoan> pageServiceFinancialLoan(PageSpecification<FinancialLoan> pageSpecification,int state);

    /**
     * 根据ID查询详情
     */
    public FinancialLoan getFinancialLoanDetailById(Integer loanId);

    /**
     * 修改状态
     */
    public Map<String,Object> undateState(Integer loanId,LoanState state)throws Exception;

    /**
     * 审核
     */
    public void updateFinancialLoan(FinancialLoan financialLoan)throws Exception;
}
