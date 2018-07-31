package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.BankInfo;
import com.yunxin.cb.mall.entity.Brand;
import com.yunxin.cb.mall.vo.BankInfoVO;

import java.util.List;

public interface BankInfoService {

    BankInfoVO selectByPrimaryKey(Integer bankId,Integer customerId);

    List<BankInfoVO> selectAll(Integer customerId);

    int insert(BankInfo bankInfo);

    int deleteByPrimaryKey(Integer bankId,Integer customerId);
}
