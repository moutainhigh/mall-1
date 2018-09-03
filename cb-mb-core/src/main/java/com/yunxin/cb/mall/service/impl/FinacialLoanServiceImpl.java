package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.FinacialCreditLineBill;
import com.yunxin.cb.mall.entity.FinacialLoan;
import com.yunxin.cb.mall.entity.meta.CapitalType;
import com.yunxin.cb.mall.entity.meta.LoanState;
import com.yunxin.cb.mall.entity.meta.LoanType;
import com.yunxin.cb.mall.entity.meta.TransactionType;
import com.yunxin.cb.mall.mapper.FinacialCreditLineBillMapper;
import com.yunxin.cb.mall.mapper.FinacialLoanMapper;
import com.yunxin.cb.mall.service.FinacialLoanService;
import com.yunxin.cb.mall.service.FinancialWalletService;
import com.yunxin.cb.mall.vo.FinacialLoanVO;
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
public class FinacialLoanServiceImpl implements FinacialLoanService {

    @Resource
    private FinacialLoanMapper finacialLoanMapper;
    @Resource
    private FinancialWalletService financialWalletService;
    @Resource
    private FinacialCreditLineBillMapper finacialCreditLineBillMapper;

    private static final Log log = LogFactory.getLog(FinacialLoanServiceImpl.class);
    /**
     * 添加
     * @author      likang
     * @param finacialLoanVO
     * @param financialWalletVO
     * @return      com.yunxin.cb.mall.vo.FinacialLoanVO
     * @exception
     * @date        2018/8/9 14:32
     */
    @Override
    @Transactional
    public FinacialLoanVO add(FinacialLoanVO finacialLoanVO, FinancialWalletVO financialWalletVO){
        log.info("add:"+finacialLoanVO);
        //借款金额小于保单额度（优先减少保单额度，再减少感恩额度）
        if (finacialLoanVO.getAmount().compareTo(financialWalletVO.getInsuranceAmount()) < 1) {
            //钱包减少金额
            financialWalletVO.setInsuranceAmount(financialWalletVO.getInsuranceAmount().subtract(finacialLoanVO.getAmount()));
            //借款金额区分
            finacialLoanVO.setInsuranceAmount(finacialLoanVO.getAmount());
            finacialLoanVO.setCreditAmount(BigDecimal.ZERO);
        } else { //借款金额大于保单额度但小于总额度
            //借款金额区分
            finacialLoanVO.setInsuranceAmount(financialWalletVO.getInsuranceAmount());
            finacialLoanVO.setCreditAmount(finacialLoanVO.getAmount().subtract(financialWalletVO.getInsuranceAmount()));
            //钱包减少金额
            financialWalletVO.setInsuranceAmount(BigDecimal.ZERO);
            financialWalletVO.setCreditAmount(finacialLoanVO.getAmount().subtract(financialWalletVO.getInsuranceAmount()));
        }
        FinacialLoan finacialLoan = new FinacialLoan();
        BeanUtils.copyProperties(finacialLoan, finacialLoanVO);
        //更新钱包额度
        financialWalletService.updateFinancialWallet(financialWalletVO);
        Date now = new Date();
        //添加借款记录
        finacialLoan.setCreateTime(now);
        finacialLoan.setState(LoanState.WAIT_LOAN);
        finacialLoan.setType(LoanType.LOAN);
        //还款时间
        finacialLoan.setFinalRepaymentTime(CalendarUtils.addMonth(now, finacialLoan.getRepaymentTerm()));
        finacialLoan.setLateFee(BigDecimal.ZERO);
        finacialLoan.setOverdueNumer(0);
        finacialLoan.setReadyAmount(BigDecimal.ZERO);
        finacialLoan.setRepayAmount(finacialLoan.getAmount().add(finacialLoan.getInterest()));
        finacialLoan.setTerm(1);//默认为1期
        finacialLoanMapper.insert(finacialLoan);
        //添加额度明细
        FinacialCreditLineBill finacialCreditLineBill = new FinacialCreditLineBill();
        finacialCreditLineBill.setCustomerId(finacialLoan.getCustomerId());
        finacialCreditLineBill.setType(CapitalType.SUBTRACT);
        finacialCreditLineBill.setTransactionType(TransactionType.APPLY_LOAN);
        finacialCreditLineBill.setAmount(finacialLoan.getInsuranceAmount());//只记录保单额度变更
        finacialCreditLineBill.setCreateTime(now);
        finacialCreditLineBill.setFinacialCreditLineId(finacialLoan.getLoanId());
        finacialCreditLineBill.setTransactionDesc("贷款申请");
        finacialCreditLineBillMapper.insert(finacialCreditLineBill);
        return finacialLoanVO;
    }

    /**
     * 根据用户ID获取借款信息
     * @author      likang
     * @param customerId
     * @return      java.util.List<com.yunxin.cb.mall.vo.FinacialLoanVO>
     * @exception
     * @date        2018/8/9 14:38
     */
    @Override
    public List<FinacialLoanVO> getByCustomerIdAndType(int customerId){
        List<FinacialLoan> list = finacialLoanMapper.selectByCustomerIdAndType(customerId);
        List<FinacialLoanVO> listVo = new ArrayList<>();
        list.stream().forEach(p ->{
            FinacialLoanVO vo = new FinacialLoanVO();
            BeanUtils.copyProperties(p, vo);
            listVo.add(vo);
        });
        return listVo;
    }

    /**
     * 获取一条记录
     * @author      likang
     * @param repaymentId
     * @return      com.yunxin.cb.mall.vo.FinacialLoanVO
     * @exception
     * @date        2018/8/9 14:44
     */
    @Override
    public FinacialLoanVO getById(int repaymentId){
        FinacialLoanVO vo = new FinacialLoanVO();
        FinacialLoan loan = finacialLoanMapper.selectByPrimaryKey(repaymentId);
        BeanUtils.copyProperties(loan, vo);
        return vo;
    }

    /**
     * 更新
     * @author      likang
     * @param vo
     * @return      com.yunxin.cb.mall.vo.FinacialLoanVO
     * @exception
     * @date        2018/8/9 14:44
     */
    @Override
    public FinacialLoanVO update(FinacialLoanVO vo){
        log.info("update:"+vo);
        FinacialLoan finacialLoan = new FinacialLoan();
        BeanUtils.copyProperties(finacialLoan, vo);
        finacialLoanMapper.updateByPrimaryKey(finacialLoan);
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
        FinacialLoan finacialLoan = new FinacialLoan();
        finacialLoan.setCustomerId(customerId);
        List<LoanState> list = new ArrayList<LoanState>();
        list.add(LoanState.APPLY_SUCCESS);
        list.add(LoanState.SETTLE);
        finacialLoan.setStateList(list);
        q.setData(finacialLoan);
        return finacialLoanMapper.count(q);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PageFinder<FinacialLoan> page(Query q) {
        try {
            //调用dao查询满足条件的分页数据
            List<FinacialLoan> list = finacialLoanMapper.pageList(q);
            //调用dao统计满足条件的记录总数
            long rowCount = finacialLoanMapper.count(q);
            //如list为null时，则改为返回一个空列表
            list = list == null ? new ArrayList<FinacialLoan>(0) : list;
            //将分页数据和记录总数设置到分页结果对象中
            PageFinder<FinacialLoan> page = new PageFinder<FinacialLoan>(q.getPageNo(), q.getPageSize(), rowCount);
            page.setData(list);
            return page;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
