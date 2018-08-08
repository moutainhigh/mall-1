package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.vo.FinacialExpectBillVO;

import java.util.List;

public interface FinacialExpectBillService {

    FinacialExpectBillVO addFinacialExpectBill(FinacialExpectBillVO vo);

    List<FinacialExpectBillVO> getFinacialExpectBillByCustomerId(int customerId);
}
