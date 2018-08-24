package com.yunxin.cb.rb.service.impl;

import com.yunxin.cb.mall.dao.CatalogDao;
import com.yunxin.cb.mall.dao.OrderItemDao;
import com.yunxin.cb.mall.dao.ProductDao;
import com.yunxin.cb.mall.entity.Catalog;
import com.yunxin.cb.mall.entity.OrderItem;
import com.yunxin.cb.mall.entity.Product;
import com.yunxin.cb.rb.dao.FundsPoolDao;
import com.yunxin.cb.rb.dao.FundsPoolLogDao;
import com.yunxin.cb.rb.dao.ReimbursementOrderDao;
import com.yunxin.cb.rb.entity.*;
import com.yunxin.cb.rb.service.IFundsPoolService;
import com.yunxin.common.ConstantsCB.FundsPoolLogType;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description:    资金池接口服务实现类
 * @author: lxc
 * @Return :
 * @DateTime: 2018/8/7 11:03
 */
@Service
@Transactional
public class FundsPoolService implements IFundsPoolService {

    private static Logger logger = LoggerFactory.getLogger(FundsPoolService.class);
    @Resource
    private FundsPoolDao fundsPoolDao;
    @Resource
    private FundsPoolLogDao fundsPoolLogDao;
    @Resource
    private ProductDao productDao;
    @Resource
    private CatalogDao catalogDao;
    @Resource
    private OrderItemDao orderItemDao;
    @Resource
    private ReimbursementOrderDao reimbursementOrderDao;

    @Override
    public List<FundsPool> getFundsPoolList() {
        return fundsPoolDao.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<FundsPool> pageFundsPool(PageSpecification<FundsPool> queryRequest) {
        queryRequest.setCustomSpecification(new CustomSpecification<FundsPool>() {
            @Override
            public void addConditions(Root<FundsPool> root, CriteriaQuery<?> query,
                                      CriteriaBuilder builder, List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(FundsPool_.poolId)));
            }
        });
        Page<FundsPool> page = fundsPoolDao.findAll(queryRequest, queryRequest.getPageRequest());
        return page;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public FundsPool getFundsPoolByid(int poolId) {
        return fundsPoolDao.findOne(poolId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<FundsPoolLog> pageFundsPoolLog(PageSpecification<FundsPoolLog> queryRequest) {
        queryRequest.setCustomSpecification(new CustomSpecification<FundsPoolLog>() {
            @Override
            public void addConditions(Root<FundsPoolLog> root, CriteriaQuery<?> query,
                                      CriteriaBuilder builder, List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(FundsPoolLog_.poolLogId)));
            }
        });
        Page<FundsPoolLog> page = fundsPoolLogDao.findAll(queryRequest, queryRequest.getPageRequest());
        return page;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public FundsPoolLog getFundsPoolLogByid(int poolLogId) {
        return fundsPoolLogDao.findOne(poolLogId);
    }

    @Override
    public FundsPoolLog getFullFundsPoolLogByid(int poolLogId) {
        return fundsPoolLogDao.findFundsPoolLogDetail(poolLogId);
    }

    @Override
    public boolean updateFundsAndSaveFundsPoolLog(BigDecimal amount,int productId,int transactionId,int itemId,int type ) {
        Product p = productDao.finByProductId(productId);
        if(p != null){
            Integer catalogId  = getOneLevelCatalog(p.getCommodity().getCatalog().getCatalogId());
            FundsPool fundsPool = fundsPoolDao.findByCatalog_CatalogId(catalogId);
            if(fundsPool != null){//理论上资金池不会存在null的情况，兼容旧数据
                int version = fundsPool.getVersion();
                int poolId = fundsPool.getPoolId();
                if(type == FundsPoolLogType.REIMBURSE.getStatus()){
                    amount = amount.multiply(new BigDecimal(-1));
                }
                int i = fundsPoolDao.updateFundsByIdAndAndVersion(amount,version,poolId);
                if(i == 1){
                    FundsPoolLog f = new FundsPoolLog();
                    f.setAmount(amount);//操作金额
                    f.setCatalog(fundsPool.getCatalog());
                    f.setCreateTime(new Date());
                    f.setFunds(fundsPool.getFunds().add(amount));
                    f.setFundsPool(fundsPool);
                    f.setPoolName(fundsPool.getPoolName());
                    f.setProduct(new Product(productId));//
                    f.setTransactionId(transactionId);//交易ID,累计为订单号，报帐为报帐ID
                    f.setItemId(itemId);
                    f.setType(type);//类型：1.累计，2.报账
                    f.setVersion(fundsPool.getVersion()+1);
                    fundsPoolLogDao.save(f);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean updateAndCountReimbursementAmout(int transactionId) {
        BigDecimal reimburseAmount = reimbursementOrderDao.getReimburseAmountByReimburseId(transactionId);//报账总金额
        List<ReimbursementOrder> reimbursementOrders = reimbursementOrderDao.getReimbursementOrderItemById(transactionId);
        Product p = productDao.finByProductId(reimbursementOrders.get(0).getOrderItem().getProduct().getProductId());
        Integer catalogId  = getOneLevelCatalog(p.getCommodity().getCatalog().getCatalogId());
        FundsPool fundsPool = fundsPoolDao.findByCatalog_CatalogId(catalogId);
        if (fundsPool != null) {//理论上资金池不会存在null的情况，兼容旧数据
            if (fundsPool.getFunds().compareTo(reimburseAmount) >= 0) {
                reimbursementOrders.stream().forEach(o -> {
                    BigDecimal amount = o.getAmount();
                    updateFundsAndSaveFundsPoolLog(amount, o.getOrderItem().getProduct().getProductId(), transactionId, o.getReimbursementOrderId(), FundsPoolLogType.REIMBURSE.getStatus());
                });
                return true;
            } else {
                logger.info("目前资金池金额：{},报账的金额：{}，资金池金额不足！！！",fundsPool.getFunds(),reimburseAmount);
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean updateAndCountOrderAmout(int transactionId) {
        List<OrderItem> orderItems = orderItemDao.findOrderItemsByOrder_OrderId(transactionId);
        orderItems.stream().forEach(o->{
            float v = (o.getSalePrice() - o.getCostPrice()) * o.getProductNum();
            BigDecimal amount = new BigDecimal(Float.toString(v));
            updateFundsAndSaveFundsPoolLog(amount,o.getProduct().getProductId(),transactionId,o.getItemId(),FundsPoolLogType.GRAND.getStatus());
        });
        return true;
    }

    /**
     * @Description:        根据分类id获得一级分类对象
     * @author: lxc
     * @param catalogId
     * @Return com.yunxin.cb.mall.entity.Catalog:
     * @DateTime: 2018/8/10 17:27
     */
    private Integer getOneLevelCatalog(int catalogId){
        Catalog catalog = catalogDao.findByCategoryId(catalogId);
        if(catalog.getParentCatalogId()==1)return catalog.getCatalogId();//分类对象的父分类对象等于1则说明此catalog为一级分类对象
        //一级分类父对象不会存在null的情况
        if(catalog.getParentCatalog().getParentCatalogId() == 1) {
            return catalog.getParentCatalogId();
        }else{
            return getOneLevelCatalog(catalog.getParentCatalogId());
        }

    }


}
