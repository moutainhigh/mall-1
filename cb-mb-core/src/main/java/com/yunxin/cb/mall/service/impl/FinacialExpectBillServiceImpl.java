package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.FinacialExpectBill;
import com.yunxin.cb.mall.entity.FinacialWallet;
import com.yunxin.cb.mall.mapper.FinacialExpectBillMapper;
import com.yunxin.cb.mall.service.FinacialExpectBillService;
import com.yunxin.cb.mall.vo.FinacialExpectBillVO;
import com.yunxin.cb.mall.vo.FinacialWalletVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FinacialExpectBillServiceImpl implements FinacialExpectBillService {

    @Resource
    private FinacialExpectBillMapper finacialExpectBillMapper;

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
}
