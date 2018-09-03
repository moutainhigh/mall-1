package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinacialLoan;
import com.yunxin.cb.mall.vo.FinacialLoanVO;
import com.yunxin.cb.mall.vo.FinancialWalletVO;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;

import java.util.List;

public interface FinacialLoanService {

    public FinacialLoanVO add(FinacialLoanVO vo, FinancialWalletVO financialWalletVO);

    public List<FinacialLoanVO> getByCustomerIdAndType(int customerId);

    public FinacialLoanVO getById(int repaymentId);

    public FinacialLoanVO update(FinacialLoanVO vo);

    /**
     * 获取用户借款条数
     * @param customerId
     * @return
     */
    public int countByCustomerId(int customerId);

    public PageFinder<FinacialLoan> page(Query q);

}
