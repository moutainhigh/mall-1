package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.vo.FinacialLoanVO;
import com.yunxin.cb.mall.vo.FinacialRepaymentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface FinacialLoanService {

    public FinacialLoanVO add(FinacialLoanVO vo);

    public List<FinacialLoanVO> getByCustomerId(int customerId);

    public FinacialLoanVO getById(int repaymentId);

    public FinacialLoanVO update(FinacialLoanVO vo);

}