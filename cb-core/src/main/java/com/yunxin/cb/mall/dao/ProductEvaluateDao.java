/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.ProductEvaluate;
import com.yunxin.cb.mall.entity.ProductEvaluateReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * @author j000101
 */
public interface ProductEvaluateDao extends JpaRepository<ProductEvaluate, Integer>, JpaSpecificationExecutor<ProductEvaluate> {

    @Query("select per from ProductEvaluateReply per left join fetch per.productEvaluate pe  where pe.evaluateId=?1")
    Set<ProductEvaluateReply> findProductEvaluateReplysByEvaluateId(int evaluateId);

    //@Query("from CommodityEvaluate c left join fetch c.commodity where c.member=?")
//	List<CommodityEvaluate> findByCustomer(int	customerId);

    @Query("select count(ce.evaluateId) from ProductEvaluate ce where ce.commodity.commodityId=?1")
    public long countCommodityEvaluateByCommodityId(int commodityId);

    @Query("select ce.score from ProductEvaluate ce where ce.commodity.commodityId=?1")
    public List<Integer> getAllEvaluatesByCommodityId(int commodityId);

//    @Query("select distinct ce from ProductEvaluate ce left join fetch ce.productEvaluateReplies where ce.orderItem.product.commodity.commodityId=?1")
//    public List<ProductEvaluate> findCommodityEvaluateByCommodityId(int commodityId);

//    @Query("select count(ce.evaluateId) from ProductEvaluate ce where ce.customerId=?1")
//    public long countCommodityEvaluateByCustomerId(int customerId);

//    @Query("from CommodityEvaluate ce left join fetch ce.commodity co where co.commodityId=?1 and ce.customerId=?1")
//    public CommodityEvaluate getCommodityEvaluateByCusIdAndCommId(int customerId,int commodityId);

//    @Query("select ce from ProductEvaluate ce left join fetch ce.orderItem co where ce.orderId=?1 and ce.customerId=?2 and co.productId=?3")
//    public ProductEvaluate getCommodityByCondition(int orderId, int customerId, int commodityId);

//    @Query("select ce from ProductEvaluate ce where ce.orderId=?1 ")
//    List<ProductEvaluate> getEvaluateByOrderId(int orderId);
}
