package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.BankInfo;
import com.yunxin.cb.mall.entity.FinacialWallet;
import com.yunxin.cb.mall.entity.FinacialWalletLog;
import com.yunxin.cb.mall.entity.FinacialWithdraw;
import com.yunxin.cb.mall.entity.meta.WithdrawState;
import com.yunxin.cb.mall.entity.meta.WithdrawType;
import com.yunxin.cb.mall.mapper.BankInfoMapper;
import com.yunxin.cb.mall.mapper.FinacialWalletLogMapper;
import com.yunxin.cb.mall.mapper.FinacialWalletMapper;
import com.yunxin.cb.mall.mapper.FinacialWithdrawMapper;
import com.yunxin.cb.mall.restful.ResponseResult;
import com.yunxin.cb.mall.restful.meta.Result;
import com.yunxin.cb.mall.service.FinacialWalletService;
import com.yunxin.cb.mall.vo.FinacialWalletVO;
import com.yunxin.cb.util.LogicUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FinacialWalletServiceImpl implements FinacialWalletService {

    private static final Log log = LogFactory.getLog(FinacialWalletServiceImpl.class);
    @Resource
    private FinacialWalletMapper finacialWalletMapper;
    @Resource
    private FinacialWalletLogMapper finacialWalletLogMapper;
    @Resource
    private BankInfoMapper bankInfoMapper;
    @Resource
    private FinacialWithdrawMapper finacialWithdrawMapper;

    /**
     * 添加钱包信息
     * @author      likang
     * @param vo
     * @return      com.yunxin.cb.mall.entity.FinacialWallet
     * @exception
     * @date        2018/8/7 11:54
     */
    @Override
    public FinacialWalletVO addFinaciaWallet(FinacialWalletVO vo){
        log.info("addFinaciaWallet:"+vo.toString());
        FinacialWallet finacialWallet = new FinacialWallet();
        BeanUtils.copyProperties(finacialWallet, vo);
        if(finacialWalletMapper.selectByCustomerId(finacialWallet.getCustomerId())==null){
            /**初始化钱包信息*/
            finacialWallet.setAssets(new BigDecimal(0));
            finacialWallet.setBalance(new BigDecimal(0));
            finacialWallet.setDebtTotal(new BigDecimal(0));
            finacialWallet.setDebtExpected(new BigDecimal(0));
            finacialWallet.setDebtCredit(new BigDecimal(0));
            finacialWallet.setCreditAmount(new BigDecimal(0));
            finacialWallet.setFreezingAmount(new BigDecimal(0));
            finacialWallet.setInsuranceAmount(finacialWallet.getExpectedAmount());
            finacialWallet.setTotalAmount(finacialWallet.getExpectedAmount());
            finacialWallet.setVersion(0);//初始化版本号
            finacialWalletMapper.insert(finacialWallet);
        }
        return vo;
    }

    /**
     * 获取用户钱包信息
     * @author      likang
     * @param customerId
     * @return      com.yunxin.cb.mall.entity.FinacialWallet
     * @exception
     * @date        2018/8/7 11:55
     */
    @Override
    public FinacialWalletVO getFinacialWalletByCustomerId(int customerId){
        FinacialWalletVO vo = new FinacialWalletVO();
        FinacialWallet finacialWallet = finacialWalletMapper.selectByCustomerId(customerId);
        BeanUtils.copyProperties(finacialWallet, vo);
        return vo;
    }

    /**
     * 更新用户钱包信息
     * @author      likang
     * @param vo
     * @return      com.yunxin.cb.mall.entity.FinacialWallet
     * @exception
     * @date        2018/8/7 11:56
     */
    @Override
    public FinacialWalletVO updateFinacialWallet(FinacialWalletVO vo){
        FinacialWallet finacialWallet = new FinacialWallet();
        BeanUtils.copyProperties(finacialWallet, vo);
        finacialWalletMapper.updateByPrimaryKey(finacialWallet);
        /**添加日志信息*/
        FinacialWalletLog finacialWalletlog = new FinacialWalletLog();
        BeanUtils.copyProperties(finacialWallet, finacialWalletlog);
        finacialWalletlog.setAmount(new BigDecimal("0"));
        finacialWalletLogMapper.insert(finacialWalletlog);
        return vo;
    }

    @Override
    public ResponseResult processCustomerMoney(Integer customerId, BigDecimal money, WithdrawType type) throws RuntimeException {
        //成功标识
        ResponseResult result=new ResponseResult(Result.FAILURE);
        //获取用户钱包
        FinacialWallet finacialWallet=finacialWalletMapper.selectByCustomerId(customerId);
        log.info("用户钱包："+finacialWallet);
        if(LogicUtils.isNull(finacialWallet)){
            throw new RuntimeException("用户没有钱包，返现处理失败");
        }
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
            FinacialWalletVO finacialWalletVO = new FinacialWalletVO();
            BeanUtils.copyProperties(finacialWalletVO,finacialWallet);
            this.updateFinacialWallet(finacialWalletVO);
            //操作成功
            result.setResult(Result.SUCCESS);
        }
        log.info("返现金额为："+money);
        //还完负债，如果还有返现余额，将余额加入提现记录表
        if(money.compareTo(BigDecimal.ZERO)>0){
            //获取用户银行卡
            List<BankInfo> bankInfos = bankInfoMapper.selectAll(customerId);
            if(LogicUtils.isNotNullAndEmpty(bankInfos)){
                //默认获取用户首张银行卡（目前只支持绑定一张银行卡）
                BankInfo bankInfo=bankInfos.get(0);
                //新增提现记录
                Date nowDate=new Date();
                FinacialWithdraw finacialWithdraw = new FinacialWithdraw();
                finacialWithdraw.setCustomerId(customerId);
                finacialWithdraw.setBankId(bankInfo.getBankId());
                finacialWithdraw.setAmount(money);
                finacialWithdraw.setRealAmount(money);
                finacialWithdraw.setChargeFee(BigDecimal.ZERO);//默认没有手续费
                finacialWithdraw.setState(WithdrawState.WAIT_GRANT.getValue());
                finacialWithdraw.setWithdrawType(type.getValue());
                finacialWithdraw.setApplyDate(nowDate);
                finacialWithdraw.setUpdateDate(nowDate);
                finacialWithdrawMapper.insert(finacialWithdraw);
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
