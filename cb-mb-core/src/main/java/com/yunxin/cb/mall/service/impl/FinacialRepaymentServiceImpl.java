package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.FinacialRepayment;
import com.yunxin.cb.mall.entity.meta.CapitalType;
import com.yunxin.cb.mall.entity.meta.LoanState;
import com.yunxin.cb.mall.entity.meta.TransactionType;
import com.yunxin.cb.mall.mapper.FinacialRepaymentMapper;
import com.yunxin.cb.mall.service.FinacialLiabilitiesBillService;
import com.yunxin.cb.mall.service.FinacialLoanService;
import com.yunxin.cb.mall.service.FinacialRepaymentService;
import com.yunxin.cb.mall.vo.FinacialLiabilitiesBillVO;
import com.yunxin.cb.mall.vo.FinacialLoanVO;
import com.yunxin.cb.mall.vo.FinacialRepaymentVO;
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
    public FinacialRepaymentVO add(FinacialRepaymentVO vo){
        log.info("add:"+vo);
        FinacialRepayment finacialRepayment = new FinacialRepayment();
        BeanUtils.copyProperties(finacialRepayment, vo);
        finacialRepayment.setCreateTime(new Date());
        finacialRepayment.setRepayTime(new Date());
        BigDecimal repayAmout=finacialRepayment.getRepayAmount();//实际还款金
        /**还款，添加交易记录START*/
        FinacialLiabilitiesBillVO billvo = new FinacialLiabilitiesBillVO();
        billvo.setAmount(repayAmout);
        billvo.setCustomerId(finacialRepayment.getCustomerId());
        billvo.setTransactionType(TransactionType.MANUAL_REPAYMENT);
        billvo.setType(CapitalType.ADD);
        billvo.setTransactionDesc("手动还款");
        finacialLiabilitiesBillService.addFinacialLiabilitiesBill(billvo);
        /**还款，添加交易记录END*/
        log.info("开始还款cutomerId:"+finacialRepayment.getCustomerId()+";还款金repayAmount:"+repayAmout);
        List<FinacialLoanVO> list = finacialLoanService.getByCustomerId(finacialRepayment.getCustomerId());
        for (FinacialLoanVO p : list) {
            BigDecimal surplusAmount = p.getSurplusAmount();
            //表示还款金大于贷款金
            if (repayAmout.doubleValue() > surplusAmount.doubleValue()) {
                p.setSurplusAmount(new BigDecimal(0));
                p.setState(LoanState.SETTLE);
                FinacialLoanVO fvo = new FinacialLoanVO();
                BeanUtils.copyProperties(p, fvo);
                finacialLoanService.update(fvo);
                repayAmout = repayAmout.subtract(surplusAmount);
                log.info("还款loanId"+p.getLoanId()+"cutomerId:"+finacialRepayment.getCustomerId()+
                        ";repayAmount:"+repayAmout+";surplusAmount:"+surplusAmount);
                break;
            }else{
                surplusAmount = surplusAmount.subtract(repayAmout);
                p.setSurplusAmount(surplusAmount);
                FinacialLoanVO fvo = new FinacialLoanVO();
                BeanUtils.copyProperties(p, fvo);
                finacialLoanService.update(fvo);
                repayAmout = new BigDecimal(0);
                log.info("还款loanId"+p.getLoanId()+"cutomerId:"+finacialRepayment.getCustomerId()+
                        ";repayAmount:"+repayAmout+";surplusAmount:"+surplusAmount);
            }
        }
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
