package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinancialLoan;
import com.yunxin.cb.mall.vo.FinancialLoanVO;
import com.yunxin.cb.util.page.PageFinder;

import java.math.BigDecimal;
import java.util.List;

public interface FinancialLoanService {

    /**
     * @Author chenpeng
     * @Description  借款提交
     * @Date 2018/9/4 10:47
     **/
    void add(Integer customerId, Integer loanConfigId, Integer bankId, BigDecimal amount);

    List<FinancialLoanVO> getByCustomerIdAndType(int customerId);

    FinancialLoanVO getById(int repaymentId);

    FinancialLoanVO update(FinancialLoanVO vo);

    /**
     * 获取用户借款条数
     * @param customerId
     * @return
     */
    int countByCustomerId(Integer customerId);

    /**
     * @Author chenpeng
     * @Description 借款信息分页列表
     * @Date 2018/9/5 14:29
     **/
    PageFinder<FinancialLoan> pageByCustomer(Integer customerId, Integer pageNo, Integer pageSize);

    /**
     * @Author chenpeng
     * @Description 用户未还清的借款列表
     * @Date 2018/9/5 14:29
     **/
    List<FinancialLoan> getByCustomerRepaying(Integer customerId);
}
