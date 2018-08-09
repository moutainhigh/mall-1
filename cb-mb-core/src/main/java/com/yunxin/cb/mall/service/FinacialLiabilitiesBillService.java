package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.vo.FinacialLiabilitiesBillVO;

import java.util.List;

public interface FinacialLiabilitiesBillService {

    FinacialLiabilitiesBillVO addFinacialLiabilitiesBill(FinacialLiabilitiesBillVO vo);

    List<FinacialLiabilitiesBillVO> getFinacialLiabilitiesBillByCustomerId(int customerId);
}
