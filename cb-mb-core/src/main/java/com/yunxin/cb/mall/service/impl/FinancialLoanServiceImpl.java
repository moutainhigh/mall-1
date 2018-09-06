package com.yunxin.cb.mall.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.*;
import com.yunxin.cb.mall.mapper.FinancialCreditLineBillMapper;
import com.yunxin.cb.mall.mapper.FinancialLoanConfigMapper;
import com.yunxin.cb.mall.mapper.FinancialLoanMapper;
import com.yunxin.cb.mall.restful.ResponseResult;
import com.yunxin.cb.mall.restful.meta.Result;
import com.yunxin.cb.mall.service.*;
import com.yunxin.cb.mall.vo.BankInfoVO;
import com.yunxin.cb.mall.vo.FinancialWalletVO;
import com.yunxin.cb.util.page.PageFinder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FinancialLoanServiceImpl implements FinancialLoanService {

    @Resource
    private CustomerService customerService;
    @Resource
    private BankInfoService bankInfoService;
    @Resource
    private FinancialWalletService financialWalletService;
    @Resource
    private ProfileService profileService;
    @Resource
    private FinancialLoanMapper financialLoanMapper;
    @Resource
    private FinancialLoanConfigMapper financialLoanConfigMapper;
    @Resource
    private FinancialCreditLineBillMapper financialCreditLineBillMapper;

    private static final Log log = LogFactory.getLog(FinancialLoanServiceImpl.class);
    

    /**
     * @Author chenpeng
     * @Description 申请贷款
     * @Date 2018/9/4 18:28 
     **/
    @Override
    @Transactional
    public ResponseResult add(Integer customerId, Integer loanConfigId, Integer bankId, BigDecimal amount) {

        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null || customer.getAuthFlag() != 1) {
//            throw new RuntimeException("未实名认证");
            return new ResponseResult<>(Result.FAILURE, "未实名认证");
        }
        BankInfoVO bankInfoVO = bankInfoService.selectByPrimaryKey(bankId, customerId);
        if (bankInfoVO == null) {
//            throw new RuntimeException("您选择的银行卡有误");
            return new ResponseResult<>(Result.FAILURE, "您选择的银行卡有误");
        }

        // 最高可贷金额,判断额度是否满足贷款
        FinancialWalletVO walletVO = financialWalletService.getFinancialWalletByCustomerId(customerId);
        BigDecimal totalAmount = walletVO.getCreditAmount();
        if (amount.compareTo(totalAmount) > 0) {
//            throw new RuntimeException("您的可贷金额不足");
            return new ResponseResult<>(Result.FAILURE, "您的可贷金额不足");
        }

        // 判断是否超过最多次数
        Profile profile = profileService.getProfileByName(ProfileState.MAX_LOAN_NUM.name());
        if (profile != null) {
            int maxCount = Integer.valueOf(profile.getFileValue());
            // 查询审核通过的我的借款次数
            int count = this.countByCustomerId(customerId);
            if (count >= maxCount) {
//                throw new RuntimeException("您已经贷了" + maxCount + "次款了，不能再贷了");
                return new ResponseResult<>(Result.FAILURE,"您已经贷了" + maxCount + "次款了，不能再贷了");
            }
        }

        // 贷款期限
        FinancialLoanConfig financialLoanConfig = financialLoanConfigMapper.selectByPrimaryKey(loanConfigId);
        if (financialLoanConfig == null) {
//            throw new RuntimeException("您选择的贷款期限有误");
            return new ResponseResult<>(Result.FAILURE,"您选择的贷款期限有误");
        }

        // 钱包变动
        walletVO.setCreditAmount(walletVO.getCreditAmount().subtract(amount));
        String remark = "申请借款：" + amount + ",减少对应信用额度";
        boolean resultFlag = financialWalletService.updateFinancialWallet(walletVO, amount, OperationType.SUBTRACT, remark);
        if (!resultFlag) {
            throw new RuntimeException("内部错误(钱包)");
        }

        // 贷款申请
        FinancialLoan financialLoan = new FinancialLoan();
        financialLoan.setCustomerId(customerId);
        financialLoan.setBankId(bankId);
        financialLoan.setAmount(amount);
        financialLoan.setTerm(financialLoanConfig.getTerm());
        financialLoan.setInterestRate(financialLoanConfig.getInterestRate());
        BigDecimal interest = amount.multiply(financialLoanConfig.getInterestRate()).setScale(2, RoundingMode.HALF_UP);
        financialLoan.setInterest(interest);    //利息
        financialLoan.setType(LoanType.LOAN);
        LocalDate finalDate = LocalDate.now().plusMonths(financialLoanConfig.getTerm());
        financialLoan.setFinalRepaymentTime(finalDate);     //最后还款日
        financialLoan.setVersion(0);
        financialLoan.setRepayAmount(amount.add(interest));     //应还总额 = 本金+利息
        financialLoan.setReadyAmount(BigDecimal.ZERO);
        financialLoan.setLeftAmount(amount);
        financialLoan.setLeftInterest(interest);
        financialLoan.setOverdueNumber(0);
        financialLoan.setLateFee(BigDecimal.ZERO);
        financialLoan.setState(LoanState.WAIT_AUDIT);
        financialLoan.setRepaymentState(RepaymentState.NON_REPAYMENT);
        financialLoan.setCreateTime(LocalDateTime.now());
        resultFlag = financialLoanMapper.insert(financialLoan) == 1;
        if (!resultFlag) {
            throw new RuntimeException("内部错误(借款提交)");
        }

        // 信用额度变动
        FinancialCreditLineBill financialCreditLineBill = new FinancialCreditLineBill();
        financialCreditLineBill.setCustomerId(customerId);
        financialCreditLineBill.setType(CapitalType.SUBTRACT);
        financialCreditLineBill.setTransactionType(TransactionType.APPLY_LOAN);
        financialCreditLineBill.setAmount(amount);
        financialCreditLineBill.setTransactionDesc("申请贷款：" + amount + ",减少对应信用额度");
        financialCreditLineBill.setCreateTime(LocalDateTime.now());
        resultFlag = financialCreditLineBillMapper.insert(financialCreditLineBill) == 1;
        if (!resultFlag) {
            throw new RuntimeException("内部错误(借款提交后信用额度变动)");
        }

        return new ResponseResult<>(Result.SUCCESS);
    }

    /**
     * 获取用户借款条数
     *
     * @param customerId
     * @return
     */
    @Override
    public int countByCustomerId(Integer customerId) {

        return financialLoanMapper.countLoanByCustomer(customerId);
    }

    @Override
    public PageFinder<FinancialLoan> pageByCustomer(Integer customerId, Integer pageNo, Integer pageSize) {

        PageHelper.startPage(pageNo, pageSize);
        Page<FinancialLoan> page = financialLoanMapper.pageByCustomer(customerId);
        PageFinder<FinancialLoan> pageFinder = new PageFinder<>(pageNo, pageSize, page.getTotal());
        pageFinder.setData(page.getResult());
        return pageFinder;
    }

    @Override
    public List<FinancialLoan> getByCustomerRepaying(Integer customerId) {

        return financialLoanMapper.selectByCustomerRepaying(customerId);
    }
}
