package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.CommodityDao;
import com.yunxin.cb.mall.dao.CouponDao;
import com.yunxin.cb.mall.dao.CouponSchemaDao;
import com.yunxin.cb.mall.dao.CustomerDao;
import com.yunxin.cb.mall.entity.Coupon;
import com.yunxin.cb.mall.entity.CouponSchema;
import com.yunxin.cb.mall.entity.CouponSchema_;
import com.yunxin.cb.mall.entity.Coupon_;
import com.yunxin.cb.mall.entity.meta.CouponState;
import com.yunxin.cb.mall.service.ICouponService;
import com.yunxin.cb.mall.vo.CouponScopeVo;
import com.yunxin.cb.mall.vo.CouponVo;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.LogicUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.yunxin.cb.mall.entity.meta.CouponRuleScope;
//import com.yunxin.cb.mall.entity.meta.CouponType;

/**
 * Created by k001389 on 2015/1/30.
 */
@Service
@Transactional
public class CouponService implements ICouponService {

    @Resource
    private CouponDao couponDao;

    @Resource
    private CustomerDao customerDao;

//    @Resource
//    private CustomerCouponDao customerCouponDao;


    @Resource
    private CommodityDao commodityDao;

    @Resource
    private CouponSchemaDao couponSchemaDao;


    @Override
    public void createCoupon(Coupon coupon, List<Integer> scopeIds, List<Integer> customerOrRanKIds) throws EntityExistException {
        if (!couponDao.isUnique(coupon, Coupon_.couponCode)) {
            throw new EntityExistException("优惠券编码已存在");
        }
        if (LogicUtils.isNotNull(coupon)) {
//            CouponType couponType = coupon.getCouponType();
//            coupon = super.createCoupon(coupon);
//            //保存优惠券基本信息
//            coupon =saveCoupon(coupon);
//            if(LogicUtils.isNotNullAndEmpty(scopeIds)) {
//                //保存对应优惠券范围
//                List<CouponScope> couponScopes = super.createCouponScope(coupon,scopeIds);
//                couponScopeDao.save(couponScopes);
//            }
//            if(!(coupon.getCouponType().equals(CouponType.RECEIVE))&&LogicUtils.isNotNull(scopeIds)) {
//                //若非用户领取类型，在向用户进行发放，即绑定用户
////                List<Integer> idsList = Arrays.asList(ids);
//                grantCoupon(coupon, customerOrRanKIds);
//            }
        }
    }


    private Coupon saveCoupon(Coupon coupon) {
        return couponDao.save(coupon);
    }

//    private List<CustomerCoupon> saveCustomerCoupons(List<CustomerCoupon> customerCoupons){
//        if(LogicUtils.isNotNullAndEmpty(customerCoupons)) {
//            //以集合方式保存到数据库最多只能保存1000条，所以此处进行判断，然后进行保存
////            if(customerCoupons.size()>ARRAYLIST) {
////                int count = customerCoupons.size()/ARRAYLIST;
////                int countMod = customerCoupons.size()%ARRAYLIST;
////                count = countMod==0?count:count+1;
////                for(int i=0;i<count;i++) {
////                    int toIndex = ARRAYLIST*(i+1);
////                    if(i==count-1&&countMod>0) {
////                        toIndex = countMod;
////                    }
////                    return customerCouponDao.save(customerCoupons.subList(ARRAYLIST * i, toIndex));
////                }
////            } else {
////                return customerCouponDao.save(customerCoupons);
////            }
//        }
//
//        return null;
//    }


    private void grantCoupon(Coupon coupon, List<Integer> ids) {
        if (LogicUtils.isNotNull(coupon)) {
            //取出用户购物卷的集合
//            List<CustomerCoupon> customerCoupons = super.bindCustomerCoupon(coupon,ids);
//            //保存到数据库中
//            if(LogicUtils.isNotNull(customerCoupons)) {
//               saveCustomerCoupons(customerCoupons);
//            }
        }
    }


    @Override
    public List<CouponVo> getCustomerCouponForOrder(Map<Integer, Double> commIdAndTotalPrices, int customerId) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//        List<Coupon> couponslist = customerCouponDao.findCouponByCustomerId(customerId, CustomerCouponStatus.ACTIVITION, CouponState.PASSREVIEW);
//        Set<Coupon> coupons = new HashSet<>(couponslist);
//        if(LogicUtils.isNotNullAndEmpty(coupons)) {
//            List<CouponVo> couponVos = new ArrayList<>();
//            for(Coupon coupon : coupons) {
////                CouponRuleScope scope = coupon.getCouponRuleScope();
////                if(scope.equals(CouponRuleScope.ALL)) {
////                    //若为全站，则可以直接使用对应领取的优惠卷
////                    addCouponVo(customerId, couponVos, coupon);
////                } else {
////                   //非全站
//////                    List<Integer> couponScopeIds = couponScopeDao.findCouponScopeIdByCouponId(coupon.getCouponId());
//////                    if(LogicUtils.isNotNull(couponScopeIds)) {
//////                        if(scope.equals(CouponRuleScope.COMMODITY)) {
//////                            //若为商品
//////                            compareTotalPrice(commIdAndTotalPrices, customerId, couponVos, coupon, couponScopeIds);
//////                        } else if(scope.equals(CouponRuleScope.BRAND)) {
//////                            HashMap<Integer,Double> brandIdAndTotalPrices = new HashMap<>();
//////                            //若为品牌
//////                            for(Map.Entry<Integer,Double> commIdAndTotalPrice : commIdAndTotalPrices.entrySet()) {
//////                                int brandId = commodityDao.findBrandIdByCommodityId(commIdAndTotalPrice.getKey());
//////                                mergerCommIdToBranIdOrCateId(brandIdAndTotalPrices, commIdAndTotalPrice, brandId);
//////                            }
//////                            if(LogicUtils.isNotNullAndEmpty(brandIdAndTotalPrices)) {
//////                                compareTotalPrice(brandIdAndTotalPrices, customerId, couponVos, coupon, couponScopeIds);
//////                            }
//////                        } else if(scope.equals(CouponRuleScope.CATEGORY)) {
//////                            //若为分类
//////                            HashMap<Integer,Double> categoryIdAndTotalPrices = new HashMap<>();
//////                            for(Map.Entry<Integer,Double> commIdAndTotalPrice : commIdAndTotalPrices.entrySet()) {
//////                                int categoryId = commodityDao.findCategoryIdByCommodityId(commIdAndTotalPrice.getKey());
//////                                mergerCommIdToBranIdOrCateId(categoryIdAndTotalPrices, commIdAndTotalPrice, categoryId);
//////                            }
//////                            if(LogicUtils.isNotNullAndEmpty(categoryIdAndTotalPrices)) {
//////                                compareTotalPrice(categoryIdAndTotalPrices, customerId, couponVos, coupon, couponScopeIds);
//////                            }
//////                        }
//////                    }
////                }
//            }
//            return couponVos;
//        }
        return null;
    }

    private void mergerCommIdToBranIdOrCateId(HashMap<Integer, Double> brandIdAndCateIdTotalPrices, Map.Entry<Integer, Double> commIdAndTotalPrice, int brandId) {
        if (0 != brandId) {
            //该商品对应价格即为该品牌价格
            double totalPrice = commIdAndTotalPrice.getValue();
            //若该品牌下其他商品时，则将其价格总加
            if (LogicUtils.isNotNull(brandIdAndCateIdTotalPrices.get(brandId))) {
                totalPrice += brandIdAndCateIdTotalPrices.get(brandId);
            }
            brandIdAndCateIdTotalPrices.put(brandId, totalPrice);
        }
    }

    private void compareTotalPrice(Map<Integer, Double> idAndTotalPrices, int customerId,
                                   List<CouponVo> couponVos, Coupon coupon, List<Integer> couponScopeIds) {
        double totalPrice = 0;
        for (Map.Entry<Integer, Double> idAndTotalPrice : idAndTotalPrices.entrySet()) {
            double price = idAndTotalPrice.getValue();
            if (couponScopeIds.contains(idAndTotalPrice.getKey())) {
                totalPrice += price;
            }
        }
//        if(totalPrice>=coupon.getOverPrice()) { //可参与优惠
//            addCouponVo(customerId, couponVos, coupon);
//        }
    }

    private void addCouponVo(int customerId, List<CouponVo> couponVos, Coupon coupon) {
//        List<CouponVo> couponVosTemp = super.findBCouponVoyCustomerId(customerId,coupon.getCouponId());
//        if(LogicUtils.isNotNull(couponVosTemp)) {
//            couponVos.addAll(couponVosTemp);
//        }
    }

    @Override
    public String receiveCoupon(int couponId, int customerId) {
        if (0 != couponId && 0 != customerId) {
            //用户领取,需要符合Coupon的领取条件
//            Coupon coupon = super.findPassViewCouponById(couponId, CouponState.PASSREVIEW);
//            //1判断优惠券是否仍存在
//            if(LogicUtils.isNotNull(coupon)) {
//                //2，优惠券是否已发放完
//                List<CustomerCoupon> customerCouponsAll = super.findByCouponId(couponId);
//                if(LogicUtils.isNullOrEmpty(customerCouponsAll)||
//                        (LogicUtils.isNotNullAndEmpty(customerCouponsAll)&&customerCouponsAll.get(0).getCoupon().getCouponSize()>customerCouponsAll.size())) {
//                    //3,用户领取的优惠券是否超过限额
//                    List<CustomerCoupon> customerCoupons = super.findByCustomer_CustomerId(customerId,couponId);
//                    if(LogicUtils.isNullOrEmpty(customerCoupons)||
//                            (LogicUtils.isNotNullAndEmpty(customerCoupons)&&customerCoupons.size()<customerCoupons.get(0).getCoupon().getBindingUserNumber())){
//                        //3绑定用户
//                        List<Integer> idsList = new ArrayList<>(1);
//                        idsList.add(customerId);
//                        List<CustomerCoupon> customerCoupon = super.bindCustomerCoupon(coupon,idsList);
//                        if(LogicUtils.isNotNull(customerCoupon)) {
//                            customerCouponDao.save(customerCoupon);
//                            return "success";
//                        } else {
//                            return "error";
//                        }
//                    } else {
//                        return "hasReceive";
//                    }
//                } else {
//                    return  "couponOver";
//                }
//            } else {
//                return "couponNotExist";
//            }
        } else {
            return "couponIdOrCustomerIdEmpty";
        }
        return "couponIdOrCustomerIdEmpty";
    }

    @Override
    public Page<Coupon> pageCoupons(PageSpecification<Coupon> couponQuery) {
        couponQuery.setCustomSpecification(new CustomSpecification<Coupon>() {
            @Override
            public void addConditions(Root<Coupon> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(Coupon_.couponId)));
            }

            @Override
            public void buildFetch(Root<Coupon> root) {
                super.buildFetch(root);
            }
        });
        return couponDao.findAll(couponQuery, couponQuery.getPageRequest());
    }

    @Override
    public String changeStatus(CouponState status, int couponId) {
//        return super.changeStatus(status,couponId);
        return null;
    }

//    @Override
//    public CustomerCoupon checkCustomerCoupon(String customerCouponCode, Date date, CustomerCouponStatus activition) {
////        CustomerCoupon customerCoupon = super.findCustomerCouponByCodeAndDateAndStatus(customerCouponCode,date,activition);
////        return customerCoupon;
//        return null;
//    }

    @Override
    public Map<Coupon, List<CouponScopeVo>> getCouponScopeVoBycouponId(int couponId) {
        if (0 != couponId) {
            Coupon coupon = couponDao.findOne(couponId);
            if (LogicUtils.isNotNull(coupon)) {
//                CouponRuleScope scope = coupon.getCouponRuleScope();
//                Map<Coupon,List<CouponScopeVo>> couponListMap = new HashMap<>();
//                List<CouponScopeVo> couponScopeVos = new ArrayList<>();
////                if(CouponRuleScope.COMMODITY.equals(scope)) {
////                    couponScopeVos = couponScopeDao.findCouponScopeCommVoIdByCouponId(couponId);
////                } else if(CouponRuleScope.BRAND.equals(scope)) {
////                   couponScopeVos = couponScopeDao.findCouponScopeBrandVoIdByCouponId(couponId);
////                } else if(CouponRuleScope.CATEGORY.equals(scope)) {
////                   couponScopeVos = couponScopeDao.findCouponScopeCateVoIdByCouponId(couponId);
////                } else if(CouponRuleScope.ALL.equals(scope)) {
////                   CouponScopeVo couponScopeVo = new CouponScopeVo(0,"全站");
////                }
//                if(LogicUtils.isNotNullAndEmpty(couponScopeVos)) {
//                    couponListMap.put(coupon,couponScopeVos);
//                    return couponListMap;
//                }
            }
        }
        return null;
    }

    @Override
    public Coupon findCouponById(int couponId) {
        return couponDao.findOne(couponId);
    }

//    @Override
//    public List<CouponScopeVo> getCouponScopeVoBycouponIdAndScope(int couponId, CouponRuleScope scope) {
//        if(0!=couponId&&LogicUtils.isNotNull(scope)) {
//            Map<Coupon,List<CouponScopeVo>> couponListMap = new HashMap<>();
//            List<CouponScopeVo> couponScopeVos = new ArrayList<>();
////            if(CouponRuleScope.COMMODITY.equals(scope)) {
////                couponScopeVos = couponScopeDao.findCouponScopeCommVoIdByCouponId(couponId);
////            } else if(CouponRuleScope.BRAND.equals(scope)) {
////                couponScopeVos = couponScopeDao.findCouponScopeBrandVoIdByCouponId(couponId);
////            } else if(CouponRuleScope.CATEGORY.equals(scope)) {
////                couponScopeVos = couponScopeDao.findCouponScopeCateVoIdByCouponId(couponId);
////            } else if(CouponRuleScope.ALL.equals(scope)) {
////                CouponScopeVo couponScopeVo = new CouponScopeVo(0,"全站");
////            }
//            return couponScopeVos;
//        }
//        return null;
//    }

    @Override
    public String removeCouponById(int couponId) {
//        return couponDao.delete(couponId);
        return null;
    }

    @Override
    public void updateStatusAuto() {
//        List<Integer> customerCouponId = customerCouponDao.getCustomerCouponIdByStatusAndEndTime(new Date(),CustomerCouponStatus.ACTIVITION);
//        customerCouponDao.updateStatusByStatusAndId(CustomerCouponStatus.CANCELLATION,customerCouponId);
////        try{
////            List<Integer> customerCouponId = customerCouponDao.getCustomerCouponIdByStatusAndEndTime(new Date(),CustomerCouponStatus.ACTIVITION);
////            customerCouponDao.updateStatusByStatusAndId(CustomerCouponStatus.CANCELLATION,customerCouponId);
////        } catch (Exception e) {
////           System.err.print(e.getMessage());
////        }

    }

    @Override
    public CouponSchema addCouponSchema(CouponSchema couponSchema) throws EntityExistException {
        if (!couponSchemaDao.isOrUnique(couponSchema, CouponSchema_.schemeNo, CouponSchema_.schemeName)) {
            throw new EntityExistException("优惠券方案编号或优惠券方案名称已存在");
        }
        couponSchema.setEnabled(true);
        couponSchema.setCreateTime(new Date());
        return couponSchemaDao.save(couponSchema);
    }

    @Override
    public CouponSchema updateCouponSchema(CouponSchema couponSchema) throws EntityExistException {
        if (!couponSchemaDao.isOrUnique(couponSchema, CouponSchema_.schemeNo, CouponSchema_.schemeName)) {
            throw new EntityExistException("优惠券方案编号或优惠券方案名称已存在");
        }
        CouponSchema oldSchema = couponSchemaDao.findOne(couponSchema.getSchemaId());
        AttributeReplication.copying(couponSchema, oldSchema, CouponSchema_.schemeNo, CouponSchema_.schemeName, CouponSchema_.distributionMethod, CouponSchema_.faceValue,
                CouponSchema_.distributionNum, CouponSchema_.lowestConsume, CouponSchema_.startTime, CouponSchema_.endTime, CouponSchema_.limitCommodity, CouponSchema_.enabled);
        return null;
    }

    @Override
    public void removeCouponSchemaById(int schemaId) {
        couponSchemaDao.delete(schemaId);
    }

    @Override
    public CouponSchema getCouponSchemaById(int schemaId) {
        return couponSchemaDao.findOne(schemaId);
    }

    @Override
    public Page<CouponSchema> pageCouponSchemas(PageSpecification<CouponSchema> query) {
        query.setCustomSpecification(new CustomSpecification<CouponSchema>() {
            @Override
            public void addConditions(Root<CouponSchema> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(CouponSchema_.createTime)));
            }

            @Override
            public void buildFetch(Root<CouponSchema> root) {
                super.buildFetch(root);
            }
        });
        return couponSchemaDao.findAll(query, query.getPageRequest());
    }


}
