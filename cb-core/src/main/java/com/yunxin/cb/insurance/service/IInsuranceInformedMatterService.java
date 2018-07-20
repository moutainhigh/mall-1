package com.yunxin.cb.insurance.service;

import com.yunxin.cb.insurance.entity.InsuranceInformedMatter;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author likang
 *
 */
public interface IInsuranceInformedMatterService {


    /**
     * 分页
     * @param query
     * @return
     */
    Page<InsuranceInformedMatter> pageInsuranceInformedMatter(PageSpecification<InsuranceInformedMatter> query);


    Page<InsuranceInformedMatter> pageaddMatter(PageSpecification<InsuranceInformedMatter> query);
    /**
     * 获取详情
     * @param metterId
     * @return
     */
    public InsuranceInformedMatter getInsuranceInformedMatter(int metterId);


    /**
     *
     * @param insuranceInformedMatter
     * @return
     */
    InsuranceInformedMatter addInsuranceInformedMatter(InsuranceInformedMatter insuranceInformedMatter);

    /**
     *
     * @param metterId
     */
    public void removeById(int metterId);

    /**
     *
     * @param insuranceInformedMatter
     * @return
     */
    public InsuranceInformedMatter update(InsuranceInformedMatter insuranceInformedMatter);

    /**
     *
     * @return
     */
    public List<InsuranceInformedMatter> getInsuranceInformedMatterList();

    /**
     *
     * @param matterId
     * @param enabled
     */
    public void enableInformedMatterById(int matterId,int enabled);



}
