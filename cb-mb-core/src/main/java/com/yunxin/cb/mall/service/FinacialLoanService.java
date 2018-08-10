package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.vo.FinacialLoanVO;

import java.util.List;

public interface FinacialLoanService {

    public FinacialLoanVO add(FinacialLoanVO vo);

    public List<FinacialLoanVO> getByCustomerIdAndType(int customerId);

    public FinacialLoanVO getById(int repaymentId);

    public FinacialLoanVO update(FinacialLoanVO vo);

    /**
     * 获取用户借款条数
     * @param customerId
     * @return
     */
    public int countByCustomerId(int customerId);

}
