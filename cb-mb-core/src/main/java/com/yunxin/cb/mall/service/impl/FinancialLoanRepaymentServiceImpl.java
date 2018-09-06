package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.*;
import com.yunxin.cb.mall.mapper.*;
import com.yunxin.cb.mall.service.FinancialLoanRepaymentService;
import com.yunxin.cb.mall.service.FinancialLoanService;
import com.yunxin.cb.mall.service.FinancialWalletService;
import com.yunxin.cb.mall.vo.FinancialLoanRepaymentVO;
import com.yunxin.cb.mall.vo.FinancialWalletVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FinancialLoanRepaymentServiceImpl implements FinancialLoanRepaymentService {

    @Resource
    private FinancialLoanRepaymentMapper financialLoanRepaymentMapper;
    @Resource
    private FinancialLoanService financialLoanService;
    @Resource
    private FinancialLoanMapper financialLoanMapper;
    @Resource
    private FinancialLoanBillMapper financialLoanBillMapper;
    @Resource
    private FinancialWalletMapper financialWalletMapper;
    @Resource
    private FinancialWalletService financialWalletService;
    @Resource
    private FinancialLogMapper financialLogMapper;
    @Resource
    private CustomerMapper customerMapper;

    private static final Log log = LogFactory.getLog(FinancialLoanRepaymentServiceImpl.class);

    /**
     * @Author chenpeng
     * @Description 自动还款(返利和报账还款)
     * @Date 2018/9/5 15:48
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void autoRepay(BigDecimal repayAmount, Integer customerId, FinancialLoanRepayment.Type type) {

        // 还款后剩余金额
        BigDecimal leftAvailableAmount = repayAmount;
        // 还汽车贷款的金额
        BigDecimal carRepayment = BigDecimal.ZERO;
        // 还借款的金额
        BigDecimal creditRepayment = BigDecimal.ZERO;

        // 用户钱包
        FinancialWalletVO walletVO = financialWalletService.getFinancialWalletByCustomerId(customerId);

        // 1.还汽车贷款
        if (walletVO.getDebtCar().compareTo(BigDecimal.ZERO) > 0) {
            if (walletVO.getDebtCar().compareTo(leftAvailableAmount) >= 0) {
                carRepayment = leftAvailableAmount;
                leftAvailableAmount = BigDecimal.ZERO;
            } else {
                carRepayment = walletVO.getDebtCar();
                leftAvailableAmount = leftAvailableAmount.subtract(walletVO.getDebtCar());
            }
            // TODO 还汽车贷款，汽车贷款还款记录
        }

        // 2.还负债
        BigDecimal[] result = this.repayLoan(customerId, type, leftAvailableAmount, creditRepayment, carRepayment);
        leftAvailableAmount = result[0];
        creditRepayment = result[1];
        carRepayment = result[2];

        // 3.钱包变动
        if (carRepayment.compareTo(BigDecimal.ZERO) > 0 || creditRepayment.compareTo(BigDecimal.ZERO) > 0) {
            walletVO.setDebtCar(walletVO.getDebtCar().subtract(carRepayment));
            BigDecimal debtCredit = walletVO.getCreditAmount().compareTo(creditRepayment) > 0
                    ? walletVO.getCreditAmount().subtract(creditRepayment) : BigDecimal.ZERO;
            walletVO.setDebtCredit(debtCredit);
            String remark = "自动还款：" + carRepayment.add(creditRepayment);
            boolean resultFlag = financialWalletService.updateFinancialWallet(walletVO,
                    carRepayment.add(creditRepayment), OperationType.SUBTRACT, remark);
            if (!resultFlag) {
                throw new RuntimeException("内部错误(钱包)");
            }
        }

        // 4. 账单
        // 4.1 返利或者报账账单
        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        FinancialLogBill financialLogBill = new FinancialLogBill();
        financialLogBill.setCustomerId(customerId);
        financialLogBill.setCustomerName(customer.getRealName());
        financialLogBill.setAmount(repayAmount);
        financialLogBill.setType(OperationType.ADD);
        financialLogBill.setTransactionTypeOnPayment(type, false);
        financialLogBill.setPayTypeOnPayment(type);
        financialLogBill.setState(PayState.PROCESSED_SUCCESS);
        financialLogBill.setTransactionNo("transactionNo");
        financialLogBill.setTransactionDesc("返利或者报账账单");
        financialLogBill.setCreateTime(new Date());
        financialLogMapper.insert(financialLogBill);
        // 4.2 还款账单
        FinancialLogBill bill = new FinancialLogBill();
        bill.setCustomerId(customerId);
        bill.setCustomerName(customer.getRealName());
        bill.setAmount(carRepayment.add(creditRepayment));
        bill.setType(OperationType.SUBTRACT);
        bill.setTransactionTypeOnPayment(type, true);
        bill.setPayType(FiaciaLogPayType.LOAN);
        bill.setState(PayState.PROCESSED_SUCCESS);
        bill.setTransactionNo("transactionNo");
        bill.setTransactionDesc("自动还款账单");
        bill.setCreateTime(new Date());
        financialLogMapper.insert(bill);

        // TODO 5.自动提现
        if (leftAvailableAmount.compareTo(BigDecimal.ZERO) > 0) {

        }
    }

    /**
     * @Author chenpeng
     * @Description 手动还款（负债）
     * @Date 2018/9/5 19:05
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void repay(BigDecimal repayAmount, Integer customerId) {

        // 还款后剩余金额
        BigDecimal leftAvailableAmount = repayAmount;
        // 还借款的金额
        BigDecimal creditRepayment = BigDecimal.ZERO;

        // 用户钱包
        FinancialWalletVO walletVO = financialWalletService.getFinancialWalletByCustomerId(customerId);

        // 1.还负债
        BigDecimal[] result = this.repayLoan(customerId, FinancialLoanRepayment.Type.MANUAL_REPAYMENT,
                leftAvailableAmount, creditRepayment, BigDecimal.ZERO);
        leftAvailableAmount = result[0];
        creditRepayment = result[1];

        // 2.钱包变动
        if (creditRepayment.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal debtCredit = walletVO.getDebtCredit().compareTo(creditRepayment) > 0
                    ? walletVO.getDebtCredit().subtract(creditRepayment) : BigDecimal.ZERO;
            walletVO.setDebtCredit(debtCredit);
            String remark = "手动还款：" + creditRepayment;
            boolean resultFlag = financialWalletService.updateFinancialWallet(walletVO,
                    creditRepayment, OperationType.SUBTRACT, remark);
            if (!resultFlag) {
                throw new RuntimeException("内部错误(钱包)");
            }
        }

        // 3.还款账单
        Customer customer = customerMapper.selectByPrimaryKey(customerId);
        FinancialLogBill bill = new FinancialLogBill();
        bill.setCustomerId(customerId);
        bill.setCustomerName(customer.getRealName());
        bill.setAmount(creditRepayment);
        bill.setType(OperationType.SUBTRACT);
        bill.setTransactionType(FiaciaLogTransType.MANUAL_REPAYMENT);
        bill.setPayType(FiaciaLogPayType.LOAN);
        bill.setState(PayState.PROCESSED_SUCCESS);
        bill.setTransactionNo("transactionNo");
        bill.setTransactionDesc("手动还款");
        bill.setCreateTime(new Date());
        financialLogMapper.insert(bill);

        // 4.自动提现
        // TODO 手动还款多余金额提现？？？
        if (leftAvailableAmount.compareTo(BigDecimal.ZERO) > 0) {

        }

    }

    private BigDecimal[] repayLoan(Integer customerId, FinancialLoanRepayment.Type type,
                           BigDecimal leftAvailableAmount, BigDecimal creditRepayment, BigDecimal carRepayment) {

        if (leftAvailableAmount.compareTo(BigDecimal.ZERO) > 0) {
            List<FinancialLoan> loans = financialLoanMapper.selectByCustomerRepaying(customerId);
            for (FinancialLoan loan : loans) {
                if (leftAvailableAmount.compareTo(BigDecimal.ZERO) > 0) {
                    // 借款-对应的还款记录
                    FinancialLoanRepayment loanRepayment = new FinancialLoanRepayment();
                    loanRepayment.setCustomerId(customerId);
                    loanRepayment.setLoanId(loan.getLoanId());
                    loanRepayment.setType(type);
                    loanRepayment.setCreateTime(LocalDateTime.now());
                    loanRepayment.setRepayAmount(BigDecimal.ZERO);
                    loanRepayment.setRepayCapital(BigDecimal.ZERO);
                    loanRepayment.setRepayInterest(BigDecimal.ZERO);
                    // 2.1先还利息
                    if (loan.getLeftInterest().compareTo(BigDecimal.ZERO) > 0) {
                        BigDecimal leftInterest = loan.getLeftInterest();
                        // 本次还款利息
                        BigDecimal repayInterest = leftAvailableAmount.compareTo(leftInterest) > 0 ? leftInterest : leftAvailableAmount;
                        leftAvailableAmount = leftAvailableAmount.compareTo(leftInterest) > 0 ? leftAvailableAmount.subtract(leftInterest) : BigDecimal.ZERO;
                        creditRepayment = creditRepayment.add(repayInterest);
                        // 借款剩余利息
                        loan.setLeftInterest(leftInterest.subtract(repayInterest));
                        // 借款已还金额
                        loan.setReadyAmount(loan.getReadyAmount().add(repayInterest));
                        // 还款记录利息
                        loanRepayment.setRepayInterest(repayInterest);
                    }
                    // 2.2再还本金
                    if (leftAvailableAmount.compareTo(BigDecimal.ZERO) > 0
                            && loan.getLeftAmount().compareTo(BigDecimal.ZERO) > 0) {
                        BigDecimal leftAmount = loan.getLeftAmount();
                        // 本次还款本金
                        BigDecimal repayCapital = leftAvailableAmount.compareTo(leftAmount) > 0 ? leftAmount : leftAvailableAmount;
                        leftAvailableAmount = leftAvailableAmount.compareTo(leftAmount) > 0 ? leftAvailableAmount.subtract(leftAmount) : BigDecimal.ZERO;
                        creditRepayment = creditRepayment.add(repayCapital);
                        // 借款剩余本金
                        loan.setLeftAmount(leftAmount.subtract(repayCapital));
                        // 借款已还金额
                        loan.setReadyAmount(loan.getReadyAmount().add(repayCapital));
                        // 还款记录本金
                        loanRepayment.setRepayCapital(repayCapital);
                    }
                    loanRepayment.setRepayAmount(loanRepayment.getRepayCapital().add(loanRepayment.getRepayInterest()));
                    financialLoanRepaymentMapper.insert(loanRepayment);
                    // 2.3还完，修改还款状态
                    if (loan.getLeftInterest().equals(BigDecimal.ZERO) && loan.getLeftAmount().equals(BigDecimal.ZERO)) {
                        loan.setRepaymentState(RepaymentState.ALREADY_REPAYMENT);
                    }
                    boolean resultFlag = financialLoanMapper.updateOnVersion(loan) == 1;
                    if (!resultFlag) {
                        throw new RuntimeException("内部错误(修改借款信息)");
                    }
                } else {
                    break;
                }
            }
            // 2.4 负债交易记录
            if (carRepayment.add(creditRepayment).compareTo(BigDecimal.ZERO) > 0) {
                FinancialLoanBill loanBill = new FinancialLoanBill();
                loanBill.setCustomerId(customerId);
                loanBill.setAmount(carRepayment.add(creditRepayment));
                loanBill.setType(CapitalType.SUBTRACT);
                loanBill.setTransactionTypeOnPayment(type);
                loanBill.setTransactionDesc("还款：" + carRepayment.add(creditRepayment));
                loanBill.setCreateTime(LocalDateTime.now());
                financialLoanBillMapper.insert(loanBill);
            }
        }

        BigDecimal[] result = new BigDecimal[3];
        result[0] = leftAvailableAmount;
        result[1] = creditRepayment;
        result[2] = carRepayment;

        return result;
    }

    /**
     * 根据用户获取还款信息
     * @author      likang
     * @param customerId
     * @return      java.util.List<com.yunxin.cb.mall.vo.FinancialLoanRepaymentVO>
     * @exception
     * @date        2018/8/9 14:48
     */
    @Override
    public List<FinancialLoanRepaymentVO> getByCustomerId(int customerId){
        List<FinancialLoanRepayment> list = financialLoanRepaymentMapper.selectByCustomerId(customerId);
        List<FinancialLoanRepaymentVO> listVo = new ArrayList<>();
        list.forEach(p ->{
            FinancialLoanRepaymentVO vo = new FinancialLoanRepaymentVO();
            BeanUtils.copyProperties(p, vo);
            listVo.add(vo);
        });
        return listVo;
    }

    /**
     * 根据id获取还款信息
     * @author      likang
     * @param repaymentId
     * @return      com.yunxin.cb.mall.vo.FinancialLoanRepaymentVO
     * @exception
     * @date        2018/8/9 14:50
     */
    @Override
    public FinancialLoanRepaymentVO getById(int repaymentId){
        FinancialLoanRepaymentVO vo = new FinancialLoanRepaymentVO();
        FinancialLoanRepayment loan = financialLoanRepaymentMapper.selectByPrimaryKey(repaymentId);
        BeanUtils.copyProperties(loan, vo);
        return vo;
    }
}
