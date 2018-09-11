package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.FinancialCashbackInsuranceDao;
import com.yunxin.cb.mall.dao.FinancialCashbackLogDao;
import com.yunxin.cb.mall.dao.FinancialCreditLineBillDao;
import com.yunxin.cb.mall.dao.FinancialFreezingBillDao;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.CapitalType;
import com.yunxin.cb.mall.entity.meta.CashbackLogState;
import com.yunxin.cb.mall.entity.meta.TransactionType;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.mall.service.IFinancialCashbackInsuranceService;
import com.yunxin.cb.mall.service.IFinancialLoanRepaymentService;
import com.yunxin.cb.mall.service.IFinancialWalletService;
import com.yunxin.cb.rb.entity.meta.LoanRepaymentType;
import com.yunxin.cb.system.entity.Profile;
import com.yunxin.cb.system.meta.ProfileName;
import com.yunxin.cb.system.service.IProfileService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FinancialCashbackInsuranceService implements IFinancialCashbackInsuranceService {

    @Resource
    private ICustomerService customerService;
    @Resource
    private IProfileService iProfileService;
    @Resource
    private FinancialCashbackInsuranceDao cashbackInsuranceDao;
    @Resource
    private FinancialCashbackLogDao financialCashbackLogDao;
    @Resource
    private IFinancialWalletService walletService;
    @Resource
    private FinancialFreezingBillDao freezingBillDao;
    @Resource
    private FinancialCreditLineBillDao creditLineBillDao;
    @Resource
    private IFinancialLoanRepaymentService loanRepaymentService;


    /**
     * @Author chenpeng
     * @Param customer 用户
     * @Param amount 保单金额
     * @Param insuranceNo 保单号
     * @Description 购买保险，增加保险返利计划
     * @Date 2018/9/11 11:17
     **/
    @Override
    @Transactional
    public void buyInsuranceCashback(Customer customer, BigDecimal amount, String insuranceNo) {

        // 返利金额 保单金额*50%
        BigDecimal insuranceAmount = amount.multiply(new BigDecimal(0.5));

        // 1.新增保险返利计划
        Profile Profile = iProfileService.getProfileByProfileName(ProfileName.INSURANCE_CASHBACK_TERM);
        Integer term;
        try {
            term = new Integer(Profile.getFileValue());
        } catch (Exception e) {
            term = new Integer(ProfileName.INSURANCE_CASHBACK_TERM.getDefaultValue());
        }
        BigDecimal singleRate = BigDecimal.ONE.divide(new BigDecimal(term), 4, RoundingMode.DOWN);  //抹掉省略的小数位
        BigDecimal rateArray[] = new BigDecimal[term];
        for (int i = 0; i < term; i++) {
            rateArray[i] = singleRate;
        }
        String rate = StringUtils.join(rateArray, ",");
        FinancialCashbackInsurance cashbackInsurance = new FinancialCashbackInsurance();
        cashbackInsurance.setCustomer(customer);
        cashbackInsurance.setInsuranceNo(insuranceNo);
        cashbackInsurance.setCashbackTotal(insuranceAmount);
        cashbackInsurance.setRate(rate);
        cashbackInsurance.setNextTerm(0);
        cashbackInsurance.setNextYear(LocalDate.now().getYear() + 1);
        cashbackInsurance.setState(FinancialCashbackInsurance.State.PROCESSING);
        cashbackInsurance.setCreateTime(new Date());
        cashbackInsuranceDao.save(cashbackInsurance);

        // 2.增加冻结金额，信用额度，修改钱包
        FinancialWallet wallet = walletService.getFinancialWalletByCustomerId(customer.getCustomerId());
        wallet.setFreezingAmount(wallet.getFreezingAmount().add(insuranceAmount));
        wallet.setCreditAmount(wallet.getCreditAmount().add(insuranceAmount));
        String remark = "购买保险,单号：" + insuranceNo + ",金额：" + amount + ",增加冻结金额和信用额度：" + insuranceAmount;
        boolean resultFlag = walletService.updateFinancialWalletOnVersion(wallet,
                insuranceAmount, OperationType.ADD, remark);
        if (!resultFlag) {
            throw new RuntimeException("内部错误(钱包)");
        }

        // 3.冻结金额变动记录
        FinancialFreezingBill freezingBill = new FinancialFreezingBill();
        freezingBill.setCustomer(customer);
        freezingBill.setType(CapitalType.ADD);
        freezingBill.setTransactionType(TransactionType.INSURANCE_PURCHASE);
        freezingBill.setTransactionDesc("购买保险50%");
        freezingBill.setAmount(insuranceAmount);
        freezingBill.setCreateTime(new Date());
        freezingBillDao.save(freezingBill);

        // 4.信用额度变动记录
        FinancialCreditLineBill creditLineBill = new FinancialCreditLineBill();
        creditLineBill.setCustomer(customer);
        creditLineBill.setType(CapitalType.ADD);
        creditLineBill.setTransactionType(TransactionType.INSURANCE_PURCHASE);
        creditLineBill.setTransactionDesc("购买保险50%");
        creditLineBill.setAmount(insuranceAmount);
        creditLineBill.setCreateTime(new Date());
        creditLineBillDao.save(creditLineBill);

    }

    /**
     * @Author chenpeng
     * @Param customer 用户
     * @Param amount 保单金额
     * @Description 感恩点赞。推荐人以及所有上级增加5%的信用额度
     * @Date 2018/9/11 15:06
     **/
    @Override
    @Transactional
    public void praiseInsurance(Customer customer, BigDecimal amount) {

        if (!customer.isPraise()) {
            List<Customer> listCustomer = customerService.findCustomerByLevelCode(customer.getLevelCode());
            if (listCustomer != null && listCustomer.size() > 0) {
                Profile Profile = iProfileService.getProfileByProfileName(ProfileName.GIVE_THE_THUMBS_UP);
                BigDecimal ration;
                try {
                    ration = new BigDecimal(Profile.getFileValue());
                } catch (Exception e) {
                    ration = new BigDecimal(ProfileName.GIVE_THE_THUMBS_UP.getDefaultValue());
                }
                BigDecimal rationPrice = amount.multiply(ration);
                for (Customer oneCustomer : listCustomer) {
                    // 推荐人以及所有上级增加5%的授信额度
                    // 1.增加信用额度，修改钱包
                    FinancialWallet wallet = walletService.getFinancialWalletByCustomerId(oneCustomer.getCustomerId());
                    wallet.setCreditAmount(wallet.getCreditAmount().add(rationPrice));
                    BigDecimal ratePercent = ration.multiply(new BigDecimal(100)).setScale(0, RoundingMode.DOWN);
                    String remark = "感恩点赞,推荐人以及所有上级增加"+ratePercent+"%的信用额度：" + rationPrice;
                    boolean resultFlag = walletService.updateFinancialWalletOnVersion(wallet,
                            rationPrice, OperationType.ADD, remark);
                    if (!resultFlag) {
                        throw new RuntimeException("内部错误(钱包)");
                    }
                    // 2.信用额度变动记录
                    FinancialCreditLineBill creditLineBill = new FinancialCreditLineBill();
                    creditLineBill.setCustomer(oneCustomer);
                    creditLineBill.setType(CapitalType.ADD);
                    creditLineBill.setTransactionType(TransactionType.PRAISE_INSURANCE);
                    creditLineBill.setTransactionDesc("感恩点赞,推荐人以及所有上级增加" + ratePercent + "%的信用额度");
                    creditLineBill.setAmount(rationPrice);
                    creditLineBill.setCreateTime(new Date());
                    creditLineBillDao.save(creditLineBill);
                }
            }
        }
    }

    /**
     * @Author chenpeng
     * @Description 保险返利，定时启动
     * @Date 2018/9/11 15:57
     **/
    @Override
    @Transactional
    public void processInsuranceCashback(FinancialCashbackInsurance cashbackInsurance) {

        String[] termsArray = StringUtils.split(cashbackInsurance.getRate(), ",");
        Integer terms = termsArray.length;     //返利总期数
        Integer currentTerm = cashbackInsurance.getNextTerm();  //当前返利期数

        // 1.验证返利记录正确性
        if (cashbackInsurance.getNextYear() != LocalDate.now().getYear()) {
            return;
        }

        // 2.修改返利信息和状态
        if (currentTerm >= terms - 1) {
            // 最后一期
            cashbackInsurance.setState(FinancialCashbackInsurance.State.COMPLETE);
        } else {
            cashbackInsurance.setNextTerm(currentTerm + 1);
            cashbackInsurance.setNextYear(cashbackInsurance.getNextYear() + 1);
        }
        cashbackInsuranceDao.save(cashbackInsurance);

        Customer customer = cashbackInsurance.getCustomer();
        // 返现金额
        BigDecimal amount = cashbackInsurance.getCashbackTotal().multiply(new BigDecimal(termsArray[currentTerm])).setScale(2, RoundingMode.DOWN);

        // 3.保险返利记录
        FinancialCashbackLog financialCashbackLog = new FinancialCashbackLog();
        financialCashbackLog.setCustomer(customer);
        financialCashbackLog.setCustomerName(StringUtils.isNotBlank(customer.getRealName()) ? customer.getRealName() : customer.getMobile());
        financialCashbackLog.setMobile(customer.getMobile());
        financialCashbackLog.setAmount(amount);
        financialCashbackLog.setState(CashbackLogState.FINISHED);
        financialCashbackLog.setOrderNo(cashbackInsurance.getInsuranceNo());
        financialCashbackLog.setCreateTime(new Date());
        financialCashbackLogDao.save(financialCashbackLog);

        // 4.还款or提现(包含钱包变动)
        loanRepaymentService.autoRepay(amount, customer.getCustomerId(), LoanRepaymentType.INSURANCE_REBATE_REPAYMENT, cashbackInsurance.getInsuranceNo());

        // 5.冻结金额变动记录
        FinancialFreezingBill freezingBill = new FinancialFreezingBill();
        freezingBill.setCustomer(customer);
        freezingBill.setType(CapitalType.SUBTRACT);
        freezingBill.setTransactionType(TransactionType.INSURANCE_REBATE);
        freezingBill.setTransactionDesc("保险返利");
        freezingBill.setAmount(amount);
        freezingBill.setCreateTime(new Date());
        freezingBillDao.save(freezingBill);
    }

    @Override
    public List<FinancialCashbackInsurance> getByProcessing() {

        return cashbackInsuranceDao.findAll(
                (root, criteriaQuery, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    predicates.add(criteriaBuilder.equal(root.get(FinancialCashbackInsurance_.state), FinancialCashbackInsurance.State.PROCESSING));
                    predicates.add(criteriaBuilder.equal(root.get(FinancialCashbackInsurance_.nextYear), LocalDate.now().getYear()));
                    criteriaQuery.orderBy(criteriaBuilder.asc(root.get(FinancialCashbackInsurance_.createTime)));
                    // 将所有条件用 and 联合起来
                    if (predicates.size() > 0) {
                        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                    }
                    return criteriaBuilder.conjunction();
                }
        );
    }
}
