package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.*;
import com.yunxin.cb.mall.entity.FinancialWallet;
import com.yunxin.cb.mall.entity.FinancialWalletLog;
import com.yunxin.cb.mall.entity.FinancialWallet_;
import com.yunxin.cb.mall.entity.OperationType;
import com.yunxin.cb.mall.service.IFinancialWalletService;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class FinancialWalletService implements IFinancialWalletService {

    private static final Logger logger = LoggerFactory.getLogger(FinancialWalletService.class);

    @Resource
    private FinancialWalletDao financialWalletDao;
    @Resource
    private FinancialWalletLogDao financialWalletLogDao;
    @Resource
    private CustomerDao customerDao;


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

    @Override
    @Transactional
    public FinancialWallet getFinancialWalletByCustomerId(Integer customerId) {

        FinancialWallet wallet = financialWalletDao.findByCustomerId(customerId);
        if (wallet == null) {
            //初始化钱包信息
            wallet = new FinancialWallet();
            wallet.setCustomer(customerDao.findByCustomerId(customerId));
            wallet.setDebtCredit(new BigDecimal(0));
            wallet.setDebtCar(new BigDecimal(0));
            wallet.setCreditAmount(new BigDecimal(0));
            wallet.setFreezingAmount(new BigDecimal(0));
            wallet.setVersion(0);//初始化版本号
            wallet.setFrequency(0);
            wallet.setSumAmount(new BigDecimal(0));
            wallet.setLoanInterest(new BigDecimal(0));
            wallet = financialWalletDao.save(wallet);
        }
        return wallet;
    }

    @Override
    @Transactional
    public boolean updateFinancialWalletOnVersion(FinancialWallet wallet, BigDecimal amount, OperationType type, String remark) {

        int result = financialWalletDao.updateFinancialWalletOnVersion(wallet);
        if (result == 1) {
            // 钱包变动日志
            FinancialWalletLog financialWalletlog = new FinancialWalletLog();
            BeanUtils.copyProperties(wallet, financialWalletlog);
            financialWalletlog.setFinancialWallet(wallet);
            financialWalletlog.setAmount(amount);
            financialWalletlog.setType(type);
            financialWalletlog.setRemark(remark);
            financialWalletLogDao.save(financialWalletlog);
        }

        return result  == 1;
    }

}
