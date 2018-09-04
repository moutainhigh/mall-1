package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.WithdrawType;
import com.yunxin.cb.mall.mapper.*;
import com.yunxin.cb.mall.restful.ResponseResult;
import com.yunxin.cb.mall.restful.meta.Result;
import com.yunxin.cb.mall.service.FinancialWalletService;
import com.yunxin.cb.mall.vo.FinancialWalletVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class FinancialWalletServiceImpl implements FinancialWalletService {

    private static final Log log = LogFactory.getLog(FinancialWalletServiceImpl.class);
    @Resource
    private FinacialWalletMapper finacialWalletMapper;
    @Resource
    private FinacialWalletLogMapper finacialWalletLogMapper;
    @Resource
    private BankInfoMapper bankInfoMapper;
    @Resource
    private FinancialWithdrawMapper financialWithdrawMapper;
    @Resource
    private FinancialCashbackLogMapper financialCashbackLogMapper;
    @Resource
    private CustomerMapper customerMapper;

    /**
     * 添加钱包信息
     *
     * @param vo
     * @return com.yunxin.cb.mall.entity.FinancialWallet
     * @throws
     * @author likang
     * @date 2018/8/7 11:54
     */
    @Override
    public FinancialWalletVO addFinancialWallet(FinancialWalletVO vo) {
        log.info("addFinancialWallet:" + vo.toString());
        FinancialWallet financialWallet = new FinancialWallet();
        BeanUtils.copyProperties(financialWallet, vo);
        if (finacialWalletMapper.selectByCustomerId(financialWallet.getCustomerId()) == null) {
            /**初始化钱包信息*/
            financialWallet.setDebtCredit(new BigDecimal(0));
            financialWallet.setDebtCar(new BigDecimal(0));
            financialWallet.setCreditAmount(new BigDecimal(0));
            financialWallet.setFreezingAmount(new BigDecimal(0));
            financialWallet.setVersion(0);//初始化版本号
            finacialWalletMapper.insert(financialWallet);
        }
        return vo;
    }

    /**
     * 获取用户钱包信息
     * @author      likang
     * @param customerId
     * @return      com.yunxin.cb.mall.entity.FinancialWallet
     * @exception
     * @date        2018/8/7 11:55
     */
    @Override
    public FinancialWalletVO getFinancialWalletByCustomerId(int customerId){
        FinancialWalletVO vo = new FinancialWalletVO();
        FinancialWallet financialWallet = finacialWalletMapper.selectByCustomerId(customerId);
        BeanUtils.copyProperties(financialWallet, vo);
        return vo;
    }

    /**
     * 更新用户钱包信息
     * @author      likang
     * @param vo
     * @return      com.yunxin.cb.mall.entity.FinancialWallet
     * @exception
     * @date        2018/8/7 11:56
     */
    @Override
    public FinancialWalletVO updateFinancialWallet(FinancialWalletVO vo){
        FinancialWallet financialWallet = new FinancialWallet();
        BeanUtils.copyProperties(financialWallet, vo);
        finacialWalletMapper.updateByPrimaryKey(financialWallet);
        /**添加日志信息*/
        FinacialWalletLog finacialWalletlog = new FinacialWalletLog();
        BeanUtils.copyProperties(financialWallet, finacialWalletlog);
        finacialWalletlog.setAmount(new BigDecimal("0"));
        finacialWalletLogMapper.insert(finacialWalletlog);
        return vo;
    }

    @Override
    public ResponseResult processCustomerMoney(Integer customerId, BigDecimal money, WithdrawType type,String remark) throws RuntimeException {
        //成功标识
        ResponseResult result=new ResponseResult(Result.FAILURE);
//        //获取用户钱包
//        FinancialWallet financialWallet =finacialWalletMapper.selectByCustomerId(customerId);
//        log.info("用户钱包："+ financialWallet);
//        if(LogicUtils.isNull(financialWallet)){
//            throw new RuntimeException("用户没有钱包，返现处理失败");
//        }
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
//            Customer customer=customerMapper.selectByPrimaryKey(customerId);
//            FinancialCashbackLog financialCashbackLog=new FinancialCashbackLog();
//            financialCashbackLog.setCustomerId(customerId);
//            if(LogicUtils.isNotNull(customer)){
//                financialCashbackLog.setCustomerName(customer.getRealName());
//                financialCashbackLog.setMobile(customer.getMobile());
//            }
//            financialCashbackLog.setAmount(money);
//            financialCashbackLog.setState(CashbackLogState.FINISHED.getValue());
//            financialCashbackLog.setOrderNo(remark);
//            financialCashbackLog.setCreateTime(new Date());
//            financialCashbackLogMapper.insert(financialCashbackLog);
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
//            FinancialWalletVO finacialWalletVO = new FinancialWalletVO();
//            BeanUtils.copyProperties(finacialWalletVO, financialWallet);
//            this.updateFinancialWallet(finacialWalletVO);
//            //操作成功
//            result.setResult(Result.SUCCESS);
//        }
//        log.info("返现金额为："+money);
//        //还完负债，如果还有返现余额，将余额加入提现记录表
//        if(money.compareTo(BigDecimal.ZERO)>0){
//            //获取用户银行卡
//            List<BankInfo> bankInfos = bankInfoMapper.selectAll(customerId);
//            if(LogicUtils.isNotNullAndEmpty(bankInfos)){
//                //默认获取用户首张银行卡（目前只支持绑定一张银行卡）
//                BankInfo bankInfo=bankInfos.get(0);
//                //新增提现记录
//                Date nowDate=new Date();
//                FinancialWithdraw financialWithdraw = new FinancialWithdraw();
//                financialWithdraw.setCustomerId(customerId);
//                financialWithdraw.setBankId(bankInfo.getBankId());
//                financialWithdraw.setAmount(money);
//                financialWithdraw.setRealAmount(money);
//                financialWithdraw.setChargeFee(BigDecimal.ZERO);//默认没有手续费
//                financialWithdraw.setState(WithdrawState.WAIT_GRANT.getValue());
//                financialWithdraw.setWithdrawType(type.getValue());
//                financialWithdraw.setApplyDate(nowDate);
//                financialWithdraw.setUpdateDate(nowDate);
//                financialWithdrawMapper.insert(financialWithdraw);
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
