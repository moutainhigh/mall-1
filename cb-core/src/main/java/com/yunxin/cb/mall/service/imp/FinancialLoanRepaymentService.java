package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.FinancialLoanRepaymentDao;
import com.yunxin.cb.mall.entity.FinancialLoanRepayment;
import com.yunxin.cb.mall.entity.FinancialLoanRepayment_;
import com.yunxin.cb.mall.service.IFinancialLoanRepaymentService;
import com.yunxin.cb.rb.entity.meta.LoanRepaymentType;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.List;

@Service
@Transactional
public class FinancialLoanRepaymentService implements IFinancialLoanRepaymentService {

    private static final Logger logger = LoggerFactory.getLogger(FinancialLoanRepaymentService.class);

    @Resource
    private FinancialLoanRepaymentDao financialLoanRepaymentDao;

    @Override
    public Page<FinancialLoanRepayment> pageServiceFinancialLoanRepayment(PageSpecification<FinancialLoanRepayment> pageSpecification,Integer loanRepayMentType) {
        pageSpecification.setCustomSpecification(new CustomSpecification<FinancialLoanRepayment>() {
            public void buildFetch(Root<FinancialLoanRepayment> root) {
                root.fetch(FinancialLoanRepayment_.customer, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<FinancialLoanRepayment> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                switch (loanRepayMentType){
                    case 0:
                        predicates.add(builder.equal(root.get(FinancialLoanRepayment_.loanRepaymentType), LoanRepaymentType.INSURANCE_REBATE_REPAYMENT));
                        break;
                    case 1:
                        predicates.add(builder.equal(root.get(FinancialLoanRepayment_.loanRepaymentType), LoanRepaymentType.COMMODITY_REIMBURESE_REPAYMENT));
                        break;
                    case 2:
                        predicates.add(builder.equal(root.get(FinancialLoanRepayment_.loanRepaymentType), LoanRepaymentType.MANUAL_REIMBURSEMENT_REPAYMENT));
                        break;
                    case 3:
                        predicates.add(builder.equal(root.get(FinancialLoanRepayment_.loanRepaymentType), LoanRepaymentType.CAR_REBATE_REPAYMENT));
                        break;
                }
                query.orderBy(builder.desc(root.get(FinancialLoanRepayment_.createTime)));
            }
        });
        Page<FinancialLoanRepayment> pages = financialLoanRepaymentDao.findAll(pageSpecification, pageSpecification.getPageRequest());
        return pages;
    }

}
