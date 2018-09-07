package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.FiaciaLog;
import com.yunxin.cb.mall.mapper.FinacialLogMapper;
import com.yunxin.cb.mall.service.FiaciaLogService;
import com.yunxin.cb.mall.vo.FiaciaLogDataVO;
import com.yunxin.cb.mall.vo.FiaciaLogVO;
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
public class FiaciaLogServiceImpl implements FiaciaLogService {

    private static final Log log = LogFactory.getLog(FiaciaLogServiceImpl.class);

    @Resource
    private FinacialLogMapper finacialLogMapper;

    @Override
    public int addFiaciaLog(FiaciaLogVO vo) {
        log.info("add:"+vo);
        FiaciaLog log=new FiaciaLog();
        vo.setCreateTime(new Date());
        BeanUtils.copyProperties(vo,log);
        return finacialLogMapper.insert(log);
    }

    @Override
    public FiaciaLogDataVO pageFiaciaLog(Query q) {
        try {
            FiaciaLogDataVO fiaciaLogDataVO=new FiaciaLogDataVO();
            //调用dao查询满足条件的分页数据
            List<FiaciaLogVO> list = finacialLogMapper.pageList(q);
            //调用dao统计满足条件的记录总数
            long rowCount = finacialLogMapper.count(q);
            //如list为null时，则改为返回一个空列表
            list = list == null ? new ArrayList<>(0) : list;
            //将分页数据和记录总数设置到分页结果对象中
            PageFinder<FiaciaLogVO> page = new PageFinder<>(q.getPageNo(), q.getPageSize(), rowCount);
            page.setData(list);
            fiaciaLogDataVO.setFiaciaLogVOS(list);
            Map map = finacialLogMapper.queryTotalAmount(q);
            BigDecimal addTotalAmount=new BigDecimal(String.valueOf(map.get("addTotalAmount")));//收入
            BigDecimal subTotalAmount=new BigDecimal(String.valueOf(map.get("subTotalAmount")));//支出
            fiaciaLogDataVO.setAddTotalAmount(addTotalAmount);
            fiaciaLogDataVO.setSubTotalAmount(subTotalAmount);
            return fiaciaLogDataVO;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
