package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.FinancialLoanConfig;
import com.yunxin.cb.mall.mapper.FinancialLoanConfigMapper;
import com.yunxin.cb.mall.service.FinancialLoanConfigService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FinancialLoanConfigServiceImpl implements FinancialLoanConfigService {

    private static final Log log = LogFactory.getLog(FinancialLoanConfigServiceImpl.class);

    @Resource
    private FinancialLoanConfigMapper financialLoanConfigMapper;


    @Override
    public List<FinancialLoanConfig> getFinancialLoanConfigs() {
        return financialLoanConfigMapper.selectAll();
    }
    public FinancialLoanConfig getFinancialLoanConfigById(int loanConfigId) {
        return financialLoanConfigMapper.selectByPrimaryKey(loanConfigId);
    }
}
