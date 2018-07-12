package com.yunxin.cb.insurance.service;

import com.yunxin.cb.insurance.entity.InsuranceInformedMatter;
import com.yunxin.cb.insurance.entity.InsuranceInformedMatterGroup;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

/**
 * @author likang
 *
 */
public interface IInsuranceInformedMatterGroupService {


    /**
     * 分页
     * @param query
     * @return
     */
    Page<InsuranceInformedMatterGroup> pageInsuranceInformedMatterGroup(PageSpecification<InsuranceInformedMatterGroup> query);

    /**
     * 获取详情
     * @param groupId
     * @return
     */
    public InsuranceInformedMatterGroup getInsuranceInformedMatterGroup(int groupId);


    /**
     *
     * @param insuranceInformedMatterGroup
     * @return
     */
    InsuranceInformedMatterGroup addInsuranceInformedMatterGroup(InsuranceInformedMatterGroup insuranceInformedMatterGroup);

    /**
     *
     * @param metterId
     */
    public void removeByid(int metterId);

    /**
     *
     * @param insuranceInformedMatterGroup
     * @return
     */
    public InsuranceInformedMatterGroup update(InsuranceInformedMatterGroup insuranceInformedMatterGroup);

}
