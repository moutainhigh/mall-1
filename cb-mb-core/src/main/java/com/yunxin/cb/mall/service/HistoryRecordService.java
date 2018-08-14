package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.HistoryRecord;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;

import java.util.List;

/**
 * @title: 我的浏览 服务接口类
 * @auther: eleven
 * @date: 2018/8/14 11:21
 */
public interface HistoryRecordService {

    /**
     * @title: 添加我的浏览
     * @param: [historyRecord]
     * @return: com.yunxin.cb.mall.entity.HistoryRecord
     * @auther: eleven
     * @date: 2018/8/14 11:26
     */
    public HistoryRecord addHistoryRecord(HistoryRecord historyRecord);

    /**
     * @title: 商品移出我的浏览(批量)
     * @param: [historyRecords, customerId]
     * @return: int
     * @auther: eleven
     * @date: 2018/8/14 11:26
     */
    public int removeHistoryRecordBatch(List<Integer> historyRecords, Integer customerId);

    /**
     * @title: 分页查询我的浏览
     * @param: [q]
     * @return: com.yunxin.cb.util.page.PageFinder<com.yunxin.cb.mall.entity.HistoryRecord>
     * @auther: eleven
     * @date: 2018/8/14 11:26
     */
    public PageFinder<HistoryRecord> pageCustomerHistoryRecords(Query q);
}
