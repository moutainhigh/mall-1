package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.*;
import com.yunxin.cb.mall.exception.CommonException;
import com.yunxin.cb.mall.mapper.*;
import com.yunxin.cb.mall.service.CommodityService;
import com.yunxin.cb.mall.service.OrderService;
import com.yunxin.cb.mall.vo.CommodityVo;
import com.yunxin.cb.mall.vo.DeliveryAddressVO;
import com.yunxin.cb.mall.vo.TempOrderItemVO;
import com.yunxin.cb.mall.vo.TempOrderVO;
import com.yunxin.cb.util.UUIDGeneratorUtil;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    private DeliveryAddressMapper deliveryAddressMapper;
    @Resource
    private CommodityService commodityService;

    @Resource
    private ProductMapper productMapper;

    /***
     * 获取预下单数据（订单确认页数据）
     * @param customerId
     * @param productId
     * @param buyNum
     * @param paymentType
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public TempOrderVO getTempOrder(int customerId, int productId, int buyNum, PaymentType paymentType) throws Exception {
        //根据货品id查询货品（审核通过且上架货品）
        Product product = productMapper.selectProductById(productId, ProductState.AUDITED.ordinal(), PublishState.UP_SHELVES.ordinal());
        //判断货品是否存在，且库存足够
        if (product == null || product.getStoreNum() <= 0) {
            //库存不足
            throw new CommonException("库存不足");
        }
        //获取商品信息
        TempOrderVO tempOrderVO = new TempOrderVO();
        CommodityVo commodityVo = commodityService.getCommdityDetail(productId, customerId);
        if (commodityVo == null) {
            return null;
        }
        //商品信息
        BeanUtils.copyProperties(tempOrderVO, commodityVo);
        //货品信息组装
        TempOrderItemVO tempOrderItemVO = null;
        if(!StringUtils.isEmpty(commodityVo.getProductVo())){
            tempOrderItemVO = new TempOrderItemVO();
            BeanUtils.copyProperties(tempOrderItemVO, commodityVo.getProductVo());
            tempOrderItemVO.setBuyNum(buyNum);
        }
        //获取默认地址
        DeliveryAddress deliveryAddress = deliveryAddressMapper.selectDefaultByCustomerId(customerId);
        if(!StringUtils.isEmpty(deliveryAddress)){
            DeliveryAddressVO deliveryAddressVO = new DeliveryAddressVO();
            BeanUtils.copyProperties(deliveryAddressVO, deliveryAddress);
            tempOrderVO.setDeliveryAddressVO(deliveryAddressVO);
        }
        //商家信息
        tempOrderVO.setSellerVo(commodityVo.getSellerVo());
        //规格信息
        tempOrderVO.setSpecs(commodityVo.getSpecs());
        //货品信息
        tempOrderVO.setTempOrderItemVO(tempOrderItemVO);
        //选择的支付方式
        for (PaymentType pay : PaymentType.values()){
            if (pay.equals(paymentType)) {
                tempOrderVO.setSelectPaymentType(pay);
            }
        }
        return tempOrderVO;
    }

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
        order.setOrderState(OrderState.PENDING_PAYMENT);
        defaultValue(order);//添加默认数据
        double totalPrice = 0; // 订单总价
        int totalQuantity = 0;//订单货品总数量

        //TODO :活动相关和会员积分相关的还未加
        Set<OrderItem> orderItems = order.getOrderItems();
        if (orderItems != null && !orderItems.isEmpty()) {
            for (OrderItem orderItem : orderItems) {
                //根据货品id查询货品（审核通过且上架货品）
                Product product = productMapper.selectProductById(orderItem.getProductId(), ProductState.AUDITED.ordinal(), PublishState.UP_SHELVES.ordinal());
                //判断货品是否存在，且库存足够
                if (product == null || product.getStoreNum() <= 0) {
                    //库存不足
                    throw new CommonException("库存不足");
                }
                int productNum = orderItem.getProductNum() == null ? 0 : orderItem.getProductNum();
                totalQuantity += productNum;
                orderItem.setOrderId(order.getOrderId());
                orderItem.setOrderItemPrice(product.getSalePrice() * productNum);
                orderItem.setProductId(product.getProductId());
                orderItem.setSalePrice(product.getSalePrice());
                orderItem.setProductImg(product.getDefaultPicPath());
                orderItem.setEvaluate(false);
                orderItem.setCreateTime(createTime);
                orderItem.setCostPrice(product.getCostPrice());
                //减少库存
                product.setStoreNum(product.getStoreNum() - productNum);
                int reservedStoreNum = product.getReservedStoreNum() == null ? 0  : product.getReservedStoreNum();
                product.setReservedStoreNum(productNum + reservedStoreNum);
                productMapper.updateByPrimaryKey(product);
                totalPrice += product.getSalePrice();
            }
        }
        //支付方式
        if (order.getPaymentType()== PaymentType.LOAN) {
            order.setAuditState(AuditState.WAIT_AUDIT);
//            CustomerWallet customerWallet = customerWalletMapper.selectByCustomerId(order.getCustomerId());
//            if (customerWallet != null) {
//                double expectedReturnAmount = customerWallet.getExpectedReturnAmount() == null ? 0 : customerWallet.getExpectedReturnAmount();
//                double loanQuota = customerWallet.getLoanQuota() == null ? 0 : customerWallet.getLoanQuota();
//                //查询用户的钱包的待收收益和可贷余额总额是否大于或等于商品的销售金额
//                if (expectedReturnAmount + loanQuota < totalPrice){
//                    throw new CommonException("您的信用额度不够，无法贷款购买此商品，请选择其他商品");
//                }
//                //在后台审核贷款申请通过是减少，优先减收益在减额度
//            } else {
//                throw new CommonException("您的信用额度不够，无法贷款购买此商品，请选择其他商品");
//            }
        } else {
            order.setAuditState(AuditState.AUDITED);
        }
        //收货地址
        DeliveryAddress deliveryAddress = deliveryAddressMapper.selectByPrimaryKey(order.getAddressId(), order.getCustomerId());
        if (deliveryAddress != null){
            order.setProvince(deliveryAddress.getProvince());
            order.setCity(deliveryAddress.getCity());
            order.setDistrict(deliveryAddress.getDistrict());
            order.setConsigneeAddress(deliveryAddress.getConsigneeAddress());
            order.setConsigneeName(deliveryAddress.getConsigneeName());
            order.setConsigneeMobile(deliveryAddress.getConsigneeMobile());
            order.setConsigneeTelephone(deliveryAddress.getConsigneeTelephone());
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
        //添加订单日志
        orderLogMapper.insert(new OrderLog(String.valueOf(order.getCustomerId()),order.getOrderCode(),"订单确认"));
        return order;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public PageFinder<Order> pageOrder(Query q) {
        try {
            //调用dao查询满足条件的分页数据
            List<Order> list = orderMapper.pageList(q);
            //调用dao统计满足条件的记录总数
            long rowCount = orderMapper.count(q);
            //如list为null时，则改为返回一个空列表
            list = list == null ? new ArrayList<Order>(0) : list;
            //将分页数据和记录总数设置到分页结果对象中
            PageFinder<Order> page = new PageFinder<Order>(q.getPageNo(), q.getPageSize(), rowCount);
            page.setData(list);
            return page;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public Order getByOrderIdAndCustomerId(Integer orderId, Integer customerId){
        return orderMapper.selectByOrderIdAndCustomerId(orderId, customerId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order cancelOrder(Order order) throws Exception {
        Order orderDb = orderMapper.selectByOrderIdAndCustomerId(order.getOrderId(), order.getCustomerId());
        //待付款订单才可取消
        if (orderDb != null && orderDb.getOrderState() == OrderState.PENDING_PAYMENT){
            Set<OrderItem> orderItems = orderDb.getOrderItems();
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
                throw new CommonException("无货品");
            }
            Date now = new Date();
            //更改订单为取消状态
            orderDb.setCancelReason(order.getCancelReason());
            orderDb.setCancelTime(now);
            orderDb.setOrderState(OrderState.CANCELED);
            orderMapper.updateByPrimaryKey(orderDb);
            //添加订单日志
            orderLogMapper.insert(new OrderLog(String.valueOf(orderDb.getCustomerId()),orderDb.getOrderCode(),"订单取消"));
        } else {
            throw new CommonException("该订单不可取消");
        }
        return orderDb;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int confirmOrder(Integer orderId, Integer customerId) throws Exception {
        Order orderDb = orderMapper.selectByOrderIdAndCustomerId(orderId, customerId);
        //已付款订单才可确认收货
        if (orderDb != null &&
                (orderDb.getOrderState() == OrderState.PAID_PAYMENT || orderDb.getOrderState() == OrderState.OUT_STOCK)){
            int count = orderMapper.updateStateByOrderIdAndCustomerId(orderId, customerId, OrderState.RECEIVED, DeliveryState.RECEIVED);
            //添加订单日志
            if (count > 0) {
                OrderLog orderLog = new OrderLog(String.valueOf(customerId),orderDb.getOrderCode(),"买家确认收货");
                orderLogMapper.insert(orderLog);
            }
            return count;
        } else {
            throw new CommonException("该订单暂不可确认收货");
        }
    }

    @Override
    public int updateOrderStatusTimeOut(Integer orderId, String orderCode, Integer customerId) throws Exception {
        int count = orderMapper.updateStateByOrderIdAndCustomerId(orderId, customerId, OrderState.CANCELED, null);
        //添加订单日志
        if (count > 0) {
            orderLogMapper.insert(new OrderLog(String.valueOf(customerId),orderCode,"订单超时"));
        }
        return count;
    }

    private void defaultValue(Order order) {
        order.setCouponsFee(0d);
        order.setDelivery(false);
        order.setDeliveryFeeTotal(0d);
        if (order.getDeliveryType() == null) {
            order.setDeliveryType(DeliveryType.ZT);
        }
        order.setScoreTotal(0);
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

