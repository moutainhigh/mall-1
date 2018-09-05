package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.meta.OperationType;
import com.yunxin.cb.mall.entity.meta.WithdrawType;
import com.yunxin.cb.mall.restful.ResponseResult;
import com.yunxin.cb.mall.vo.FinancialWalletVO;

import java.math.BigDecimal;

public interface FinancialWalletService {

    /**
     * 添加钱包信息
     * @author      likang
     * @param vo
     * @return      com.yunxin.cb.mall.entity.FinancialWallet
     * @exception
     * @date        2018/8/7 11:54
     */
    FinancialWalletVO addFinancialWallet(FinancialWalletVO vo);


    /**
     * 获取用户钱包信息
     * @author      likang
     * @param customerId
     * @return      com.yunxin.cb.mall.entity.FinancialWallet
     * @exception
     * @date        2018/8/7 11:55
     */
    FinancialWalletVO getFinancialWalletByCustomerId(Integer customerId);


    /**
     * 更新用户钱包信息
     * @author      likang
     * @param vo
     * @return      com.yunxin.cb.mall.entity.FinancialWallet
     * @exception
     * @date        2018/8/7 11:56
     */
    boolean updateFinancialWallet(FinancialWalletVO vo, BigDecimal amount, OperationType type, String remark);

    /**
     * @title: 处理用户返现接口
     * @param: [customerId, money]
     * @return: com.yunxin.cb.mall.restful.ResponseResult
     * @auther: eleven
     * @date: 2018/8/8 19:39
     */
    ResponseResult processCustomerMoney(Integer customerId, BigDecimal money, WithdrawType type,String remark);
}
