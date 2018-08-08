package com.yunxin.cb.mall.service.impl;


import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.ReimbursementState;
import com.yunxin.cb.mall.mapper.*;
import com.yunxin.cb.mall.service.ReimbursementQueryService;
import com.yunxin.cb.mall.vo.AddReimbursementRequestVO;
import com.yunxin.cb.mall.vo.ReimbursementSuccessVO;
import com.yunxin.cb.mall.vo.ReimbursementVO;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ReimbursementQueryServiceImpl implements ReimbursementQueryService {

    private static final Logger logger = LoggerFactory.getLogger(ReimbursementQueryService.class);

    @Resource
    private ReimbursementQueryMapper reimbursementQueryMapper;
    @Resource
    private CatalogMapper catalogMapper;
    @Resource
    private OrderItemMapper orderItemMapper;
    @Resource
    private ReimbursementMapper reimbursementMapper;
    @Resource
    private ReimbursementOrderMapper reimbursementOrderMapper;


    @Override
    public PageFinder<ReimbursementVO> pageReimbursementQuery(Query q)throws Exception {
        //调用dao查询满足条件的分页数据
        List<ReimbursementQuery> list = reimbursementQueryMapper.selectReimbursementQuery(q);
        List<ReimbursementVO> listVO = new ArrayList<>();
        if(list.size()>0){
            for(ReimbursementQuery reimbursementQuery :list){
                ReimbursementVO reimbursementVO = new ReimbursementVO();
                BeanUtils.copyProperties(reimbursementQuery, reimbursementVO);
                BigDecimal tax = BigDecimal.valueOf(reimbursementQuery.getAccountSalePrice()*0.23);
                BigDecimal accountAmount = BigDecimal.valueOf(reimbursementQuery.getAccountSalePrice()).subtract(tax);
                reimbursementVO.setTax(tax);
                reimbursementVO.setAccountAmount(accountAmount);
                //String s = CalendarUtils.formatDateTime(reimbursementQuery.getCreateTime());
                listVO.add(reimbursementVO);
            }
        }
        //调用dao统计满足条件的记录总数
        long rowCount = reimbursementQueryMapper.count(q);
        //如list为null时，则改为返回一个空列表
        listVO = listVO == null ? new ArrayList<ReimbursementVO>(0) : listVO;
        //将分页数据和记录总数设置到分页结果对象中
        PageFinder<ReimbursementVO> page = new PageFinder<ReimbursementVO>(q.getPageNo(), q.getPageSize(), rowCount);
        page.setData(listVO);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReimbursementSuccessVO addReimbursement(List<AddReimbursementRequestVO> list) throws Exception {
        //查询二级商品分类列表
        List<Catalog> catalogList = catalogMapper.selectAll();
        Map<Integer, List<OrderItem>> lotaLogMap = new HashMap<>();
        //税点
        BigDecimal taxPoint = new BigDecimal("0.23");
        //所有商品的总价
        BigDecimal allAccountSalePrice = new BigDecimal(0);
        for(AddReimbursementRequestVO addReimbursementRequestVO : list){
            //根据orderId和productId查询 货品
            OrderItem orderItem = orderItemMapper.selectByOrderIdAndProductId(addReimbursementRequestVO.getOrderId(),addReimbursementRequestVO.getProductId());
            //拆分订单
            int commodityId = addReimbursementRequestVO.getCommodityId();
            Catalog catalog = catalogMapper.selectByCommodityId(commodityId);
            if(catalog.getParentCatalogId() == 1){
//                    Integer catalogId=isTwoFrool(catalog);
                List<OrderItem> items=lotaLogMap.get(catalog.getCatalogId());
                if(items==null){
                    items=new ArrayList<>();
                }
                items.add(orderItem);
                lotaLogMap.put(catalog.getCatalogId(),items);
            }else{
                Integer catalogId=isTwoFrool(catalog);
                List<OrderItem> items=lotaLogMap.get(catalogId);
                if(items==null){
                    items=new ArrayList<>();
                }
                items.add(orderItem);
                lotaLogMap.put(catalogId,items);
            }
        }

        //合并同一商品大类的订单
        for(Map.Entry<Integer, List<OrderItem>> entry : lotaLogMap.entrySet()){
            Reimbursement reimbursement = new Reimbursement();
            //总金额
            BigDecimal accountSalePrice = new BigDecimal(0);
            //税
            BigDecimal tax;

            //实际到账金额
            BigDecimal accountAmount;
            //合并同一类商品的订单
            int catalogId = entry.getKey();
            List<OrderItem> orderItemList = entry.getValue();
            for(OrderItem orderItems : orderItemList){
                accountSalePrice =  accountSalePrice.add(BigDecimal.valueOf(orderItems.getProductNum()*orderItems.getSalePrice()));
                allAccountSalePrice = allAccountSalePrice.add(BigDecimal.valueOf(orderItems.getProductNum()*orderItems.getSalePrice()));
            }
            tax = accountSalePrice.multiply(taxPoint);
            accountAmount = accountSalePrice.subtract(tax);
            long time = System.currentTimeMillis();
            reimbursement.setAmount(accountSalePrice);
            reimbursement.setCreateTime(new Date());
            reimbursement.setCustomerId(1);
            reimbursement.setOrderAmount(accountAmount);
            reimbursement.setTax(tax);
            reimbursement.setReimbursementNo(String.valueOf(time));
            reimbursement.setOrderState(ReimbursementState.FINANCE_IN_APPROVAL);
            reimbursementMapper.insert(reimbursement);
            //保存报账信息关联表
            for(OrderItem orderItemes : orderItemList){
                ReimbursementOrder reimbursementOrder = new ReimbursementOrder();
                BigDecimal oneAmount = BigDecimal.valueOf(orderItemes.getSalePrice()).subtract(taxPoint);
                reimbursementOrder.setAmount(oneAmount);
                reimbursementOrder.setCreateTime(new Date());
                reimbursementOrder.setOrderItemId(orderItemes.getItemId());
                reimbursementOrder.setProductId(orderItemes.getProductId());
                reimbursementOrder.setProductPrice(BigDecimal.valueOf(orderItemes.getSalePrice()));
                reimbursementOrder.setReimbursementId(reimbursement.getReimbursementId());
                reimbursementOrder.setTax(BigDecimal.valueOf(orderItemes.getSalePrice()).subtract(oneAmount));
                reimbursementOrderMapper.insert(reimbursementOrder);
            }
        }
        //所有商品交的税
        BigDecimal allTax = allAccountSalePrice.multiply(taxPoint);
        //所有商品报账金额
        BigDecimal allAmount = allAccountSalePrice.subtract(allTax);
        ReimbursementSuccessVO reimbursementSuccessVO = new ReimbursementSuccessVO();
        reimbursementSuccessVO.setAccountAmount(allAmount);
        reimbursementSuccessVO.setAccountSalePrice(allAccountSalePrice);
        reimbursementSuccessVO.setTax(allTax);
        reimbursementSuccessVO.setTaxPoint(String.valueOf(taxPoint));
        return reimbursementSuccessVO;
    }
    //递归查找商品分类
    public Integer isTwoFrool(Catalog catalog){
        Catalog queryLog = catalogMapper.selectByParentCatalogId(catalog.getParentCatalogId());
        if(queryLog.getParentCatalogId()==1){
            return queryLog.getCatalogId();
        }else{
            isTwoFrool(queryLog);
        }
        return queryLog.getParentCatalogId();
    }
}
