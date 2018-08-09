package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinacialExpectBill;
import com.yunxin.cb.mall.vo.FinacialExpectBillVO;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;

import java.util.List;

public interface FinacialExpectBillService {

    FinacialExpectBillVO addFinacialExpectBill(FinacialExpectBillVO vo);

    List<FinacialExpectBillVO> getFinacialExpectBillByCustomerId(int customerId);

    public PageFinder<FinacialExpectBill> page(Query q);
}
