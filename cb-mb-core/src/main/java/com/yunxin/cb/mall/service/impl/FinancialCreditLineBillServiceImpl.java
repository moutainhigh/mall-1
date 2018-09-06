package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.mapper.FinancialCreditLineBillMapper;
import com.yunxin.cb.mall.service.FinancialCreditLineBillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FinancialCreditLineBillServiceImpl implements FinancialCreditLineBillService {

    @Resource
    private FinancialCreditLineBillMapper financialCreditLineBillMapper;


}
