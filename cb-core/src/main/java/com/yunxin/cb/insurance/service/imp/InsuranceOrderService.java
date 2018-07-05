package com.yunxin.cb.insurance.service.imp;

import com.yunxin.cb.insurance.dao.*;
import com.yunxin.cb.insurance.entity.InsuranceOrder;
import com.yunxin.cb.insurance.entity.InsuranceOrderBeneficiary;
import com.yunxin.cb.insurance.entity.InsuranceOrderInformedMatter;
import com.yunxin.cb.insurance.service.IInsuranceOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class InsuranceOrderService implements IInsuranceOrderService {

    @Resource
    private InsuranceOrderDao insuranceOrderDao;
    @Resource
    private InsuranceOrderBeneficiaryDao insuranceOrderBeneficiaryDao;
    @Resource
    private InsuranceOrderInformedMatterDao insuranceOrderInformedMatterDao;
    @Resource
    private InsuranceOrderInsuredDao insuranceOrderInsuredDao;
    @Resource
    private InsuranceOrderPolicyholderBankDao insuranceOrderPolicyholderBankDao;
    @Resource
    private InsuranceOrderPolicyholderDao insuranceOrderPolicyholderDao;




    /**
     * 根据用户ID查询保险订单列表
     * @return
     */
    public List<InsuranceOrder> getInsuranceOrderByCustomer() {
        return insuranceOrderDao.findAll();
    }

    /**
     * 添加保险订单
     * @param insuranceOrder
     * @return
     */
    @Override
    @Transactional
    public InsuranceOrder addInsuranceOrder(InsuranceOrder insuranceOrder) {

        //InsuranceOrderInsured insuranceOrderInsured =

        insuranceOrderInsuredDao.save(insuranceOrder.getInsuranceOrderInsured());
        insuranceOrderPolicyholderDao.save(insuranceOrder.getInsuranceOrderPolicyholder());
        insuranceOrderPolicyholderBankDao.save(insuranceOrder.getInsuranceOrderPolicyholderBank());
        insuranceOrderPolicyholderDao.save(insuranceOrder.getInsuranceOrderPolicyholder());
        insuranceOrder = insuranceOrderDao.save(insuranceOrder);

        Set<InsuranceOrderInformedMatter> insuranceOrderInformedMatters = insuranceOrder.getInsuranceOrderInformedMatters();
        for(InsuranceOrderInformedMatter insuranceOrderInformedMatter: insuranceOrderInformedMatters)
        {
            insuranceOrderInformedMatter.setInsuranceOrder(insuranceOrder);
        }
        insuranceOrderInformedMatterDao.save(insuranceOrderInformedMatters);

        Set<InsuranceOrderBeneficiary> insuranceOrderBeneficiarys = insuranceOrder.getInsuranceOrderBeneficiarys();
        for(InsuranceOrderBeneficiary insuranceOrderBeneficiary: insuranceOrderBeneficiarys)
        {
            insuranceOrderBeneficiary.setInsuranceOrder(insuranceOrder);
        }
        insuranceOrderBeneficiaryDao.save(insuranceOrderBeneficiarys);

        return insuranceOrder;
    }
}
