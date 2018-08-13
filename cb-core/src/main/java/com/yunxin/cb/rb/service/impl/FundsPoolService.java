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
import java.util.ArrayList;
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
    public boolean updateFundsAndSaveFundsPoolLog(FundsPool fundsPool,BigDecimal amount,int type,List<FundsPoolLog> fundsPoolLogs) {
        int version = fundsPool.getVersion();
        int poolId = fundsPool.getPoolId();
        if(type == FundsPoolLogType.REIMBURSE.getStatus()){
            amount = amount.multiply(new BigDecimal(-1));
        }
        int i = fundsPoolDao.updateFundsByIdAndAndVersion(amount,version,poolId);
        if(i == 1){
            fundsPoolLogDao.save(fundsPoolLogs);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAndCountReimbursementAmout(int transactionId) {
        List<ReimbursementOrder> reimbursementOrders = reimbursementOrderDao.getReimbursementOrderItemById(transactionId);
        Product p = productDao.finByProductId(reimbursementOrders.get(0).getOrderItem().getProduct().getProductId());
        Integer catalogId  = getOneLevelCatalog(p.getCommodity().getCatalog().getCatalogId());
        FundsPool fundsPool = fundsPoolDao.findByCatalog_CatalogId(catalogId);
        if (fundsPool != null) {//理论上资金池不会存在null的情况，兼容旧数据
            List<FundsPoolLog> fundsPoolLogs = new ArrayList<>();
            BigDecimal totalAmount = new BigDecimal(0);             //操作总金额
            BigDecimal reimburseAmount = new BigDecimal(0);         //报账总金额
            BigDecimal funds = fundsPool.getFunds();                     //资金池金额
            for (int i = 0 ; i < reimbursementOrders.size() ; i++){
                ReimbursementOrder o = reimbursementOrders.get(i);
                BigDecimal amount = o.getAmount();
                reimburseAmount = reimburseAmount.add(amount);
                amount = amount.multiply(new BigDecimal(-1));
                funds = funds.add(amount);
                totalAmount = totalAmount.add(amount);
                FundsPoolLog f = new FundsPoolLog();
                f.setAmount(amount);//操作金额
                f.setCatalog(fundsPool.getCatalog());
                f.setCreateTime(new Date());
                f.setFunds(funds);
                f.setFundsPool(fundsPool);
                f.setPoolName(fundsPool.getPoolName());
                f.setProduct(new Product(p.getProductId()));//
                f.setTransactionId(transactionId);//交易ID,累计为订单号，报帐为报帐ID
                f.setItemId(o.getReimbursementOrderId());
                f.setType(FundsPoolLogType.REIMBURSE.getStatus());//类型：1.累计，2.报账
                f.setVersion(fundsPool.getVersion()+1);
                fundsPoolLogs.add(f);
            }
            if (fundsPool.getFunds().compareTo(reimburseAmount) >= 0) {
                return updateFundsAndSaveFundsPoolLog(fundsPool,totalAmount,FundsPoolLogType.REIMBURSE.getStatus(),fundsPoolLogs);
            } else {
                logger.info("目前资金池金额：{},报账的金额：{}，资金池金额不足！！！",fundsPool.getFunds(),reimburseAmount);
                return false;
            }
        }
        logger.error("一级分类id：{}的资金池为null,有异常",catalogId);
        return false;
    }

    @Override
    public boolean updateAndCountOrderAmout(int transactionId) {
        List<OrderItem> orderItems = orderItemDao.findOrderItemsByOrder_OrderId(transactionId);
        List<FundsPoolLog> fundsPoolLogs = new ArrayList<>();
        Product p = productDao.finByProductId(orderItems.get(0).getProduct().getProductId());
        Integer catalogId  = getOneLevelCatalog(p.getCommodity().getCatalog().getCatalogId());
        FundsPool fundsPool = fundsPoolDao.findByCatalog_CatalogId(catalogId);
        BigDecimal totalAmount = new BigDecimal(0);         //操作总金额
        BigDecimal funds = fundsPool.getFunds();                 //资金池金额
        for (int i = 0 ; i < orderItems.size() ; i++){
            OrderItem o = orderItems.get(i);
            float v = (o.getSalePrice() - o.getCostPrice()) * o.getProductNum();
            BigDecimal amount = new BigDecimal(Float.toString(v));
            funds = funds.add(amount);
            totalAmount = totalAmount.add(amount);
            FundsPoolLog f = new FundsPoolLog();
            f.setAmount(amount);//操作金额
            f.setCatalog(fundsPool.getCatalog());
            f.setCreateTime(new Date());
            f.setFunds(funds);
            f.setFundsPool(fundsPool);
            f.setPoolName(fundsPool.getPoolName());
            f.setProduct(new Product(p.getProductId()));//
            f.setTransactionId(transactionId);//交易ID,累计为订单号，报帐为报帐ID
            f.setItemId(o.getItemId());
            f.setType(FundsPoolLogType.GRAND.getStatus());//类型：1.累计，2.报账
            f.setVersion(fundsPool.getVersion()+1);
            fundsPoolLogs.add(f);
        }
        if(totalAmount.compareTo(new BigDecimal(0))>=0) {
            return updateFundsAndSaveFundsPoolLog(fundsPool, totalAmount, FundsPoolLogType.GRAND.getStatus(), fundsPoolLogs);
        }
        logger.error("订单号:{}的操作总金额:{},存在异常",transactionId,totalAmount);
        return false;
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
