package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.CustomerWallet;
import com.yunxin.cb.mall.mapper.CustomerWalletMapper;
import com.yunxin.cb.mall.service.CustomerWalletService;
import com.yunxin.cb.mall.vo.CustomerWalletVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CustomerWalletServiceImpl implements CustomerWalletService{
    @Resource
    private CustomerWalletMapper customerWalletMapper;

    /**
     * 查询用户的钱包信息
     * @author      likang
     * @param customerId
     * @return      com.yunxin.cb.mall.vo.CustomerWalletVO
     * @exception
     * @date        2018/7/31 16:32
     */
    @Override
    public CustomerWalletVO selectByCustomerId(Integer customerId){
        CustomerWalletVO customerWalletVO=new CustomerWalletVO();
        CustomerWallet customerWallet = customerWalletMapper.selectByCustomerId(customerId);
        BeanUtils.copyProperties(customerWallet, customerWalletVO);
        return customerWalletVO;
    }
}
