/**
 *
 */
package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.config.OrderConfig;
import com.yunxin.cb.mall.dao.*;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.Order;
import com.yunxin.cb.mall.entity.meta.*;
import com.yunxin.cb.mall.exception.BusinessNotInStockException;
import com.yunxin.cb.mall.exception.ProductBarterException;
import com.yunxin.cb.mall.exception.ProductReturnException;
import com.yunxin.cb.mall.service.*;
import com.yunxin.cb.mall.vo.ConfirmOrder;
import com.yunxin.cb.rb.service.IFundsPoolService;
import com.yunxin.cb.util.CalculateHelper;
import com.yunxin.cb.util.UUIDGeneratorUtil;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.LogicUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.*;

/**
 * @author gonglei
 */
@Service
@Transactional
public class OrderService implements IOrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Resource
    private OrderDao orderDao;


    @Resource
    private ProductDao productDao;

    @Resource
    private OrderItemDao orderItemDao;

    @Resource
    private CustomerDao customerDao;

    @Resource
    private ShoppingCartDao shoppingCartDao;

    @Resource
    private RankDao rankDao;

    @Resource
    private DeliveryAddressDao deliveryAddressDao;

    @Resource
    private ProductBarterDao productBarterDao;

    @Resource
    private ProductReturnDao productReturnDao;

    @Resource
    private IActivityService activityService;

    @Resource
    private ICommodityService commodityService;

    @Resource
    private IEvaluateService evaluateService;

    @Resource
    private ILogisticsService logisticsService;

    @Resource
    private IRankService rankService;

    @Resource
    private IRuleConditionService ruleConditionService;

    @Resource
    private IShoppingCartService shoppingCartService;

    @Resource
    private IIntegralService integralService;

    @Resource
    private OrderInvoiceDao orderInvoiceDao;

    @Resource
    private OrderLoanApplyDao orderLoanApplyDao;
    @Resource
    private CustomerTradingRecordDao customerTradingRecordDaoDao;
    @Resource
    private CustomerWalletDao customerWalletDao;
    @Resource
    private OrdersLogDao orderLogDao;
    @Resource
    private IFundsPoolService fundsPoolService;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Order getOrderByCode(String orderCode) {
        Order order = orderDao.findTopByOrderCode(orderCode);
        return order;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Order getOrderById(int orderId) {
        Order order = orderDao.findOne(orderId);
        return order;
    }

    /***
     * 前台订单
     *
     * @param query
     * @param customer
     * @param orderState
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Order> pageCustomerOrders(PageSpecification<Order> query, Customer customer, OrderState orderState) {
        query.setCustomSpecification(new CustomSpecification<Order>() {
            public void buildFetch(Root<Order> root) {
                Fetch<Order, OrderItem> fetchOrderItem = root.fetch(Order_.orderItems, JoinType.LEFT);
                fetchOrderItem.fetch(OrderItem_.product, JoinType.LEFT).fetch(Product_.commodity);
                root.fetch(Order_.customer, JoinType.LEFT);
//                root.fetch(Order_.seller, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
                predicates.add(builder.equal(root.get(Order_.customer), customer));
                if (null != orderState) {
                    predicates.add(builder.equal(root.get(Order_.orderState), orderState));
                }
                query.orderBy(builder.desc(root.get(Order_.createTime)));
            }
        });
        return orderDao.findAll(query, query.getPageRequest());
    }

    /***
     * 后台订单
     *
     * @param query
     * @param orderState
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Order> pageOrders(PageSpecification<Order> query, OrderState orderState) {
        query.setCustomSpecification(new CustomSpecification<Order>() {
            public void buildFetch(Root<Order> root) {
                Fetch<Order, OrderItem> fetchOrderItem = root.fetch(Order_.orderItems, JoinType.LEFT);
                fetchOrderItem.fetch(OrderItem_.product, JoinType.LEFT).fetch(Product_.commodity);
                root.fetch(Order_.customer, JoinType.LEFT);
                root.fetch(Order_.seller, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
                if (null != orderState) {
                    predicates.add(builder.equal(root.get(Order_.orderState), orderState));
                }
                query.orderBy(builder.desc(root.get(Order_.createTime)));
            }
        });
        return orderDao.findAll(query, query.getPageRequest());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<ProductReturn> pageReturnOrders(PageSpecification<ProductReturn> query) {
        query.setCustomSpecification(new CustomSpecification<ProductReturn>() {
            public void buildFetch(Root<ProductReturn> root) {
                root.fetch(ProductReturn_.order, JoinType.LEFT);
                root.fetch(ProductReturn_.orderItem, JoinType.LEFT);
                root.fetch(ProductReturn_.customer, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<ProductReturn> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(ProductReturn_.applyTime)));
            }
        });
        return productReturnDao.findAll(query, query.getPageRequest());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<ProductBarter> pageBarterOrders(PageSpecification<ProductBarter> query) {
        query.setCustomSpecification(new CustomSpecification<ProductBarter>() {
            public void buildFetch(Root<ProductBarter> root) {
                root.fetch(ProductBarter_.order, JoinType.LEFT);
                root.fetch(ProductBarter_.orderItem, JoinType.LEFT);
                root.fetch(ProductBarter_.customer, JoinType.LEFT);
            }

            @Override
            public void addConditions(Root<ProductBarter> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
//                predicates.add(builder.equal(root.get(Order_.orderState), OrderState.SUCCESS));
//                predicates.add(builder.isNotNull(root.get(Order_.barterState)));
                query.orderBy(builder.desc(root.get(ProductBarter_.applyTime)));
            }
        });
        return productBarterDao.findAll(query, query.getPageRequest());
    }

    /**
     * 根据购物车内容 一次性生成Order和OrderDetail
     */

    @Override
    public Order createOrder(ConfirmOrder confirmOrder) throws Exception {

        double totalPrice = 0; // 订单总价
        Date createTime = new Date();
        Customer customer = confirmOrder.getCustomer();
        int[] cartId = confirmOrder.getCartId();
        int[] productId = confirmOrder.getProductId();
        int[] quantity = confirmOrder.getQuantity();
        int[] activityId = confirmOrder.getActivityId();
        String[] buyerMessage = confirmOrder.getProductBuyerMessage();
        List<OrderItem> orderItems = new ArrayList<>(productId.length);
        int totalQuantity = 0;
        for (int i = 0; i < productId.length; i++) {
            OrderItem orderItem = new OrderItem();
            Product product = productDao.findOne(productId[i]);
            orderItem.setProduct(product);
            orderItem.setProductNum(quantity[i]);
            orderItem.setCreateTime(createTime);

            if (activityId[i] != 0) {
                Activity activity = activityService.getEffectActivityById(activityId[i]);
                double fresult = CalculateHelper.calculate(product.getSalePrice(), Double.valueOf(activity.getRuleCondition().getRuleValue()), activity.getRuleCondition().getOperator());
                orderItem.setSalePrice((float) fresult);
                float orderItemPrice = (float) fresult * quantity[i];
                orderItem.setOrderItemPrice(orderItemPrice);
                totalPrice += orderItemPrice;
            } else {
                orderItem.setSalePrice(product.getSalePrice());
                float orderItemPrice = product.getSalePrice() * quantity[i];
                orderItem.setOrderItemPrice(orderItemPrice);
                totalPrice += orderItemPrice;
            }

            totalQuantity += quantity[i];

            if (buyerMessage != null && buyerMessage.length > 0) {
                orderItem.setBuyerMessage(buyerMessage[i]);
            }


            orderItems.add(orderItem);
        }


        // 遍历购物车，找到对应货品， 存储对应物品可用库存
        // 利用UUID生成唯一订单编码
        Order order = new Order();
        order.setCustomer(customer);
        order.setDeliveryType(confirmOrder.getDeliveryType());
        order.setCreateTime(createTime);
        order.setOrderCode(UUIDGeneratorUtil.getUUCode());
        order.setOrderState(OrderState.PENDING_PAYMENT);
        if (confirmOrder.isUseIntegralDeduction()) {
            //计算积分抵扣价格
            RuleCondition ruleCondition = ruleConditionService.getRuleConditionByCode("INTEGRAL_DEDUCTION");
            double deductionPrice = CalculateHelper.calculate(customer.getIntegral(), Double.valueOf(ruleCondition.getRuleValue()), ruleCondition.getOperator());
            totalPrice = totalPrice - deductionPrice;
            order.setUsedScore(customer.getIntegral());
            order.setPayByIntegral(deductionPrice);
            order.setDiscountTotal(deductionPrice);
            //积分已全部抵扣，进行可用积分清零操作
            customer.setIntegral(0);
            customerDao.save(customer);
            //积分使用记录
            Integral integral = new Integral();
            integral.setCustomer(customer);
            integral.setScore(order.getUsedScore());
            integral.setOrigin(ruleCondition.getRuleName());
            integral.setRuleCondition(ruleCondition);
            integralService.addIntegral(integral);
        }
        //计算用户可获得积分
        RuleCondition ruleCondition = ruleConditionService.getRuleConditionByCode("INTEGRAL_RATIO");
        double fresult = CalculateHelper.calculate(totalPrice, Double.valueOf(ruleCondition.getRuleValue()), ruleCondition.getOperator());
        order.setScoreTotal((int) Math.round(fresult));

        order.setTotalPrice(totalPrice);
        order.setProdQuantity(totalQuantity);

        order.setFeeTotal(totalPrice);//需计算是否包邮，不包邮的价格需加上物流价格。
        DeliveryAddress address = deliveryAddressDao.findOne(confirmOrder.getAddressId());
        order.setDeliveryAddress(address);
        if (!orderDao.isUnique(order, Order_.orderCode)) {
            throw new EntityExistException("订单编号已存在");
        }
        order.setBuyerMessage(confirmOrder.getBuyerMessage());
        order = orderDao.save(order);

        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(order);
            orderItemDao.save(orderItem);
        }

        if (confirmOrder.isNeedInvoice()) {
            OrderInvoice newOrderInvoice = new OrderInvoice();
            newOrderInvoice.setOrder(order);
            newOrderInvoice.setInvoiceCode(UUIDGeneratorUtil.getUUCode());
            newOrderInvoice.setInvoiceType(confirmOrder.getInvoiceType());
            if (confirmOrder.getInvoiceType() == InvoiceType.NORMAL) {
                newOrderInvoice.setInvoiceTitleType(confirmOrder.getInvoiceTitleType());
                if (confirmOrder.getInvoiceTitleType() == InvoiceTitleType.COMPANY) {
                    newOrderInvoice.setInvoiceTitle(confirmOrder.getInvoiceTitle());
                } else {
                    newOrderInvoice.setInvoiceTitle(customer.getRealName());
                }
                newOrderInvoice.setContent(confirmOrder.getContent());
            } else {
                newOrderInvoice.setInvoiceTitleType(InvoiceTitleType.COMPANY);
                newOrderInvoice.setInvoiceTitle(confirmOrder.getAddedTaxInvoiceTitle());
                newOrderInvoice.setTaxpayerNo(confirmOrder.getTaxpayerNo());
                newOrderInvoice.setRegisterAddress(confirmOrder.getRegisterAddress());
                newOrderInvoice.setRegisterPhone(confirmOrder.getRegisterPhone());
                newOrderInvoice.setBankName(confirmOrder.getBankName());
                newOrderInvoice.setBankAccount(confirmOrder.getBankAccount());
                newOrderInvoice.setInvoiceAmount((float) order.getFeeTotal());
                newOrderInvoice.setContent(confirmOrder.getAddedTaxContent());
            }
            orderInvoiceDao.save(newOrderInvoice);
        }

        // 删除已生成订单的购物车选项
        shoppingCartService.emptyAllShoppingCart(customer);

        return order;
    }

    @Override
    public Order updateOrder(Order order) {
        Order dbOrder = orderDao.findOne(order.getOrderId());
        return dbOrder;
    }

    @Override
    public void removeOrderById(int orderId) {
        orderDao.delete(orderId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Order getOrderDetailById(int orderId) {
        return orderDao.findOne(new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Fetch<Order, OrderItem> fetchOrderItem = root.fetch(Order_.orderItems, JoinType.LEFT);
                fetchOrderItem.fetch(OrderItem_.product, JoinType.LEFT).fetch(Product_.commodity);
                root.fetch(Order_.orderInvoices, JoinType.LEFT);
                root.fetch(Order_.customer, JoinType.LEFT);
                root.fetch(Order_.seller, JoinType.LEFT);
                return criteriaBuilder.equal(root.get(Order_.orderId), orderId);
            }
        });
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Order getOrderDetailByCode(String orderCode) {
        return orderDao.findOne(new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Fetch<Order, OrderItem> fetchOrderItem = root.fetch(Order_.orderItems, JoinType.LEFT);
                fetchOrderItem.fetch(OrderItem_.product, JoinType.LEFT).fetch(Product_.commodity);
                root.fetch(Order_.orderInvoices, JoinType.LEFT);
                root.fetch(Order_.customer, JoinType.LEFT);
                root.fetch(Order_.seller, JoinType.LEFT);
                return criteriaBuilder.equal(root.get(Order_.orderCode), orderCode);
            }
        });
    }

    @Override
    public Order changeOrderPrice(int orderId, double changePrice) {
        Order order = orderDao.findOne(orderId);
        if (null != order) {
            order.setTotalPrice(changePrice);
            order.setFeeTotal(changePrice + order.getDeliveryFeeTotal());
            return order;
        }
        return null;
    }

    @Override
    public Order changeOrderDelivery(int orderId, DeliveryAddress deliveryAddress) {
        Order order = orderDao.findOne(orderId);
        if (null != order) {
            order.setDeliveryAddress(deliveryAddress);
            return order;
        }
        return null;
    }

    @Override
    public String updateOrderState(OrderState orderState, int orderId) {
        Order order = orderDao.getOrderAndLogisticByOrderId(orderId);
        switch (orderState) {
            case OUT_STOCK: {
                if (null == order.getLogistic() || LogicUtils.isNullOrEmpty(order.getCourierNumber())) {
                    return "courier";
                }
            }
            case CANCELED: {
                if (order.getOrderState() == OrderState.PENDING_PAYMENT) {
                    order.setOrderState(OrderState.CANCELED);
                    return "success";
                }
            }
            case RECEIVED: {
                order.setOrderState(OrderState.RECEIVED);
                order.setFinishTime(new Date());
                return "success";
            }
            default: {
                order.setOrderState(orderState);
                order.setUpdateTime(new Date());
                return "success";
            }
        }

    }

    @Override
    public Order editOrderLogistic(int orderId, int logisticId, String courierNumber) {
        Order order = orderDao.findById(orderId);
        Logistic logistic = logisticsService.findById(logisticId);
        order.setLogistic(logistic);
        order.setOrderState(OrderState.OUT_STOCK);
        order.setUpdateTime(new Date());
        order.setCourierNumber(courierNumber);
        return order;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public OrderItem getOrderItemById(int orderDetailId) {
        return orderItemDao.getOrderItemById(orderDetailId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<OrderItem> getOrderItemsByOrderId(int orderId) {
        return orderItemDao.getOrderItemsByOrderId(orderId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<OrderItem> getOrderItemsByOrderCode(String orderCode) {
        return orderItemDao.getOrderItemsByOrderCode(orderCode);
    }

    /**
     * 查询未付款订单 如果超过24h 则将其订单状态设为 已取消
     */
    @Override
    public void cancelTimeOutOrders() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR_OF_DAY, -OrderConfig.ORDER_OVER_TIME.getTime());
        //orderDao.cancelTimeOutOrders(OrderState.PENDING_PAYMENT, c.getTime());
        List<Order> orders = orderDao.findOrderByOrderStateAndCreateTime(OrderState.PAID_PAYMENT, c.getTime());
        if (orders != null && !orders.isEmpty()) {
            Date now = new Date();
            for (Order order : orders) {
                order.setOrderState(OrderState.CANCELED);
                order.setCancelReason("订单超时未支付");
                order.setCancelTime(now);
                order.setUpdateTime(now);
                //库存操作
                List<OrderItem> orderItems = order.getOrderItems();
                if (orderItems != null && !orderItems.isEmpty()) {
                    for (OrderItem orderItem : orderItems) {
                        //更新库存
                        Product product = orderItem.getProduct();
                        //增加库存
                        product.setStoreNum(product.getStoreNum() + orderItem.getProductNum());
                        int reservedStoreNum = product.getReservedStoreNum();
                        product.setReservedStoreNum(reservedStoreNum - orderItem.getProductNum());
                        if (reservedStoreNum - orderItem.getProductNum() < 0) {
                            product.setReservedStoreNum(0);
                        }
                    }
                }
                //日志操作
                OrderLog orderLog = new OrderLog();
                orderLog.setTime(now);
                orderLog.setOrderCode(order.getOrderCode());
                orderLog.setHandler("后台定时任务");
                orderLog.setRemark("订单定时取消");
                orderLogDao.save(orderLog);
            }
        }
    }

    /**
     * 查询已发货订单 如果超过1周则将其订单状态设为 已收货
     */
    @Override
    public void confirmReceivedOrders() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_WEEK ,-OrderConfig.ORDER_RECEIVED_TIME.getTime());
        //orderDao.taskDeliverTimeOrders(OrderState.RECEIVED, OrderState.OUT_STOCK, c.getTime());
        List<Order> orders = orderDao.findOrderByOrderStateAndDeliverTime(OrderState.OUT_STOCK, c.getTime());
        if (orders != null && !orders.isEmpty()) {
            Date now = new Date();
            for (Order order : orders) {
                order.setOrderState(OrderState.RECEIVED);
                order.setCollectTime(now);
                order.setUpdateTime(now);
                //日志操作
                OrderLog orderLog = new OrderLog();
                orderLog.setTime(now);
                orderLog.setOrderCode(order.getOrderCode());
                orderLog.setHandler("后台定时任务");
                orderLog.setRemark("订单定时收货");
                orderLogDao.save(orderLog);
            }
        }
    }

    /**
     * 查询已收货订单 如果超过1周则将其订单状态设为 已完成
     */
    @Override
    public void completedOrders() {
        Calendar c = Calendar.getInstance();
        //c.add(Calendar.DAY_OF_WEEK ,-OrderConfig.ORDER_COMPLETE_TIME.getTime());
        c.add(Calendar.MINUTE ,-OrderConfig.ORDER_COMPLETE_TIME.getTime());
        //orderDao.taskCollectTimeOrders(OrderState.SUCCESS, OrderState.RECEIVED, c.getTime());
        List<Order> orders = orderDao.findOrderByOrderStateAndCollectTime(OrderState.RECEIVED, c.getTime());
        if (orders != null && !orders.isEmpty()) {
            Date now = new Date();
            for (Order order : orders) {
                order.setOrderState(OrderState.SUCCESS);
                order.setFinishTime(now);
                order.setUpdateTime(now);
                //日志操作
                OrderLog orderLog = new OrderLog();
                orderLog.setTime(now);
                orderLog.setOrderCode(order.getOrderCode());
                orderLog.setHandler("后台定时任务");
                orderLog.setRemark("订单定时完成");
                orderLogDao.save(orderLog);
                //商品增加销售量
                if (order.getOrderItems()!= null && order.getOrderItems().size() > 0) {
                    for (OrderItem orderItem : order.getOrderItems()) {
                        Product product = orderItem.getProduct();
                        Commodity commodity = product.getCommodity();
                        if (commodity != null) {
                            int num = commodity.getSaleNum();
                            commodity.setSaleNum(num + orderItem.getProductNum());
                        }
                    }
                }
                //资金如资金池
                fundsPoolService.updateAndCountOrderAmout(order.getOrderId());
            }
        }

    }

    @Override
    public boolean cancelOrder(int orderId, String cancelReason) {
        Order order = orderDao.findOne(orderId);
        if (order.getOrderState() == OrderState.PENDING_PAYMENT) {
            order.setOrderState(OrderState.CANCELED);
            order.setCancelReason(cancelReason);

            Customer customer = order.getCustomer();
            customer.setIntegral(customer.getIntegral() + order.getUsedScore());
            customerDao.save(customer);
            Integral integral = new Integral();
            integral.setCustomer(customer);
            integral.setScore(order.getUsedScore());
            integral.setOrigin("取消订单退回积分");
            integral.setRuleCondition(null);
            integralService.addIntegral(integral);
            return true;
        } else {
            return false;
        }

    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Order> getLastedOrders(int limit) {
        Pageable pageable = new PageRequest(0, limit);
        return orderDao.findByCreateTime(pageable);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public double getOrderTotalPriceSum(Date beginDate, Date endDate) {
        return orderDao.getOrderTotalPriceSum(beginDate, endDate);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public double getPayedOrderTotalPriceSum(Date beginDate, Date endDate) {
        return orderDao.getOrderTotalPriceSum(beginDate, endDate, OrderState.PENDING_PAYMENT);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getOrderCount(Date beginDate, Date endDate) {
        return orderDao.getOrderCount(beginDate, endDate);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getPayedOrderCount(Date beginDate, Date endDate) {
        return orderDao.getOrderCount(beginDate, endDate, OrderState.PENDING_PAYMENT);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getOrderCount() {
        return orderDao.count();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getDisctinctCommodityCount() {
        return null == orderItemDao.getDisctinctCommodityCount() ? 0L : orderItemDao.getDisctinctCommodityCount();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getOrderProductCount() {
        return null == orderItemDao.getOrderProductCount() ? 0L : orderItemDao.getOrderProductCount();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public double getOrderTotalPrice() {
        return orderDao.getOrderTotalPrice(OrderState.PENDING_PAYMENT);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getPayedOrderCount() {
        return orderDao.getPayedOrderCount(OrderState.PENDING_PAYMENT);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getOrderCountByCustomerId(int customerId) {
        return orderDao.getOrderCountByCustomerId(customerId);
    }

    public void updateOrderReturnRefundState(int orderId, ReturnRefundState state) {
        Order order = orderDao.findOne(orderId);
        order.setReturnRefundState(state);
    }

    public void updateOrderBarterState(int orderId, BarterState barterState) {
        Order order = orderDao.findOne(orderId);
        order.setBarterState(barterState);
        order.setOrderState(OrderState.OUT_STOCK);
    }

    @Override
    public String applyOrderProductBarter(ProductBarter productBarter) throws EntityExistException {
        ProductBarter dbBarter = productBarterDao.getProductBarterByOrderId(productBarter.getOrder().getOrderId());
        if (null != dbBarter) {
            throw new EntityExistException("该订单已提交换货申请");
        }
        int[] orderItemIds = productBarter.getOrderItemIds();
        Order order = orderDao.findOne(productBarter.getOrder().getOrderId());
        for (int itemId : orderItemIds) {
            ProductBarter nBarter = new ProductBarter();
            BeanUtils.copyProperties(productBarter, nBarter);
            OrderItem oItem = orderItemDao.findOne(itemId);
            nBarter.setBarterCode(UUIDGeneratorUtil.getUUCode());
            nBarter.setOrderItem(oItem);
            nBarter.setApplyTime(new Date());
            nBarter.setPurchasingTime(order.getCreateTime());
            nBarter.setBarterState(BarterState.APPLY_BARTER);
            nBarter.setAuditState(AuditState.WAIT_AUDIT);
            updateOrderBarterState(nBarter.getOrder().getOrderId(), BarterState.APPLY_BARTER);
            productBarterDao.save(nBarter);
        }
        return order.getOrderCode();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<ProductBarter> getProductBartersByOrderCode(String orderCode) {
        return productBarterDao.getProductBartersByOrderCode(orderCode);
    }

    @Override
    public void productBarterAudit(int barterId, AuditState auditState, String auditRemark) {
        ProductBarter productBarter = productBarterDao.findOne(barterId);
        if (auditState == AuditState.AUDITED) {
            productBarter.setBarterState(BarterState.WAIT_BARTER);
            productBarter.setReceivedSellerProduct(false);
            updateOrderBarterState(productBarter.getOrder().getOrderId(), BarterState.WAIT_BARTER);
        } else if (auditState == AuditState.NOT_AUDIT) {
            productBarter.setBarterState(BarterState.BARTER_DENIED);
            updateOrderBarterState(productBarter.getOrder().getOrderId(), BarterState.BARTER_DENIED);
        }
        productBarter.setAuditState(auditState);
        productBarter.setAuditRemark(auditRemark);
    }

    public void confirmReceivedBarterProduct(int barterId) throws ProductBarterException {
        ProductBarter productBarter = productBarterDao.findOne(barterId);
        if (!productBarter.isReceivedSellerProduct()) {
            productBarter.setReceivedSellerProduct(true);
        } else {
            throw new ProductBarterException("已确认收到买家货物，请勿重复提交！");
        }
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public boolean productBarter(int orderItemId) throws ProductBarterException, BusinessNotInStockException {

        ProductBarter productBarter = productBarterDao.getProductBarterByOrderItemId(orderItemId);
        if (null != productBarter) {
            if (productBarter.getBarterState() != BarterState.WAIT_BARTER) {
                throw new ProductBarterException("该订单已换货，请勿重复提交！");
            }
        }
        Date date = new Date();
        OrderItem oItem = orderItemDao.getOrderItemById(orderItemId);
        int storeNum = productDao.checkStoreNumByProductId(oItem.getProduct().getProductId());
        if (0 == storeNum) {
            productBarter.setBarterState(BarterState.BARTER_FAIL);
            productBarter.setApplyTime(date);
            productBarterDao.save(productBarter);
            throw new BusinessNotInStockException("货品暂无库存");
        }

        Order detailorder = getOrderDetailById(oItem.getOrder().getOrderId());
        OrderItem orderItem = new OrderItem();
        Order order = new Order();

        BeanUtils.copyProperties(detailorder, order);
        BeanUtils.copyProperties(oItem, orderItem);
        order.setOrderCode(UUIDGeneratorUtil.getUUCode());
        order.setLogistic(null);
        order.setCourierNumber(null);
        order.setOrderInvoices(null);
        order.setOrderId(0);
        order.setOriginOrderCode(detailorder.getOrderCode());
        order.setCreateTime(date);
        order.setUpdateTime(null);
        order.setFinishTime(null);
        order.setOrderItems(null);
        order.setOrderState(OrderState.CHANGE_GOODS);
        orderDao.save(order);
        orderItem.setItemId(0);
        orderItem.setOrder(order);
        orderItem.setCreateTime(date);
        orderItemDao.save(orderItem);

        productBarter.setBarterState(BarterState.BARTER_SUCCESS);
        productBarter.setDisposeTime(date);
        productBarterDao.save(productBarter);
        return true;
    }

    public String applyOrderProductReturn(ProductReturn productReturn) throws EntityExistException {
        ProductReturn dbReturn = productReturnDao.getProductReturnByOrderId(productReturn.getOrder().getOrderId());
        if (null != dbReturn) {
            throw new EntityExistException("该订单已提交退货申请");
        }
        int[] orderItemIds = productReturn.getOrderItemIds();
        Order order = orderDao.findOne(productReturn.getOrder().getOrderId());
        for (int itemId : orderItemIds) {
            ProductReturn nReturn = new ProductReturn();
            BeanUtils.copyProperties(productReturn, nReturn);
            OrderItem oItem = orderItemDao.findOne(itemId);

            nReturn.setReturnCode(UUIDGeneratorUtil.getUUCode());
            nReturn.setOrderItem(oItem);
            nReturn.setApplyTime(new Date());
            nReturn.setPurchasingTime(order.getCreateTime());
            if (nReturn.isReceivedBuyerProduct()) {
                nReturn.setReturnRefundState(ReturnRefundState.APPLY_RETURN_REFUND);
                updateOrderReturnRefundState(productReturn.getOrder().getOrderId(), ReturnRefundState.APPLY_RETURN_REFUND);
            } else {
                nReturn.setReturnRefundState(ReturnRefundState.APPLY_REFUND);
                updateOrderReturnRefundState(productReturn.getOrder().getOrderId(), ReturnRefundState.APPLY_REFUND);
            }
            nReturn.setAuditState(AuditState.WAIT_AUDIT);
            productReturnDao.save(nReturn);
        }
        return order.getOrderCode();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<ProductReturn> getProductReturnsByOrderCode(String orderCode) {
        return productReturnDao.getProductReturnsByOrderCode(orderCode);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public ProductReturn getProductReturnByReturnCode(String returnCode) {
        return productReturnDao.getProductReturnByReturnCode(returnCode);
    }

    @Override
    public void productReturnAudit(int returnId, AuditState auditState, String auditRemark) {
        ProductReturn productReturn = productReturnDao.findOne(returnId);
        if (auditState == AuditState.AUDITED) {
            if (productReturn.getReturnRefundState() == ReturnRefundState.APPLY_RETURN_REFUND) {
                productReturn.setReturnRefundState(ReturnRefundState.WAIT_RETURNED_REFUND);
                updateOrderReturnRefundState(productReturn.getOrder().getOrderId(), ReturnRefundState.WAIT_RETURNED_REFUND);
            } else if (productReturn.getReturnRefundState() == ReturnRefundState.APPLY_REFUND) {
                productReturn.setReturnRefundState(ReturnRefundState.WAIT_REFUND);
                updateOrderReturnRefundState(productReturn.getOrder().getOrderId(), ReturnRefundState.WAIT_REFUND);
            }
            productReturn.setReceivedSellerProduct(false);
        } else if (auditState == AuditState.NOT_AUDIT) {
            productReturn.setReturnRefundState(ReturnRefundState.RETURN_REFUND_DENIED);
            updateOrderReturnRefundState(productReturn.getOrder().getOrderId(), ReturnRefundState.RETURN_REFUND_DENIED);
        }
        productReturn.setAuditState(auditState);
        productReturn.setAuditRemark(auditRemark);
        productReturn.setAuditTime(new Date());
    }

    public void confirmReceivedReturnProduct(int returnId) throws ProductReturnException {
        ProductReturn productReturn = productReturnDao.findOne(returnId);
        if (productReturn.getReturnRefundState() == ReturnRefundState.WAIT_RETURNED_REFUND) {
            if (!productReturn.isReceivedSellerProduct()) {
                productReturn.setReceivedSellerProduct(true);
            } else {
                throw new ProductReturnException("已确认收到买家货物，请勿重复提交！");
            }
        } else {
            throw new ProductReturnException("此交易为仅退款操作，无需进行收货操作！");
        }

    }

    public String returnRefundOrder(ProductReturn productReturn) {
        ProductReturn dbReturn = productReturnDao.getProductReturnByReturnCode(productReturn.getReturnCode());
        boolean isRefund = productReturn.isRefundOnly();
        if (isRefund) {
            refundOrder(dbReturn);
        } else {
            OrderItem orderItem = dbReturn.getOrderItem();
            Product product = dbReturn.getOrderItem().getProduct();

            //退货货品入库
            Product dbproduct = productDao.getOne(product.getProductId());
            dbproduct.setStoreNum(dbproduct.getStoreNum() + orderItem.getProductNum());
            dbReturn.setRefundPrice(productReturn.getRefundPrice());
            dbReturn.setRemark(productReturn.getRemark());

            dbReturn.setReturnRefundState(ReturnRefundState.RETURNED_WAIT_REFUND);
        }
        return dbReturn.getOrder().getOrderCode();
    }

    public void refundOrder(ProductReturn productReturn) {
        ProductReturn dbReturn = productReturnDao.findOne(productReturn.getReturnId());
        dbReturn.setReturnRefundState(ReturnRefundState.REFUNDED);
    }

    public void updateOrderItemEvaluate(int itemId) {
        OrderItem item = orderItemDao.findOne(itemId);
        item.setEvaluate(true);
    }

    @Override
    public boolean orderAudit(int orderId, AuditState auditState, String auditRemark) {
        Order order = orderDao.findOne(orderId);
        if (order == null || order.getOrderState() != OrderState.PENDING_PAYMENT
                || order.getPaymentType() != PaymentType.UNDER_LINE){
            return false;
        }
        Date now = new Date();
        //添加订单日志
        OrderLog orderLog = new OrderLog();

        if (auditState == AuditState.AUDITED) {
            if (order.getDeliveryType() == DeliveryType.ZT) {
                order.setOrderState(OrderState.OUT_STOCK);//直接跳过已支付到已发货
            } else {
                order.setOrderState(OrderState.PAID_PAYMENT);
            }
            order.setPaymentState(PaymentState.SUCCESS_PAID);
            order.setPaymentTime(now);
            order.setDeliverTime(now);
            order.setUpdateTime(now);
            orderLog.setRemark("订单审核通过");
        } else if (auditState == AuditState.NOT_AUDIT) {
            order.setPaymentState(PaymentState.FAIL_PAID);
            order.setOrderState(OrderState.CANCELED);
            order.setCancelReason(auditRemark);
            order.setCancelTime(now);
            orderLog.setRemark("订单审核不通过");
        }
        order.setAuditState(auditState);
        order.setAuditRemark(auditRemark);

        orderLog.setTime(now);
        orderLog.setOrderCode(order.getOrderCode());
        orderLog.setHandler("后台操作人员");
        orderLogDao.save(orderLog);
        return true;
    }

    @Override
    public boolean orderCancel(int orderId, String cancelReason) {
        Order order = orderDao.findOne(orderId);
        if (order == null || order.getOrderState() != OrderState.PENDING_PAYMENT){
            return false;
        }
        Date now = new Date();
        order.setOrderState(OrderState.CANCELED);
        order.setCancelReason(cancelReason);
        order.setCancelTime(now);
        order.setUpdateTime(now);
        //库存操作
        List<OrderItem> orderItems = order.getOrderItems();
        if (orderItems != null && !orderItems.isEmpty()) {
            for (OrderItem orderItem : orderItems) {
                //更新库存
                Product product = orderItem.getProduct();
                //增加库存
                product.setStoreNum(product.getStoreNum() + orderItem.getProductNum());
                int reservedStoreNum = product.getReservedStoreNum();
                product.setReservedStoreNum(reservedStoreNum - orderItem.getProductNum());
                if (reservedStoreNum - orderItem.getProductNum() < 0) {
                    product.setReservedStoreNum(0);
                }
            }
        }
        //添加订单日志
        OrderLog orderLog = new OrderLog();
        orderLog.setTime(now);
        orderLog.setOrderCode(order.getOrderCode());
        orderLog.setHandler("后台操作人员");
        orderLog.setRemark("订单取消");
        orderLogDao.save(orderLog);
        return true;
    }
}
