package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.CustomerTradingRecordDao;
import com.yunxin.cb.mall.dao.CustomerWalletDao;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.CustomerTradingRecord;
import com.yunxin.cb.mall.entity.CustomerWallet;
import com.yunxin.cb.mall.entity.OperationType;
import com.yunxin.cb.mall.entity.meta.BusinessType;
import com.yunxin.cb.mall.service.ICustomerWalletService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerWalletService implements ICustomerWalletService {
    @Resource
    private CustomerWalletDao CustomerWalletDao;
    @Resource
    private CustomerTradingRecordDao customerTradingRecordDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public CustomerWallet findCustomerWallet(int customerId) {
        return CustomerWalletDao.getOne(customerId);
    }


    @Override
    public CustomerWallet updateCustomerWallet(int customerId,Double ratios,String remark,BusinessType businessType) {

        CustomerWallet  customerWalletBean=CustomerWalletDao.findOne(customerId);

        if(null!=customerWalletBean){
            BigDecimal loanQuota=new BigDecimal(customerWalletBean.getLoanQuota());
            BigDecimal ratio=new BigDecimal(ratios);
            BigDecimal addedLoanQuota=loanQuota.multiply(ratio);
            Double  newLoanQuota=loanQuota.add(addedLoanQuota).setScale(2,BigDecimal.ROUND_UP).doubleValue();
            customerWalletBean.setLoanQuota(newLoanQuota);
            final double amount=addedLoanQuota.setScale(2,BigDecimal.ROUND_UP).doubleValue();
            /**
             * 更新交易记录
             */
            CustomerTradingRecord CustomerTradingRecord=new CustomerTradingRecord();
            CustomerTradingRecord.setRemark(remark);
            CustomerTradingRecord.setOperationType(OperationType.ADD);
            CustomerTradingRecord.setBusinessType(businessType);
            CustomerTradingRecord.setAmount(amount);
            CustomerTradingRecord.setCreateTime(new Date());
            Customer Customer=new Customer();
            Customer.setCustomerId(customerId);
            CustomerTradingRecord.setCustomer(Customer);
            customerTradingRecordDao.save(CustomerTradingRecord);
        }
        return customerWalletBean;
    }



//    @Override
//    public CustomerWallet updateExpectedReturnAmount(CustomerWallet customerWallet) {
//        CustomerWallet  customerWalletBean=CustomerWalletDao.findOne(customerWallet.getCustomerId());
//        //预期收益金额
//        BigDecimal expectedReturnAmount=new BigDecimal(customerWalletBean.getExpectedReturnAmount());
//        BigDecimal ratio=new BigDecimal(0.5);
//        BigDecimal addedExpectedReturnAmount= expectedReturnAmount.multiply(ratio);
//        Double  newExpectedReturnAmount=expectedReturnAmount.add(addedExpectedReturnAmount).doubleValue();
//        customerWalletBean.setExpectedReturnAmount(newExpectedReturnAmount);
//        final double amount=addedExpectedReturnAmount.doubleValue();
//        /**
//         * 更新交易记录
//         */
//        iCustomerTradingRecordService.addCustomerTradingRecord(new CustomerTradingRecord(){
//            {
//                setCustomer(new Customer(){{
//                    setCustomerId(customerWallet.getCustomerId());
//                }});
//                setAmount(amount);
//                setCreateTime(new Date());
//                setRemark("推荐人增加50%的预期收益金额");
//                setOperationType(OperationType.ADD);
//                setBusinessType(BusinessType.LOAN_EXPECTED_RETURN_FIFTY);
//            }
//        });
//        return customerWalletBean;
//    }


}
