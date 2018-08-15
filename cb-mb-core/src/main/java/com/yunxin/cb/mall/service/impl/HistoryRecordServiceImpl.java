package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.HistoryRecord;
import com.yunxin.cb.mall.mapper.HistoryRecordMapper;
import com.yunxin.cb.mall.service.HistoryRecordService;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @title: 我的浏览 实现类
 * @auther: eleven
 * @date: 2018/8/14 11:27
 */
@Service
public class HistoryRecordServiceImpl implements HistoryRecordService {

    private static final Log log = LogFactory.getLog(HistoryRecordServiceImpl.class);

    @Resource
    private HistoryRecordMapper historyRecordMapper;

    @Override
    public HistoryRecord addHistoryRecord(HistoryRecord historyRecord) {
        historyRecord.setCreateTime(new Date());
        int result=historyRecordMapper.insert(historyRecord);
        return result>0?historyRecord:null;
    }

    @Override
    public int removeHistoryRecordBatch(List<Integer> historyRecords, Integer customerId) {
        return historyRecordMapper.removeHistoryRecordeBatch(historyRecords,customerId);
    }

    @Override
    public PageFinder<HistoryRecord> pageCustomerHistoryRecords(Query q) {
        List<HistoryRecord> list = null;
        long rowCount = 0L;
        try {
            //调用dao查询满足条件的分页数据
            list = historyRecordMapper.pageList(q);
            //调用dao统计满足条件的记录总数
            rowCount = historyRecordMapper.count(q);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        //如list为null时，则改为返回一个空列表
        list = list == null ? new ArrayList<HistoryRecord>(0) : list;
        //将分页数据和记录总数设置到分页结果对象中
        PageFinder<HistoryRecord> page = new PageFinder<>(q.getPageNo(), q.getPageSize(), rowCount);
        page.setData(list);
        return page;
    }
}
