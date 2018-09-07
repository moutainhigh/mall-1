package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.FinancialLoanBillDao;
import com.yunxin.cb.mall.entity.FinancialLoanBill;
import com.yunxin.cb.mall.entity.FinancialLoanBill_;
import com.yunxin.cb.mall.service.IFinancialLoanBillService;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.List;

@Service
@Transactional
public class FinancialLoanBillService implements IFinancialLoanBillService {
    @Resource
    private FinancialLoanBillDao financialLoanBillDao;

    @Override
    public Page<FinancialLoanBill> pageServiceFinancialLoanBill(PageSpecification<FinancialLoanBill> pageSpecification) {
        pageSpecification.setCustomSpecification(new CustomSpecification<FinancialLoanBill>() {
            public void buildFetch(Root<FinancialLoanBill> root) {
                root.fetch(FinancialLoanBill_.customer, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<FinancialLoanBill> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(FinancialLoanBill_.createTime)));
            }
        });
        Page<FinancialLoanBill> pages = financialLoanBillDao.findAll(pageSpecification, pageSpecification.getPageRequest());
        return pages;
    }
}
