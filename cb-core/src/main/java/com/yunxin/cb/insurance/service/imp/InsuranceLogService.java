package com.yunxin.cb.insurance.service.imp;

import com.yunxin.cb.insurance.dao.InsuranceLogDao;
import com.yunxin.cb.insurance.entity.InsuranceLog;
import com.yunxin.cb.insurance.entity.InsuranceLog_;
import com.yunxin.cb.insurance.service.IInsuranceLogService;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class InsuranceLogService  implements IInsuranceLogService {
    @Resource
    private InsuranceLogDao insuranceLogDao;
    @Override
    public Page<InsuranceLog> pageInsuranceLog(PageSpecification<InsuranceLog> query) {
        query.setCustomSpecification(new CustomSpecification<InsuranceLog>(){

            @Override
            public void addConditions(Root<InsuranceLog> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(InsuranceLog_.createTime)));
            }
        });
        return insuranceLogDao.findAll(query,query.getPageRequest());
    }
}
