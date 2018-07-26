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
    public synchronized CustomerWallet updateCustomerWallet(int customerId,Double ratios,String remark,BusinessType businessType,int price) {

        CustomerWallet  customerWalletBean=CustomerWalletDao.findOne(customerId);
        BigDecimal bigPrice=new BigDecimal(price);
        BigDecimal ratio=new BigDecimal(ratios);
        BigDecimal added=bigPrice.multiply(ratio);
        double amount=added.setScale(2,BigDecimal.ROUND_UP).doubleValue();
        if(null!=customerWalletBean){
            if(businessType.equals(BusinessType.GIVE_THE_THUMBS_UP)){
                BigDecimal loanQuota=new BigDecimal(customerWalletBean.getLoanQuota());
                Double  newLoanQuota=loanQuota.add(added).setScale(2,BigDecimal.ROUND_UP).doubleValue();
                customerWalletBean.setLoanQuota(newLoanQuota);
            }else if(businessType.equals(BusinessType.LOAN_EXPECTED_RETURN_FIFTY)){

                BigDecimal expectedReturnAmount=new BigDecimal(customerWalletBean.getExpectedReturnAmount());
                Double  newExpectedReturnAmount=expectedReturnAmount.add(added).setScale(2,BigDecimal.ROUND_UP).doubleValue();
                customerWalletBean.setExpectedReturnAmount(newExpectedReturnAmount);
            }
            customerWalletBean.setUpdateTime(new Date());

        }else{
            CustomerWallet customerWallet=new CustomerWallet();
            customerWallet.setAvailableBalance(0.0);
            customerWallet.setExpectedReturnAmount(0.0);
            customerWallet.setArrearsAmount(0.0);
            if(businessType.equals(BusinessType.GIVE_THE_THUMBS_UP))
                customerWallet.setLoanQuota(amount);
            else if(businessType.equals(BusinessType.LOAN_EXPECTED_RETURN_FIFTY))
                customerWallet.setExpectedReturnAmount(amount);
            customerWallet.setUpdateTime(new Date());
            customerWallet.setCreateTime(new Date());
            CustomerWalletDao.save(customerWallet);
        }
        /**
         * 更新交易记录
         */
        CustomerTradingRecord customerTradingRecord=new CustomerTradingRecord();
        customerTradingRecord.setRemark(remark);
        customerTradingRecord.setOperationType(OperationType.ADD);
        customerTradingRecord.setBusinessType(businessType);
        customerTradingRecord.setAmount(amount);
        customerTradingRecord.setCreateTime(new Date());
        Customer Customer=new Customer();
        Customer.setCustomerId(customerId);
        customerTradingRecord.setCustomer(Customer);
        customerTradingRecordDao.save(customerTradingRecord);
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
