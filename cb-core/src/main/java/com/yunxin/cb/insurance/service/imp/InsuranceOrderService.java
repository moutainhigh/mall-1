package com.yunxin.cb.insurance.service.imp;

import com.yunxin.cb.insurance.dao.InsuranceOrderDao;
import com.yunxin.cb.insurance.entity.InsuranceOrder;
import com.yunxin.cb.insurance.entity.InsuranceOrder_;
import com.yunxin.cb.insurance.service.IInsuranceOrderService;
import com.yunxin.cb.mall.entity.Brand;
import com.yunxin.cb.mall.entity.Brand_;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

/**
 * @author wangteng
 *
 */
@Service
public class InsuranceOrderService implements IInsuranceOrderService {
    @Resource
    private InsuranceOrderDao InsuranceOrderDao;
    /**
     * 保单分页
     * @param query
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<InsuranceOrder> pageInsuranceOrder(PageSpecification<InsuranceOrder> query) {
        query.setCustomSpecification(new CustomSpecification<InsuranceOrder>(){
            @Override
            public void buildFetch(Root<InsuranceOrder> root) {
                root.fetch(InsuranceOrder_.insuranceOrderPolicyholder, JoinType.LEFT);
                root.fetch(InsuranceOrder_.insuranceProduct,JoinType.LEFT);
                root.fetch(InsuranceOrder_.insuranceProductPrice,JoinType.LEFT);
            }
        });
        return InsuranceOrderDao.findAll(query,query.getPageRequest());
    }

    /**
     * 保单详情
     * @param orderId
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public InsuranceOrder getInsuranceOrderDetailById(int orderId) {
        InsuranceOrder InsuranceOrder=InsuranceOrderDao.getInsuranceOrderDetailById(orderId);
        return InsuranceOrder;
    }
}
