package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.FinancialLoanBill;
import com.yunxin.cb.mall.mapper.FinacialLiabilitiesBillMapper;
import com.yunxin.cb.mall.service.FinancialLoanBillService;
import com.yunxin.cb.mall.vo.FinancialLoanBillVO;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FinancialLoanBillServiceImpl implements FinancialLoanBillService {

    @Resource
    private FinacialLiabilitiesBillMapper finacialLiabilitiesBillMapper;

    private static final Log log = LogFactory.getLog(FinancialLoanBillServiceImpl.class);
    /**
     * 添加
     * @author      likang
     * @param vo
     * @return      com.yunxin.cb.mall.vo.FinancialLoanBillVO
     * @exception
     * @date        2018/8/8 16:59
     */
    @Override
    public FinancialLoanBillVO addFinacialLiabilitiesBill(FinancialLoanBillVO vo){
        log.info("addFinacialLiabilitiesBill:"+vo);
        FinancialLoanBill financialLoanBill = new FinancialLoanBill();
        BeanUtils.copyProperties(financialLoanBill, vo);
        financialLoanBill.setCreateTime(LocalDateTime.now());
        finacialLiabilitiesBillMapper.insert(financialLoanBill);
        return vo;
    }

    /**
     * 获取记录
     * @author      likang
     * @param customerId
     * @return      java.util.List<com.yunxin.cb.mall.vo.FinancialLoanBillVO>
     * @exception
     * @date        2018/8/8 17:04
     */
    @Override
    public List<FinancialLoanBillVO> getFinacialLiabilitiesBillByCustomerId(int customerId){
        List<FinancialLoanBill> list = finacialLiabilitiesBillMapper.selectByCustomerId(customerId);
        List<FinancialLoanBillVO> listvo=new ArrayList<>();
        list.stream().forEach(f ->{
            FinancialLoanBillVO vo = new FinancialLoanBillVO();
            BeanUtils.copyProperties(f, vo);
            listvo.add(vo);
        });
        return listvo;
    }

    /**
     * 获取分页信息
     * @author      likang
     * @param q
     * @return      com.yunxin.cb.util.page.PageFinder<com.yunxin.cb.mall.entity.FinancialLoanBill>
     * @exception
     * @date        2018/8/9 17:33
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PageFinder<FinancialLoanBill> page(Query q) {
        PageFinder<FinancialLoanBill> page = new PageFinder<FinancialLoanBill>(q.getPageNo(), q.getPageSize());
        List<FinancialLoanBill> list = null;
        long rowCount = 0L;
        try {
            //调用dao查询满足条件的分页数据
            list = finacialLiabilitiesBillMapper.pageList(q);
            //调用dao统计满足条件的记录总数
            rowCount = finacialLiabilitiesBillMapper.count(q);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        //如list为null时，则改为返回一个空列表
        list = list == null ? new ArrayList<FinancialLoanBill>(0) : list;
        //将分页数据和记录总数设置到分页结果对象中
        page.setData(list);
        page.setRowCount(rowCount);//记录总数
        page.setPageCount((int)rowCount);//总页数
        return page;
    }
}
