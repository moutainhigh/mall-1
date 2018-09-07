package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.*;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.CapitalType;
import com.yunxin.cb.mall.entity.meta.TransactionType;
import com.yunxin.cb.mall.entity.meta.WithdrawType;
import com.yunxin.cb.mall.service.IFinancialWalletService;
import com.yunxin.cb.search.vo.ResponseResult;
import com.yunxin.cb.search.vo.meta.Result;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Resource
    private BankInfoDao bankInfoDao;
    @Resource
    private FinancialWithdrawDao financialWithdrawDao;
    @Resource
    private FinancialCashbackLogDao financialCashbackLogDao;


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
    public FinancialWallet getFinacialWalletByCustomerId(int customerId) {
        return financialWalletDao.findByCustomerId(customerId);
    }

    @Override
    public ResponseResult processCustomerMoney(Integer customerId, BigDecimal money, WithdrawType type, String remark) throws RuntimeException {
        //成功标识
        ResponseResult result=new ResponseResult(Result.FAILURE);
        //获取用户钱包
//        FinancialWallet financialWallet =finacialWalletDao.findByCustomerId(customerId);
//        logger.info("用户钱包："+ financialWallet);
//        if(LogicUtils.isNull(financialWallet)){
//            throw new RuntimeException("用户没有钱包，返现处理失败");
//        }
//        Customer customer=customerDao.findOne(customerId);
//        //总负债
//        BigDecimal debtTotal= financialWallet.getDebtTotal();
//        //预期收益贷
//        BigDecimal debtExpected= financialWallet.getDebtExpected();
//        //信用贷
//        BigDecimal debtCredit= financialWallet.getDebtCredit();
//        //自动还款总额
//        BigDecimal repayAmount=BigDecimal.ZERO;
//        //如果是保险返利，则扣减预期收益
//        if(type.getValue()==WithdrawType.BX.getValue()){
//            financialWallet.setExpectedAmount(financialWallet.getExpectedAmount().subtract(money));
//            //加入返现日志记录
//            FinancialCashbackLog financialCashbackLog = new FinancialCashbackLog();
//            financialCashbackLog.setCustomer(customer);
//            if(LogicUtils.isNotNull(customer)){
//                financialCashbackLog.setCustomerName(customer.getRealName());
//                financialCashbackLog.setMobile(customer.getMobile());
//            }
//            financialCashbackLog.setAmount(money);
//            financialCashbackLog.setState(CashbackLogState.FINISHED);
//            financialCashbackLog.setOrderNo(remark);
//            financialCashbackLog.setCreateTime(new Date());
//            financialCashbackLogDao.save(financialCashbackLog);
//        }
//        //是否有负债，先还负债
//        if(debtTotal.compareTo(BigDecimal.ZERO)>0){
//            //预期收益贷负债
//            if(debtExpected.compareTo(BigDecimal.ZERO)>0){
//                //如果负债大于返现，负债减去返现，返现清0
//                if(debtExpected.compareTo(money)>0){
//                    repayAmount=repayAmount.add(money);
//                    financialWallet.setDebtExpected(financialWallet.getDebtExpected().subtract(money));
//                    money=BigDecimal.ZERO;
//                }else{//如果负债小于返现，返现减去负债，负债清0
//                    financialWallet.setDebtExpected(BigDecimal.ZERO);
//                    money=money.subtract(debtExpected);
//                    repayAmount=repayAmount.add(debtExpected);
//                }
//            }
//            //信用贷负债
//            if(debtCredit.compareTo(BigDecimal.ZERO)>0&&money.compareTo(BigDecimal.ZERO)>0){
//                //如果负债大于返现，负债减去返现，返现清0
//                if(debtCredit.compareTo(money)>0){
//                    repayAmount=repayAmount.add(money);
//                    financialWallet.setDebtCredit(financialWallet.getDebtCredit().subtract(money));
//                    money=BigDecimal.ZERO;
//                    //恢复已还信用额度
//                    financialWallet.setCreditAmount(financialWallet.getCreditAmount().add(money));
//                }else{//如果负债小于返现，返现减去负债，负债清0
//                    financialWallet.setDebtCredit(BigDecimal.ZERO);
//                    money=money.subtract(debtCredit);
//                    //恢复已还信用额度
//                    financialWallet.setCreditAmount(financialWallet.getCreditAmount().add(debtCredit));
//                    financialWallet.setTotalAmount(financialWallet.getTotalAmount().add(debtCredit));
//                    repayAmount=repayAmount.add(debtCredit);
//                }
//            }
//            //总负债金额
//            financialWallet.setDebtTotal(financialWallet.getDebtTotal().subtract(repayAmount));
//            //更新负债金额
//            this.updateFinacialWallet(financialWallet);
//            //操作成功
//            result.setResult(Result.SUCCESS);
//        }
//        logger.info("返现金额为："+money);
//        //还完负债，如果还有返现余额，将余额加入提现记录表
//        if(money.compareTo(BigDecimal.ZERO)>0){
//            //获取用户银行卡
//            List<BankInfo> bankInfos = bankInfoDao.selectAllByCustomerId(customerId);
//            if(LogicUtils.isNotNullAndEmpty(bankInfos)){
//                //默认获取用户首张银行卡（目前只支持绑定一张银行卡）
//                BankInfo bankInfo=bankInfos.get(0);
//                //新增提现记录
//                Date nowDate=new Date();
//                FinancialWithdraw financialWithdraw = new FinancialWithdraw();
//                financialWithdraw.setCustomer(customer);
//                financialWithdraw.setBank(bankInfo);
//                financialWithdraw.setAmount(money);
//                financialWithdraw.setRealAmount(money);
//                financialWithdraw.setChargeFee(BigDecimal.ZERO);//默认没有手续费
//                financialWithdraw.setState(WithdrawState.WAIT_GRANT);
//                financialWithdraw.setWithdrawType(type);
//                financialWithdraw.setApplyDate(nowDate);
//                financialWithdraw.setUpdateDate(nowDate);
//                financialWithdrawDao.save(financialWithdraw);
//                //操作成功
//                result.setResult(Result.SUCCESS);
//            }else{
//                throw new RuntimeException("用户没有银行卡，增加提现记录失败");
//            }
//        }
        Map dataMap=new HashMap();
//        dataMap.put("repayAmount",repayAmount);//自动还款
        dataMap.put("realMoney",money);//实际到账
        result.setData(dataMap);
        return result;
    }
}
