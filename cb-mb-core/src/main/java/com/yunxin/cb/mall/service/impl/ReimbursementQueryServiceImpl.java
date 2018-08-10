package com.yunxin.cb.mall.service.impl;


import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.ProfileState;
import com.yunxin.cb.mall.entity.meta.ReimbursementState;
import com.yunxin.cb.mall.mapper.*;
import com.yunxin.cb.mall.service.ReimbursementQueryService;
import com.yunxin.cb.mall.vo.*;
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

    //税点
//    private static final BigDecimal taxPoint = new BigDecimal("0.23");

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
    @Resource
    private ReimbursementProcessMapper reimbursementProcessMapper;
    @Resource
    private ProfileMapper profileMapper;

    /**
     * 查询可报账分页列表
     * @param q
     * @return
     * @throws Exception
     */
    @Override
    public PageFinder<ReimbursementVO> pageReimbursementQuery(Query q)throws Exception {
        //调用dao查询满足条件的分页数据
        List<ReimbursementQuery> list = reimbursementQueryMapper.selectReimbursementQuery(q);
        //税点
        Profile profile = profileMapper.getProfileByName(ProfileState.TAX_RATE.name());
        BigDecimal taxPoint = new BigDecimal(profile.getFileValue());
        //组装返回数据
        List<ReimbursementVO> listVO = new ArrayList<>();
        for(ReimbursementQuery reimbursementQuery :list){
            ReimbursementVO reimbursementVO = new ReimbursementVO();
            BeanUtils.copyProperties(reimbursementQuery, reimbursementVO);
            //税
            BigDecimal tax = BigDecimal.valueOf(reimbursementQuery.getAccountSalePrice()).multiply(taxPoint);
            //报账金额
            BigDecimal accountAmount = BigDecimal.valueOf(reimbursementQuery.getAccountSalePrice()).subtract(tax);
            reimbursementVO.setTax(tax);
            reimbursementVO.setAccountAmount(accountAmount);
            //String s = CalendarUtils.formatDateTime(reimbursementQuery.getCreateTime());
            listVO.add(reimbursementVO);
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
    /**
     * 报账
     * @param list
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReimbursementSuccessVO addReimbursement(List<AddReimbursementRequestVO> list) throws Exception {
        //查询二级商品分类列表
//        List<Catalog> catalogList = catalogMapper.selectAll();
        //税点
        Profile profile = profileMapper.getProfileByName(ProfileState.TAX_RATE.name());
        BigDecimal taxPoint = new BigDecimal(profile.getFileValue());
        Map<Integer, List<OrderItem>> lotaLogMap = new HashMap<>();
        //所有商品的总价
        BigDecimal allAccountSalePrice = new BigDecimal(0);
        for(AddReimbursementRequestVO addReimbursementRequestVO : list){
            //根据orderId和productId查询 货品
            OrderItem orderItem = orderItemMapper.selectByOrderIdAndProductId(addReimbursementRequestVO.getOrderId(),addReimbursementRequestVO.getProductId());
            //拆分订单
//            int commodityId = addReimbursementRequestVO.getCommodityId();
            //获取商品分类
            Catalog catalog = catalogMapper.selectByCommodityId(addReimbursementRequestVO.getCommodityId());
            //判断该订单属于哪一商品分类
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
            List<OrderItem> orderItemList = entry.getValue();
            for(OrderItem orderItems : orderItemList){
                accountSalePrice =  accountSalePrice.add(BigDecimal.valueOf(orderItems.getProductNum()*orderItems.getSalePrice()));
                allAccountSalePrice = allAccountSalePrice.add(BigDecimal.valueOf(orderItems.getProductNum()*orderItems.getSalePrice()));
            }
            //税
            tax = accountSalePrice.multiply(taxPoint);
            //到账金额
            accountAmount = accountSalePrice.subtract(tax);
            long time = System.currentTimeMillis();
            reimbursement.setAmount(accountSalePrice);
            reimbursement.setCreateTime(new Date());
            reimbursement.setCustomerId(1);
            reimbursement.setOrderAmount(accountAmount);
            reimbursement.setTax(tax);
            reimbursement.setReimbursementNo(String.valueOf(time));
            reimbursement.setOrderState(ReimbursementState.FINANCE_IN_APPROVAL);
            reimbursement.setCatalogId(entry.getKey());
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
    /**
     * 已报账列表查询
     * @param q
     * @return
     * @throws Exception
     */
    @Override
    public PageFinder<AlreadyReimbursementVO> selectAlreadyReimbursementQuery(Query q) throws Exception {
        //调用dao查询满足条件的分页数据
        List<Reimbursement> list = reimbursementMapper.selectAllByCustomerId(q);
        List<AlreadyReimbursementVO> listVO = new ArrayList<>();
        for(Reimbursement reimbursement : list){
            //商品总数量
            int num = 0;
            AlreadyReimbursementVO alreadyReimbursementVO = new AlreadyReimbursementVO();
            //查询商品相关信息
            List<ReimbursementProductVO> listProductVO = reimbursementQueryMapper.selectAlreadyReimbursementQuery(reimbursement.getReimbursementId());
            for(ReimbursementProductVO reimbursementProductVO : listProductVO){
                num = num + reimbursementProductVO.getProductNum();
            }
            BeanUtils.copyProperties(reimbursement, alreadyReimbursementVO);
            alreadyReimbursementVO.setList(listProductVO);
            alreadyReimbursementVO.setSum(num);
            listVO.add(alreadyReimbursementVO);
        }
        //调用dao统计满足条件的记录总数
        long rowCount = reimbursementQueryMapper.alreadyReimbursementCount(q);
        //如list为null时，则改为返回一个空列表
        listVO = listVO == null ? new ArrayList<AlreadyReimbursementVO>(0) : listVO;
        //将分页数据和记录总数设置到分页结果对象中
        PageFinder<AlreadyReimbursementVO> page = new PageFinder<AlreadyReimbursementVO>(q.getPageNo(), q.getPageSize(), rowCount);
        page.setData(listVO);
        return page;
    }
    /**
     * 已报账详情
     * @param reimbursementId
     * @param cuntomerId
     * @return
     * @throws Exception
     */
    @Override
    public AlreadyReimbursementVO selectAlreadyReimbursementDetail(int reimbursementId,int cuntomerId)throws Exception{
        Reimbursement reimbursement = reimbursementMapper.selectByPrimaryKeyAndCustomerId(reimbursementId,cuntomerId);
        //税点
        Profile profile = profileMapper.getProfileByName(ProfileState.TAX_RATE.name());
        BigDecimal taxPoint = new BigDecimal(profile.getFileValue());
        //商品总数量
        int num = 0;
        AlreadyReimbursementVO alreadyReimbursementVO = new AlreadyReimbursementVO();
        //查询商品相关信息
        List<ReimbursementProductVO> listProductVO = reimbursementQueryMapper.selectAlreadyReimbursementQuery(reimbursement.getReimbursementId());
        for(ReimbursementProductVO reimbursementProductVO : listProductVO){
            num = num + reimbursementProductVO.getProductNum();
        }
        //查询该报账订单审批时间
        List<ReimbursementProcess> list = reimbursementProcessMapper.selectAllByReimbursementId(reimbursement.getReimbursementId());
        //第一次审批时间
        if(list.size() == 1){
            ReimbursementProcess reimbursementProcess = list.get(0);
            alreadyReimbursementVO.setOperationTime(reimbursementProcess.getCreateTime());
            alreadyReimbursementVO.setRemarks(reimbursementProcess.getRemarks());
        }else if(list.size() > 1){
            //到账时间
            ReimbursementProcess reimbursementProcess = list.get(list.size()-1);
            alreadyReimbursementVO.setExaminationTime(reimbursementProcess.getCreateTime());
            alreadyReimbursementVO.setRemarks(reimbursementProcess.getRemarks());
        }
        //组合参数
        BeanUtils.copyProperties(reimbursement, alreadyReimbursementVO);
        alreadyReimbursementVO.setList(listProductVO);
        alreadyReimbursementVO.setSum(num);
        alreadyReimbursementVO.setTaxPoint(String.valueOf(taxPoint));
        return alreadyReimbursementVO;
    }
    /**
     * 用户取消报账
     * @param reimbursement
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelReimbursement(Reimbursement reimbursement)throws Exception{
        reimbursementMapper.updateByPrimaryKey(reimbursement);
    }
    /**
     * 报账已完成列表
     * @param q
     * @return
     * @throws Exception
     */
    @Override
    public PageFinder<CompleteReimbursementVO> selectCompleteReimbursement(Query q)throws Exception{
        //调用dao查询满足条件的分页数据
        List<Reimbursement> list = reimbursementMapper.selectAllByCustomerId(q);
        List<CompleteReimbursementVO> listVO = new ArrayList<>();
        for(Reimbursement reimbursement : list){
            //商品总数量
            int num = 0;
            CompleteReimbursementVO completeReimbursementVO = new CompleteReimbursementVO();
            //查询商品相关信息
            List<ReimbursementProductVO> listProductVO = reimbursementQueryMapper.selectAlreadyReimbursementQuery(reimbursement.getReimbursementId());
            for(ReimbursementProductVO reimbursementProductVO : listProductVO){
                completeReimbursementVO.setProductName(reimbursementProductVO.getProductName());
                num = num + reimbursementProductVO.getProductNum();
            }
            //查询该报账订单审批时间
            List<ReimbursementProcess> listProcess = reimbursementProcessMapper.selectAllByReimbursementId(reimbursement.getReimbursementId());
            completeReimbursementVO.setAccountAmount(reimbursement.getAmount());
            completeReimbursementVO.setSum(num);
            completeReimbursementVO.setReimbursementId(reimbursement.getReimbursementId());
            completeReimbursementVO.setOperationTime(/*listProcess.get(1).getCreateTime()*/new Date());
            listVO.add(completeReimbursementVO);
        }
        //如list为null时，则改为返回一个空列表
        listVO = listVO == null ? new ArrayList<CompleteReimbursementVO>(0) : listVO;
        //调用dao统计满足条件的记录总数
        long rowCount = reimbursementQueryMapper.alreadyReimbursementCount(q);
        //将分页数据和记录总数设置到分页结果对象中
        PageFinder<CompleteReimbursementVO> page = new PageFinder<CompleteReimbursementVO>(q.getPageNo(), q.getPageSize(), rowCount);
        page.setData(listVO);
        return page;
    }
    /**
     * 报账已完成详情
     * @param reimbursementId
     * @param cuntomerId
     * @return
     * @throws Exception
     */
    @Override
    public CompleteReimbursementDetailVO getCompleteReimbursementDetail(int reimbursementId,int cuntomerId)throws Exception{
        Reimbursement reimbursement = reimbursementMapper.selectByPrimaryKeyAndCustomerId(reimbursementId,cuntomerId);
        CompleteReimbursementDetailVO completeReimbursementDetailVO = new CompleteReimbursementDetailVO();
        List<String> list = new ArrayList<>();
        //查询商品相关信息
        List<ReimbursementProductVO> listProductVO = reimbursementQueryMapper.selectAlreadyReimbursementQuery(reimbursement.getReimbursementId());
        for(ReimbursementProductVO reimbursementProductVO : listProductVO){
            list.add(reimbursementProductVO.getProductName());
        }
        //查询该报账订单审批时间
        List<ReimbursementProcess> listVO = reimbursementProcessMapper.selectAllByReimbursementId(reimbursement.getReimbursementId());
        completeReimbursementDetailVO.setAccountAmount(reimbursement.getAmount());
        completeReimbursementDetailVO.setOrderState(reimbursement.getOrderState());
        completeReimbursementDetailVO.setOperationTime(/*listVO.get(1).getCreateTime()*/new Date());
        completeReimbursementDetailVO.setReimbursementNo(reimbursement.getReimbursementNo());
        completeReimbursementDetailVO.setPayment(reimbursement.getRepaymentType());
        if(reimbursement.getRepaymentAmount() != null){
            completeReimbursementDetailVO.setRepayment(reimbursement.getRepaymentAmount());
        }
        if(reimbursement.getRepaymentAmount() != null){
            completeReimbursementDetailVO.setActualAccount(reimbursement.getAmount().subtract(reimbursement.getRepaymentAmount()));
        }
        completeReimbursementDetailVO.setList(list);
        return completeReimbursementDetailVO;
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

    /**
     * 根据用户ID和报账订单ID查询订单
     */
    public Reimbursement selectByReimbursmentIdAndCustomer(int reimbursementId,int cuntomerId){
        Reimbursement reimbursement = reimbursementMapper.selectByPrimaryKeyAndCustomerId(reimbursementId,cuntomerId);
        return reimbursement;
    }
}
