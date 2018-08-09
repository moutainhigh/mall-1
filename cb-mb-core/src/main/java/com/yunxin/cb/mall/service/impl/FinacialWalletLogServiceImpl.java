package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.FinacialWalletLog;
import com.yunxin.cb.mall.mapper.FinacialWalletLogMapper;
import com.yunxin.cb.mall.service.FinacialWalletLogService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FinacialWalletLogServiceImpl implements FinacialWalletLogService {

    @Resource
    private FinacialWalletLogMapper finacialWalletLogMapper;
    private static final Log log = LogFactory.getLog(FinacialWalletLogServiceImpl.class);
    /**
     * 添加钱包日志信息
     * @author      likang
     * @param finacialWalletLog
     * @return      com.yunxin.cb.mall.entity.FinacialWalletLog
     * @exception
     * @date        2018/8/7 11:59
     */
    @Override
    public FinacialWalletLog addFinacialWalletLog(FinacialWalletLog finacialWalletLog){
        log.info("add:"+finacialWalletLog);
        if(finacialWalletLogMapper.selectByCustomerIdAndVersion(finacialWalletLog.getCustomerId(),finacialWalletLog.getVersion())==null){
            finacialWalletLogMapper.insert(finacialWalletLog);
        }
        return finacialWalletLog;
    }

    /**
     * 查询钱包日志信息
     * @author      likang
     * @param customerId
    * @param version
     * @return      com.yunxin.cb.mall.entity.FinacialWalletLog
     * @exception
     * @date        2018/8/7 15:05
     */
    public FinacialWalletLog getFinacialWalletLog(int customerId,int version){
        return finacialWalletLogMapper.selectByCustomerIdAndVersion(customerId,version);
    }

}
