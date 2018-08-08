package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.FinacialExpectBill;
import com.yunxin.cb.mall.entity.FinacialLiabilitiesBill;
import com.yunxin.cb.mall.mapper.FinacialLiabilitiesBillMapper;
import com.yunxin.cb.mall.service.FinacialLiabilitiesBillService;
import com.yunxin.cb.mall.vo.FinacialExpectBillVO;
import com.yunxin.cb.mall.vo.FinacialLiabilitiesBillVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FinacialLiabilitiesBillServiceImpl implements FinacialLiabilitiesBillService {

    @Resource
    private FinacialLiabilitiesBillMapper finacialLiabilitiesBillMapper;
    /**
     * 添加
     * @author      likang
     * @param vo
     * @return      com.yunxin.cb.mall.vo.FinacialLiabilitiesBillVO
     * @exception
     * @date        2018/8/8 16:59
     */
    @Override
    public FinacialLiabilitiesBillVO addFinacialLiabilitiesBill(FinacialLiabilitiesBillVO vo){
        FinacialLiabilitiesBill finacialLiabilitiesBill = new FinacialLiabilitiesBill();
        BeanUtils.copyProperties(finacialLiabilitiesBill, vo);
        finacialLiabilitiesBillMapper.insert(finacialLiabilitiesBill);
        return vo;
    }

    /**
     * 获取记录
     * @author      likang
     * @param customerId
     * @return      java.util.List<com.yunxin.cb.mall.vo.FinacialLiabilitiesBillVO>
     * @exception
     * @date        2018/8/8 17:04
     */
    @Override
    public List<FinacialLiabilitiesBillVO> getFinacialLiabilitiesBillByCustomerId(int customerId){
        List<FinacialLiabilitiesBill> list = finacialLiabilitiesBillMapper.selectByCustomerId(customerId);
        List<FinacialLiabilitiesBillVO> listvo=new ArrayList<>();
        list.stream().forEach(f ->{
            FinacialLiabilitiesBillVO vo = new FinacialLiabilitiesBillVO();
            BeanUtils.copyProperties(f, vo);
            listvo.add(vo);
        });
        return listvo;
    }
}
