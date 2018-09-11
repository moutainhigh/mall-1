package com.yunxin.cb.mall.service;

import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.mall.entity.FinancialLoan;
import com.yunxin.cb.mall.entity.meta.LoanState;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface IFinancialLoanService {

    /**
     * @title: 分页查询贷款记录
     * @param: [serviceRuleQuery]
     * @auther: pengcong
     * @date: 2018/8/10 14:46
     */
    Page<FinancialLoan> pageServiceFinancialLoan(PageSpecification<FinancialLoan> pageSpecification,int state);

    /**
     * 根据ID查询详情
     */
    FinancialLoan getFinancialLoanDetailById(Integer loanId);

    /**
     * 修改状态
     */
    Map<String,Object> undateState(Integer loanId,LoanState state,User user)throws Exception;

    /**
     * 审核
     */
    void updateFinancialLoan(FinancialLoan financialLoan)throws Exception;

    /**
     * @Author chenpeng
     * @Description 获取用户正在还的贷款，按借款时间正序排序
     * @Date 2018/9/10 11:38
     **/
    List<FinancialLoan> getByCustomerRepaying(Integer customerId);

    /**
     * @Author chenpeng
     * @Description 根据版本号修改借款信息
     * @Date 2018/9/10 15:24
     **/
    boolean updateFinancialLoanOnVersion(FinancialLoan loan);
}
