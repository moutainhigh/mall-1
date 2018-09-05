package com.yunxin.cb.mall.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yunxin.cb.mall.entity.FinancialLoanBill;
import com.yunxin.cb.mall.mapper.FinancialLoanBillMapper;
import com.yunxin.cb.mall.service.FinancialLoanBillService;
import com.yunxin.cb.mall.vo.FinancialLoanBillVO;
import com.yunxin.cb.util.page.PageFinder;
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
    private FinancialLoanBillMapper financialLoanBillMapper;

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
        financialLoanBillMapper.insert(financialLoanBill);
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
        List<FinancialLoanBill> list = financialLoanBillMapper.selectByCustomerId(customerId);
        List<FinancialLoanBillVO> listvo=new ArrayList<>();
        list.stream().forEach(f ->{
            FinancialLoanBillVO vo = new FinancialLoanBillVO();
            BeanUtils.copyProperties(f, vo);
            listvo.add(vo);
        });
        return listvo;
    }

    /**
     * @Author chenpeng
     * @Description 获取 负债记录 分页信息
     * @Date 2018/9/5 11:10 
     **/
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PageFinder<FinancialLoanBill> page(Integer customerId, Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Page<FinancialLoanBill> page = financialLoanBillMapper.pageListByCustomer(customerId);
        PageFinder<FinancialLoanBill> pageFinder = new PageFinder<>(pageNo, pageSize, page.getTotal());
        pageFinder.setData(page.getResult());
        return pageFinder;
    }
}
