package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.FinacialExpectBill;
import com.yunxin.cb.mall.mapper.FinacialExpectBillMapper;
import com.yunxin.cb.mall.service.FinacialExpectBillService;
import com.yunxin.cb.mall.vo.FinacialExpectBillVO;
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
public class FinacialExpectBillServiceImpl implements FinacialExpectBillService {

    @Resource
    private FinacialExpectBillMapper finacialExpectBillMapper;

    private static final Log log = LogFactory.getLog(FinacialExpectBillServiceImpl.class);

    /**
     * 添加
     *
     * @param vo
     * @return com.yunxin.cb.mall.vo.FinacialExpectBillVO
     * @throws
     * @author likang
     * @date 2018/8/8 14:34
     */
    @Override
    public FinacialExpectBillVO addFinacialExpectBill(FinacialExpectBillVO vo) {
        FinacialExpectBill finacialExpectBill = new FinacialExpectBill();
        BeanUtils.copyProperties(finacialExpectBill, vo);
        finacialExpectBillMapper.insert(finacialExpectBill);
        return vo;
    }

    /**
     * 查询记录
     *
     * @param customerId
     * @return java.util.List<com.yunxin.cb.mall.vo.FinacialExpectBillVO>
     * @throws
     * @author likang
     * @date 2018/8/8 14:37
     */
    @Override
    public List<FinacialExpectBillVO> getFinacialExpectBillByCustomerId(int customerId) {
        List<FinacialExpectBill> list = finacialExpectBillMapper.selectByCustomerId(customerId);
        List<FinacialExpectBillVO> listvo=new ArrayList<>();
        list.stream().forEach(f ->{
            FinacialExpectBillVO vo = new FinacialExpectBillVO();
            BeanUtils.copyProperties(f, vo);
            listvo.add(vo);
        });
        return listvo;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PageFinder<FinacialExpectBill> page(Query q) {
        PageFinder<FinacialExpectBill> page = new PageFinder<FinacialExpectBill>(q.getPageNo(), q.getPageSize());
        List<FinacialExpectBill> list = null;
        long rowCount = 0L;
        try {
            //调用dao查询满足条件的分页数据
            list = finacialExpectBillMapper.pageList(q);
            //调用dao统计满足条件的记录总数
            rowCount = finacialExpectBillMapper.count(q);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        //如list为null时，则改为返回一个空列表
        list = list == null ? new ArrayList<FinacialExpectBill>(0) : list;
        //将分页数据和记录总数设置到分页结果对象中
        page.setData(list);
        page.setRowCount(rowCount);//记录总数
        page.setPageCount((int)rowCount);//总页数
        return page;
    }
}
