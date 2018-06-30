package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Coupon;
import com.yunxin.cb.mall.entity.meta.CouponState;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by k001389 on 2015/1/30.
 */
public interface CouponDao extends JpaRepository<Coupon, Integer>, JpaSpecificationExecutor<Coupon>, BaseDao<Coupon> {

    @Query("select c from Coupon c where c.couponId=?1 and c.couponState=?2")
    Coupon findByIdAndStatus(int couponId, CouponState status);


}
