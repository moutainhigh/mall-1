package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.meta.WithdrawType;
import com.yunxin.cb.mall.vo.FinacialWalletVO;

import java.math.BigDecimal;

public interface FinacialWalletService {

    /**
     * 添加钱包信息
     * @author      likang
     * @param vo
     * @return      com.yunxin.cb.mall.entity.FinacialWallet
     * @exception
     * @date        2018/8/7 11:54
     */
    public FinacialWalletVO addFinaciaWallet(FinacialWalletVO vo);


    /**
     * 获取用户钱包信息
     * @author      likang
     * @param customerId
     * @return      com.yunxin.cb.mall.entity.FinacialWallet
     * @exception
     * @date        2018/8/7 11:55
     */
    public FinacialWalletVO getFinacialWalletByCustomerId(int customerId);


    /**
     * 更新用户钱包信息
     * @author      likang
     * @param vo
     * @return      com.yunxin.cb.mall.entity.FinacialWallet
     * @exception
     * @date        2018/8/7 11:56
     */
    public FinacialWalletVO updateFinacialWallet(FinacialWalletVO vo);

    /**
     * @title: 处理用户返现接口
     * @param: [customerId, money]
     * @return: com.yunxin.cb.mall.restful.ResponseResult
     * @auther: eleven
     * @date: 2018/8/8 19:39
     */
    public boolean processCustomerMoney(Integer customerId, BigDecimal money, WithdrawType type);
}
