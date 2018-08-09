package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.vo.FinacialRepaymentVO;

import java.util.List;

public interface FinacialRepaymentService {

    public FinacialRepaymentVO add(FinacialRepaymentVO vo);

    public List<FinacialRepaymentVO> getByCustomerId(int customerId);

    public FinacialRepaymentVO getById(int repaymentId);
}
