package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.vo.FinacialRepaymentVO;

import java.math.BigDecimal;
import java.util.List;

public interface FinacialRepaymentService {

    public void add(BigDecimal repayAmount,int coutomerId) throws Exception;

    public List<FinacialRepaymentVO> getByCustomerId(int customerId);

    public FinacialRepaymentVO getById(int repaymentId);
}
