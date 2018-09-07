package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.mapper.FinacialCreditLineBillMapper;
import com.yunxin.cb.mall.service.FinacialCreditLineBillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FinacialCreditLineBillServiceImpl implements FinacialCreditLineBillService {

    @Resource
    private FinacialCreditLineBillMapper finacialCreditLineBillMapper;


}
