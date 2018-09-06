package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.FinancialLoanDao;
import com.yunxin.cb.mall.entity.FinancialLoan;
import com.yunxin.cb.mall.entity.FinancialLoan_;
import com.yunxin.cb.mall.entity.meta.LoanState;
import com.yunxin.cb.mall.service.IFinancialLoanService;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FinancialLoanService implements IFinancialLoanService {

    @Resource
    private FinancialLoanDao financialLoanDao;

    @Override
    public Page<FinancialLoan> pageServiceFinancialLoan(PageSpecification<FinancialLoan> pageSpecification,int state) {
        pageSpecification.setCustomSpecification(new CustomSpecification<FinancialLoan>() {
            public void buildFetch(Root<FinancialLoan> root) {
                root.fetch(FinancialLoan_.customer, JoinType.LEFT);
                root.fetch(FinancialLoan_.bank, JoinType.LEFT);
            }
            @Override
            public void addConditions(Root<FinancialLoan> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                switch (state){
                    case 1:
                        Predicate p1 = builder.equal(root.get(FinancialLoan_.state), LoanState.WAIT_LOAN);
                        Predicate p2 = builder.equal(root.get(FinancialLoan_.state), LoanState.APPLY_SUCCESS);
                        predicates.add( builder.or(p1,p2));
                        break;
                }
                query.orderBy(builder.desc(root.get(FinancialLoan_.createTime)));
            }
        });
        Page<FinancialLoan> pages = financialLoanDao.findAll(pageSpecification, pageSpecification.getPageRequest());
        return pages;
    }

    @Override
    public FinancialLoan getFinancialLoanDetailById(Integer loanId) {
        return financialLoanDao.getFinancialLoanDetailById(loanId);
    }

    @Override
    public Map<String, Object> undateState(Integer loanId ,LoanState state) throws Exception{
        Map<String,Object> map = new HashMap<>();
        FinancialLoan financialLoan = financialLoanDao.findOne(loanId);
        //TODO 还需把数据同步到负债记录表里
        if(financialLoan.getState().ordinal() == state.ordinal()){
            financialLoanDao.updateFinancialLoanStateById(LoanState.APPLY_TRANSFERRED,loanId,new Date());
            map.put("result","success");
            return map;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFinancialLoan(FinancialLoan financialLoan)throws Exception {
        financialLoanDao.updateFinancialLoan(financialLoan.getState(),financialLoan.getLoanId(),new Date(),financialLoan.getAuditRemark());

    }
}
