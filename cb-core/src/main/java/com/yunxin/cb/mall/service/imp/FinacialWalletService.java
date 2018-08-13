package com.yunxin.cb.mall.service.imp;


import com.yunxin.cb.mall.dao.*;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.CapitalType;
import com.yunxin.cb.mall.entity.meta.TransactionType;
import com.yunxin.cb.mall.service.IFinaciaWalletService;
import com.yunxin.core.persistence.AttributeReplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@Service
@Transactional
public class FinacialWalletService implements IFinaciaWalletService {

    private static final Logger logger = LoggerFactory.getLogger(FinacialWalletService.class);

    @Resource
    private FinacialWalletDao finacialWalletDao;

    @Resource
    private FinacialWalletLogDao finacialWalletLogDao;

    @Resource
    private FinacialExpectBillDao finacialExpectBillDao;

    @Resource
    private FinacialCreditLineBillDao FinacialCreditLineBillDao;

    /**
     * 方法实现说明
     * @author      likang
     * @param fw   钱包信息
     * @param type  0：保险 1：点赞
     * @param  amount 操作金额
     * @return      com.yunxin.cb.mall.entity.FinacialWallet
     * @exception
     * @date        2018/8/10 11:58
     */
    @Override
    public FinacialWallet addFinaciaWallet(FinacialWallet fw,int type, BigDecimal amount){
        logger.info("addFinaciaWallet:"+fw);
        Customer customer = fw.getCustomer();
        if(finacialWalletDao.findFinacialWalletByCustomerId(customer.getCustomerId())==null){
//            FinacialWallet addfw=new FinacialWallet(customer);
            /**钱包:初始化钱包信息*/
            finacialWalletDao.save(fw);
            logger.info("addFinaciaWallet sucess");
        }else{
            FinacialWallet oldfw = finacialWalletDao.findOne(fw.getWalletId());
            fw.setVersion(fw.getVersion()+1);
            /**钱包:更新钱包信息*/
            AttributeReplication.copying(fw, oldfw, FinacialWallet_.assets, FinacialWallet_.balance,FinacialWallet_.creditAmount,
                    FinacialWallet_.debtCredit,FinacialWallet_.debtExpected,FinacialWallet_.debtTotal,FinacialWallet_.expectedAmount,
                    FinacialWallet_.freezingAmount,FinacialWallet_.insuranceAmount,FinacialWallet_.totalAmount,
                    FinacialWallet_.version);
            FinacialWalletLog flog = new FinacialWalletLog();
            BeanUtils.copyProperties(fw,flog);
            flog.setFinacialWallet(fw);
            flog.setWalletLogId(null);
            flog.setType(0);
            flog.setAmount(amount);
            /**钱包:保存钱包日志记录*/
            finacialWalletLogDao.save(flog);
            logger.info("updateFinaciaWallet sucess");
            FinacialExpectBill finacialExpectBill = new FinacialExpectBill();
            finacialExpectBill.setAmount(amount);
            finacialExpectBill.setCreateTime(new Date());
            finacialExpectBill.setCustomer(customer);
            finacialExpectBill.setTransactionDesc(customer.getAccountName()+"买保险增加50%");
            finacialExpectBill.setTransactionType(TransactionType.INSURANCE_PURCHASE);
            finacialExpectBill.setType(CapitalType.ADD);
            FinacialCreditLineBill finacialCreditLineBill = new FinacialCreditLineBill();
            BeanUtils.copyProperties(finacialExpectBill,finacialCreditLineBill);
            finacialCreditLineBill.setTransactionDesc(customer.getAccountName()+"点赞增加5%");
            //保险购买
            if(type==0){
                /**保险:保存预期收益记录*/
                finacialExpectBillDao.save(finacialExpectBill);
                logger.info("add FinacialExpectBill sucess:"+finacialExpectBill);
                /**点赞:保存额度记录*/
                FinacialCreditLineBillDao.save(finacialCreditLineBill);
                logger.info("add finacialCreditLineBill sucess:"+finacialCreditLineBill);
            }else{
                /**点赞:保存额度记录*/
                FinacialCreditLineBillDao.save(finacialCreditLineBill);
                logger.info("add finacialCreditLineBill sucess:"+finacialCreditLineBill);
            }
        }
        return fw;
    }


    @Override
    public FinacialWallet updateFinacialWallet(FinacialWallet fw){
        return null;
    }

    @Override
    public FinacialWallet getFinacialWalletByCustomerId(int customerId) {
        return finacialWalletDao.findFinacialWalletByCustomerId(customerId);
    }

}
