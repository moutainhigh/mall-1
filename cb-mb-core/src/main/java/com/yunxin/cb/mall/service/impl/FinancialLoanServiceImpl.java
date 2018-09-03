package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.FinacialCreditLineBill;
import com.yunxin.cb.mall.entity.FinancialLoan;
import com.yunxin.cb.mall.entity.meta.CapitalType;
import com.yunxin.cb.mall.entity.meta.LoanState;
import com.yunxin.cb.mall.entity.meta.LoanType;
import com.yunxin.cb.mall.entity.meta.TransactionType;
import com.yunxin.cb.mall.mapper.FinancialCreditLineBillMapper;
import com.yunxin.cb.mall.mapper.FinancialLoanMapper;
import com.yunxin.cb.mall.service.FinancialLoanService;
import com.yunxin.cb.mall.service.FinancialWalletService;
import com.yunxin.cb.mall.vo.FinancialLoanVO;
import com.yunxin.cb.mall.vo.FinancialWalletVO;
import com.yunxin.cb.util.CalendarUtils;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FinancialLoanServiceImpl implements FinancialLoanService {

    @Resource
    private FinancialLoanMapper financialLoanMapper;
    @Resource
    private FinancialWalletService financialWalletService;
    @Resource
    private FinancialCreditLineBillMapper financialCreditLineBillMapper;

    private static final Log log = LogFactory.getLog(FinancialLoanServiceImpl.class);
    /**
     * 添加
     * @author      likang
     * @param financialLoanVO
     * @param financialWalletVO
     * @return      com.yunxin.cb.mall.vo.FinancialLoanVO
     * @exception
     * @date        2018/8/9 14:32
     */
    @Override
    @Transactional
    public FinancialLoanVO add(FinancialLoanVO financialLoanVO, FinancialWalletVO financialWalletVO){
        log.info("add:"+ financialLoanVO);
        //借款金额小于保单额度（优先减少保单额度，再减少感恩额度）
//        if (financialLoanVO.getAmount().compareTo(financialWalletVO.getInsuranceAmount()) < 1) {
//            //钱包减少金额
//            financialWalletVO.setInsuranceAmount(financialWalletVO.getInsuranceAmount().subtract(financialLoanVO.getAmount()));
//            //借款金额区分
//            financialLoanVO.setInsuranceAmount(financialLoanVO.getAmount());
//            financialLoanVO.setCreditAmount(BigDecimal.ZERO);
//        } else { //借款金额大于保单额度但小于总额度
//            //借款金额区分
//            financialLoanVO.setInsuranceAmount(financialWalletVO.getInsuranceAmount());
//            financialLoanVO.setCreditAmount(financialLoanVO.getAmount().subtract(financialWalletVO.getInsuranceAmount()));
//            //钱包减少金额
//            financialWalletVO.setInsuranceAmount(BigDecimal.ZERO);
//            financialWalletVO.setCreditAmount(financialLoanVO.getAmount().subtract(financialWalletVO.getInsuranceAmount()));
//        }
        FinancialLoan financialLoan = new FinancialLoan();
        BeanUtils.copyProperties(financialLoan, financialLoanVO);
        //更新钱包额度
        financialWalletService.updateFinancialWallet(financialWalletVO);
        Date now = new Date();
        //添加借款记录
        financialLoan.setCreateTime(now);
        financialLoan.setState(LoanState.WAIT_LOAN);
        financialLoan.setType(LoanType.LOAN);
        //还款时间
        financialLoan.setFinalRepaymentTime(CalendarUtils.addMonth(now, financialLoan.getRepaymentTerm()));
        financialLoan.setLateFee(BigDecimal.ZERO);
        financialLoan.setOverdueNumer(0);
        financialLoan.setReadyAmount(BigDecimal.ZERO);
        financialLoan.setRepayAmount(financialLoan.getAmount().add(financialLoan.getInterest()));
        financialLoan.setTerm(1);//默认为1期
        financialLoanMapper.insert(financialLoan);
        //添加额度明细
        FinacialCreditLineBill finacialCreditLineBill = new FinacialCreditLineBill();
        finacialCreditLineBill.setCustomerId(financialLoan.getCustomerId());
        finacialCreditLineBill.setType(CapitalType.SUBTRACT);
        finacialCreditLineBill.setTransactionType(TransactionType.APPLY_LOAN);
        finacialCreditLineBill.setAmount(financialLoan.getInsuranceAmount());//只记录保单额度变更
        finacialCreditLineBill.setCreateTime(now);
        finacialCreditLineBill.setFinacialCreditLineId(financialLoan.getLoanId());
        finacialCreditLineBill.setTransactionDesc("贷款申请");
        financialCreditLineBillMapper.insert(finacialCreditLineBill);
        return financialLoanVO;
    }

    /**
     * 根据用户ID获取借款信息
     * @author      likang
     * @param customerId
     * @return      java.util.List<com.yunxin.cb.mall.vo.FinancialLoanVO>
     * @exception
     * @date        2018/8/9 14:38
     */
    @Override
    public List<FinancialLoanVO> getByCustomerIdAndType(int customerId){
        List<FinancialLoan> list = financialLoanMapper.selectByCustomerIdAndType(customerId);
        List<FinancialLoanVO> listVo = new ArrayList<>();
        list.stream().forEach(p ->{
            FinancialLoanVO vo = new FinancialLoanVO();
            BeanUtils.copyProperties(p, vo);
            listVo.add(vo);
        });
        return listVo;
    }

    /**
     * 获取一条记录
     * @author      likang
     * @param repaymentId
     * @return      com.yunxin.cb.mall.vo.FinancialLoanVO
     * @exception
     * @date        2018/8/9 14:44
     */
    @Override
    public FinancialLoanVO getById(int repaymentId){
        FinancialLoanVO vo = new FinancialLoanVO();
        FinancialLoan loan = financialLoanMapper.selectByPrimaryKey(repaymentId);
        BeanUtils.copyProperties(loan, vo);
        return vo;
    }

    /**
     * 更新
     * @author      likang
     * @param vo
     * @return      com.yunxin.cb.mall.vo.FinancialLoanVO
     * @exception
     * @date        2018/8/9 14:44
     */
    @Override
    public FinancialLoanVO update(FinancialLoanVO vo){
        log.info("update:"+vo);
        FinancialLoan financialLoan = new FinancialLoan();
        BeanUtils.copyProperties(financialLoan, vo);
        financialLoanMapper.updateByPrimaryKey(financialLoan);
        return vo;
    }

    /**
     * 获取用户借款条数
     * @param customerId
     * @return
     */
    @Override
    public int countByCustomerId(int customerId) {
        Query q = new Query();
        FinancialLoan financialLoan = new FinancialLoan();
        financialLoan.setCustomerId(customerId);
        List<LoanState> list = new ArrayList<LoanState>();
        list.add(LoanState.APPLY_SUCCESS);
        list.add(LoanState.SETTLE);
        financialLoan.setStateList(list);
        q.setData(financialLoan);
        return financialLoanMapper.count(q);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PageFinder<FinancialLoan> page(Query q) {
        try {
            //调用dao查询满足条件的分页数据
            List<FinancialLoan> list = financialLoanMapper.pageList(q);
            //调用dao统计满足条件的记录总数
            long rowCount = financialLoanMapper.count(q);
            //如list为null时，则改为返回一个空列表
            list = list == null ? new ArrayList<FinancialLoan>(0) : list;
            //将分页数据和记录总数设置到分页结果对象中
            PageFinder<FinancialLoan> page = new PageFinder<FinancialLoan>(q.getPageNo(), q.getPageSize(), rowCount);
            page.setData(list);
            return page;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
