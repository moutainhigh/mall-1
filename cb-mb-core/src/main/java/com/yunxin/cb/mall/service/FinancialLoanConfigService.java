package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinancialLoanConfig;

import java.util.List;

public interface FinancialLoanConfigService {

    List<FinancialLoanConfig> getFinancialLoanConfigs();

    FinancialLoanConfig getFinancialLoanConfigById(int loanConfigId);

}
