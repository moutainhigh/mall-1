package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.BankInfo;
import com.yunxin.cb.mall.mapper.BankInfoMapper;
import com.yunxin.cb.mall.service.BankInfoService;
import com.yunxin.cb.mall.vo.BankInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BankInfoServiceImpl implements BankInfoService {
    @Resource
    private BankInfoMapper bankInfoMapper;

    /**
     * 根据bankid 查询BankInfoVO
     * @param bankId
     * @return com.yunxin.cb.mall.entity.BankInfo
     * @throws
     * @author likang
     * @date 2018/7/31 11:30
     */
    public BankInfoVO selectByPrimaryKey(Integer bankId,Integer customerId) {
        BankInfoVO bankInfoVO = new BankInfoVO();
        BankInfo bankInfo = bankInfoMapper.selectByPrimaryKey(bankId,customerId);
        BeanUtils.copyProperties(bankInfo, bankInfoVO);
        return bankInfoVO;
    }

    /**
     * 根据customerId查询List<BankInfoVO>
     * @author      likang
     * @param customerId
     * @return      java.util.List<com.yunxin.cb.mall.vo.BankInfoVO>
     * @exception
     * @date        2018/7/31 11:35
     */
    public List<BankInfoVO> selectAll(Integer customerId) {
        List<BankInfoVO> listVO=new ArrayList<>();
        List<BankInfo> list = bankInfoMapper.selectAll(customerId);
        list.stream().forEach(p ->{
            BankInfoVO bankInfoVO = new BankInfoVO();
            BeanUtils.copyProperties(p, bankInfoVO);
            listVO.add(bankInfoVO);
        });
        return listVO;
    }

    /**
     * 添加银行卡
     * @author      likang
     * @param bankInfo
     * @return      int
     * @exception
     * @date        2018/7/31 11:37
     */
    public int insert(BankInfo bankInfo){
        return bankInfoMapper.insert(bankInfo);
    }

    /**
     * 删除银行卡信息
     * @author      likang
     * @param bankId
     * @return      int
     * @exception
     * @date        2018/7/31 11:40
     */
    public int deleteByPrimaryKey(Integer bankId,Integer customerId){
        return bankInfoMapper.deleteByPrimaryKey(bankId,customerId);
    }
}
