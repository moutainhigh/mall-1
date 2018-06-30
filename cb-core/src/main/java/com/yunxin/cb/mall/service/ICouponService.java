package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Coupon;
import com.yunxin.cb.mall.entity.CouponSchema;
import com.yunxin.cb.mall.entity.meta.CouponState;
import com.yunxin.cb.mall.vo.CouponScopeVo;
import com.yunxin.cb.mall.vo.CouponVo;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

//import com.yunxin.cb.mall.entity.CustomerCoupon;

//import com.yunxin.cb.mall.entity.meta.CouponRuleScope;

/**
 * Created by k001389 on 2015/2/2.
 */
public interface ICouponService {


    /**
     * 创建优惠券
     *
     * @param coupon
     * @param ids    为此优惠券范围id
     */
    public void createCoupon(Coupon coupon, List<Integer> ids, List<Integer> customerOrRanKIds) throws EntityExistException;

//    /**
//     * 绑定用户和优惠卷：有两种方式，一种是领取，一种是系统发放
//     * @param couponId
//     * @param ids
//     * @return
//     */
//    public void bindingCoupon(int couponId, Integer ... ids) ;

    /**
     * 取用户某个订单中可用优惠券
     *
     * @param commIdAndTotalPrice
     * @param customerId
     * @return
     */
    public List<CouponVo> getCustomerCouponForOrder(Map<Integer, Double> commIdAndTotalPrice, int customerId) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException;

    /**
     * 领取优惠券。因发放优惠卷为内部发放，所以不需要提供对外接口
     *
     * @param couponId
     * @param customerId
     * @return
     */
    public String receiveCoupon(int couponId, int customerId);


    Page<Coupon> pageCoupons(PageSpecification<Coupon> query);

    String changeStatus(CouponState status, int couponId);

//    CustomerCoupon checkCustomerCoupon(String customerCouponCode, Date date, CustomerCouponStatus activition);

    Map<Coupon, List<CouponScopeVo>> getCouponScopeVoBycouponId(int couponId);

    Coupon findCouponById(int couponId);

//    List<CouponScopeVo> getCouponScopeVoBycouponIdAndScope(int couponId, CouponRuleScope scope);

    String removeCouponById(int couponId);

    void updateStatusAuto();

    CouponSchema addCouponSchema(CouponSchema couponSchema) throws EntityExistException;

    CouponSchema updateCouponSchema(CouponSchema couponSchema) throws EntityExistException;

    void removeCouponSchemaById(int schemaId);

    CouponSchema getCouponSchemaById(int schemaId);

    Page<CouponSchema> pageCouponSchemas(PageSpecification<CouponSchema> query);


}
