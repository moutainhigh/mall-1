package com.yunxin.cb.rb.service.impl;

import com.yunxin.cb.mall.dao.ProductDao;
import com.yunxin.cb.mall.entity.Product;
import com.yunxin.cb.rb.dao.FundsPoolDao;
import com.yunxin.cb.rb.dao.FundsPoolLogDao;
import com.yunxin.cb.rb.entity.FundsPool;
import com.yunxin.cb.rb.entity.FundsPoolLog;
import com.yunxin.cb.rb.entity.FundsPoolLog_;
import com.yunxin.cb.rb.entity.FundsPool_;
import com.yunxin.cb.rb.service.IFundsPoolService;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
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

    @Resource
    private FundsPoolDao fundsPoolDao;
    @Resource
    private FundsPoolLogDao fundsPoolLogDao;
    @Resource
    private ProductDao productDao;

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
    public boolean updateFundsAndSaveFundsPoolLog(BigDecimal amount, int version, int poolId,int productId,int transactionId,int type) {
        if (amount == null || amount.compareTo(new BigDecimal(0)) == 0){
            Product p = productDao.finByProductId(productId);
            float v = p.getSalePrice() - p.getCostPrice();
            amount = new BigDecimal(Float.toString(v));
        }
        int i = fundsPoolDao.updateFundsByIdAndAndVersion(amount,version,poolId);
        if(i == 1){

            FundsPool fundsPool = fundsPoolDao.findOne(poolId);
            FundsPoolLog f = new FundsPoolLog();
            f.setAmount(amount);//操作金额
            f.setCatalog(fundsPool.getCatalog());
            f.setCreateTime(new Date());
            f.setFunds(fundsPool.getFunds());
            f.setFundsPool(fundsPool);
            f.setPoolName(fundsPool.getPoolName());
            f.setProduct(new Product(productId));//
            f.setTransactionId(transactionId);//交易ID,累计为订单号，报帐为报帐ID
            f.setType(type);//类型：1.累计，2.报账
            f.setVersion(fundsPool.getVersion());
            fundsPoolLogDao.save(f);
            return true;
        }
        return false;
    }
}
