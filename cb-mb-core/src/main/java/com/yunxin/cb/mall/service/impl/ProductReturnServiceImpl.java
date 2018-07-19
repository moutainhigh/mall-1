package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.Order;
import com.yunxin.cb.mall.entity.OrderItem;
import com.yunxin.cb.mall.entity.Product;
import com.yunxin.cb.mall.entity.ProductReturn;
import com.yunxin.cb.mall.entity.meta.OrderState;
import com.yunxin.cb.mall.entity.meta.ReturnRefundState;
import com.yunxin.cb.mall.mapper.OrderMapper;
import com.yunxin.cb.mall.mapper.ProductMapper;
import com.yunxin.cb.mall.mapper.ProductReturnMapper;
import com.yunxin.cb.mall.service.ProductReturnService;
import com.yunxin.cb.util.UUIDGeneratorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
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

    @Transactional
    public ProductReturn applyOrderProductReturn(ProductReturn productReturn) throws Exception {
        ProductReturn dbReturn = productReturnMapper.selectByOrderId(productReturn.getOrderId());
        if (null != dbReturn) {
            throw new Exception("该订单已提交退货申请");
        }
        Order order = orderMapper.selectByOrderIdAndCustomerId(productReturn.getOrderId(), productReturn.getCustomerId());
        //判断订单是否是已支付待提货状态
        if (order == null || (order.getOrderState() != OrderState.PAID_PAYMENT.ordinal() && order.getOrderState() != OrderState.OUT_STOCK.ordinal())) {
            throw new Exception("该订单不可以退货申请");
        }
        ProductReturn nReturn = new ProductReturn();
        BeanUtils.copyProperties(productReturn, nReturn);
        nReturn.setReturnCode(UUIDGeneratorUtil.getUUCode());
        nReturn.setItemId(productReturn.getItemId());
        nReturn.setApplyTime(new Date());
        nReturn.setPurchasingTime(order.getCreateTime());
        nReturn.setReturnRefundState(ReturnRefundState.APPLY_REFUND.ordinal());
        nReturn.setAuditState(0);
        nReturn.setRefundOnly(true);
        //更新库存（是否需要）
        Set<OrderItem> orderItems = order.getOrderItems();
        if (orderItems != null && !orderItems.isEmpty()) {
            for (OrderItem orderItem : orderItems) {
                if (orderItem.getItemId() == productReturn.getItemId()) {
                    //更新库存
                    Product product = productMapper.selectByPrimaryKey(orderItem.getProductId());
                    //增加库存
                    product.setStoreNum(product.getStoreNum() + orderItem.getProductNum());
                    productMapper.updateByPrimaryKey(product);
                    nReturn.setRefundPrice(Double.valueOf(orderItem.getOrderItemPrice()));
                }
            }
        }
        //更新订单状态
        order.setReturnRefundState(ReturnRefundState.APPLY_REFUND.ordinal());
        orderMapper.updateByPrimaryKey(order);
        //添加退货申请
        productReturnMapper.insert(productReturn);
        return productReturn;
    }

}

