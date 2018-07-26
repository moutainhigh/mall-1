package com.yunxin.cb.mall.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yunxin.cb.mall.entity.Order;
import com.yunxin.cb.mall.entity.OrderItem;
import com.yunxin.cb.mall.entity.ProductReturn;
import com.yunxin.cb.util.page.PageFinder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.beanutils.BeanUtils;

import java.util.*;
/**
* @author gws
* @date 2018/7/24 20:00
* @param 
* @return
*/
@ApiModel(value="商城订单详情",description="订单详情VO对象")
public class ProductReturnDetailVO implements java.io.Serializable{

    /** 退货id */
    @ApiModelProperty(value="退货id",name="returnId",example="1")
    private Integer returnId;
    /** 退货编号 */
    @ApiModelProperty(value="退货编号",name="returnCode",example="1111111")
    private String returnCode;
    /**
     * 订单id
     */
    @ApiModelProperty(value="订单id",name="orderId",example="1")
    private Integer orderId;

    /** 申请时间 */
    @ApiModelProperty(value="申请时间 ",name="applyTime",example="1")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date applyTime;

    /** 退货退款状态 0:申请退货退款 1:待退货退款 2:已退货待退款 3:已退货退款 4:拒绝退货退款 5:申请退款 6:待退款 7:已退款 8:拒绝退款 */
    @ApiModelProperty(value="退货退款状态 ",name="returnRefundState",example="1")
    private Integer returnRefundState;

    /** 退货原因 */
    @ApiModelProperty(value="退货原因",name="returnReason",example="我不想买了")
    private String returnReason;

    /** 审核备注 */
    @ApiModelProperty(value="审核备注",name="auditRemark",example="通过")
    private String auditRemark;

    /** 审核状态 */
    @ApiModelProperty(value="审核状态",name="auditState",example="1")
    private Integer auditState;

    /** 处理时间 */
    @ApiModelProperty(value="处理时间 ",name="disposeTime",example="1")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date disposeTime;

    /** 退款金额 */
    @ApiModelProperty(value="退款金额",name="refundPrice",example="1")
    private Double refundPrice;

    /**货品信息*/
    Set<ProductReturnItemDetailVO> productReturnItemDetails;

    public Integer getReturnId() {
        return returnId;
    }

    public void setReturnId(Integer returnId) {
        this.returnId = returnId;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getReturnRefundState() {
        return returnRefundState;
    }

    public void setReturnRefundState(Integer returnRefundState) {
        this.returnRefundState = returnRefundState;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public Integer getAuditState() {
        return auditState;
    }

    public void setAuditState(Integer auditState) {
        this.auditState = auditState;
    }

    public Date getDisposeTime() {
        return disposeTime;
    }

    public void setDisposeTime(Date disposeTime) {
        this.disposeTime = disposeTime;
    }

    public Double getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(Double refundPrice) {
        this.refundPrice = refundPrice;
    }

    public Set<ProductReturnItemDetailVO> getProductReturnItemDetails() {
        return productReturnItemDetails;
    }

    public void setProductReturnItemDetails(Set<ProductReturnItemDetailVO> productReturnItemDetails) {
        this.productReturnItemDetails = productReturnItemDetails;
    }

    @Override
    public String toString() {
        return "ProductReturnDetailVO{" +
                "returnId=" + returnId +
                ", returnCode='" + returnCode + '\'' +
                ", orderId=" + orderId +
                ", applyTime=" + applyTime +
                ", returnRefundState=" + returnRefundState +
                ", returnReason='" + returnReason + '\'' +
                ", auditRemark='" + auditRemark + '\'' +
                ", auditState=" + auditState +
                ", disposeTime=" + disposeTime +
                ", refundPrice=" + refundPrice +
                ", productReturnItemDetails=" + productReturnItemDetails +
                '}';
    }
    /**
     * 分页DO转换VO
     */
    public static PageFinder<ProductReturnDetailVO> dOconvertVOPage (PageFinder<ProductReturn> pageFinder) throws Exception{
        PageFinder<ProductReturnDetailVO> page = new PageFinder<ProductReturnDetailVO> (pageFinder.getPageNo(), pageFinder.getPageSize());
        if (pageFinder != null) {
            List<ProductReturnDetailVO> list = ProductReturnDetailVO.dOconvertVOList(pageFinder.getData());
            page.setData(list);
        }
        page.setRowCount(pageFinder.getRowCount());//记录总数
        page.setPageCount(pageFinder.getPageCount());//总页数
        return page;
    }

    /**
     * 列表DO转换VO
     */
    public static List<ProductReturnDetailVO> dOconvertVOList (List<ProductReturn> productReturnList) throws Exception{
        List<ProductReturnDetailVO> list = new ArrayList<ProductReturnDetailVO> ();
        if (productReturnList != null) {
            for (ProductReturn model : productReturnList) {
                ProductReturnDetailVO productReturnDetailVO =  ProductReturnDetailVO.dOconvertVO(model);
                list.add(productReturnDetailVO);
            }
        }
        return list;
    }

    /**
     * DO转换VO
     */
    public static ProductReturnDetailVO dOconvertVO (ProductReturn productReturn) throws Exception{
        if (productReturn == null) {
            return null;
        }
        ProductReturnDetailVO productReturnDetailVO = new ProductReturnDetailVO();
        BeanUtils.copyProperties(productReturnDetailVO, productReturn);
        Order model = productReturn.getOrder();
        if (model != null) {
            BeanUtils.copyProperties(productReturnDetailVO, model);
            if (model.getOrderItems() != null && !model.getOrderItems().isEmpty()) {
                Set<ProductReturnItemDetailVO> productReturnItemDetails = new HashSet<ProductReturnItemDetailVO>();
                for (OrderItem orderItem : model.getOrderItems()) {
                    ProductReturnItemDetailVO productReturnItemDetailVO = new ProductReturnItemDetailVO();
                    BeanUtils.copyProperties(productReturnItemDetailVO, orderItem);
                    //货品信息
                    if (orderItem.getProduct() != null) {
                        productReturnItemDetailVO.setProductName(orderItem.getProduct().getProductName());
                        productReturnItemDetailVO.setProductNo(orderItem.getProduct().getProductNo());
                        //商品信息
                        if (orderItem.getProduct().getCommodity() != null) {
                            productReturnItemDetailVO.setCommodityId(orderItem.getProduct().getCommodity().getCommodityId());
                            productReturnItemDetailVO.setCommodityName(orderItem.getProduct().getCommodity().getCommodityName());
                        }
                    }
                    productReturnItemDetails.add(productReturnItemDetailVO);
                }
                productReturnDetailVO.setProductReturnItemDetails(productReturnItemDetails);
            }
        }
        return productReturnDetailVO;
    }
}
