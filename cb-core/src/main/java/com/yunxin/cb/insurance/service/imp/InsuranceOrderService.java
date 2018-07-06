package com.yunxin.cb.insurance.service.imp;

import com.yunxin.cb.insurance.dao.*;
import com.yunxin.cb.insurance.entity.InsuranceOrder;
import com.yunxin.cb.insurance.entity.InsuranceOrderBeneficiary;
import com.yunxin.cb.insurance.entity.InsuranceOrderInformedMatter;
import com.yunxin.cb.insurance.entity.InsuranceOrder_;
import com.yunxin.cb.insurance.service.IInsuranceOrderService;
import com.yunxin.cb.mall.entity.meta.InsuranceOrderState;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Date;
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
        insuranceOrder.setOrderCode("INS2018070600105");
        insuranceOrder.setCreateTime(new Date());
        insuranceOrderInsuredDao.save(insuranceOrder.getInsuranceOrderInsured());
        insuranceOrderPolicyholderDao.save(insuranceOrder.getInsuranceOrderPolicyholder());
        insuranceOrderPolicyholderBankDao.save(insuranceOrder.getInsuranceOrderPolicyholderBank());
        insuranceOrder = insuranceOrderDao.save(insuranceOrder);
        insuranceOrder.setOrderCode(insuranceOrder.getOrderCode()+insuranceOrder.getOrderId());
        insuranceOrder.setContractNo(insuranceOrder.getOrderCode());
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

    @Override
    public boolean updInsuranceOrderState(int orderId,InsuranceOrderState orderState) {
        return false;
    }

    /**
     * 保单分页
     * @param query
     * @return
     */
    @Override
    public Page<InsuranceOrder> pageInsuranceOrder(PageSpecification<InsuranceOrder> query) {
        query.setCustomSpecification(new CustomSpecification<InsuranceOrder>(){
            @Override
            public void buildFetch(Root<InsuranceOrder> root) {
                root.fetch(InsuranceOrder_.insuranceOrderPolicyholder, JoinType.LEFT);
                root.fetch(InsuranceOrder_.insuranceProduct,JoinType.LEFT);
                root.fetch(InsuranceOrder_.insuranceProductPrice,JoinType.LEFT);
            }
        });
        return insuranceOrderDao.findAll(query,query.getPageRequest());
    }

    /**
     * 保单详情
     * @param orderId
     * @return
     */
    @Override
    public InsuranceOrder getInsuranceOrderDetailById(int orderId) {
        InsuranceOrder InsuranceOrder = insuranceOrderDao.getInsuranceOrderDetailById(orderId);
        return InsuranceOrder;
    }
}
