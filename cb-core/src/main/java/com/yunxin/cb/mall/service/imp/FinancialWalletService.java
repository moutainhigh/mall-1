package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.FinancialWalletDao;
import com.yunxin.cb.mall.entity.FinancialWallet;
import com.yunxin.cb.mall.entity.FinancialWallet_;
import com.yunxin.cb.mall.service.IFinancialWalletService;
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
public class FinancialWalletService implements IFinancialWalletService {
    @Resource
    private FinancialWalletDao financialWalletDao;

    @Override
    public Page<FinancialWallet> pageServiceFinancialWallet(PageSpecification<FinancialWallet> pageSpecification) {
        pageSpecification.setCustomSpecification(new CustomSpecification<FinancialWallet>() {
            public void buildFetch(Root<FinancialWallet> root) {
                root.fetch(FinancialWallet_.customer, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<FinancialWallet> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(FinancialWallet_.walletId)));
            }
        });
        Page<FinancialWallet> pages = financialWalletDao.findAll(pageSpecification, pageSpecification.getPageRequest());
        return pages;
    }
}
