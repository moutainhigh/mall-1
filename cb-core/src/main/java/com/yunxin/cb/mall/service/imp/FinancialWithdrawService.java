package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.FinancialWithdrawDao;
import com.yunxin.cb.mall.entity.FinancialWithdraw;
import com.yunxin.cb.mall.entity.FinancialWithdraw_;
import com.yunxin.cb.mall.service.IFinancialWithdrawService;
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
public class FinancialWithdrawService implements IFinancialWithdrawService {

    private static final Logger logger = LoggerFactory.getLogger(FinancialWithdrawService.class);

    @Resource
    private FinancialWithdrawDao financialWithdrawDao;

    @Override
    public Page<FinancialWithdraw> pageServiceFinancialWithdraw(PageSpecification<FinancialWithdraw> pageSpecification) {
        pageSpecification.setCustomSpecification(new CustomSpecification<FinancialWithdraw>() {
            public void buildFetch(Root<FinancialWithdraw> root) {
                root.fetch(FinancialWithdraw_.customer, JoinType.LEFT);
                root.fetch(FinancialWithdraw_.bank, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<FinancialWithdraw> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(FinancialWithdraw_.applyDate)));
            }
        });
        Page<FinancialWithdraw> pages = financialWithdrawDao.findAll(pageSpecification, pageSpecification.getPageRequest());
        return pages;
    }

    @Override
    @Transactional
    public int tansfer(String ids) throws Exception {
        return financialWithdrawDao.tansfer(ids);
    }
}
