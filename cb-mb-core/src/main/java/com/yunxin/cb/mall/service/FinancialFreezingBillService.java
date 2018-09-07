package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinancialFreezingBill;
import com.yunxin.cb.mall.vo.FinancialFreezingBillVO;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;

import java.util.List;

public interface FinancialFreezingBillService {

    /**
     * @Author chenpeng
     * @Description 分页获取 冻结金额交易明细
     * @Date 2018/9/7 17:42 
     **/
    PageFinder<FinancialFreezingBill> page(Integer customerId, Integer pageNo, Integer pageSize);

}
