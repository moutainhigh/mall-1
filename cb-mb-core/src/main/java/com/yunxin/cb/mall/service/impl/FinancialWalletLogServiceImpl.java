package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.mapper.FinancialWalletLogMapper;
import com.yunxin.cb.mall.service.FinancialWalletLogService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FinancialWalletLogServiceImpl implements FinancialWalletLogService {

    @Resource
    private FinancialWalletLogMapper financialWalletLogMapper;
    private static final Log log = LogFactory.getLog(FinancialWalletLogServiceImpl.class);


}
