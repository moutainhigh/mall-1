package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.FinancialCashbackInsurance;

import java.math.BigDecimal;
import java.util.List;

public interface IFinancialCashbackInsuranceService {

    /**
     * @Author chenpeng
     * @Param customer 用户
     * @Param amount 保单金额
     * @Param insuranceNo 保单号
     * @Description 购买保险，增加保险返利计划
     * @Date 2018/9/11 11:17
     **/
    void buyInsuranceCashback(Customer customer, BigDecimal amount, String insuranceNo);

    /**
     * @Author chenpeng
     * @Param customer 用户
     * @Param amount 保单金额
     * @Description 感恩点赞。推荐人以及所有上级增加5%的信用额度
     * @Date 2018/9/11 15:06
     **/
    void praiseInsurance(Customer customer, BigDecimal amount);


    /**
     * @Author chenpeng
     * @Description 获取 今年需要返利的 保险返利
     * @Date 2018/9/11 16:51 
     **/
    List<FinancialCashbackInsurance> getByProcessing();

    /**
     * @Author chenpeng
     * @Description 保险返利
     * @Date 2018/9/11 15:57
     **/
    void processInsuranceCashback(FinancialCashbackInsurance cashbackInsurance);

}
