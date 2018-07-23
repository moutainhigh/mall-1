package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.*;
import com.yunxin.cb.mall.mapper.*;
import com.yunxin.cb.mall.service.OrderService;
import com.yunxin.cb.util.UUIDGeneratorUtil;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderItemMapper orderItemMapper;
    @Resource
    private OrderInvoiceMapper orderInvoiceMapper;
    @Resource
    private OrderLoanApplyMapper orderLoanApplyMapper;
    @Resource
    private OrderLogMapper orderLogMapper;
    @Resource
    private CustomerWalletMapper customerWalletMapper;

    @Resource
    private ProductMapper productMapper;

    /***
     * 创建订单
     * @param order
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order createOrder(Order order) throws Exception {
        //添加订单数据
        Date createTime = new Date();
        order.setCreateTime(createTime);
        order.setUpdateTime(createTime);
        order.setOrderCode(UUIDGeneratorUtil.getUUCode());
        order.setOrderState(OrderState.PENDING_PAYMENT.ordinal());
        defaultValue(order);//添加默认数据
        double totalPrice = 0; // 订单总价
        int totalQuantity = 0;//订单货品总数量

        //TODO :活动相关和会员积分相关的还未加
        Set<OrderItem> orderItems = order.getOrderItems();
        if (orderItems != null && !orderItems.isEmpty()) {
            for (OrderItem orderItem : orderItems) {
                //根据货品id查询货品
                Product product = productMapper.selectByPrimaryKey(orderItem.getProductId());
                //判断货品是否存在，且库存足够
                if (product == null || product.getStoreNum() <= 0) {
                    //库存不足
                    throw new Exception("库存不足");
                }
                int productNum = orderItem.getProductNum() == null ? 0 : orderItem.getProductNum();
                totalQuantity += productNum;
                orderItem.setOrderId(order.getOrderId());
                orderItem.setOrderItemPrice(product.getSalePrice() * productNum);
                orderItem.setProductId(product.getProductId());
                orderItem.setSalePrice(product.getSalePrice());
                orderItem.setEvaluate(false);
                orderItem.setCreateTime(createTime);
                //减少库存
                product.setStoreNum(product.getStoreNum() - productNum);
                int reservedStoreNum = product.getReservedStoreNum() == null ? 0  : product.getReservedStoreNum();
                product.setReservedStoreNum(productNum + reservedStoreNum);
                productMapper.updateByPrimaryKey(product);
                totalPrice += product.getSalePrice();
            }
        }
        //支付方式
        if (order.getPaymentType()== PaymentType.LOAN.ordinal()) {
            CustomerWallet customerWallet = customerWalletMapper.selectByPrimaryKey(order.getCustomerId());
            if (customerWallet != null) {
                double expectedReturnAmount = customerWallet.getExpectedReturnAmount() == null ? 0 : customerWallet.getExpectedReturnAmount();
                double loanQuota = customerWallet.getLoanQuota() == null ? 0 : customerWallet.getLoanQuota();
                //查询用户的钱包的待收收益和可贷余额总额是否大于或等于商品的销售金额
                if (expectedReturnAmount + loanQuota < totalPrice){
                    throw new Exception("您的信用额度不够，无法贷款购买此商品，请选择其他商品");
                }
                //在后台审核贷款申请通过是减少，优先减收益在减额度
            } else {
                throw new Exception("您的信用额度不够，无法贷款购买此商品，请选择其他商品");
            }
            //自提
            order.setDeliveryType(DeliveryType.ZT.ordinal());
        }

        order.setProdQuantity(totalQuantity);
        order.setTotalPrice(totalPrice);
        order.setFeeTotal(order.getTotalPrice());
        orderMapper.insert(order);

        //添加订单商品数据
        if (orderItems != null && !orderItems.isEmpty()) {
            for (OrderItem orderItem : orderItems) {
                orderItem.setOrderId(order.getOrderId());
                orderItemMapper.insert(orderItem);
            }
        }
        //发票数据
        if (order.getOrderInvoice() != null) {
            order.getOrderInvoice().setOrderId(order.getOrderId());
            orderInvoiceMapper.insert(order.getOrderInvoice());
        }
        if (order.getPaymentType()== PaymentType.LOAN.ordinal()) {
            //添加订单借款申请数据
            OrderLoanApply orderLoanApply = new OrderLoanApply();
            orderLoanApply.setLoanCode(UUIDGeneratorUtil.getUUCode());
            orderLoanApply.setOrderId(order.getOrderId());
            orderLoanApply.setCustomerId(order.getCustomerId());
            orderLoanApply.setLoanPrice(totalPrice);
            orderLoanApply.setLoanState(LoanState.WAIT_LOAN.ordinal());
            orderLoanApply.setAuditState(AuditState.WAIT_AUDIT.ordinal());
            orderLoanApply.setCreateTime(createTime);
            orderLoanApply.setUpdateTime(createTime);
            orderLoanApplyMapper.insert(orderLoanApply);
        }
        //添加订单日志
        OrderLog orderLog = new OrderLog();
        orderLog.setTime(createTime);
        orderLog.setOrderCode(order.getOrderCode());
        orderLog.setHandler(String.valueOf(order.getCustomerId()));
        orderLog.setRemark("订单确认");
        orderLogMapper.insert(orderLog);
        return order;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PageFinder<Order> pageOrder(Query q) {
        PageFinder<Order> page = new PageFinder<Order>(q.getPageNo(), q.getPageSize());
        List<Order> list = null;
        long rowCount = 0L;
        try {
            //调用dao查询满足条件的分页数据
            list = orderMapper.pageList(q);
            //调用dao统计满足条件的记录总数
            rowCount = orderMapper.count(q);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        //如list为null时，则改为返回一个空列表
        list = list == null ? new ArrayList<Order>(0) : list;
        //将分页数据和记录总数设置到分页结果对象中
        page.setData(list);
        page.setRowCount(rowCount);//记录总数
        page.setPageCount((int)rowCount);//总页数
        return page;
    }

    @Override
    public Order getByOrderIdAndCustomerId(Integer orderId, Integer customerId){
        return orderMapper.selectByOrderIdAndCustomerId(orderId, customerId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order cancelOrder(Order order) throws Exception {
        order = orderMapper.selectByOrderIdAndCustomerId(order.getOrderId(), order.getCustomerId());
        //待付款订单才可取消
        if (order != null && order.getOrderState() == OrderState.PENDING_PAYMENT.ordinal()){
            Set<OrderItem> orderItems = order.getOrderItems();
            if (orderItems != null && !orderItems.isEmpty()) {
                for (OrderItem orderItem : orderItems) {
                    //更新库存
                    Product product = productMapper.selectByPrimaryKey(orderItem.getProductId());
                    //增加库存
                    product.setStoreNum(product.getStoreNum() + orderItem.getProductNum());
                    int reservedStoreNum = product.getReservedStoreNum() == null ? 0  : product.getReservedStoreNum();
                    product.setReservedStoreNum(reservedStoreNum - orderItem.getProductNum());
                    if (reservedStoreNum - orderItem.getProductNum() < 0) {
                        product.setReservedStoreNum(0);
                    }
                    productMapper.updateByPrimaryKey(product);
                }
            } else {
                throw new Exception("无可退货品");
            }
            Date now = new Date();
            //更改订单为取消状态
            order.setCancelTime(now);
            order.setOrderState(OrderState.CANCELED.ordinal());
            orderMapper.updateByPrimaryKey(order);
            //更改订单贷款申请为取消
            OrderLoanApply orderLoanApply = orderLoanApplyMapper.selectByOrderId(order.getOrderId());
            orderLoanApply.setLoanState(LoanState.CANCELED.ordinal());
            orderLoanApplyMapper.updateByPrimaryKey(orderLoanApply);
            //添加订单日志
            OrderLog orderLog = new OrderLog();
            orderLog.setTime(now);
            orderLog.setOrderCode(order.getOrderCode());
            orderLog.setHandler(String.valueOf(order.getCustomerId()));
            orderLog.setRemark("订单取消");
            orderLogMapper.insert(orderLog);
        } else {
            throw new Exception("该订单不可取消");
        }
        return order;
    }

    public void confirm () {
        //确认收货
    }

    private void defaultValue(Order order) {
        order.setCouponsFee(0d);
        order.setDelivery(false);
        order.setDeliveryFeeTotal(0d);
        order.setDeliveryState(0);
        if (order.getDeliveryType() == null) {
            order.setDeliveryType(DeliveryType.ZT.ordinal());
        }
        order.setScoreTotal(0);
        order.setProvince("0");
        order.setCity("0");
        order.setDistrict("0");
        //order.setConsigneeAddress("");
        //order.setConsigneeName("");
        order.setEnabled(true);
        order.setWeightTotal(0d);
        order.setVolumeTotal(0d);
        order.setUsedScore(0);
        order.setPostCode("");
        order.setPayByIntegral(0d);
        order.setDiscountTotal(0d);
        order.setDiscountDeliveryFeeTotal(0d);
        //order.setSellerId(0);
        //order.setLogisticId(0);
    }
}

