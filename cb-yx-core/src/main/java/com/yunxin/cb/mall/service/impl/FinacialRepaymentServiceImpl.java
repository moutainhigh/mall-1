package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.FinacialRepayment;
import com.yunxin.cb.mall.entity.FinacialWallet;
import com.yunxin.cb.mall.entity.meta.CapitalType;
import com.yunxin.cb.mall.entity.meta.LoanState;
import com.yunxin.cb.mall.entity.meta.LoanType;
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
import com.yunxin.cb.mall.vo.FinacialWalletVO;
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
    @Transactional
    public FinacialRepaymentVO add(FinacialRepaymentVO vo) {
        log.info("add:"+vo);
        //获取用户钱包
        FinacialWallet finacialWallet=finacialWalletMapper.selectByCustomerId(vo.getCustomerId());
        FinacialRepayment finacialRepayment = new FinacialRepayment();
        BeanUtils.copyProperties(finacialRepayment, vo);
        finacialRepayment.setCreateTime(new Date());
        finacialRepayment.setRepayTime(new Date());
        BigDecimal totalAmount=finacialRepayment.getRepayAmount();//实际还款金
        BigDecimal repayAmount=finacialRepayment.getRepayAmount();//实际还款金
        if(finacialWallet.getDebtTotal().subtract(totalAmount).doubleValue()<0){

        }
        /**还款，添加交易记录START*/
        FinacialLiabilitiesBillVO billvo = new FinacialLiabilitiesBillVO();
        billvo.setAmount(repayAmount);
        billvo.setCustomerId(finacialRepayment.getCustomerId());
        billvo.setTransactionType(TransactionType.MANUAL_REPAYMENT);
        billvo.setType(CapitalType.ADD);
        billvo.setTransactionDesc("手动还款");
        finacialLiabilitiesBillService.addFinacialLiabilitiesBill(billvo);
        /**还款，添加交易记录END*/
        log.info("start repay cutomerId:"+finacialRepayment.getCustomerId()+";repayAmount:"+repayAmount);
        List<FinacialLoanVO> insuranlist = finacialLoanService.getByCustomerIdAndType(finacialRepayment.getCustomerId());
        for (FinacialLoanVO p : insuranlist) {
            BigDecimal surplusAmount = p.getSurplusAmount();
            BigDecimal insuranceAmount=p.getInsuranceAmount();
            BigDecimal creditAmount=p.getCreditAmount();
            //表示还款金大于贷款金
            if (repayAmount.doubleValue() > surplusAmount.doubleValue()) {
                p.setSurplusAmount(new BigDecimal(0));
                p.setState(LoanState.SETTLE);
                p.setInsuranceAmount(new BigDecimal(0));
                p.setCreditAmount(new BigDecimal(0));
                FinacialLoanVO fvo = new FinacialLoanVO();
                BeanUtils.copyProperties(p, fvo);
                finacialLoanService.update(fvo);
                repayAmount = repayAmount.subtract(surplusAmount);
                log.info("repay loanId"+p.getLoanId()+"cutomerId:"+finacialRepayment.getCustomerId()+
                        ";repayAmount:"+repayAmount+";surplusAmount:"+surplusAmount);
            }else{
                surplusAmount = surplusAmount.subtract(repayAmount);
                p.setSurplusAmount(surplusAmount);
                if(repayAmount.doubleValue()>insuranceAmount.doubleValue()){
                    p.setInsuranceAmount(new BigDecimal(0));
                    creditAmount = creditAmount.subtract(repayAmount);
                    p.setCreditAmount(creditAmount);
                }else{
                    insuranceAmount = insuranceAmount.subtract(repayAmount);
                    p.setInsuranceAmount(insuranceAmount);
                }
                FinacialLoanVO fvo = new FinacialLoanVO();
                BeanUtils.copyProperties(p, fvo);
                finacialLoanService.update(fvo);
                repayAmount = new BigDecimal(0);
                log.info("repay loanId"+p.getLoanId()+"cutomerId:"+finacialRepayment.getCustomerId()+
                        ";repayAmount:"+repayAmount+";surplusAmount:"+surplusAmount);
                break;
            }
        }
        //总负债金额
        finacialWallet.setDebtTotal(finacialWallet.getDebtTotal().subtract(totalAmount));
        //更新负债金额
        FinacialWalletVO finacialWalletVO = new FinacialWalletVO();
        BeanUtils.copyProperties(finacialWalletVO,finacialWallet);
        finacialWalletService.updateFinacialWallet(finacialWalletVO);
        finacialRepaymentServiceMapper.insert(finacialRepayment);
        return vo;
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
