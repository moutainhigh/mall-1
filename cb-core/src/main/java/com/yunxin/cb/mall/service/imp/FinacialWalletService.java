package com.yunxin.cb.mall.service.imp;


import com.yunxin.cb.mall.dao.*;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.*;
import com.yunxin.cb.mall.service.IFinaciaWalletService;
import com.yunxin.cb.search.vo.ResponseResult;
import com.yunxin.cb.search.vo.meta.Result;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.util.LogicUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FinacialWalletService implements IFinaciaWalletService {

    private static final Logger logger = LoggerFactory.getLogger(FinacialWalletService.class);

    @Resource
    private FinacialWalletDao finacialWalletDao;

    @Resource
    private FinacialWalletLogDao finacialWalletLogDao;

    @Resource
    private FinancialFreezingBillDao financialFreezingBillDao;

    @Resource
    private FinacialCreditLineBillDao FinacialCreditLineBillDao;
    @Resource
    private CustomerDao customerDao;
    @Resource
    private BankInfoDao bankInfoDao;
    @Resource
    private FinancialWithdrawDao financialWithdrawDao;
    @Resource
    private FinancialCashbackLogDao financialCashbackLogDao;

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
            /**钱包:初始化钱包信息*/
            finacialWalletDao.save(fw);
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
            FinacialCreditLineBill finacialCreditLineBill = new FinacialCreditLineBill();
            BeanUtils.copyProperties(financialFreezingBill,finacialCreditLineBill);
            finacialCreditLineBill.setTransactionDesc(customer.getAccountName()+"点赞增加5%");
            //保险购买
            if(type==0){
                /**保险:保存预期收益记录*/
                financialFreezingBillDao.save(financialFreezingBill);
                logger.info("add FinancialFreezingBill sucess:"+financialFreezingBill);
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


    /**
     * 修改钱包信息
     * @author      likang
     * @param fw
     * @return      com.yunxin.cb.mall.entity.FinacialWallet
     * @exception
     * @date        2018/8/13 11:34
     */
    @Override
    public FinacialWallet updateFinacialWallet(FinacialWallet fw){
        FinacialWallet oldfw = finacialWalletDao.findOne(fw.getWalletId());
        if(fw.getVersion()!=fw.getVersion()){
            throw new RuntimeException("用户版本号不正确，请稍后再试");
        }
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
        flog.setAmount(new BigDecimal(0));
        /**钱包:保存钱包日志记录*/
        finacialWalletLogDao.save(flog);
        logger.info("updateFinaciaWallet sucess");
        return fw;
    }

    @Override
    public FinacialWallet getFinacialWalletByCustomerId(int customerId) {
        return finacialWalletDao.findFinacialWalletByCustomerId(customerId);
    }

    @Override
    public ResponseResult processCustomerMoney(Integer customerId, BigDecimal money, WithdrawType type, String remark) throws RuntimeException {
        //成功标识
        ResponseResult result=new ResponseResult(Result.FAILURE);
        //获取用户钱包
        FinacialWallet finacialWallet=finacialWalletDao.findFinacialWalletByCustomerId(customerId);
        logger.info("用户钱包："+finacialWallet);
        if(LogicUtils.isNull(finacialWallet)){
            throw new RuntimeException("用户没有钱包，返现处理失败");
        }
        Customer customer=customerDao.findOne(customerId);
        //总负债
        BigDecimal debtTotal=finacialWallet.getDebtTotal();
        //预期收益贷
        BigDecimal debtExpected= finacialWallet.getDebtExpected();
        //信用贷
        BigDecimal debtCredit= finacialWallet.getDebtCredit();
        //自动还款总额
        BigDecimal repayAmount=BigDecimal.ZERO;
        //如果是保险返利，则扣减预期收益
        if(type.getValue()==WithdrawType.BX.getValue()){
            finacialWallet.setExpectedAmount(finacialWallet.getExpectedAmount().subtract(money));
            //加入返现日志记录
            FinancialCashbackLog financialCashbackLog = new FinancialCashbackLog();
            financialCashbackLog.setCustomer(customer);
            if(LogicUtils.isNotNull(customer)){
                financialCashbackLog.setCustomerName(customer.getRealName());
                financialCashbackLog.setMobile(customer.getMobile());
            }
            financialCashbackLog.setAmount(money);
            financialCashbackLog.setState(CashbackLogState.FINISHED);
            financialCashbackLog.setOrderNo(remark);
            financialCashbackLog.setCreateTime(new Date());
            financialCashbackLogDao.save(financialCashbackLog);
        }
        //是否有负债，先还负债
        if(debtTotal.compareTo(BigDecimal.ZERO)>0){
            //预期收益贷负债
            if(debtExpected.compareTo(BigDecimal.ZERO)>0){
                //如果负债大于返现，负债减去返现，返现清0
                if(debtExpected.compareTo(money)>0){
                    repayAmount=repayAmount.add(money);
                    finacialWallet.setDebtExpected(finacialWallet.getDebtExpected().subtract(money));
                    money=BigDecimal.ZERO;
                }else{//如果负债小于返现，返现减去负债，负债清0
                    finacialWallet.setDebtExpected(BigDecimal.ZERO);
                    money=money.subtract(debtExpected);
                    repayAmount=repayAmount.add(debtExpected);
                }
            }
            //信用贷负债
            if(debtCredit.compareTo(BigDecimal.ZERO)>0&&money.compareTo(BigDecimal.ZERO)>0){
                //如果负债大于返现，负债减去返现，返现清0
                if(debtCredit.compareTo(money)>0){
                    repayAmount=repayAmount.add(money);
                    finacialWallet.setDebtCredit(finacialWallet.getDebtCredit().subtract(money));
                    money=BigDecimal.ZERO;
                    //恢复已还信用额度
                    finacialWallet.setCreditAmount(finacialWallet.getCreditAmount().add(money));
                }else{//如果负债小于返现，返现减去负债，负债清0
                    finacialWallet.setDebtCredit(BigDecimal.ZERO);
                    money=money.subtract(debtCredit);
                    //恢复已还信用额度
                    finacialWallet.setCreditAmount(finacialWallet.getCreditAmount().add(debtCredit));
                    finacialWallet.setTotalAmount(finacialWallet.getTotalAmount().add(debtCredit));
                    repayAmount=repayAmount.add(debtCredit);
                }
            }
            //总负债金额
            finacialWallet.setDebtTotal(finacialWallet.getDebtTotal().subtract(repayAmount));
            //更新负债金额
            this.updateFinacialWallet(finacialWallet);
            //操作成功
            result.setResult(Result.SUCCESS);
        }
        logger.info("返现金额为："+money);
        //还完负债，如果还有返现余额，将余额加入提现记录表
        if(money.compareTo(BigDecimal.ZERO)>0){
            //获取用户银行卡
            List<BankInfo> bankInfos = bankInfoDao.selectAllByCustomerId(customerId);
            if(LogicUtils.isNotNullAndEmpty(bankInfos)){
                //默认获取用户首张银行卡（目前只支持绑定一张银行卡）
                BankInfo bankInfo=bankInfos.get(0);
                //新增提现记录
                Date nowDate=new Date();
                FinancialWithdraw financialWithdraw = new FinancialWithdraw();
                financialWithdraw.setCustomer(customer);
                financialWithdraw.setBank(bankInfo);
                financialWithdraw.setAmount(money);
                financialWithdraw.setRealAmount(money);
                financialWithdraw.setChargeFee(BigDecimal.ZERO);//默认没有手续费
                financialWithdraw.setState(WithdrawState.WAIT_GRANT);
                financialWithdraw.setWithdrawType(type);
                financialWithdraw.setApplyDate(nowDate);
                financialWithdraw.setUpdateDate(nowDate);
                financialWithdrawDao.save(financialWithdraw);
                //操作成功
                result.setResult(Result.SUCCESS);
            }else{
                throw new RuntimeException("用户没有银行卡，增加提现记录失败");
            }
        }
        Map dataMap=new HashMap();
        dataMap.put("repayAmount",repayAmount);//自动还款
        dataMap.put("realMoney",money);//实际到账
        result.setData(dataMap);
        return result;
    }

}
