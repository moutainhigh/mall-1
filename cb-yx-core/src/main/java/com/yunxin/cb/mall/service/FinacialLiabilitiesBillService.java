package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinacialLiabilitiesBill;
import com.yunxin.cb.mall.vo.FinacialLiabilitiesBillVO;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;

import java.util.List;

public interface FinacialLiabilitiesBillService {

    FinacialLiabilitiesBillVO addFinacialLiabilitiesBill(FinacialLiabilitiesBillVO vo);

    List<FinacialLiabilitiesBillVO> getFinacialLiabilitiesBillByCustomerId(int customerId);

    public PageFinder<FinacialLiabilitiesBill> page(Query q);
}
