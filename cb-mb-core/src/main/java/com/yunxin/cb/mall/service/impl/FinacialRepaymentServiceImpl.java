package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.FinacialRepayment;
import com.yunxin.cb.mall.entity.FinancialWallet;
import com.yunxin.cb.mall.entity.meta.CapitalType;
import com.yunxin.cb.mall.entity.meta.LoanState;
import com.yunxin.cb.mall.entity.meta.TransactionType;
import com.yunxin.cb.mall.mapper.FinacialRepaymentMapper;
import com.yunxin.cb.mall.mapper.FinacialWalletMapper;
import com.yunxin.cb.mall.service.FinacialLiabilitiesBillService;
import com.yunxin.cb.mall.service.FinacialLoanService;
import com.yunxin.cb.mall.service.FinacialRepaymentService;
import com.yunxin.cb.mall.service.FinacialWalletService;
import com.yunxin.cb.mall.vo.FinacialLiabilitiesBillVO;
import com.yunxin.cb.mall.vo.FinacialLoanVO;
import com.yunxin.cb.mall.vo.FinacialRepaymentVO;
import com.yunxin.cb.mall.vo.FinancialWalletVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FinacialRepaymentServiceImpl implements FinacialRepaymentService {

    @Resource
    private FinacialRepaymentMapper finacialRepaymentServiceMapper;

    @Resource
    private FinacialLoanService finacialLoanService;

    @Resource
    private FinacialLiabilitiesBillService finacialLiabilitiesBillService;

    @Resource
    private FinacialWalletMapper finacialWalletMapper;

    @Resource
    private FinacialWalletService finacialWalletService;

    private static final Log log = LogFactory.getLog(FinacialRepaymentServiceImpl.class);


    /**
     * 添加
     * @author      likang
     * @param vo
     * @return      com.yunxin.cb.mall.vo.FinacialRepaymentVO
     * @exception
     * @date        2018/8/9 14:47
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(BigDecimal repayAmount,int coutomerId) throws Exception{
        //获取用户钱包
        FinancialWallet financialWallet =finacialWalletMapper.selectByCustomerId(coutomerId);
        BigDecimal totalAmount=repayAmount;//实际还款金
//        if(financialWallet.getDebtTotal().subtract(totalAmount).doubleValue()<0){
//            throw new CommonException("还款失败，还款金额不对");
//        }
        /**还款，添加交易记录START*/
        FinacialLiabilitiesBillVO billvo = new FinacialLiabilitiesBillVO();
        billvo.setAmount(repayAmount);
        billvo.setCustomerId(coutomerId);
        billvo.setTransactionType(TransactionType.MANUAL_REPAYMENT);
        billvo.setType(CapitalType.ADD);
        billvo.setTransactionDesc("手动还款");
        finacialLiabilitiesBillService.addFinacialLiabilitiesBill(billvo);
        /**还款，添加交易记录END*/
        log.info("start repay cutomerId:"+coutomerId+";repayAmount:"+repayAmount);
        List<FinacialLoanVO> insuranlist = finacialLoanService.getByCustomerIdAndType(coutomerId);
        for (FinacialLoanVO p : insuranlist) {
            BigDecimal surplusAmount = p.getSurplusAmount();//该贷款总负债
            BigDecimal insuranceAmount=p.getInsuranceAmount();//保险金负债
            BigDecimal creditAmount=p.getCreditAmount();//信用金负债
            /**表示还款金大于此次贷款金，直接还完*/
            if (repayAmount.doubleValue() > surplusAmount.doubleValue()) {
                /**已还完，结清并修改剩余还款金为0*/
                p.setSurplusAmount(new BigDecimal(0));
                p.setState(LoanState.SETTLE);
                p.setInsuranceAmount(new BigDecimal(0));
                p.setCreditAmount(new BigDecimal(0));
                finacialLoanService.update(p);
                repayAmount = repayAmount.subtract(surplusAmount);
                /**添加还款计划*/
                FinacialRepayment finacialRepayment = new FinacialRepayment(p.getCustomerId(), p.getLoanId(), surplusAmount,
                        new Date(), surplusAmount, new Date());
                finacialRepaymentServiceMapper.insert(finacialRepayment);
                log.info("repay loanId"+p.getLoanId()+"cutomerId:"+coutomerId+
                        ";repayAmount:"+repayAmount+";surplusAmount:"+surplusAmount);
            }else{
                surplusAmount = surplusAmount.subtract(repayAmount);
                p.setSurplusAmount(surplusAmount);
                /**优先还保险负债*/
                if(repayAmount.doubleValue()>insuranceAmount.doubleValue()){
                    p.setInsuranceAmount(new BigDecimal(0));
                    creditAmount = creditAmount.subtract(repayAmount);
                    p.setCreditAmount(creditAmount);
                }else{
                    insuranceAmount = insuranceAmount.subtract(repayAmount);
                    p.setInsuranceAmount(insuranceAmount);
                }
                finacialLoanService.update(p);
                repayAmount = new BigDecimal(0);
                /**添加还款计划*/
                FinacialRepayment finacialRepayment = new FinacialRepayment(p.getCustomerId(), p.getLoanId(), repayAmount,
                        new Date(), surplusAmount, new Date());
                finacialRepaymentServiceMapper.insert(finacialRepayment);
                break;
            }
        }
        /**更新负债金额*/
//        financialWallet.setDebtTotal(financialWallet.getDebtTotal().subtract(totalAmount));
        FinancialWalletVO financialWalletVO = new FinancialWalletVO();
        BeanUtils.copyProperties(financialWalletVO, financialWallet);
        finacialWalletService.updateFinancialWallet(financialWalletVO);
    }

    /**
     * 根据用户获取还款信息
     * @author      likang
     * @param customerId
     * @return      java.util.List<com.yunxin.cb.mall.vo.FinacialRepaymentVO>
     * @exception
     * @date        2018/8/9 14:48
     */
    @Override
    public List<FinacialRepaymentVO> getByCustomerId(int customerId){
        List<FinacialRepayment> list = finacialRepaymentServiceMapper.selectByCustomerId(customerId);
        List<FinacialRepaymentVO> listVo = new ArrayList<>();
        list.stream().forEach(p ->{
            FinacialRepaymentVO vo = new FinacialRepaymentVO();
            BeanUtils.copyProperties(p, vo);
            listVo.add(vo);
        });
        return listVo;
    }

    /**
     * 根据id获取还款信息
     * @author      likang
     * @param repaymentId
     * @return      com.yunxin.cb.mall.vo.FinacialRepaymentVO
     * @exception
     * @date        2018/8/9 14:50
     */
    @Override
    public FinacialRepaymentVO getById(int repaymentId){
        FinacialRepaymentVO vo = new FinacialRepaymentVO();
        FinacialRepayment loan = finacialRepaymentServiceMapper.selectByPrimaryKey(repaymentId);
        BeanUtils.copyProperties(loan, vo);
        return vo;
    }
}
