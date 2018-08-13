package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.FinacialWithdrawDao;
import com.yunxin.cb.mall.entity.FinacialWithdraw;
import com.yunxin.cb.mall.entity.FinacialWithdraw_;
import com.yunxin.cb.mall.service.IFinacialWithdrawService;
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
public class FinacialWithdrawService implements IFinacialWithdrawService {

    private static final Logger logger = LoggerFactory.getLogger(FinacialWithdrawService.class);

    @Resource
    private FinacialWithdrawDao finacialWithdrawDao;

    @Override
    public Page<FinacialWithdraw> pageServiceFinacialWithdraw(PageSpecification<FinacialWithdraw> pageSpecification) {
        pageSpecification.setCustomSpecification(new CustomSpecification<FinacialWithdraw>() {
            public void buildFetch(Root<FinacialWithdraw> root) {
                root.fetch(FinacialWithdraw_.customer, JoinType.LEFT);
                root.fetch(FinacialWithdraw_.bank, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<FinacialWithdraw> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(FinacialWithdraw_.applyDate)));
            }
        });
        Page<FinacialWithdraw> pages = finacialWithdrawDao.findAll(pageSpecification, pageSpecification.getPageRequest());
        return pages;
    }

    @Override
    @Transactional
    public int tansfer(String ids) throws Exception {
        return finacialWithdrawDao.tansfer(ids);
    }
}
