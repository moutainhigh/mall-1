package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.FinancialFreezingBill;
import com.yunxin.cb.mall.mapper.FinancialFreezingBillMapper;
import com.yunxin.cb.mall.service.FinancialFreezingBillService;
import com.yunxin.cb.mall.vo.FinancialFreezingBillVO;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FinancialFreezingBillServiceImpl implements FinancialFreezingBillService {

    @Resource
    private FinancialFreezingBillMapper financialFreezingBillMapper;

    private static final Log log = LogFactory.getLog(FinancialFreezingBillServiceImpl.class);

    /**
     * 添加
     *
     * @param vo
     * @return com.yunxin.cb.mall.vo.FinancialFreezingBillVO
     * @throws
     * @author likang
     * @date 2018/8/8 14:34
     */
    @Override
    public FinancialFreezingBillVO addFinancialFreezingBill(FinancialFreezingBillVO vo) {
        FinancialFreezingBill financialFreezingBill = new FinancialFreezingBill();
        BeanUtils.copyProperties(financialFreezingBill, vo);
        financialFreezingBillMapper.insert(financialFreezingBill);
        return vo;
    }

    /**
     * 查询记录
     *
     * @param customerId
     * @return java.util.List<com.yunxin.cb.mall.vo.FinancialFreezingBillVO>
     * @throws
     * @author likang
     * @date 2018/8/8 14:37
     */
    @Override
    public List<FinancialFreezingBillVO> getFinancialFreezingBillByCustomerId(int customerId) {
        List<FinancialFreezingBill> list = financialFreezingBillMapper.selectByCustomerId(customerId);
        List<FinancialFreezingBillVO> listvo=new ArrayList<>();
        list.stream().forEach(f ->{
            FinancialFreezingBillVO vo = new FinancialFreezingBillVO();
            BeanUtils.copyProperties(f, vo);
            listvo.add(vo);
        });
        return listvo;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PageFinder<FinancialFreezingBill> page(Query q) {
        PageFinder<FinancialFreezingBill> page = new PageFinder<FinancialFreezingBill>(q.getPageNo(), q.getPageSize());
        List<FinancialFreezingBill> list = null;
        long rowCount = 0L;
        try {
            //调用dao查询满足条件的分页数据
            list = financialFreezingBillMapper.pageList(q);
            //调用dao统计满足条件的记录总数
            rowCount = financialFreezingBillMapper.count(q);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        //如list为null时，则改为返回一个空列表
        list = list == null ? new ArrayList<>(0) : list;
        //将分页数据和记录总数设置到分页结果对象中
        page.setData(list);
        page.setRowCount(rowCount);//记录总数
        page.setPageCount((int)rowCount);//总页数
        return page;
    }
}
