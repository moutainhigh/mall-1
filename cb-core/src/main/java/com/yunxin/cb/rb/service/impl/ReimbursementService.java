package com.yunxin.cb.rb.service.impl;

import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.mall.dao.OrderItemDao;
import com.yunxin.cb.mall.dao.ProductDao;
import com.yunxin.cb.mall.entity.OrderItem;
import com.yunxin.cb.mall.entity.Product;
import com.yunxin.cb.mall.entity.meta.WithdrawType;
import com.yunxin.cb.mall.service.IFinaciaWalletService;
import com.yunxin.cb.rb.dao.FundsPoolDao;
import com.yunxin.cb.rb.dao.ReimbursementDao;
import com.yunxin.cb.rb.dao.ReimbursementOrderDao;
import com.yunxin.cb.rb.dao.ReimbursementProcessDao;
import com.yunxin.cb.rb.entity.*;
import com.yunxin.cb.rb.entity.meta.ReimbursementProcessType;
import com.yunxin.cb.rb.entity.meta.ReimbursementType;
import com.yunxin.cb.rb.entity.meta.RepaymentType;
import com.yunxin.cb.rb.service.IReimbursementService;
import com.yunxin.cb.search.vo.ResponseResult;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 报账信息wangteng
 */
@Service
@Transactional(rollbackFor =RuntimeException.class)
public class ReimbursementService implements IReimbursementService {

    private final Logger logger=LoggerFactory.getLogger(getClass());
    @Resource
    private ReimbursementDao reimbursementDao;
    @Resource
    private OrderItemDao orderItemDao;
    @Resource
    private ReimbursementProcessDao reimbursementProcessDao;
    @Resource
    private ProductDao productDao;
    @Resource
    private ReimbursementOrderDao reimbursementOrderDao;
    @Resource
    private FundsPoolDao fundsPoolDao;
    @Resource
    private FundsPoolService fundsPoolService;

    @Resource
    private IFinaciaWalletService iFinaciaWalletService;

    @Override
    public Page<Reimbursement> pageReimbursement(PageSpecification<Reimbursement> query,int orderState) {


        query.setCustomSpecification(new CustomSpecification<Reimbursement>(){
            @Override
            public void buildFetch(Root<Reimbursement> root) {
                root.fetch(Reimbursement_.customer, JoinType.LEFT);
                root.fetch(Reimbursement_.reimbursementOrder, JoinType.LEFT);
            }
            @Override
            public void addConditions(Root<Reimbursement> root,
                                      CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                switch (orderState){
                    case 1:
                        predicates.add(builder.equal(root.get(Reimbursement_.orderState), ReimbursementType.FINANCE_IN_APPROVAL));
                        break;
                    case 2:
                        predicates.add(builder.equal(root.get(Reimbursement_.orderState), ReimbursementType.DIRECTOR_IN_APPROVAL));
                        break;
                }

                query.orderBy(builder.asc(root.get(Reimbursement_.createTime)));

            }
        });
        Page<Reimbursement> page=reimbursementDao.findAll(query,query.getPageRequest());
                page.getContent().forEach(reimbursement -> {
                    List<OrderItem> list=orderItemDao.getOrderItemByReimbursement(reimbursement.getReimbursementId());

                    if(list!=null&&list.size()>0){
                        StringBuffer codes=new StringBuffer();
                        for (int i=0;i<list.size();i++){
                            if(i==list.size()-1)
                                codes.append(list.get(i).getOrder().getOrderCode());
                            else
                                codes.append(list.get(i).getOrder().getOrderCode()).append(",");
                        }
                        reimbursement.setOrderCodes(codes.toString());
                    }
                   FundsPool  fundsPool=fundsPoolDao.findByCatalog_CatalogId(reimbursement.getCatalogId());
                   if(fundsPool==null)
                       reimbursement.setFundsPoolRemark("无法分析");
                   else
                       reimbursement.setFundsPoolRemark(reimbursement.getOrderAmount().compareTo(fundsPool.getFunds())==1?"资金池不足，不可报账":"资金池满足，可报账");
                   //报账总金额
                    BigDecimal big=new BigDecimal(10000);
                    if(reimbursement.getAmount().compareTo(big)==1)
                        reimbursement.setAmountStr(reimbursement.getAmount().divide(big).setScale(2,BigDecimal.ROUND_DOWN).toString()+"万");
                    else
                        reimbursement.setAmountStr(reimbursement.getAmount().toString());
                    if(reimbursement.getOrderAmount().compareTo(big)==1)
                        reimbursement.setOrderAmountStr(reimbursement.getOrderAmount().divide(big).setScale(2,BigDecimal.ROUND_DOWN).toString()+"万");
                    else
                        reimbursement.setOrderAmountStr(reimbursement.getOrderAmount().toString());
                    if(reimbursement.getTax().compareTo(big)==1)
                        reimbursement.setTaxStr(reimbursement.getTax().divide(big).setScale(2,BigDecimal.ROUND_DOWN).toString()+"万");
                    else
                        reimbursement.setTaxStr(reimbursement.getTax().toString());

                });
        return page;
    }

    @Override
    public List<OrderItem> queryOrderItemByIds(int reimbursementId) {

        List<OrderItem> list= orderItemDao.getOrderItemByReimbursement(reimbursementId);
        list.forEach(orderItem -> {
            Product product= productDao.finByProductId(orderItem.getProduct().getProductId());
            product.setProductName(product.getCommodity().getCommodityName());
            orderItem.setProduct(product);

        });
        return list;
    }

    public List<ReimbursementOrder>  findOrder(int reimbursementId){
        List<ReimbursementOrder>   list=reimbursementOrderDao.getReimbursementOrderItemById(reimbursementId);
        list.forEach(reimbursementOrder -> {
            OrderItem orderItem= orderItemDao.getOrderItemById(reimbursementOrder.getOrderItem().getItemId());
            reimbursementOrder.setProductName(orderItem.getProduct().getCommodity().getCommodityName());
            reimbursementOrder.setImgPath(orderItem.getProductImg());
            reimbursementOrder.setOrderCode(orderItem.getOrder().getOrderCode());

        } );
        return list;
    }

    @Override
    public List<ReimbursementProcess> getReimbursementProcessByRe(int reimbursementId) {
        return reimbursementProcessDao.getReimbursementProcessByRe(reimbursementId);
    }


    @Override
    public Reimbursement getReimbursement(int reimbursementId) {
        return  reimbursementDao.getReimbursement(reimbursementId);
    }

    @Override
    public String reimbursementAuditing(int reimbursementId, ReimbursementType reimbursementType,String remarks,int operType, User user) {

    try {

       Reimbursement reimbursement=getReimbursement(reimbursementId);
//        User user = (User) request.getSession().getAttribute("loginSession");
        //TODO 审批不通过需重新生成订单详情
        ReimbursementProcess reimbursementProcess=new ReimbursementProcess();
        switch (reimbursementType){
            //财务人员审批
            case FINANCE_IN_APPROVAL:
                if(!reimbursement.getOrderState().equals(ReimbursementType.FINANCE_IN_APPROVAL)){
                    return "操作失败";
                }

                //审核通过
                if(operType==1){
                    reimbursementProcess.setOrderState(ReimbursementProcessType.FINANCE_IN_APPROVAL);
                    reimbursementDao.updateReimbursementState(ReimbursementType.DIRECTOR_IN_APPROVAL,reimbursementId);
                }else{
                    reimbursementProcess.setOrderState(ReimbursementProcessType.FINANCE_NOT_PASS_THROUGH);
                    reimbursementDao.updateReimbursementState(ReimbursementType.NOT_PASS_THROUGH,reimbursementId);
                }

                break;
            //财务主管审批
            case DIRECTOR_IN_APPROVAL:
                if(!reimbursement.getOrderState().equals(ReimbursementType.DIRECTOR_IN_APPROVAL)){
                    return "操作失败";
                }

                //审核通过
                if(operType==1){

                    FundsPool  fundsPool=fundsPoolDao.findByCatalog_CatalogId(reimbursement.getCatalogId());
                    if(reimbursement.getOrderAmount().compareTo(fundsPool.getFunds())==1){
                        return "操作失败，资金池不足";
                    }


                    //更新资金池

                        reimbursementProcess.setOrderState(ReimbursementProcessType.DIRECTOR_IN_APPROVAL);

                        try{
                            //更新钱包
                            ResponseResult responseResult=iFinaciaWalletService.processCustomerMoney(reimbursement.getCustomer().getCustomerId(),reimbursement.getOrderAmount(),WithdrawType.BZ,"");
                            Map<String,BigDecimal> map=(Map<String,BigDecimal>)responseResult.getData();
                            logger.info(map.toString());
                            if(map!=null){
                                //自动还款
                                BigDecimal repayAmount=map.get("repayAmount");
                                //实际到账
                                BigDecimal realMoney=map.get("realMoney");
                                if(!fundsPoolService.updateAndCountReimbursementAmout(reimbursementId)){
                                    return "操作失败，资金池不足";
                                }

                                reimbursementDao.updateReimbursementsState(ReimbursementType.ALREADY_TO_ACCOUNT,repayAmount,realMoney, RepaymentType.WALLET,reimbursementId);
                            }
                        }catch (RuntimeException e){
                            logger.error("processCustomerMoney failed", e);
                            return "操作失败，未找到用户钱包";
                        }
                }else{
                    reimbursementProcess.setOrderState(ReimbursementProcessType.DIRECTOR_NOT_PASS_THROUGH);
                    reimbursementDao.updateReimbursementState(ReimbursementType.NOT_PASS_THROUGH,reimbursementId);
                }
                break;
        }
        reimbursementProcess.setCreateTime(new Date());
        reimbursementProcess.setRemarks(remarks==null?"":remarks);
        reimbursementProcess.setUser(user);
        reimbursementProcess.setReimbursement(reimbursement);
        reimbursementProcessDao.save(reimbursementProcess);

    }catch (Exception e){
        logger.error("reimbursementAuditing failed", e);
        return "服务器异常，请稍后重试";
    }
        return "审核成功";

    }
}
