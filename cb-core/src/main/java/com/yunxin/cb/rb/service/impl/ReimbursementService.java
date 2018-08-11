package com.yunxin.cb.rb.service.impl;

import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.mall.dao.OrderItemDao;
import com.yunxin.cb.mall.dao.ProductDao;
import com.yunxin.cb.mall.entity.OrderItem;
import com.yunxin.cb.mall.entity.Product;
import com.yunxin.cb.rb.dao.FundsPoolDao;
import com.yunxin.cb.rb.dao.ReimbursementDao;
import com.yunxin.cb.rb.dao.ReimbursementOrderDao;
import com.yunxin.cb.rb.dao.ReimbursementProcessDao;
import com.yunxin.cb.rb.entity.*;
import com.yunxin.cb.rb.entity.meta.ReimbursementProcessType;
import com.yunxin.cb.rb.entity.meta.ReimbursementType;
import com.yunxin.cb.rb.service.IReimbursementService;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 报账信息wangteng
 */
@Service
@Transactional
public class ReimbursementService implements IReimbursementService {
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

//                query.where(builder.equal(root.get(Reimbursement_.orderState),"FINANCE_IN_APPROVAL"));
            }
        });
        Page<Reimbursement> page=reimbursementDao.findAll(query,query.getPageRequest());
                page.getContent().forEach(reireimbursement -> {
                    List<OrderItem> list=orderItemDao.getOrderItemByReimbursement(reireimbursement.getReimbursementId());

                    if(list!=null&&list.size()>0){
                        StringBuffer codes=new StringBuffer();
                        for (int i=0;i<list.size();i++){
                            if(i==list.size()-1)
                                codes.append(list.get(i).getOrder().getOrderCode());
                            else
                                codes.append(list.get(i).getOrder().getOrderCode()).append(",");
                        }
                        reireimbursement.setOrderCodes(codes.toString());
                    }
                   FundsPool  fundsPool=fundsPoolDao.findByCatalog_CatalogId(reireimbursement.getCatalogId());
                   if(fundsPool==null)
                        reireimbursement.setFundsPoolRemark("无法分析");
                   else
                       reireimbursement.setFundsPoolRemark(reireimbursement.getOrderAmount().compareTo(fundsPool.getFunds())==1?"资金池不足，不可报账":"资金池满足，可报账");
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
    public boolean reimbursementAuditing(int reimbursementId, ReimbursementType reimbursementType,String remarks,int operType, HttpServletRequest request) {

       Reimbursement reimbursement=getReimbursement(reimbursementId);
        User user = (User) request.getSession().getAttribute("loginSession");
        ReimbursementProcess reimbursementProcess=new ReimbursementProcess();
        switch (reimbursementType){
            //财务人员审批
            case FINANCE_IN_APPROVAL:
                if(!reimbursement.getOrderState().equals(ReimbursementType.FINANCE_IN_APPROVAL))
                    return false;
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
                if(!reimbursement.getOrderState().equals(ReimbursementType.DIRECTOR_IN_APPROVAL))
                    return false;
                //审核通过
                if(operType==1){
                    //更新资金池
                    if(fundsPoolService.updateAndCountReimbursementAmout(reimbursementId)){
                        reimbursementProcess.setOrderState(ReimbursementProcessType.DIRECTOR_IN_APPROVAL);
                        reimbursementDao.updateReimbursementState(ReimbursementType.ALREADY_TO_ACCOUNT,reimbursementId);
                    }else
                        return false;
                    //更新钱包
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
        return true;

    }
}
