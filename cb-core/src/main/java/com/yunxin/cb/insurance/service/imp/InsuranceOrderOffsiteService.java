package com.yunxin.cb.insurance.service.imp;

import com.yunxin.cb.insurance.dao.InsuranceOrderOffsiteDao;
import com.yunxin.cb.insurance.entity.InsuranceOrderOffsite;
import com.yunxin.cb.insurance.service.IInsuranceOrderOffsiteService;
import org.springframework.stereotype.Service;



import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class InsuranceOrderOffsiteService implements IInsuranceOrderOffsiteService{
    @Resource
    private InsuranceOrderOffsiteDao insuranceOrderOffsiteDao;

    @Override
    @Transactional
    public InsuranceOrderOffsite upInsuranceOrderOffsite(InsuranceOrderOffsite sensue) {
        InsuranceOrderOffsite insuranceOrderOffsite=insuranceOrderOffsiteDao.findOne(sensue.getOffsiteId());
        insuranceOrderOffsite.setOtherMatter(sensue.getOtherMatter());
        insuranceOrderOffsite.setSensue(sensue.getSensue());
        insuranceOrderOffsite.setWorkplace(sensue.getWorkplace());
        insuranceOrderOffsite.setStayTime(sensue.getStayTime());
        insuranceOrderOffsite.setLeaveReason(sensue.getLeaveReason());
        insuranceOrderOffsite.setOffsiteAddress(sensue.getOffsiteAddress());
        return insuranceOrderOffsite;
    }
}
