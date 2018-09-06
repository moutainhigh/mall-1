package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.vo.FinancialLogDataVO;
import com.yunxin.cb.mall.vo.FinancialLogDetailVO;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;

public interface FinancialLogService {

    /**
     * @title: 分页查询用户账单
     * @param: [q]
     * @return: com.yunxin.cb.util.page.PageFinder<com.yunxin.cb.mall.vo.FinancialLogDataVO>
     * @auther: eleven
     * @date: 2018/8/9 19:12
     */
    PageFinder<FinancialLogDataVO> pageFinancialLog(Query q);

    /**
     * 根据用户ID和账单ID查看详情
     * @param logId
     * @param customerId
     * @return
     */
    FinancialLogDetailVO getCustomerFinancialLogDetail(Integer logId,Integer customerId);

}
