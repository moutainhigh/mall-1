package com.yunxin.cb.insurance.service;

import com.yunxin.cb.insurance.entity.InsuranceOrder;
import com.yunxin.cb.insurance.entity.InsuranceOrderCode;
import com.yunxin.cb.mall.entity.meta.InsuranceOrderState;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @author likang
 *
 */
public interface IInsuranceOrderCodeService {


    /**
     * 唯一校验
     * @param codeNo
     * @return
     */
    public InsuranceOrderCode getInsuranceOrderCodeByCodeNo(String codeNo);

    /**
     * 添加
     * @param insuranceOrderCode
     */
    public void addInsuranceOrderCode(InsuranceOrderCode insuranceOrderCode);

    /**
     * 分页
     * @param query
     * @return
     */
    Page<InsuranceOrderCode> pageInsuranceOrderCode(PageSpecification<InsuranceOrderCode> query);

}
