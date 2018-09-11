package com.yunxin.cb.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.entity.Order;
import com.yunxin.cb.mall.entity.OrderItem;
import com.yunxin.cb.mall.entity.meta.OrderState;
import com.yunxin.cb.util.page.PageFinder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.constraints.Pattern;
import java.util.*;

/**
 * @title: 订单详情VO
* @author gws
* @date 2018/7/24 20:00
* @param 
* @return 
*/
@ApiModel(value="订单详情VO",description="订单详情VO对象")
public class OrderDetailVO implements java.io.Serializable{

    /**
     * 订单id
     */
    @ApiModelProperty(value="订单id",name="orderId",example="1")
    private Integer orderId;
    /**
     * 订单编号
     */
    @ApiModelProperty(value="订单编号",name="orderCode",example="1111111")
    private String orderCode;

    /** 订单基本状态 */
    @ApiModelProperty(value="订单基本状态",name="orderState",example="PENDING_PAYMENT")
    private OrderState orderState;

    /** 货品数量 */
    @ApiModelProperty(value="货品总数量",name="prodQuantity",example="1")
    private Integer prodQuantity;

    /** 订单付费总计 */
    @ApiModelProperty(value="订单付费总计",name="feeTotal",example="1")
    private Double feeTotal;

    /**
     * 收货人姓名
     */
    @ApiModelProperty(value="联系人",name="consigneeName",example="张三", required = true)
    private String consigneeName;

    /**
     * 收货人手机号
     */
    @Pattern(regexp = "^(1)\\d{10}$", message = "请输入正确得手机格式")
    @ApiModelProperty(value="联系人手机号",name="consigneeMobile",example="13856953362", required = true)
    private String consigneeMobile;
    /**
     * 省
     */
    @ApiModelProperty(value="省",name="province",example="广东")
    private String province;

    /**
     * 市
     */
    @ApiModelProperty(value="市",name="city",example="深圳")
    private String city;

    /**
     * 区，县
     */
    @ApiModelProperty(value="区，县",name="city",example="福田")
    private String district;

    /**
     * 收货地址
     */
    @ApiModelProperty(value="收货地址",name="consigneeAddress",example="街道", required = true)
    private String consigneeAddress;


    /** 创建时间 */
    @ApiModelProperty(value="提交时间",name="createTime",example="2018-07-24")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /** 支付时间 */
    @ApiModelProperty(value="付款时间",name="paymentTime",example="2018-07-24")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date paymentTime;

    /** 发货时间 */
    @ApiModelProperty(value="发货时间",name="deliverTime",example="2018-07-24")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date deliverTime;

    /** 完成时间 */
    @ApiModelProperty(value="成交时间",name="finishTime",example="2018-07-24")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date finishTime;

    /**
     * 商家id
     */
    @ApiModelProperty(value="商家id",name="sellerId",example="1", required = true)
    private Integer sellerId;

    /** 商家名 */
    @ApiModelProperty(value="商家名",name="sellerName",example="4s店")
    private String sellerName;

    /**货品信息*/
    @ApiModelProperty(value="货品信息",name="orderItemDetails",example="货品信息对象")
    Set<OrderListItemVO> orderItemDetails;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public Integer getProdQuantity() {
        return prodQuantity;
    }

    public void setProdQuantity(Integer prodQuantity) {
        this.prodQuantity = prodQuantity;
    }

    public Double getFeeTotal() {
        return feeTotal;
    }

    public void setFeeTotal(Double feeTotal) {
        this.feeTotal = feeTotal;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Set<OrderListItemVO> getOrderItemDetails() {
        return orderItemDetails;
    }

    public void setOrderItemDetails(Set<OrderListItemVO> orderItemDetails) {
        this.orderItemDetails = orderItemDetails;
    }

    @Override
    public String toString() {
        return "OrderDetailVO{" +
                "orderId=" + orderId +
                ", orderCode='" + orderCode + '\'' +
                ", orderState=" + orderState +
                ", prodQuantity=" + prodQuantity +
                ", feeTotal=" + feeTotal +
                ", consigneeName='" + consigneeName + '\'' +
                ", consigneeMobile='" + consigneeMobile + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", consigneeAddress='" + consigneeAddress + '\'' +
                ", createTime=" + createTime +
                ", paymentTime=" + paymentTime +
                ", deliverTime=" + deliverTime +
                ", finishTime=" + finishTime +
                ", sellerId=" + sellerId +
                ", sellerName='" + sellerName + '\'' +
                ", orderItemDetails=" + orderItemDetails +
                '}';
    }

    /**
     * 分页DO转换VO
     */
    public static PageFinder<OrderDetailVO> dOconvertVOPage (PageFinder<Order> pageFinder) throws Exception{
        PageFinder<OrderDetailVO> page = new PageFinder<OrderDetailVO> (pageFinder.getPageNo(), pageFinder.getPageSize(), pageFinder.getRowCount());
        if (pageFinder != null) {
            List<OrderDetailVO> list = OrderDetailVO.dOconvertVOList(pageFinder.getData());
            page.setData(list);
        }
        page.setRowCount(pageFinder.getRowCount());//记录总数
        return page;
    }

    /**
     * 列表DO转换VO
     */
    public static List<OrderDetailVO> dOconvertVOList (List<Order> modelList) throws Exception{
        List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
        if (modelList != null) {
            for (Order model : modelList) {
                OrderDetailVO orderDetailVO =  OrderDetailVO.dOconvertVO(model);
                list.add(orderDetailVO);
            }
        }
        return list;
    }

    /**
     * 订单DO转换VO
     */
    public static OrderDetailVO dOconvertVO (Order model) throws Exception{
        if (model == null) {
            return null;
        }
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        BeanUtils.copyProperties(orderDetailVO, model);
        //商家地址信息
        if (model.getSeller() != null) {
            orderDetailVO.setSellerName(model.getSeller().getSellerName());
        }
        if (model.getOrderItems() != null && !model.getOrderItems().isEmpty()) {
            Set<OrderListItemVO> orderItemDetails = new HashSet<OrderListItemVO>();
            for (OrderItem orderItem : model.getOrderItems()) {
                OrderListItemVO oderItemDetailVO = new OrderListItemVO();
                BeanUtils.copyProperties(oderItemDetailVO, orderItem);
                //货品信息
                if (orderItem.getProduct() != null) {
                    oderItemDetailVO.setProductName(orderItem.getProduct().getProductName());
                    //商品信息
                    Commodity commodity = orderItem.getProduct().getCommodity();
                    if (commodity != null) {
                        oderItemDetailVO.setCommodityId(commodity.getCommodityId());
                        oderItemDetailVO.setCommodityName(commodity.getCommodityName());
                        oderItemDetailVO.setCommodityTitle(commodity.getCommodityTitle());
                    }
                }
                orderItemDetails.add(oderItemDetailVO);
            }
            orderDetailVO.setOrderItemDetails(orderItemDetails);
        }
        return orderDetailVO;
    }

}
