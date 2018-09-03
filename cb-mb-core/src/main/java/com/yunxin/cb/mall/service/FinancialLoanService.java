package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinancialLoan;
import com.yunxin.cb.mall.vo.FinancialLoanVO;
import com.yunxin.cb.mall.vo.FinancialWalletVO;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;

import java.util.List;

public interface FinancialLoanService {

    FinancialLoanVO add(FinancialLoanVO vo, FinancialWalletVO financialWalletVO);

    List<FinancialLoanVO> getByCustomerIdAndType(int customerId);

    FinancialLoanVO getById(int repaymentId);

    FinancialLoanVO update(FinancialLoanVO vo);

    /**
     * 获取用户借款条数
     * @param customerId
     * @return
     */
    int countByCustomerId(int customerId);

    PageFinder<FinancialLoan> page(Query q);

}
