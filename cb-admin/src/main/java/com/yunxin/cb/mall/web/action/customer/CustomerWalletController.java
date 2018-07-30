package com.yunxin.cb.mall.web.action.customer;

import com.yunxin.cb.mall.entity.CustomerTradingRecord;
import com.yunxin.cb.mall.entity.CustomerWallet;
import com.yunxin.cb.mall.service.imp.CustomerWalletService;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/customerWallet")
public class CustomerWalletController {

    @Resource
    private CustomerWalletService customerWalletService;

    @RequestMapping(value = "customerWallets")
    public String customerWallets() {
        return "customer/customerWallets";
    }

    @RequestMapping(value = "pageCustomerWallets",method = RequestMethod.POST)
    @ResponseBody
    public Page<CustomerWallet> pageCustomerWallets(@RequestBody PageSpecification<CustomerWallet> specification) {
        Page<CustomerWallet> page = customerWalletService.pageCustomerWallets(specification);
        return page;
    }

    @RequestMapping(value = "customerTradings")
    public String customerTrading() {
        return "customer/customerTradingRecord";
    }

    @RequestMapping(value = "pageCustomerTrading",method = RequestMethod.POST)
    @ResponseBody
    public Page<CustomerTradingRecord> pageCustomerTrading(@RequestBody PageSpecification<CustomerTradingRecord> specification) {
        Page<CustomerTradingRecord> page = customerWalletService.pageCustomerTradingRecord(specification);
        return page;
    }
}
