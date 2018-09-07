package com.yunxin.cb.mall.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunxin.cb.mall.entity.FinancialFreezingBill;
import com.yunxin.cb.mall.mapper.FinancialFreezingBillMapper;
import com.yunxin.cb.mall.service.FinancialFreezingBillService;
import com.yunxin.cb.util.page.PageFinder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FinancialFreezingBillServiceImpl implements FinancialFreezingBillService {

    @Resource
    private FinancialFreezingBillMapper financialFreezingBillMapper;

    private static final Log log = LogFactory.getLog(FinancialFreezingBillServiceImpl.class);

    @Override
    public PageFinder<FinancialFreezingBill> page(Integer customerId, Integer pageNo, Integer pageSize) {

        PageHelper.startPage(pageNo, pageSize);
        Page<FinancialFreezingBill> page = financialFreezingBillMapper.pageListByCustomer(customerId);
        PageFinder<FinancialFreezingBill> pageFinder = new PageFinder<>(pageNo, pageSize, page.getTotal());
        pageFinder.setData(page.getResult());
        return pageFinder;
    }

}
