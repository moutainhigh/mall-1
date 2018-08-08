package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.FinacialWallet;
import com.yunxin.cb.mall.entity.FinacialWalletLog;
import com.yunxin.cb.mall.mapper.FinacialWalletLogMapper;
import com.yunxin.cb.mall.mapper.FinacialWalletMapper;
import com.yunxin.cb.mall.service.FinacialWalletService;
import com.yunxin.cb.mall.vo.FinacialWalletVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class FinacialWalletServiceImpl implements FinacialWalletService {

    private static final Log log = LogFactory.getLog(FinacialWalletServiceImpl.class);
    @Resource
    private FinacialWalletMapper finacialWalletMapper;
    @Resource
    private FinacialWalletLogMapper finacialWalletLogMapper;
    /**
     * 添加钱包信息
     * @author      likang
     * @param vo
     * @return      com.yunxin.cb.mall.entity.FinacialWallet
     * @exception
     * @date        2018/8/7 11:54
     */
    @Override
    public FinacialWalletVO addFinaciaWallet(FinacialWalletVO vo){
        log.info("addFinaciaWallet:"+vo.toString());
        FinacialWallet finacialWallet = new FinacialWallet();
        BeanUtils.copyProperties(finacialWallet, vo);
        if(finacialWalletMapper.selectByCustomerId(finacialWallet.getCustomerId())==null){
            /**初始化钱包信息*/
            finacialWallet.setAssets(new BigDecimal(0));
            finacialWallet.setBalance(new BigDecimal(0));
            finacialWallet.setDebtTotal(new BigDecimal(0));
            finacialWallet.setDebtExpected(new BigDecimal(0));
            finacialWallet.setDebtCredit(new BigDecimal(0));
            finacialWallet.setCreditAmount(new BigDecimal(0));
            finacialWallet.setFreezingAmount(new BigDecimal(0));
            finacialWallet.setInsuranceAmount(finacialWallet.getExpectedAmount());
            finacialWallet.setTotalAmount(finacialWallet.getExpectedAmount());
            finacialWallet.setVersion(0);//初始化版本号
            finacialWalletMapper.insert(finacialWallet);
        }
        return vo;
    }

    /**
     * 获取用户钱包信息
     * @author      likang
     * @param customerId
     * @return      com.yunxin.cb.mall.entity.FinacialWallet
     * @exception
     * @date        2018/8/7 11:55
     */
    @Override
    public FinacialWalletVO getFinacialWalletByCustomerId(int customerId){
        FinacialWalletVO vo = new FinacialWalletVO();
        FinacialWallet finacialWallet = finacialWalletMapper.selectByCustomerId(customerId);
        BeanUtils.copyProperties(finacialWallet, vo);
        return vo;
    }

    /**
     * 更新用户钱包信息
     * @author      likang
     * @param vo
     * @return      com.yunxin.cb.mall.entity.FinacialWallet
     * @exception
     * @date        2018/8/7 11:56
     */
    @Override
    public FinacialWalletVO updateFinacialWallet(FinacialWalletVO vo){
        FinacialWallet finacialWallet = new FinacialWallet();
        BeanUtils.copyProperties(finacialWallet, vo);
        finacialWalletMapper.updateByPrimaryKey(finacialWallet);
        /**添加日志信息*/
        FinacialWalletLog finacialWalletlog = new FinacialWalletLog();
        BeanUtils.copyProperties(finacialWallet, finacialWalletlog);
        finacialWalletlog.setAmount(new BigDecimal("0"));
        finacialWalletLogMapper.insert(finacialWalletlog);
        return vo;
    }
}
