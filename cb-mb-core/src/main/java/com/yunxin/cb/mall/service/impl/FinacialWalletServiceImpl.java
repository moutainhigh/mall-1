package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.FinacialWallet;
import com.yunxin.cb.mall.mapper.FinacialWalletMapper;
import com.yunxin.cb.mall.service.FinacialWalletService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FinacialWalletServiceImpl implements FinacialWalletService {

    private static final Log log = LogFactory.getLog(FinacialWalletServiceImpl.class);
    @Resource
    private FinacialWalletMapper finacialWalletMapper;
    /**
     * 添加钱包信息
     * @author      likang
     * @param finaciaWallet
     * @return      com.yunxin.cb.mall.entity.FinacialWallet
     * @exception
     * @date        2018/8/7 11:54
     */
    @Override
    public FinacialWallet addFinaciaWallet(FinacialWallet finaciaWallet){
        if(finacialWalletMapper.selectByCustomerId(finaciaWallet.getCustomerId())==null){
            finacialWalletMapper.insert(finaciaWallet);
        }
        return finaciaWallet;
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
    public FinacialWallet getFinacialWalletByCustomerId(int customerId){
        return finacialWalletMapper.selectByCustomerId(customerId);
    }

    /**
     * 更新用户钱包信息
     * @author      likang
     * @param finaciaWallet
     * @return      com.yunxin.cb.mall.entity.FinacialWallet
     * @exception
     * @date        2018/8/7 11:56
     */
    @Override
    public FinacialWallet updateFinacialWallet(FinacialWallet finaciaWallet){
        finacialWalletMapper.updateByPrimaryKey(finaciaWallet);
        return null;
    }
}
