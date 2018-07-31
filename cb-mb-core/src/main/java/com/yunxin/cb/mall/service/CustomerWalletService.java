package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.vo.CustomerWalletVO;

public interface CustomerWalletService {

    CustomerWalletVO selectByCustomerId(Integer customerId);

}
