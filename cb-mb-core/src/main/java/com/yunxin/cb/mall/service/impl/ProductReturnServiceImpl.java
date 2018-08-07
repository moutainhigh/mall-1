package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.Order;
import com.yunxin.cb.mall.entity.OrderItem;
import com.yunxin.cb.mall.entity.ProductReturn;
import com.yunxin.cb.mall.entity.meta.AuditState;
import com.yunxin.cb.mall.entity.meta.OrderState;
import com.yunxin.cb.mall.entity.meta.ReturnRefundState;
import com.yunxin.cb.mall.exception.CommonException;
import com.yunxin.cb.mall.mapper.OrderMapper;
import com.yunxin.cb.mall.mapper.ProductMapper;
import com.yunxin.cb.mall.mapper.ProductReturnMapper;
import com.yunxin.cb.mall.service.ProductReturnService;
import com.yunxin.cb.util.UUIDGeneratorUtil;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class ProductReturnServiceImpl implements ProductReturnService {

    private static final Logger logger = LoggerFactory.getLogger(ProductReturnService.class);

    @Resource
    private ProductReturnMapper productReturnMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private ProductMapper productMapper;

    @Transactional(rollbackFor = Exception.class)
    public ProductReturn applyOrderProductReturn(ProductReturn productReturn) throws Exception {
        Order order = checkProductReturnApply(productReturn.getOrderId(), productReturn.getCustomerId());
        ProductReturn nReturn = new ProductReturn();
        BeanUtils.copyProperties(productReturn, nReturn);
        nReturn.setReturnCode(UUIDGeneratorUtil.getUUCode());
        nReturn.setItemId(productReturn.getItemId());
        nReturn.setApplyTime(new Date());
        nReturn.setPurchasingTime(order.getCreateTime());
        nReturn.setReturnRefundState(ReturnRefundState.APPLY_REFUND);
        nReturn.setAuditState(AuditState.WAIT_AUDIT);
        nReturn.setRefundOnly(true);
        //更新库存（是否需要）
        Set<OrderItem> orderItems = order.getOrderItems();
        if (orderItems != null && !orderItems.isEmpty()) {
            for (OrderItem orderItem : orderItems) {
                if (orderItem.getItemId() == productReturn.getItemId()) {
//                    //更新库存
//                    Product product = productMapper.selectByPrimaryKey(orderItem.getProductId());
//                    //增加库存
//                    product.setStoreNum(product.getStoreNum() + orderItem.getProductNum());
//                    productMapper.updateByPrimaryKey(product);
                    nReturn.setRefundPrice(Double.valueOf(orderItem.getOrderItemPrice()));
                }
            }
        }
        //更新订单状态
        order.setReturnRefundState(ReturnRefundState.APPLY_REFUND);
        orderMapper.updateByPrimaryKey(order);
        //添加退货申请
        productReturnMapper.insert(nReturn);
        return productReturn;
    }
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<ProductReturn> listProductReturn(Query q) {
        List<ProductReturn> list = productReturnMapper.pageList(q);
        return list;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PageFinder<ProductReturn> pageProductReturn(Query q) {
        try {
            //调用dao查询满足条件的分页数据
            List<ProductReturn> list = productReturnMapper.pageList(q);
            //调用dao统计满足条件的记录总数
            long rowCount = productReturnMapper.count(q);
            //如list为null时，则改为返回一个空列表
            list = list == null ? new ArrayList<ProductReturn>(0) : list;
            //将分页数据和记录总数设置到分页结果对象中
            PageFinder<ProductReturn> page = new PageFinder<ProductReturn>(q.getPageNo(), q.getPageSize(), rowCount);
            page.setData(list);
            return page;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public ProductReturn getProductReturn(Integer productReturnId, Integer customerId){
        return productReturnMapper.selectByProductReturnIdAndCustomerId(productReturnId, customerId);
    }

    @Override
    public Order checkProductReturnApply(int orderId, int customerId) throws Exception{
        List<ProductReturn> dbReturn = productReturnMapper.selectByOrderId(orderId);
        if (null != dbReturn && dbReturn.size() > 0) {
            throw new CommonException("该订单已提交退货申请");
        }
        Order order = orderMapper.selectByOrderIdAndCustomerId(orderId, customerId);
        //判断订单是否是已支付待提货状态
        if (order == null) {
            throw new CommonException("该订单不存在");
        }
        if (OrderState.PAID_PAYMENT.equals(order.getOrderState()) && OrderState.OUT_STOCK.equals(order.getOrderState())) {
            throw new CommonException("该订单不可以退货申请");
        }
        return order;
    }

}

