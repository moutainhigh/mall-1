package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.FiaciaLog;
import com.yunxin.cb.mall.mapper.FinancialLogMapper;
import com.yunxin.cb.mall.service.FinancialLogService;
import com.yunxin.cb.mall.vo.FinancialLogBillVO;
import com.yunxin.cb.mall.vo.FinancialLogDataVO;
import com.yunxin.cb.mall.vo.FinancialLogDetailVO;
import com.yunxin.cb.mall.vo.FinancialLogVO;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FinancialLogServiceImpl implements FinancialLogService {

    private static final Log log = LogFactory.getLog(FinancialLogServiceImpl.class);

    @Resource
    private FinancialLogMapper financialLogMapper;

    @Override
    public int addFiaciaLog(FinancialLogBillVO vo) {
        log.info("add:"+vo);
        FiaciaLog log=new FiaciaLog();
        vo.setCreateTime(new Date());
        BeanUtils.copyProperties(vo,log);
        return financialLogMapper.insert(log);
    }

    @Override
    public PageFinder<FinancialLogDataVO> pageFinancialLog(Query q) {
        try {
            FinancialLogDataVO financialLogDataVO = new FinancialLogDataVO();
            List<FinancialLogDataVO> listVO = new ArrayList<>();
            //调用dao查询满足条件的分页数据
            List<FinancialLogVO> list = financialLogMapper.pageList(q);
            //调用dao统计满足条件的记录总数
            long rowCount = financialLogMapper.count(q);
            //如list为null时，则改为返回一个空列表
            list = list == null ? new ArrayList<>(0) : list;
            //将分页数据和记录总数设置到分页结果对象中
            PageFinder<FinancialLogDataVO> page = new PageFinder<>(q.getPageNo(), q.getPageSize(), rowCount);
//            page.setData(list);
            financialLogDataVO.setFinancialLogVO(list);
            Map map = financialLogMapper.queryTotalAmount(q);
            BigDecimal addTotalAmount=new BigDecimal(String.valueOf(map.get("addTotalAmount")));//收入
            BigDecimal subTotalAmount=new BigDecimal(String.valueOf(map.get("subTotalAmount")));//支出
            financialLogDataVO.setAddTotalAmount(addTotalAmount);
            financialLogDataVO.setSubTotalAmount(subTotalAmount);
            listVO.add(financialLogDataVO);
            page.setData(listVO);
            return page;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public FinancialLogDetailVO getCustomerFinancialLogDetail(Integer logId, Integer customerId) {
        try {
            FinancialLogDetailVO financialLogDetailVO = new FinancialLogDetailVO();
            FiaciaLog fiaciaLog = financialLogMapper.selectByPrimaryKeyAndCustomerId(logId,customerId);
            if(null != fiaciaLog){
                BeanUtils.copyProperties(fiaciaLog,financialLogDetailVO);
            }
            return financialLogDetailVO;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
