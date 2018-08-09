package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.FinacialLoanConfig;
import com.yunxin.cb.mall.mapper.FinacialLoanConfigMapper;
import com.yunxin.cb.mall.service.FinacialLoanConfigService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FinacialLoanConfigServiceImpl implements FinacialLoanConfigService {

    private static final Log log = LogFactory.getLog(FinacialLoanConfigServiceImpl.class);

    @Resource
    private FinacialLoanConfigMapper finacialLoanConfigMapper;


    @Override
    public List<FinacialLoanConfig> getFinacilaLoanConfigs() {
        return finacialLoanConfigMapper.selectAll();
    }
    public FinacialLoanConfig getFinacilaLoanConfigById(int loanConfigId) {
        return finacialLoanConfigMapper.selectByPrimaryKey(loanConfigId);
    }
}
