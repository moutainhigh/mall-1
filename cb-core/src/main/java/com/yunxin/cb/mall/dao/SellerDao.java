package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Seller;
import com.yunxin.cb.mall.entity.meta.SellerType;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 *
 *
 *
 */

public interface SellerDao extends JpaRepository<Seller, Integer>, JpaSpecificationExecutor<Seller>, BaseDao<Seller> {

    @Modifying
    @Query("update Seller a set a.enabled = ?1 where a.sellerId=?2")
    void enableSellerById(boolean enabled, int sellerId);

    @Query("select s from Seller s where s.sellerType=?1")
    Seller checkSellerBySellerTypeForAdd(SellerType sellerType);

    @Query("select s from Seller s where s.sellerType=?1 and s.sellerId <> ?2")
    Seller checkSellerBySellerTypeForEdit(SellerType sellerType, int sellerId);

}

