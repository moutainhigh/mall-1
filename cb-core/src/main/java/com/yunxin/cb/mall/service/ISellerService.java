package com.yunxin.cb.mall.service;


import com.yunxin.cb.mall.entity.Seller;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 商家(卖家)接口
 */
public interface ISellerService {

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Page<Seller> pageSellers(PageSpecification<Seller> query);

    Seller addSeller(Seller seller) throws EntityExistException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Seller getSellerById(int sellerId);

    Seller updateSeller(Seller seller) throws EntityExistException;

    void enableSellerById(int sellerId, boolean enabled);

    void removeSellerById(int sellerId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    long getSellerCount();
}
