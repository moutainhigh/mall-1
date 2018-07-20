package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.ProductReturn;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;

import java.util.List;

public interface ProductReturnService {


    /***
     * 退货申请
     * @param productReturn
     * @return
     */
    public ProductReturn applyOrderProductReturn(ProductReturn productReturn) throws Exception;

    /***
     * 退货分页列表
     * @param q
     * @return
     */
    public PageFinder<ProductReturn> pageProductReturn(Query q);

    /***
     * 退货列表
     * @param q
     * @return
     */
    public List<ProductReturn> listProductReturn(Query q);

    /***
     * 退货详情
     * @param productReturnId
     * @param customerId
     * @return
     */
    public ProductReturn getProductReturn(Integer productReturnId, Integer customerId);
}
