package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.OperationType;
import com.yunxin.cb.mall.entity.meta.WithdrawType;
import com.yunxin.cb.mall.mapper.*;
import com.yunxin.cb.mall.restful.ResponseResult;
import com.yunxin.cb.mall.restful.meta.Result;
import com.yunxin.cb.mall.service.FinancialWalletService;
import com.yunxin.cb.mall.vo.FinancialWalletVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class FinancialWalletServiceImpl implements FinancialWalletService {

    private static final Log log = LogFactory.getLog(FinancialWalletServiceImpl.class);
    @Resource
    private FinancialWalletMapper financialWalletMapper;
    @Resource
    private FinancialWalletLogMapper financialWalletLogMapper;
    @Resource
    private BankInfoMapper bankInfoMapper;
    @Resource
    private FinancialWithdrawMapper financialWithdrawMapper;
    @Resource
    private FinancialCashbackLogMapper financialCashbackLogMapper;
    @Resource
    private CustomerMapper customerMapper;

    /**
     * 添加钱包信息
     *
     * @param vo
     * @return com.yunxin.cb.mall.entity.FinancialWallet
     * @throws
     * @author likang
     * @date 2018/8/7 11:54
     */
    @Override
    public FinancialWalletVO addFinancialWallet(FinancialWalletVO vo) {
        log.info("addFinancialWallet:" + vo.toString());
        FinancialWallet financialWallet = new FinancialWallet();
        BeanUtils.copyProperties(financialWallet, vo);
        if (financialWalletMapper.selectByCustomerId(financialWallet.getCustomerId()) == null) {
            //初始化钱包信息
            financialWallet.setDebtCredit(new BigDecimal(0));
            financialWallet.setDebtCar(new BigDecimal(0));
            financialWallet.setCreditAmount(new BigDecimal(0));
            financialWallet.setFreezingAmount(new BigDecimal(0));
            financialWallet.setVersion(0);//初始化版本号
            financialWalletMapper.insert(financialWallet);
        }
        return vo;
    }

    /**
     * 获取用户钱包信息
     * @author      likang
     * @param customerId
     * @return      com.yunxin.cb.mall.entity.FinancialWallet
     * @exception
     * @date        2018/8/7 11:55
     */
    @Override
    public FinancialWalletVO getFinancialWalletByCustomerId(Integer customerId){
        FinancialWalletVO vo = new FinancialWalletVO();
        FinancialWallet financialWallet = financialWalletMapper.selectByCustomerId(customerId);
        if (financialWallet == null) {
            //初始化钱包信息
            financialWallet = new FinancialWallet();
            financialWallet.setCustomerId(customerId);
            financialWallet.setDebtCredit(new BigDecimal(0));
            financialWallet.setDebtCar(new BigDecimal(0));
            financialWallet.setCreditAmount(new BigDecimal(0));
            financialWallet.setFreezingAmount(new BigDecimal(0));
            financialWallet.setVersion(0);//初始化版本号
            financialWalletMapper.insert(financialWallet);
        }
        BeanUtils.copyProperties(financialWallet, vo);
        return vo;
    }

    /**
     * 更新用户钱包信息
     * @author      likang
     * @param vo
     * @return      com.yunxin.cb.mall.entity.FinancialWallet
     * @exception
     * @date        2018/8/7 11:56
     */
    @Override
    @Transactional
    public boolean updateFinancialWallet(FinancialWalletVO vo, BigDecimal amount, OperationType type, String remark){
        FinancialWallet financialWallet = new FinancialWallet();
        BeanUtils.copyProperties(vo, financialWallet);
        int result = financialWalletMapper.updateByPrimaryKeyOnVersion(financialWallet);
        if (result == 1) {
            // 钱包变动日志
            FinancialWalletLog financialWalletlog = new FinancialWalletLog();
            BeanUtils.copyProperties(vo, financialWalletlog);
            financialWalletlog.setAmount(amount);
            financialWalletlog.setType(type);
            financialWalletlog.setRemark(remark);
            result = financialWalletLogMapper.insert(financialWalletlog);
        }
        return result == 1;
    }

}
