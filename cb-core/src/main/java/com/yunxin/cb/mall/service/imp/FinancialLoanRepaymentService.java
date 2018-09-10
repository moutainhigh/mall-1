package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.*;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.*;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.mall.service.IFinancialLoanRepaymentService;
import com.yunxin.cb.mall.service.IFinancialLoanService;
import com.yunxin.cb.mall.service.IFinancialWalletService;
import com.yunxin.cb.rb.entity.meta.LoanRepaymentType;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FinancialLoanRepaymentService implements IFinancialLoanRepaymentService {

    private static final Logger logger = LoggerFactory.getLogger(FinancialLoanRepaymentService.class);

    @Resource
    private FinancialLoanRepaymentDao loanRepaymentDao;
    @Resource
    private IFinancialWalletService walletService;
    @Resource
    private IFinancialLoanService loanService;
    @Resource
    private ICustomerService customerService;
    @Resource
    private FinancialLoanBillDao loanBillDao;
    @Resource
    private FinancialLogBillDao logBillDao;
    @Resource
    private BankInfoDao bankInfoDao;
    @Resource
    private FinancialWithdrawDao withdrawDao;

    @Override
    public Page<FinancialLoanRepayment> pageServiceFinancialLoanRepayment(PageSpecification<FinancialLoanRepayment> pageSpecification,Integer loanRepayMentType) {
        pageSpecification.setCustomSpecification(new CustomSpecification<FinancialLoanRepayment>() {
            public void buildFetch(Root<FinancialLoanRepayment> root) {
                root.fetch(FinancialLoanRepayment_.customer, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<FinancialLoanRepayment> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                switch (loanRepayMentType){
                    case 0:
                        predicates.add(builder.equal(root.get(FinancialLoanRepayment_.loanRepaymentType), LoanRepaymentType.INSURANCE_REBATE_REPAYMENT));
                        break;
                    case 1:
                        predicates.add(builder.equal(root.get(FinancialLoanRepayment_.loanRepaymentType), LoanRepaymentType.COMMODITY_REIMBURSEMENT_REPAYMENT));
                        break;
                    case 2:
                        predicates.add(builder.equal(root.get(FinancialLoanRepayment_.loanRepaymentType), LoanRepaymentType.MANUAL_REIMBURSEMENT_REPAYMENT));
                        break;
                    case 3:
                        predicates.add(builder.equal(root.get(FinancialLoanRepayment_.loanRepaymentType), LoanRepaymentType.CAR_REBATE_REPAYMENT));
                        break;
                }
                query.orderBy(builder.desc(root.get(FinancialLoanRepayment_.createTime)));
            }
        });
        Page<FinancialLoanRepayment> pages = loanRepaymentDao.findAll(pageSpecification, pageSpecification.getPageRequest());
        return pages;
    }



    /**
     * @Author chenpeng
     * @Description 自动还款(返利和报账还款)
     * @Return 还款后剩余金额
     * @Date 2018/9/5 15:48
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BigDecimal autoRepay(BigDecimal repayAmount, Integer customerId, LoanRepaymentType type, String transactionNo) {

        logger.info("customerId=" + customerId + " autoRepay amount=" + repayAmount + " by "
                + type.toString() + ",transactionNo=" + transactionNo);

        Customer customer = customerService.getCustomerById(customerId);

        // 还款后剩余金额
        BigDecimal leftAvailableAmount = repayAmount;
        // 还汽车贷款的金额
        BigDecimal carRepayment = BigDecimal.ZERO;
        // 还借款的金额
        BigDecimal creditRepayment = BigDecimal.ZERO;

        // 用户钱包
        FinancialWallet wallet = walletService.getFinancialWalletByCustomerId(customerId);

        // 1.还汽车贷款
        if (wallet.getDebtCar().compareTo(BigDecimal.ZERO) > 0) {
            if (wallet.getDebtCar().compareTo(leftAvailableAmount) >= 0) {
                carRepayment = leftAvailableAmount;
                leftAvailableAmount = BigDecimal.ZERO;
            } else {
                carRepayment = wallet.getDebtCar();
                leftAvailableAmount = leftAvailableAmount.subtract(wallet.getDebtCar());
            }
            // TODO 还汽车贷款，汽车贷款还款记录
        }

        // 2.还负债
        BigDecimal[] result = this.repayLoan(customer, type, leftAvailableAmount, creditRepayment, carRepayment);
        leftAvailableAmount = result[0];
        creditRepayment = result[1];
        carRepayment = result[2];

        // 3.钱包变动
        if (carRepayment.compareTo(BigDecimal.ZERO) > 0 || creditRepayment.compareTo(BigDecimal.ZERO) > 0
                || LoanRepaymentType.INSURANCE_REBATE_REPAYMENT.equals(type)) {
            wallet.setDebtCar(wallet.getDebtCar().subtract(carRepayment));
            BigDecimal debtCredit = wallet.getDebtCredit().compareTo(creditRepayment) > 0
                    ? wallet.getDebtCredit().subtract(creditRepayment) : BigDecimal.ZERO;
            wallet.setDebtCredit(debtCredit);
            // 保险返利，还要减少冻结金额
            if (LoanRepaymentType.INSURANCE_REBATE_REPAYMENT.equals(type)) {
                BigDecimal freezingAmount = wallet.getFreezingAmount().compareTo(repayAmount) > 0
                        ? wallet.getFreezingAmount().subtract(repayAmount) : BigDecimal.ZERO;
                wallet.setFreezingAmount(freezingAmount);
                // TODO 冻结金额变动记录
            }
            String remark = type.getShortType() + "到账：" + repayAmount + ",自动还款：" + carRepayment.add(creditRepayment);
            boolean resultFlag = walletService.updateFinancialWalletOnVersion(wallet,
                    carRepayment.add(creditRepayment), OperationType.SUBTRACT, remark);
            if (!resultFlag) {
                throw new RuntimeException("内部错误(钱包)");
            }
        }

        // 4. 账单
        // 4.1 返利或者报账账单
        FinancialLogBill financialLogBill = new FinancialLogBill();
        financialLogBill.setCustomer(customer);
        financialLogBill.setCustomerName(customer.getRealName());
        financialLogBill.setAmount(repayAmount);
        financialLogBill.setType(OperationType.ADD);
        financialLogBill.setTransactionTypeOnPayment(type, false);
        financialLogBill.setPayTypeOnPayment(type);
        financialLogBill.setState(PayState.PROCESSED_SUCCESS);
        financialLogBill.setTransactionNo(transactionNo);
        String remark1 = type.getShortType() + "到账：" + repayAmount;
        financialLogBill.setTransactionDesc(remark1);
        financialLogBill.setCreateTime(new Date());
        logBillDao.save(financialLogBill);
        // 4.2 还款账单
        if (carRepayment.add(creditRepayment).compareTo(BigDecimal.ZERO) > 0) {
            FinancialLogBill bill = new FinancialLogBill();
            bill.setCustomer(customer);
            bill.setCustomerName(customer.getRealName());
            bill.setAmount(carRepayment.add(creditRepayment));
            bill.setType(OperationType.SUBTRACT);
            bill.setTransactionTypeOnPayment(type, true);
            bill.setPayType(FinancialLogPayType.LOAN);
            bill.setState(PayState.PROCESSED_SUCCESS);
            bill.setTransactionNo(transactionNo);
            String remark2 = type.getName() + "：" + carRepayment.add(creditRepayment);
            bill.setTransactionDesc(remark2);
            bill.setCreateTime(new Date());
            logBillDao.save(bill);
        }


        // 5.自动提现
        if (leftAvailableAmount.compareTo(BigDecimal.ZERO) > 0) {
            logger.info("customerId=" + customerId + " leftAvailableAmount=" + leftAvailableAmount + " auto withdraw...");
            List<BankInfo> list = bankInfoDao.selectAllByCustomerId(customerId);
            if (CollectionUtils.isEmpty(list)) {
                throw new RuntimeException("用户没有银行卡，提现失败");
            }
            FinancialWithdraw withdraw = new FinancialWithdraw();
            withdraw.setCustomer(customer);
            withdraw.setBank(list.get(0));
            withdraw.setAmount(leftAvailableAmount);
            withdraw.setRealAmount(leftAvailableAmount);
            withdraw.setChargeFee(BigDecimal.ZERO); // 提现手续费，默认0
            withdraw.setState(WithdrawState.TRANSFER);  // 自动提现，无审核流程，状态转帐中
            withdraw.setWithdrawTypeOnPayment(type);
            Date now = new Date();
            withdraw.setUpdateDate(now);
            withdraw.setApplyDate(now);
            withdrawDao.save(withdraw);
        }

        logger.info("customerId=" + customerId + " autoRepay success");

        return leftAvailableAmount;
    }

    private BigDecimal[] repayLoan(Customer customer, LoanRepaymentType type,
                                   BigDecimal leftAvailableAmount, BigDecimal creditRepayment, BigDecimal carRepayment) {

        if (leftAvailableAmount.compareTo(BigDecimal.ZERO) > 0) {
            List<FinancialLoan> loans = loanService.getByCustomerRepaying(customer.getCustomerId());
            for (FinancialLoan loan : loans) {
                if (leftAvailableAmount.compareTo(BigDecimal.ZERO) > 0) {
                    // 借款-对应的还款记录
                    FinancialLoanRepayment loanRepayment = new FinancialLoanRepayment();
                    loanRepayment.setCustomer(customer);
                    loanRepayment.setLoanId(loan.getLoanId());
                    loanRepayment.setLoanRepaymentType(type);
                    loanRepayment.setCreateTime(new Date());
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
                    loanRepaymentDao.save(loanRepayment);
                    // 2.3还完，修改还款状态
                    if (loan.getLeftInterest().compareTo(BigDecimal.ZERO) == 0 && loan.getLeftAmount().compareTo(BigDecimal.ZERO) == 0) {
                        loan.setRepaymentState(RepaymentState.ALREADY_REPAYMENT);
                    }
                    boolean resultFlag = loanService.updateFinancialLoanOnVersion(loan);
                    if (!resultFlag) {
                        throw new RuntimeException("内部错误(修改借款信息)");
                    }

                    logger.info("customerId=" + customer.getCustomerId() + " repayLoan repayAmount=" + loanRepayment.getRepayAmount() + ",repayCapital="
                            + loanRepayment.getRepayCapital() + ",repayInterest=" + loanRepayment.getRepayInterest());

                } else {
                    break;
                }
            }
            // 2.4 负债交易记录
            if (carRepayment.add(creditRepayment).compareTo(BigDecimal.ZERO) > 0) {
                FinancialLoanBill loanBill = new FinancialLoanBill();
                loanBill.setCustomer(customer);
                loanBill.setAmount(carRepayment.add(creditRepayment));
                loanBill.setType(CapitalType.SUBTRACT);
                loanBill.setTransactionTypeOnPayment(type);
                loanBill.setTransactionDesc(type.getName() + "：" + carRepayment.add(creditRepayment));
                loanBill.setCreateTime(new Date());
                loanBillDao.save(loanBill);
            }
        }

        BigDecimal[] result = new BigDecimal[3];
        result[0] = leftAvailableAmount;
        result[1] = creditRepayment;
        result[2] = carRepayment;

        return result;
    }

}
