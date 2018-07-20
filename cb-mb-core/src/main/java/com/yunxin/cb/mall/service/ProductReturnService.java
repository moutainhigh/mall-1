package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.ProductReturn;

public interface ProductReturnService {


    /***
     * 退货申请
     * @param productReturn
     * @return
     */
    public ProductReturn applyOrderProductReturn(ProductReturn productReturn) throws Exception;

}
