package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinacialWithdraw;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

public interface IFinacialWithdrawService {

    /**
     * @title: 分页查询提现记录
     * @param: [serviceRuleQuery]
     * @return: org.springframework.data.domain.Page<com.yunxin.cb.mall.entity.FinacialWithdraw>
     * @auther: eleven
     * @date: 2018/8/10 14:46
     */
    public Page<FinacialWithdraw> pageServiceFinacialWithdraw(PageSpecification<FinacialWithdraw> pageSpecification);

    /**
     * @title: 转账确认
     * @param: [finacialWithdraw]
     * @return: com.yunxin.cb.mall.entity.FinacialWithdraw
     * @auther: eleven
     * @date: 2018/8/10 14:44
     */
    public int tansfer(String ids) throws Exception;
}
