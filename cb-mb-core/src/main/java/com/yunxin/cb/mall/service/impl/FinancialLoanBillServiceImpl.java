package com.yunxin.cb.mall.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunxin.cb.mall.entity.FinancialLoanBill;
import com.yunxin.cb.mall.mapper.FinancialLoanBillMapper;
import com.yunxin.cb.mall.service.FinancialLoanBillService;
import com.yunxin.cb.util.page.PageFinder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class FinancialLoanBillServiceImpl implements FinancialLoanBillService {

    @Resource
    private FinancialLoanBillMapper financialLoanBillMapper;

    private static final Log log = LogFactory.getLog(FinancialLoanBillServiceImpl.class);

    /**
     * @Author chenpeng
     * @Description 获取 负债记录 分页信息
     * @Date 2018/9/5 11:10 
     **/
    @Override
    public PageFinder<FinancialLoanBill> page(Integer customerId, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Page<FinancialLoanBill> page = financialLoanBillMapper.pageListByCustomer(customerId);
        PageFinder<FinancialLoanBill> pageFinder = new PageFinder<>(pageNo, pageSize, page.getTotal());
        pageFinder.setData(page.getResult());
        return pageFinder;
    }
}
