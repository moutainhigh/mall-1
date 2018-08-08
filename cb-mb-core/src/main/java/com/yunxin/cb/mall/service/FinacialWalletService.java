package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.vo.FinacialWalletVO;

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

}
