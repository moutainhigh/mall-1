package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.FinancialCashbackLogDao;
import com.yunxin.cb.mall.entity.FinancialCashbackLog;
import com.yunxin.cb.mall.entity.FinancialCashbackLog_;
import com.yunxin.cb.mall.service.IFinancialCashbackLogService;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.List;

@Service
public class FinancialCashbackLogService implements IFinancialCashbackLogService {
    @Resource
    private FinancialCashbackLogDao financialCashbackLogDao;
    @Override
    public Page<FinancialCashbackLog> pageFinancialCashbackLog(PageSpecification<FinancialCashbackLog> query) {
        query.setCustomSpecification(new CustomSpecification<FinancialCashbackLog>(){
            @Override
            public void buildFetch(Root<FinancialCashbackLog> root) {
                root.fetch(FinancialCashbackLog_.customer, JoinType.LEFT);
            }
            @Override
            public void addConditions(Root<FinancialCashbackLog> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {

                query.orderBy(builder.asc(root.get(FinancialCashbackLog_.createTime)));
            }
        });
        return financialCashbackLogDao.findAll(query,query.getPageRequest());
    }
}
