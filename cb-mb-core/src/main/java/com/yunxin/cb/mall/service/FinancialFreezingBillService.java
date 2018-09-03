package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinancialFreezingBill;
import com.yunxin.cb.mall.vo.FinancialFreezingBillVO;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;

import java.util.List;

public interface FinancialFreezingBillService {

    FinancialFreezingBillVO addFinancialFreezingBill(FinancialFreezingBillVO vo);

    List<FinancialFreezingBillVO> getFinancialFreezingBillByCustomerId(int customerId);

    PageFinder<FinancialFreezingBill> page(Query q);
}
