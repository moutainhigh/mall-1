package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.BankInfo;
import com.yunxin.cb.mall.vo.BankInfoVO;

import java.util.List;

public interface BankInfoService {

    BankInfoVO selectByPrimaryKey(Integer bankId,Integer customerId);

    List<BankInfoVO> selectAll(Integer customerId);

    int insert(BankInfo bankInfo);

    int deleteByPrimaryKey(Integer bankId,Integer customerId);

    /**
     * @title: 增加银行卡和通过实名认证
     * @param: [bankInfoVO, customerId]
     * @return: void
     * @auther: eleven
     * @date: 2018/8/13 10:05
     */
    int submitAuth(BankInfoVO bankInfoVO, int customerId) throws Exception;
}
