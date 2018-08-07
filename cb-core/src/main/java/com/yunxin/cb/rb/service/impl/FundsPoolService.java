package com.yunxin.cb.rb.service.impl;

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
import com.yunxin.cb.rb.dao.FundsPoolDao;
import com.yunxin.cb.rb.service.IFundsPoolService;
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

/**
 * @Description:    资金池接口服务实现类
 * @author: lxc
 * @Return :
 * @DateTime: 2018/8/7 11:03
 */
@Service
@Transactional
public class FundsPoolService implements IFundsPoolService {

    @Resource
    private FundsPoolDao fundsPoolDao;


}
