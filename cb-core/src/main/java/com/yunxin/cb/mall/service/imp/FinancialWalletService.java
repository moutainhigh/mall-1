package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.*;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.CapitalType;
import com.yunxin.cb.mall.entity.meta.TransactionType;
import com.yunxin.cb.mall.service.IFinancialWalletService;
import com.yunxin.core.persistence.AttributeReplication;
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
import java.util.Date;
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
    private FinancialFreezingBillDao financialFreezingBillDao;
    @Resource
    private FinancialCreditLineBillDao FinancialCreditLineBillDao;
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

    /**
     * 方法实现说明
     * @author      likang
     * @param fw   钱包信息
     * @param type  0：保险 1：点赞
     * @param  amount 操作金额
     * @return      com.yunxin.cb.mall.entity.FinancialWallet
     * @exception
     * @date        2018/8/10 11:58
     */
    @Override
    public FinancialWallet addFinaciaWallet(FinancialWallet fw, int type, BigDecimal amount){
        logger.info("addFinaciaWallet:"+fw);
        Customer customer = fw.getCustomer();
        if(financialWalletDao.findByCustomerId(customer.getCustomerId())==null){
            /**钱包:初始化钱包信息*/
            financialWalletDao.save(fw);
            logger.info("addFinaciaWallet sucess");
        }else{
            updateFinacialWallet(fw);
            FinancialFreezingBill financialFreezingBill = new FinancialFreezingBill();
            financialFreezingBill.setAmount(amount);
            financialFreezingBill.setCreateTime(new Date());
            financialFreezingBill.setCustomer(customer);
            financialFreezingBill.setTransactionDesc(customer.getAccountName()+"买保险增加50%");
            financialFreezingBill.setTransactionType(TransactionType.INSURANCE_PURCHASE);
            financialFreezingBill.setType(CapitalType.ADD);
            FinancialCreditLineBill financialCreditLineBill = new FinancialCreditLineBill();
            BeanUtils.copyProperties(financialFreezingBill, financialCreditLineBill);
            financialCreditLineBill.setTransactionDesc(customer.getAccountName()+"点赞增加5%");
            //保险购买
            if(type==0){
                /**保险:保存预期收益记录*/
                financialFreezingBillDao.save(financialFreezingBill);
                logger.info("add FinancialFreezingBill sucess:"+financialFreezingBill);
                /**点赞:保存额度记录*/
                FinancialCreditLineBillDao.save(financialCreditLineBill);
                logger.info("add financialCreditLineBill sucess:"+ financialCreditLineBill);
            }else{
                /**点赞:保存额度记录*/
                FinancialCreditLineBillDao.save(financialCreditLineBill);
                logger.info("add financialCreditLineBill sucess:"+ financialCreditLineBill);
            }
        }
        return fw;
    }


    /**
     * 修改钱包信息
     * @author      likang
     * @param fw
     * @return      com.yunxin.cb.mall.entity.FinancialWallet
     * @exception
     * @date        2018/8/13 11:34
     */
    @Override
    public FinancialWallet updateFinacialWallet(FinancialWallet fw){
        FinancialWallet oldfw = financialWalletDao.findOne(fw.getWalletId());
        if(fw.getVersion()!=fw.getVersion()){
            throw new RuntimeException("用户版本号不正确，请稍后再试");
        }
        fw.setVersion(fw.getVersion()+1);
        /**钱包:更新钱包信息*/
        AttributeReplication.copying(fw, oldfw, FinancialWallet_.creditAmount,
                FinancialWallet_.debtCredit,
                FinancialWallet_.freezingAmount,
                FinancialWallet_.version);
        FinancialWalletLog flog = new FinancialWalletLog();
        BeanUtils.copyProperties(fw,flog);
        flog.setFinancialWallet(fw);
        flog.setWalletLogId(null);
//        flog.setType();
        flog.setAmount(new BigDecimal(0));
        /**钱包:保存钱包日志记录*/
        financialWalletLogDao.save(flog);
        logger.info("updateFinaciaWallet sucess");
        return fw;
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
